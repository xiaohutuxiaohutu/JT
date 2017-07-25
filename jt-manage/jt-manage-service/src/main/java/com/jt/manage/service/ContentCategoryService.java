package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.manage.mapper.ContentCategoryMapper;
import com.jt.manage.pojo.ContentCategory;
import redis.clients.jedis.JedisCluster;

@Service
public class ContentCategoryService extends BaseService<ContentCategory> {

	@Autowired
	private ContentCategoryMapper contenCategoryMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	private static final Logger log = Logger.getLogger(ContentCategoryService.class);// 用来打印日志
	// Jackson
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 *  content-category
	 * 到后台查询content-category分类，返回java对象列表 EasyUI自动解析结果
	 * @param id
	 * @return
	 */
	public List<ContentCategory> list(Long id) {
		String ContentCategory_KEY = "ContentCategory_" + id;
		ContentCategory params = new ContentCategory();
		params.setStatus(1);
		params.setParentId(id);
		//System.out.println("service:cgc:"+params);
		List<ContentCategory> list = contenCategoryMapper.findByParentId(id);
		return list;
		
		// 3 读取判断缓存是否有数据

		/*try {
			String jsonData = jedisCluster.get(ContentCategory_KEY);
			System.out.println("jsonDae=" + jsonData);
			if (StringUtils.isNotEmpty(jsonData)) {
				System.out.println("存在");
				// 有数据直接额返回，将json字符串转化为java对象
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				Object obj = MAPPER.readValue(jsonNode.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class, ContentCategory.class));
				return (List<ContentCategory>) obj;
			} else {
				System.out.println("不存在");
				List<ContentCategory> list = contenCategoryMapper.findByParentId(id);
				// 2 写数据到缓存，把java对象转换成字符串,缓存如果出错不能抛异常，必须让业务继续执行
				for(ContentCategory cg:list){
					System.out.println("cg="+cg);
				}
				String data = MAPPER.writeValueAsString(list);
				//System.out.println("data=" + data);
				jedisCluster.set(ContentCategory_KEY, data);// 缓存每个分支
				return list;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return null;*/
	}

	/**
	 * content-category
	 * 新增内容分类管理模块
	 * @param parentId
	 * @param name
	 * 跟新时要删除缓存中的数据
	 */
	public void createCategory(Long parentId, String name) {
		ContentCategory category = new ContentCategory();//代表新增的对象
		ContentCategory cate1 = new ContentCategory();//代表新增对象的父类对象
		category.setParentId(parentId);
		category.setName(name);
		category.setStatus(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		//判断是否是父类，思路 1 新增之后mybatis可以返回新增加的目录自增的id，通过获得的ID查询parentId是否存在，如果存在，则有子目录，不存在，则没有
		//新增的同时要修改上级 isparent 为TRUE
		/*if(parentId==0L){
			category.setIsParent(false);
		}else{
			category.setIsParent(true);
		}*/
		jedisCluster.del("ContentCategory_0");
		
		//新增的同时要修改上级 isparent 为TRUE
		cate1.setId(parentId);
		cate1.setIsParent(true);
		contenCategoryMapper.updateByPrimaryKeySelective(cate1);
		//将新增信息写入数据库
		contenCategoryMapper.insertSelective(category);
		Long cId = category.getId();
		List<ContentCategory> categoryList = contenCategoryMapper.findByParentId(cId);
		if(categoryList.isEmpty()){//不存在，没有子目录
			category.setIsParent(false);
			contenCategoryMapper.updateByPrimaryKeySelective(category);
		}else{
			category.setIsParent(true);
			contenCategoryMapper.updateByPrimaryKeySelective(category);
		}
		
		
	}

	/**
	 * 更新
	 * @param id
	 * @param name
	 */
	public void updateCategory(Long id, String name) {
		ContentCategory category = new ContentCategory();//代表更新的对象
		category.setId(id);
		category.setName(name);
		contenCategoryMapper.updateByPrimaryKeySelective(category);
	}
	/**
	 * 删除Id
	 * @param parentId
	 * @param id
	 */

	public void deleteCategory(Long parentId, Long id) {
		ContentCategory category = new ContentCategory();//代表更新的父类对象
		category.setId(parentId);
		category.setIsParent(false);
		//删除之前呀先判断父类下是否还有子类，根据parentId查询所有的集合，如果集合长度为1，则代表删除后父类不再有子类了
		List<ContentCategory> categoryList = contenCategoryMapper.findByParentId(parentId);
		if(categoryList.size()<=1){
			//代表只有一个，修改父类的状态，parentId即为父类的id
			contenCategoryMapper.updateByPrimaryKeySelective(category);
		}
		contenCategoryMapper.deleteByPrimaryKey(id);
	}

}
