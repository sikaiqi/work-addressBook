$(function(){
	var dg = $("#datagrid").datagrid({
		url:'/addressBook/findAllContacts',
		method:'get',
		fit:true,
		rownumbers:false,
		fitColumns:true,
		pagination:true,
		pageSize: 15,//每页显示的记录条数，默认为10 
		pageList: [15,25,35],//可以设置每页记录条数的列表 
		singleSelect:true,
		columns:[[
		          {field:'id',align:'center',hidden:'true'},
		          {field:'name',title:'姓名',align:'center',width:'14%'},
		          {field:'gender',title:'性别',align:'center',width:'8%'},
		          {field:'birthday',title:'生日',align:'center',width:'14%'},
		          {field:'number',title:'电话',align:'center',width:'14%'},
		          {field:'qq',title:'QQ',align:'center',width:'14%'},
		          {field:'email',title:'邮箱',align:'center',width:'16%'},
		          {field:'address',title:'工作单位',align:'center',width:'22%'}
		]],
		toolbar : "#dgtoolbar"
	});
	
	var p = $("#datagrid").datagrid('getPager');
	$(p).pagination({
        beforePageText: '第',//页数文本框前显示的文字 
        afterPageText: '页    共 {pages} 页',  //pages为默认的参数吗，代表总页数
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'// from，to , total均为默认参数名，from, to 代表现在是总记录中的第几条到第几条，tatal代表总记录数
	});
});

// 添加新联系人
function newContact(){ 
	//弹出对话框
	$('#adddg').dialog('open').dialog('setTitle','添加联系人'); 
	//数据清空
	$('#fam').form('clear'); 
}
//联系人信息修改 
function editContact(){ //选中某一行
	var row =$('#datagrid').datagrid('getSelected'); 
	if (row){
		$('#modifydg').dialog('open').dialog('setTitle','修改联系人'); 
		//显示未修改前的联系人信息
		$('#fim').form('load',row); } 
} 

//按姓名模糊匹配
function doSearch(value){
	if (value.trim()){
		 $.get('/addressBook/findByName/'+value,function(result){ 
			 if (result){
				 $('#datagrid').datagrid('loadData',result); // reload the contact data 
			 } 
		},'json'); 
	} else{
		$('#datagrid').datagrid('reload');
	}
}

//联系人信息保存
function updateContact(){ 
	var row =$('#datagrid').datagrid('getSelected');
	if(row==null){
		return;
	} else{ 
		if(!$('#fim').form('validate')){
			return;
		}
	}
	
	$.ajax({
         url:"/addressBook/updateContact",
         type:"POST",
         data: JSON.stringify($('#fim').serializeJson()),
         contentType:"application/json",  //缺失会出现URL编码，无法转成json对象
         success: function(result){ 
			 if (result.success){
				 $('#modifydg').dialog('close'); //close the dialog 
				 $('#datagrid').datagrid('reload'); // reload the Contact data 
				 $.messager.show({
					 title: 'Success', 
					 msg: '修改成功' 
				 }); 
			 } else { 
				 $.messager.show({ 
					 title: 'Error',
					 msg: result.msg 
				 }); 
			 } 
		 } 
    });
} 

//联系人信息添加按钮事件
 function addContact(){ 
	 $.ajax({
         url:"/addressBook/addContact",
         type:"POST",
         data: JSON.stringify($('#fam').serializeJson()),
         contentType:"application/json",  //缺失会出现URL编码，无法转成json对象
         success: function(result){ 
			 if (result.success){
				 $('#adddg').dialog('close'); // close the dialog
				 $('#datagrid').datagrid('reload'); // reload the Contact data 
				 $.messager.show({
					 title: 'Success', 
					 msg: '添加成功' 
				 }); 
			 } else { 
				 $.messager.show({ 
					 title: 'Error',
					 msg: result.msg 
				 }); 
			 } 
		 } 
     });
}
	 
//联系人删除按钮事件 
function removeContact(){ 
	 var row = $('#datagrid').datagrid('getSelected'); 
	 if (row){
		 $.messager.confirm('Confirm','确定要删除该联系人?',function(r){ 
			 if (r){
				 $.get('/addressBook/deleteContact/'+row.id,function(result){ 
					 if (result.success){
						 $('#datagrid').datagrid('reload'); // reload the contact data 
						 $.messager.show({
							 title: 'Success', 
							 msg: '删除成功' 
						 }); 
					 } else { 
						 $.messager.show({ // show error message 
							 title: 'Error', 
							 msg: result.msg 
						 }); 
					 } 
				},'json'); 
			} 
		});
	} 
}

//给表单绑定将表单转换成json对象方法
$.fn.serializeJson=function(){  
    var serializeObj={};  
    var array=this.serializeArray();  
    var str=this.serialize();  
    $(array).each(function(){  
        if(serializeObj[this.name]){  
            if($.isArray(serializeObj[this.name])){  
                serializeObj[this.name].push(this.value);  
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  
            }  
        }else{  
            serializeObj[this.name]=this.value;   
        }  
    });  
    return serializeObj;  
}; 

