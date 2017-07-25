package com.jt.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
//按前台的要求，封装商品分裂菜单的返回对象
public class ItemCatResult {

    @JsonProperty("data")	//json序列化时指定字段名称
    private List<ItemCatData> itemCats = new ArrayList<ItemCatData>();

    public List<ItemCatData> getItemCats() {
        return itemCats;
    }

    public void setItemCats(List<ItemCatData> itemCats) {
        this.itemCats = itemCats;
    }

}
