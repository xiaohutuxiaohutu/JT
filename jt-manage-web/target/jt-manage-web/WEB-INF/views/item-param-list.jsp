<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemParamList" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/param/list',method:'get',pageSize:30,toolbar:itemParamListToolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">ID</th>
        	<th data-options="field:'itemCatId',width:80">商品类目ID</th>
        	<th data-options="field:'itemCatName',width:100">商品类目</th>
            <th data-options="field:'paramData',width:300,title:'paramData',formatter:formatItemParamData">规格(只显示分组名称)</th>
            <th data-options="field:'created',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:KindEditorUtil.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemParamEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/page/item-param-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

	function formatItemParamData(value , index){
		var json = JSON.parse(value);
		var array = [];
		$.each(json,function(i,e){
			array.push(e.group);
		});
		return array.join(",");
	}

    function getSelectionsIds(){
    	var itemList = $("#itemParamList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var itemParamListToolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	KindEditorUtil.createWindow({
        		url : "/page/item-param-add",
        	});
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids =getSelectionsIds();
        	if(ids.length==0){
        		$.messager.alert('提示','必须选择一个商品');
        		return;
        	}
        	if(ids.indexOf(',')>0){
        		$.messager.alert('提示','只能选择一个商品');
        		return;
        	}
        	$("#itemParamEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#itemParamList").datagrid("getSelections")[0];
        			//EasyUi,jQuery提供的方法
        			$("#itemParamEditorTable").form("load",data);
        			//加载商品规格
        			$.getJSON('/item/param/query/'+data.id,function(_data){
      					$("#itemParamEditorTable .params").show();
      					//回显商品规格
      					alert(_data);
      					 var paramData = JSON.parse(_data);
      					alert("paramData="+paramData);
      					 var html = "<ul>";
      					 for(var i in paramData){
      						 var pd = paramData[i];
      						 html+="<li><table>";
      						 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
      						 for(var j in pd.params){
      							 var ps = pd.params[j];
      							 html+="<tr><td class=\"param\"><span>"+ps.k+"</span>: </td><td><input autocomplete=\"off\" type=\"text\" value='"+ps.v+"'/></td></tr>";
      						 }
      						 
      						 html+="</li></table>";
      					 }
      					 html+= "</ul>";
      					 $("#itemParamEditorTable .params td").eq(1).html(html);
        			});
        		}
        				
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品规格!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品规格吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/param/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品规格成功!',undefined,function(){
            					$("#itemParamList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>