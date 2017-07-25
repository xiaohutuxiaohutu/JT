package com.jt.manage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.mapper.ItemParamItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.pojo.ItemParamItem;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemService extends BaseService<Item>{

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	//查询所有商品列表，按修改时间倒叙
	
	public EasyUIResult queryItemList(Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);//标识分页开始
		List<Item> itemList = itemMapper.queryItemList();
		PageInfo<Item> pageInfo=new PageInfo<Item>(itemList);
		return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
	}
	
	//新增商品
	public void saveItem(Item item,String desc){
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insertSelective(item);
		//新增商品的详情
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());//mybatis+mysql自动查询最后插入的主键值并回显赋值
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insertSelective(itemDesc);
	}
	
	//修改商品
	public void updateItem(Item item,String desc){
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		//删除缓存
		jedisCluster.del("ITEM_"+item.getId());
	}
	//根据id查询item_desc表中的描述信息用来页面回显
	public ItemDesc getItemDesc(Long id){
		
		return itemDescMapper.selectByPrimaryKey(id);
	}
	//根据id查询item_param_item表中商品规则用来回显
	public ItemParamItem queryItemParam(Long id){
		return itemParamItemMapper.queryItemParam(id);
	}
	//删除商品
	public void deleteItem(Long[] ids){
		
		//6-13 删除item_desc中的信息
		itemDescMapper.deleteByIDS(ids);
		itemMapper.deleteByIDS(ids);
		
	}
	
	//上架下架
	public void updateStatus(Integer val,Long[] ids){
		
		Map<String,Object> params= new HashMap<>();
		params.put("status", val);
		params.put("ids", ids);
		itemMapper.updateStatus(params);
		
	}
}
