<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(uid,uname,upassword,uphone,udes) 
{
    location.href= encodeURI("adminusermanager!modify.action?id="+uid+"&name="+uname+"&password="+upassword+"&phone="+uphone+"&description="+udes); 
} 
function confirm_reset(rownum)
{
	var cf = confirm("确认重置？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		var uname = (document.getElementById("name_"+rownum)).value;
		var upassword = (document.getElementById("name_"+rownum)).value;
		var uphone = (document.getElementById("phone_"+rownum)).value;
		var udes = (document.getElementById("description_"+rownum)).value;
		if(uname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("登陆名不能为空 ");
		else
			if(uphone.replace(/(^\s*)|(\s*$)/g,"")!="")
				if(isNaN(uphone))
					alert("手机号必须为数字");
				else
					if(uphone.length!=11)
						alert("手机号必须为11位");
					else
						urlpara_modify(uid,uname,upassword,uphone,udes);
			else
				urlpara_modify(uid,uname,upassword,uphone,udes);
	}
}
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		var uname = (document.getElementById("name_"+rownum)).value;
		var upassword = (document.getElementById("password_"+rownum)).value;
		var uphone = (document.getElementById("phone_"+rownum)).value;
		var udes = (document.getElementById("description_"+rownum)).value;
		if(uname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("登陆名不能为空 ");
		else
			if(uphone.replace(/(^\s*)|(\s*$)/g,"")!="")
				if(isNaN(uphone))
					alert("手机号必须为数字");
				else
					if(uphone.length!=11)
						alert("手机号必须为11位");
					else
						urlpara_modify(uid,uname,upassword,uphone,udes);
			else
				urlpara_modify(uid,uname,upassword,uphone,udes);
	}
}
function urlpara_delete(uid) 
{
    location.href= encodeURI("adminusermanager!delete.action?id="+uid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		urlpara_delete(uid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newuname = (document.getElementById("newuname")).value;
		var newupass = (document.getElementById("newupass")).value;
		var newuphone = (document.getElementById("newuphone")).value;
		var newudes = (document.getElementById("newudes")).value;
		if(newuname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("登陆名不能为空 ");
			return false;
		}
		else
			if(newupass.replace(/(^\s*)|(\s*$)/g,"") == "")
			{
				alert("登录密码不能为空");
				return false;
			}
			else{
				if(newuphone.replace(/(^\s*)|(\s*$)/g,"")!="")
					if(isNaN(newuphone)){
						alert("手机号必须为数字");
						return false;
					}
					else
						if(newuphone.length!=11){
							alert("手机号必须为11位");
							return false;
						}
						else
							return true;
				return true;
			}
	}else
		return false;
}

//下面开始分页

var perpage = 10;

var uids = new Array(
	    <s:iterator id="result" value="resultList">
		    <s:property value="#result.id"/>,
	    </s:iterator>
    0);
var unames = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.name'/>",
		</s:iterator>
	0);
var pwds = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.password'/>",
		</s:iterator>
	0);
var phones = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.phone'/>",
		</s:iterator>
	0);
var descrs = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.description'/>",
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
    var td6 = tr.insertCell(-1);
    
    var textfield1 = document.createElement("input");
	textfield1.setAttribute("name","id_"+a);
	textfield1.setAttribute("id","id_"+a);
	textfield1.setAttribute("size","5");
	textfield1.onfocus=function(){textfield1.blur();};
	textfield1.value = uids[a];

	 var textfield2 = document.createElement("input");
	 textfield2.setAttribute("name","name_"+a);
	 textfield2.setAttribute("id","name_"+a);
	 textfield2.setAttribute("size","10");
	 textfield2.value = unames[a];

	 var textfield3 = document.createElement("input");
	 textfield3.setAttribute("name","password_"+a);
	 textfield3.setAttribute("id","password_"+a);
	 textfield3.setAttribute("size","10");
	 textfield3.value = pwds[a];
	// textfield3.type = "hidden";

	 var textfield4 = document.createElement("input");
	 textfield4.setAttribute("name","phone_"+a);
	 textfield4.setAttribute("id","phone_"+a);
	 textfield4.setAttribute("size","12");
	 textfield4.value = phones[a];

	 var textfield5 = document.createElement("input");
	 textfield5.setAttribute("name","description_"+a);
	 textfield5.setAttribute("id","description_"+a);
	 textfield5.setAttribute("size","15");
	 textfield5.value = descrs[a];

	td1.appendChild(textfield1);
	td2.appendChild(textfield2);
	td3.appendChild(textfield3);
	td3.style.display = "none";
	td4.appendChild(textfield4);
	td5.appendChild(textfield5);
	td6.innerHTML = "<a href='javascript:confirm_modify("+a+
	                ")'>修改</a>&nbsp;<a href='javascript:confirm_delete("+a+
	                ")'>删除</a>&nbsp;<a href='javascript:confirm_reset("+a+
	                ")'>重置</a>  ";
}
function firstpage()
{
	var mytable = document.getElementById("mytable");		
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var stopnum;
	if(uids.length<=perpage+1){
		stopnum = uids.length-1;		
	}else{
		stopnum = perpage;			
	}	
	for(var a=0;a<stopnum;a++){
		createrow(mytable,a);
	}
	var current = document.getElementById("current");
	current.value = 1;
	var sum = document.getElementById("sum");
	sum.value = Math.ceil((uids.length-1)/perpage);
	
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
		stopnum = uids.length-1;
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
	for(var a=startnum;a<uids.length-1;a++){
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
<h2 align="center">用户管理</h2>

  <s:form id="myform" name="myform" action="adminusermanager" theme="simple">
  	<table  class="list_table" id="mytable" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="8%">用户ID</th>
		    <th width="12%">登录名</th>
		    <th style="display:none">密码</th>
		    <th width="15%">手机号</th>
		    <th width="15%">描述</th>
		    <th width="35%">操作</th>
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
		 	<td><label>登录名:</label></td>
	        <td><s:textfield id="newuname" name="newuname"/></td>
	      </tr>
	      <tr>
	      	<td><label>密码:</label></td>
	         <td>
	         	<s:textfield id="newupass" name="newupass"/>
         	</td>
	      </tr>
	      <tr>
	      	<td><label>手机号:</label></td>
	        <td><s:textfield id="newuphone" name="newuphone"/></td>
	      </tr>
	      <tr>
	      	<td><label>描述:</label></td>
	        <td><s:textfield id="newudes" name="newudes"/></td>
	      </tr>
	      <tr>
	        <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="adminusermanager!add"/> </td>
	      </tr>
	      
		</table>
  </s:form>
 <script language="javascript">
   firstpage();
 </script>
</body>
</html>