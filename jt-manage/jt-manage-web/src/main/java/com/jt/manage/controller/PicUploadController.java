package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.manage.pojo.PicUploadResult;
import com.jt.manage.service.PropertyService;

@Controller
public class PicUploadController {
	@Autowired
	private PropertyService propertyService;
	//文件上传,通用的文件上传，方便集成到其他项目
	
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult upload(MultipartFile uploadFile){//filePostName  : "uploadFile",  common.js25行
		PicUploadResult result = new PicUploadResult();
		String fileName=uploadFile.getOriginalFilename();
		String extName=fileName.substring(fileName.lastIndexOf("."));
		
		//判断文件后缀类型,正则表达式abc.jpg,abc.png等
		if(!extName.matches("\\.(jpg|png|jgpeg|gif)$")){
			result.setError(1);
		}else{
			//判断是否为木马文件，从包装上的文件获取流，强制转化成文件对象
			try {
				BufferedImage image= ImageIO.read(uploadFile.getInputStream());
				result.setHeight(""+image.getHeight());
				result.setWidth(""+image.getWidth());
				//生成两个路径 1 绝对路径：文件保存的路径 D:\JTworkspace\jt-upload\images\...jpg；2 相对路径：图片网上访问路径 http://image.jt.com/images/...
				String newFileName=""+System.currentTimeMillis()+RandomUtils.nextInt(99, 999)+extName;
				String _dir= "/images/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";
				//REPOSITORY_PATH=D:/JTworkspace/jt-upload
				//IMAGE_BASE_URL=http://image.jt.com
				String path = propertyService.REPOSITORY_PATH+_dir;
				String url = propertyService.IMAGE_BASE_URL+_dir+newFileName;
				//回显的地址
				result.setUrl(url);
				//如果目录不存在就生成
				File dir = new File(path);
				if(!dir.exists()){
					dir.mkdirs();//创建多层目录
				}
				//保存文件
				System.out.println("path+newFileName="+path+newFileName);
				uploadFile.transferTo(new File(path+newFileName));
				//uploadFile.transferTo(new File(newFileName));
			} catch (IOException e) {
				result.setError(1);//不能抛异常，必须设置出错
				e.printStackTrace();
			}
		}
		return result;
		
	}

}
