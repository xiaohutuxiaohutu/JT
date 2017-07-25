package com.jt.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisSentinelService;
import com.jt.common.service.RedisService;
import com.jt.common.util.RedisCluster;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatService extends BaseService<ItemCat> {

	@Autowired
	private ItemCatMapper itemCatMapper;
	// redis用法
	@Autowired
	private RedisService redisService;

	/*
	 //redis哨兵用法，替换redisService即可
	//applicationContext-sentinel.xml与applicationContext-rediscluster.xml不能共存
	@Autowired
	private RedisSentinelService  redisSentinelService;
	*/
	
	//redis集群用法，替换redisService或者redisSentinelService
	//实例化方式是通过spring 实例工厂 bean模式创建对象，剪applicationContext-rediscluster.xml
	@Autowired
	private JedisCluster jedisCluster;
	
	
	private static final Logger log = Logger.getLogger(ItemCatService.class);// 用来打印日志
	// Jackson
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 到后台查询商品分类，返回java对象列表 
	 * @param id
	 * @return
	 */
	public List<ItemCat> list(Long id) {
		String ITEMCAT_KEY = "ITEMCAT_" + id;
		ItemCat params = new ItemCat();
		params.setStatus(1);
		params.setParentId(id);

		// 3 读取判断缓存是否有数据

		/*try {
			String jsonData = jedisCluster.get(ITEMCAT_KEY);
			System.out.println("jsonDae=" + jsonData);
			if (StringUtils.isNotEmpty(jsonData)) {
				System.out.println("存在");
				// 有数据直接额返回，将json字符串转化为java对象
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				Object obj = MAPPER.readValue(jsonNode.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
				return (List<ItemCat>) obj;
			} else {
				System.out.println("不存在");*/
				List<ItemCat> list = itemCatMapper.select(params);
				// 2 写数据到缓存，把java对象转换成字符串,缓存如果出错不能抛异常，必须让业务继续执行
				String data;
				try {
					data = MAPPER.writeValueAsString(list);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("data=" + data);
				//jedisCluster.set("ITEMCAT_" + id, data);// 缓存每个分支
				return list;
			/*}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return null;*/
	}

	//查询商品分类为前台实现，3级分类mulu,前台数据格式要求json,传输用jsonp
	public ItemCatResult getItemCatList(){
		ItemCat params = new ItemCat();
		params.setStatus(1);
		List<ItemCat> itemCatList = super.queryListByWhere(params);
		
		//获取每个节点下的所有的子节点，
		Map<Long,List<ItemCat>> map =new HashMap<Long, List<ItemCat>>();
		for(ItemCat cat:itemCatList){
			//map中还未构建这个节点
			if(!map.containsKey(cat.getParentId())){
				//当这个节点不存在时，创建空的arraylist
				map.put(cat.getParentId(), new ArrayList<ItemCat>());
			}
			map.get(cat.getParentId()).add(cat);
		}
		//组织itemcatresult结构
		ItemCatResult result = new ItemCatResult();
		//遍历一级菜单
		for(ItemCat cat1:map.get(0L)){
			ItemCatData d1 = new ItemCatData();//一级菜单
			String url = "/products/"+cat1.getId()+".html";
			d1.setUrl(url+"");
			d1.setName("<a href=\'"+url+"\'>"+cat1.getName()+"</a>");
			List<ItemCatData> list1 = new ArrayList();
			
			//遍历二级菜单
			for(ItemCat cat2:map.get(cat1.getId())){
				ItemCatData d2 =new ItemCatData();
				d2.setUrl("products/"+cat2.getId()+".html");
				d2.setName(cat2.getName());
				
				List<String > list2 = new ArrayList();
				//遍历三级菜单
				for(ItemCat cat3:map.get(cat2.getId())){
					list2.add("/products/"+cat3.getId()+".html |"+cat3.getName());
				}
				d2.setItems(list2);
				list1.add(d2);
			}
			
			d1.setItems(list1);
			//如果一级菜单超过14条就退出
			if(result.getItemCats().size()>14){
				break;
			}
			result.getItemCats().add(d1);
		}
		return result;
	}


}
