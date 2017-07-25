package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;
import com.jt.web.pojo.ItemParamItem;
import com.mysql.jdbc.StringUtils;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemService {
	@Autowired
	private HttpClientService httpClientService;
	
	@Autowired
	private JedisCluster jedisCluster;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * //通过jsonp发送请求到后台web.controller查询商品详情
	 * @param itemId
	 * @return
	 */
	//WebItemController返回的是Item对象时，正常显示
	/*public Item getItemById(Long itemId){
		try {
			//将商品详情保存到redis分片缓存中
			String key="ITEM_"+itemId;
			String jsonDat=jedisCluster.get(key);
			//1 判断缓存里是否有值
			if(StringUtils.isNullOrEmpty(jsonDat)){
				//不存在,httpclient发送请求到后台查询数据并返回
				String url="http://manage.jt.com/web/item/"+itemId;
				String jsonData = httpClientService.doGet(url);
				//将数据写入redis缓存中
				jedisCluster.set(key, jsonData);
				Item item = MAPPER.readValue(jsonData, Item.class);
				return item;
			}else{
				//存在
				Item item = MAPPER.readValue(jsonDat, Item.class);
				return item;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}*/
	//WebItemController返回的是SysResutl对象时
	public Item getItemById(Long itemId){
		try {
			//获取缓存冲的key值
			String key="ITEM_"+itemId;
			String jsonData=jedisCluster.get(key);
			
			//1 判断缓存里是否有值
			if(StringUtils.isNullOrEmpty(jsonData)){
				//不存在,httpclient发送请求到后台查询数据并返回
				String url="http://manage.jt.com/web/item/"+itemId;
				String SysResutlData = httpClientService.doGet(url);
				//获取SysResutlData中的data属性
				SysResult result=SysResult.formatToPojo(SysResutlData, Item.class);
				Item item = (Item) result.getData();
				String value = MAPPER.writeValueAsString(item);
				System.out.println("VALUE="+value);
				//将数据写入redis缓存中
				jedisCluster.set(key, value);
				return item;
			}else{
				//存在
				Item item = MAPPER.readValue(jsonData, Item.class);
				return item;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * //请求后台系统返回商品描述详情
	 * @param itemId
	 * @return
	 */
	public ItemDesc getItemDescById(Long itemId) {
		//将商品描述详情存入缓存
		String key = "ItemDesc_"+itemId;
		String jsonDat=jedisCluster.get(key);
		try {
			//判断缓存中是否存在
			if(StringUtils.isNullOrEmpty(jsonDat)){
				//不存在。httpclient跨域发送请求到后台查询数据并返回
				String url="http://manage.jt.com/web/itemdesc/"+itemId;
				String jsonData = httpClientService.doGet(url);
				//将数据存入缓存中
				jedisCluster.set(key, jsonData);
				ItemDesc itemDesc = MAPPER.readValue(jsonData, ItemDesc.class);
				//System.out.println("前台ItemService:itemDesc="+itemDesc);
				return itemDesc;
			}else{
				ItemDesc itemDesc = MAPPER.readValue(jsonDat, ItemDesc.class);
				return itemDesc;
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询商品规范信息
	 * @param itemId  商品id
	 * @return  paramdata
	 */
	public String getItemParamItemByitemId(Long itemId) {
		String key = "ItemParamItem_"+itemId;
		String jsonDat=jedisCluster.get(key);
		try{
			if(!StringUtils.isNullOrEmpty(jsonDat)){
				//缓存中存在数据
				ItemParamItem itemParam=MAPPER.readValue(jsonDat, ItemParamItem.class);
				String paramData = itemParam.getParamData();
	            System.out.println("前台ItemService:paramData="+paramData);
	            ArrayNode arrayNode = (ArrayNode) MAPPER.readTree(paramData);
	            StringBuilder sb = new StringBuilder();
	            sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\"><tbody>");
	            for (JsonNode jsonNode : arrayNode) {
	                sb.append("<tr><th class=\"tdTitle\" colspan=\"2\">" + jsonNode.get("group").asText()
	                        + "</th></tr><tr>");
	                ArrayNode params = (ArrayNode) jsonNode.get("params");
	                for (JsonNode param : params) {
	                    sb.append("<tr><td class=\"tdTitle\">" + param.get("k").asText() + "</td><td>"
	                            + param.get("v").asText() + "</td></tr>");
	                }
	            }
	            sb.append("</tbody></table>");
	            String strResult = sb.toString();
				return strResult;
			}else{
				//缓存中无数据
				String url="http://manage.jt.com/web/itemParam/"+itemId;
				String jsonData = httpClientService.doGet(url);
				//将数据写入缓存
				jedisCluster.set(key, jsonData);
				//用jackson工具解析返回的json字符串为java对象，
				ItemParamItem itemParam =MAPPER.readValue(jsonData, ItemParamItem.class);
				System.out.println("前台ItemService:itemParamitem="+itemParam);
				//ItemParamItem itemParamItem = (ItemParamItem) sysResult.getData();
	            String paramData = itemParam.getParamData();
	            System.out.println("前台ItemService:paramData="+paramData);
	            ArrayNode arrayNode = (ArrayNode) MAPPER.readTree(paramData);
	            StringBuilder sb = new StringBuilder();
	            sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\"><tbody>");
	            for (JsonNode jsonNode : arrayNode) {
	                sb.append("<tr><th class=\"tdTitle\" colspan=\"2\">" + jsonNode.get("group").asText()
	                        + "</th></tr><tr>");
	                ArrayNode params = (ArrayNode) jsonNode.get("params");
	                for (JsonNode param : params) {
	                    sb.append("<tr><td class=\"tdTitle\">" + param.get("k").asText() + "</td><td>"
	                            + param.get("v").asText() + "</td></tr>");
	                }
	            }
	            sb.append("</tbody></table>");
	            String strResult = sb.toString();
				return strResult;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
