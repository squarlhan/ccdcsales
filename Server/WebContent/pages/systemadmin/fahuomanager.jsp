<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发货单位信息管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(fid,fname) 
{
    location.href= encodeURI("adminfahuomanager!modify.action?id="+fid+"&name="+fname); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var fid = (document.getElementById("id_"+rownum)).value;
		var fname = (document.getElementById("name_"+rownum)).value;
		if(fname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("发货单位名称不能为空 ");
		else
			urlpara_modify(fid,fname);
	}
}
function urlpara_delete(fid) 
{
    location.href= encodeURI("adminfahuomanager!delete.action?id="+fid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var fid = (document.getElementById("id_"+rownum)).value;
		urlpara_delete(fid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newfname = (document.getElementById("newfname")).value;
		if(newfname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("发货单位名称不能为空 ");
			return false;
		}
		else
			return true;
	}else
		return false;
}

//下面开始分页

var perpage = 10;

var pids = new Array(
	    <s:iterator id="result" value="resultList">
		    <s:property value="#result.id"/>,
	    </s:iterator>
    0);
var pnames = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.name'/>",
		</s:iterator>
	0);

function createrow(mytable,a)
{
	var tr = mytable.insertRow(1);  		        
    var td1 = tr.insertCell(-1);
    var td2 = tr.insertCell(-1);
    var td3 = tr.insertCell(-1);
    
    var textfield1 = document.createElement("input");
	textfield1.setAttribute("name","id_"+a);
	textfield1.setAttribute("id","id_"+a);
	textfield1.setAttribute("readonly","true");
	textfield1.value = pids[a];

	 var textfield2 = document.createElement("input");
	 textfield2.setAttribute("name","name_"+a);
	 textfield2.setAttribute("id","name_"+a);
	 textfield2.value = pnames[a];

	td1.appendChild(textfield1);
	td2.appendChild(textfield2);
	td3.innerHTML = "<a href='javascript:confirm_modify("+a+")'>修改</a>&nbsp;<a href='javascript:confirm_delete("+a+")'>删除</a> ";
}
function firstpage()
{
	var mytable = document.getElementById("mytable");		
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var stopnum;
	if(pids.length<=perpage+1){
		stopnum = pids.length-1;		
	}else{
		stopnum = perpage;			
	}	
	for(var a=0;a<stopnum;a++){
		createrow(mytable,a);
	}
	var current = document.getElementById("current");
	current.value = 1;
	var sum = document.getElementById("sum");
	sum.value = Math.ceil((pids.length-1)/perpage);
	
	var last = document.getElementById("last");
	last.disabled=true;
	var next = document.getElementById("next");
	if(sum.value>1){
		next.disabled=false;
	}else{
		next.disabled=true;
	}
	var myfinal = document.getElementById("final");
	if(sum.value==0){
		myfinal.disabled=true;
	}
	
}
function lastpage()
{
	var current = document.getElementById("current").value;
	var sum = document.getElementById("sum").value;
	var mytable = document.getElementById("mytable");
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var startnum = (current-2)*perpage;
	var stopnum = (current-1)*perpage;	
	if(current>=2){
	    for(var a=startnum;a<stopnum;a++){
	    	createrow(mytable,a);
	    }
	}
	var next = document.getElementById("next");
	next.disabled=false;		
	var last = document.getElementById("last");
	if(current==2){
		last.disabled=true;
	}else{			
		last.disabled=false;
	}
	document.getElementById("current").value--;
}
function nextpage()
{
	var current = document.getElementById("current").value;
	var sum = document.getElementById("sum").value;
	var mytable = document.getElementById("mytable");
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var startnum = current*perpage;
	var stopnum;
	if(current==sum-1){
		stopnum = pids.length-1;
    }else{
    	stopnum = startnum+perpage;
    }
	for(var a=startnum;a<stopnum;a++){
		createrow(mytable,a);
	}
	var last = document.getElementById("last");
	last.disabled=false;		
	var next = document.getElementById("next");
	if(current==sum-1){
		next.disabled=true;
	}else{		
		next.disabled=false;
	}
	document.getElementById("current").value++;
}
function finalpage()
{
	var mytable = document.getElementById("mytable");
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var sum = document.getElementById("sum").value;		
	var startnum = (sum-1)*perpage;
	for(var a=startnum;a<pids.length-1;a++){
		createrow(mytable,a);
		}	
	document.getElementById("current").value = sum;
	var last = document.getElementById("last");
	if(sum!=1){
		last.disabled=false;
	}else{
		last.disabled=true;
	}
	var next = document.getElementById("next");
	next.disabled=true;
}

</script>
</head>
<body>
<h2 align="center">发货单位信息管理</h2>
  
  <s:form id="myform" name="myform" action="adminfahuomanager" theme="simple">
  	<table class="list_table" id="mytable" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="120">发货单位标识</th>
		    <th width="120">发货单位名称</th>
		    <th width="120">执行操作</th>
		</tr>
       </table>
         <div align="center">
           <input type="button" id="first" value="第一页" onclick="javascript:firstpage()"/>
           <input type="button" id="last" value="上一页" onclick="javascript:lastpage()"/>
           <input type="button" id="next" value="下一页" onclick="javascript:nextpage()"/>
           <input type="button" id="final" value="最后一页" onclick="javascript:finalpage()"/>
	                 第<input align="MIDDLE" type="text" size="2" readonly="readonly" id="current"/>页 &nbsp;
	                 共<input align="MIDDLE" type="text" size="2" readonly="readonly" id="sum"/>页
	    </div>
	    <br/>
  </s:form>
  <s:form onsubmit="return confirm_add()">
	 
		 <table class="list_table" align="center">
		 	<tr>
		 	<td><label>发货单位名称:</label></td>
	        <td><s:textfield id="newfname" name="newfname" theme="simple"/></td>
	        <td align="center"><s:submit value="添加" action="adminfahuomanager!add"/> </td>
	      </tr>
		</table>
  </s:form>
   <script language="javascript">
   firstpage();
 </script>
</body>
</html>