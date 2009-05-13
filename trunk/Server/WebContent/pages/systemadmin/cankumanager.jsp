<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(cid,cname,ctype,caddress) 
{
    location.href= encodeURI("admincanmanager!modify.action?id="+cid+"&name="+cname+"&address="+caddress+"&type="+ctype); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var cid = (document.getElementById("cid_"+rownum)).value;
		var cname = (document.getElementById("cname_"+rownum)).value;
		var ctype = (document.getElementById("reset_"+rownum)).value;
		var caddress = (document.getElementById("caddress_"+rownum)).value;
		if(cname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("仓库名不能为空 ");
		else
			if(caddress.replace(/(^\s*)|(\s*$)/g,"") == "")
				alert("仓库地址不能为空 ");
			else
				urlpara_modify(cid,cname,ctype,caddress);
	}
}
function urlpara_delete(cid) 
{
    location.href= encodeURI("admincanmanager!delete.action?id="+cid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var cid = (document.getElementById("cid_"+rownum)).value;
		urlpara_delete(cid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newcname = (document.getElementById("newcname")).value;
		var newcaddr = (document.getElementById("newcaddr")).value;
		if(newcname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("厂库名不能为空 ");
			return false;
		}
		else
			if(newcaddr.replace(/(^\s*)|(\s*$)/g,"") == "")
			{
				alert("地址名不能为空 ");
				return false;
			}
			else
				return true;
	}else
		return false;
}

//下面开始分页

var perpage = 10;

var cids = new Array(
	    <s:iterator id="result" value="resultList">
		    <s:property value="#result.id"/>,
	    </s:iterator>
    0);
var cnames = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.name'/>",
		</s:iterator>
	0);
var types = new Array(
	    <s:iterator id="result" value="resultList">
		    "<s:property value='#result.typeName'/>",
	    </s:iterator>
    0);
var caddrs = new Array(
	    <s:iterator id="result" value="resultList">
		    "<s:property value='#result.address'/>",
	    </s:iterator>
    0); 	

function createrow(mytable,a)
{
	var t;
	if(a>=perpage)t=a%perpage;
	else t=a;
	var tr = mytable.insertRow(t+1);  		        
    var td1 = tr.insertCell(-1);
    var td2 = tr.insertCell(-1);
    var td3 = tr.insertCell(-1);
    var td4 = tr.insertCell(-1);
    var td5 = tr.insertCell(-1);
    var td6 = tr.insertCell(-1);
    
    var textfield1 = document.createElement("input");
	textfield1.setAttribute("name","cid_"+a);
	textfield1.setAttribute("id","cid_"+a);
	textfield1.setAttribute("size","10");
	textfield1.onfocus=function(){textfield1.blur();};
	textfield1.value = cids[a];

	 var textfield2 = document.createElement("input");
	 textfield2.setAttribute("name","cname_"+a);
	 textfield2.setAttribute("id","cname_"+a);
	 textfield2.setAttribute("size","10");
	 textfield2.value = cnames[a];

	 var textfield3 = document.createElement("input");
	 textfield3.setAttribute("name","ctypeName_"+a);
	 textfield3.setAttribute("id","ctypeName_"+a);
	 textfield3.setAttribute("size","10");
	 textfield3.onfocus=function(){textfield3.blur();};
	 textfield3.value = types[a];

	 var select1 = document.createElement("select");
		select1.setAttribute("name","reset_"+a);
		select1.setAttribute("id","reset_"+a);
		var option1 = document.createElement("option");
		var option2 = document.createElement("option");
		var option3 = document.createElement("option");
		option1.value = "0";
		option1.text = "储运处";
		option2.value = "1";
		option2.text = "驻厂库";
		option3.value = "2";
		option3.text = "中转库";
		select1.options.add(option1);
		select1.options.add(option2);
		select1.options.add(option3);
	 
	 var textfield4 = document.createElement("input");
	 textfield4.setAttribute("name","caddress_"+a);
	 textfield4.setAttribute("id","caddress_"+a);
	 textfield4.setAttribute("size","16");
	 textfield4.value = caddrs[a];

	td1.appendChild(textfield1);
	td2.appendChild(textfield2);
	td3.appendChild(textfield3);
	td4.appendChild(select1);
	td5.appendChild(textfield4);
	td6.innerHTML = "<a href='javascript:confirm_modify("+a+")'>修改</a>&nbsp;<a href='javascript:confirm_delete("+a+")'>删除</a> ";
}
function firstpage()
{
	var mytable = document.getElementById("mytable");		
	while(mytable.rows.length>1){
		mytable.deleteRow(mytable.rows.length-1);
	}
	var stopnum;
	if(cids.length<=perpage+1){
		stopnum = cids.length-1;		
	}else{
		stopnum = perpage;			
	}	
	for(var a=0;a<stopnum;a++){
		createrow(mytable,a);
	}
	var current = document.getElementById("current");
	current.value = 1;
	var sum = document.getElementById("sum");
	sum.value = Math.ceil((cids.length-1)/perpage);
	
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
		stopnum = cids.length-1;
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
	for(var a=startnum;a<cids.length-1;a++){
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
<h2 align="center">仓库管理</h2>

  <s:form id="myform" name="myform" action="admincanmanager" theme="simple">
  	<table class="list_table" id="mytable" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="80">仓库标识</th>
		    <th width="80">仓库名称</th>
		    <th width="80">仓库类型</th>
		    <th width="80">类型设置</th>
		    <th width="80">仓库地址</th>
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
	  <table class="list_table" align="center">
		 	<tr>
		 	<td><label>仓库名称:</label></td>
	        <td><s:textfield id="newcname" name="newcname"/></td>
	      </tr>
	      <tr>
	      	<td><label>仓库类型:</label></td>
	         <td>
	         	<select id="newctype" name="newctype">
	         		<option value="0">储运处</option>
	         		<option value="1">驻广库</option>
	         		<option value="2">中转库</option>
	         	</select>
         	</td>
	      </tr>
	      <tr>
	      	<td><label>仓库地址:</label></td>
	        <td><s:textfield id="newcaddr" name="newcaddr"/></td>
	      </tr>
	      <tr>
	      <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="admincanmanager!add"/> </td>
	      </tr>
	      
		</table>
		
  </s:form>
 <script language="javascript">
   firstpage();
 </script>
</body>
</html>