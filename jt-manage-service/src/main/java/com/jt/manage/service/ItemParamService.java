package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.BaseService;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemParamMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemParam;

@Service
public class ItemParamService extends BaseService<ItemParam> {

	@Autowired
	private ItemParamMapper itemParamMapper;
	@Autowired
	private ItemService itemService;
	//查询规则参数列表
	public EasyUIResult selectItemParamList(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<ItemParam> itemParamList=itemParamMapper.queryItemParamList();
		PageInfo<ItemParam> info = new PageInfo<ItemParam>(itemParamList);
		return new EasyUIResult(info.getTotal(),info.getList());
	}
	//新增item_param
	public void saveItemParam(ItemParam itemParam) {
		itemParamMapper.insertSelective(itemParam);
	}
	//查询商品规格信息
	public ItemParam findItemParamByItemId(Long itemId) {
		//先根据itemID找到到item表中的cid,在根据cid查询itemparam信息
		Item item =itemService.queryById(itemId);
		System.out.println("Item="+item);
		System.out.println("cid="+item.getCid());
		return itemParamMapper.selectItemParamByItemId(item.getCid());
	}
	/**
	 *根据ids删除itemparam
	 * @param ids
	 * @return
	 */
	public SysResult deleteItemParamByIds(Long[] ids) {
		Integer i = super.deleteByIds(ids);
		if(i>0){
			return SysResult.oK();
		}else{
			return SysResult.build(201, "fail");
		}
	}

}
