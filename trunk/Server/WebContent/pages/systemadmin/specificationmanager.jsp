<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规格管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(sid,sname,sweight,spack) 
{
    location.href= "adminspemanager!modify.action?id="+sid+"&name="+sname+"&weight="+sweight+"&packtype="+spack; 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var sid = (document.getElementById("sid_"+rownum)).value;
		var sname = (document.getElementById("sname_"+rownum)).value;
		var sweight = (document.getElementById("sweight_"+rownum)).value;
		var spack = (document.getElementById("spackType_"+rownum)).value;
		if(sname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("规格名不能为空 ");
		else
			if(sweight.replace(/(^\s*)|(\s*$)/g,"") == "")
				alert("重量不能为空 ");
			else
				if(isNaN(sweight))
					alert("重量必须为数字");
				else
					if(spack.replace(/(^\s*)|(\s*$)/g,"") == "")
						alert("包装方式不能为空");
					else
						urlpara_modify(sid,sname,sweight,spack);
	}
}
function urlpara_delete(sid) 
{
    location.href= encodeURI("adminspemanager!delete.action?id="+sid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var sid = (document.getElementById("sid_"+rownum)).value;
		urlpara_delete(sid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newsname = (document.getElementById("newsname")).value;
		var newsweight = (document.getElementById("newsweight")).value;
		var newswrapp = (document.getElementById("newswrapp")).value;
		if(newsname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("规格名不能为空 ");
			return false;
		}
		else
			if(newsweight.replace(/(^\s*)|(\s*$)/g,"") == "")
			{
				alert("重量不能为空 ");
				return false;
			}else
				if(isNaN(newsweight)){
					alert("重量必须为数字");
					return false;
				}
			else
				if(newswrapp.replace(/(^\s*)|(\s*$)/g,"") == ""){
					alert("包装方式不能为空");
					return false;
				}else
					return true;
	}else
		return false;
}

//下面开始分页

var perpage = 10;

var sids = new Array(
	    <s:iterator id="result" value="resultList">
		    <s:property value="#result.id"/>,
	    </s:iterator>
    0);
var snames = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.name'/>",
		</s:iterator>
	0);
var wts = new Array(
	    <s:iterator id="result" value="resultList">
		    <s:property value="#result.weight"/>,
	    </s:iterator>
    0);
var types = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.packType'/>",
		</s:iterator>
	0);

function createrow(mytable,a)
{
	var tr = mytable.insertRow(1);  		        
    var td1 = tr.insertCell(-1);
    var td2 = tr.insertCell(-1);
    var td3 = tr.insertCell(-1);
    var td4 = tr.insertCell(-1);
    var td5 = tr.insertCell(-1);
    
    var textfield1 = document.createElement("input");
	textfield1.setAttribute("name","sid_"+a);
	textfield1.setAttribute("id","sid_"+a);
	textfield1.setAttribute("size","10");
	textfield1.setAttribute("readonly","true");
	textfield1.value = sids[a];

	 var textfield2 = document.createElement("input");
	 textfield2.setAttribute("name","sname_"+a);
	 textfield2.setAttribute("id","sname_"+a);
	 textfield2.setAttribute("size","10");
	 textfield2.value = snames[a];

	 var textfield3 = document.createElement("input");
	 textfield3.setAttribute("name","sweight_"+a);
	 textfield3.setAttribute("id","sweight_"+a);
	 textfield3.setAttribute("size","10");
	 textfield3.value = wts[a];

	 var textfield4 = document.createElement("input");
	 textfield4.setAttribute("name","spackType_"+a);
	 textfield4.setAttribute("id","spackType_"+a);
	 textfield4.setAttribute("size","16");
	 textfield4.value = types[a];

	td1.appendChild(textfield1);
	td2.appendChild(textfield2);
	td3.appendChild(textfield3);
	td4.appendChild(textfield4);
	td5.innerHTML = "<a href='javascript:confirm_modify("+a+")'>修改</a>&nbsp;<a href='javascript:confirm_delete("+a+")'>删除</a> ";
}
function firstpage()
{
	var mytable = document.getElementById("mytable");		
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var stopnum;
	if(sids.length<=perpage+1){
		stopnum = sids.length-1;		
	}else{
		stopnum = perpage;			
	}	
	for(var a=0;a<stopnum;a++){
		createrow(mytable,a);
	}
	var current = document.getElementById("current");
	current.value = 1;
	var sum = document.getElementById("sum");
	sum.value = Math.ceil((sids.length-1)/perpage);
	
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
		stopnum = sids.length-1;
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
	for(var a=startnum;a<sids.length-1;a++){
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
<h2 align="center">规格管理</h2>

  <s:form id="myform" name="myform" action="adminspemanager" theme="simple">
  	<table  class="list_table" id="mytable" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="80">规格标识</th>
		    <th width="80">规格名称</th>
		    <th width="80">重量</th>
		    <th width="80">包装方式</th>
		    <th width="200">执行操作</th>
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
  <s:form theme="simple" onsubmit="return confirm_add()">
	 
		 <table  class="list_table" align="center">
		 	<tr>
		 	<td><label>规格名称:</label></td>
	        <td><s:textfield id="newsname" name="newsname"/></td>
	      </tr>
	      <tr>
	      	<td><label>重量:</label></td>
	        <td><s:textfield id="newsweight" name="newsweight"/></td>
	      </tr>
	      <tr>
	      	<td><label>包装方式:</label></td>
	        <td><s:textfield id="newswrapp" name="newswrapp"/></td>
	      </tr>
	      <tr>
	        <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="adminspemanager!add"/> </td>
	      </tr>
	      
		</table>
  </s:form>
 <script language="javascript">
   firstpage();
 </script>
</body>
</html>