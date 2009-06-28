<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处到货产品入库</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
	function check()
	{
		var ele2 = document.getElementById("rew");
	
	    if(ele2.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("请输入编号");
			return false;
		}
		else if(ele2.value.length>14)
		{
			alert("编号应不超过14位");
			return false;
		}
		//下面检测是不是选择了产品
		var a = false;
		var t = document.getElementById("mytable").rows.length;
		for(var i=0;i<t-1;i++)
		{
			var check = document.getElementById("checked["+i+"]").checked;
			a = a || check;
		}
		if(a==false)
		{
			alert("请选择入库产品！");return false;
		}
		//检测结束
		if(confirm("确认产品入库？"))
			return true;
		else
			return false;
	}


	//下面开始分页
	 
    var perpage = 15;

    var bnos = new Array(
		    <s:iterator id="result" value="resultList">
			    <s:property value="#result.Id"/>,
		    </s:iterator>
	    0); 
    var prds = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.products.name'/>",
			</s:iterator>
		0);
	var pchs = new Array(
		    <s:iterator id="result" value="resultList">
			    "<s:property value='#result.pch'/>",
		    </s:iterator>
	    0); 	
	var spfs = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.specifications.displayName'/>",
			</s:iterator>
		0);
	var nums = new Array(
			<s:iterator id="result" value="resultList">
				<s:property value="#result.number"/>,
			</s:iterator>
		0);
	var wgts = new Array(
			<s:iterator id="result" value="resultList">
				<s:property value="(#result.number)*(#result.specifications.weight)"/>,
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
	    var td7 = tr.insertCell(-1);

	    var checkbox1 = document.createElement("input");
	    checkbox1.type="checkbox";
	    checkbox1.setAttribute("name","checked["+t+"]");
	    checkbox1.setAttribute("id","checked["+t+"]");
	    checkbox1.checked = false;
	    
	    var textfield1 = document.createElement("input");
		textfield1.setAttribute("name","resultList["+t+"].Id");
		textfield1.setAttribute("id","resultList["+t+"].Id");
		textfield1.setAttribute("size","10");
		textfield1.onfocus=function(){textfield1.blur();};
		textfield1.value = bnos[a];

		var textfield2 = document.createElement("input");
		textfield2.setAttribute("name","resultList["+t+"].products.name");
		textfield2.setAttribute("id","resultList["+t+"].products.name");
		textfield2.setAttribute("size","15");
		textfield2.onfocus=function(){textfield2.blur();};
		textfield2.value = prds[a];

		var textfield3 = document.createElement("input");
		textfield3.setAttribute("name","resultList["+t+"].pch");
		textfield3.setAttribute("id","resultList["+t+"].pch");
		textfield3.setAttribute("size","20");
		textfield3.onfocus=function(){textfield3.blur();};
		textfield3.value = pchs[a];

		var textfield4 = document.createElement("input");
		textfield4.setAttribute("name","resultList["+t+"].specifications.name");
		textfield4.setAttribute("id","resultList["+t+"].specifications.name");
		textfield4.setAttribute("size","10");
		textfield4.onfocus=function(){textfield4.blur();};
		textfield4.value = spfs[a];

		var textfield5 = document.createElement("input");
		textfield5.setAttribute("size","10");
		textfield5.onfocus=function(){textfield5.blur();};
		textfield5.value = wgts[a];

		var textfield6 = document.createElement("input");
		textfield6.setAttribute("name","resultList["+t+"].number");
		textfield6.setAttribute("id","resultList["+t+"].number");
		textfield6.setAttribute("size","0");
		textfield6.setAttribute("readonly","true");
		textfield6.value = nums[a];
		textfield6.type = "hidden";

		td1.appendChild(checkbox1);
		td2.appendChild(textfield1);
		td3.appendChild(textfield2);
		td4.appendChild(textfield3);
		td5.appendChild(textfield4);
		td6.appendChild(textfield5);
		td7.appendChild(textfield6);
	}

	function firstpage()
	{
		var mytable = document.getElementById("mytable");		
		while(mytable.rows.length>1){
			mytable.deleteRow(mytable.rows.length-1);
		}
		var stopnum;
		if(pchs.length<=perpage+1){
			stopnum = pchs.length-1;		
		}else{
			stopnum = perpage;			
		}	
		for(var a=0;a<stopnum;a++){
			createrow(mytable,a);
		}
		var current = document.getElementById("current");
		current.value = 1;
		var sum = document.getElementById("sum");
		sum.value = Math.ceil((pchs.length-1)/perpage);
		
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
			stopnum = pchs.length-1;
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
		for(var a=startnum;a<pchs.length-1;a++){
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
<h2>储运处到货产品入库</h2>
  <s:form theme="simple" action="cyccheckinproducts" onsubmit="return check()">
  	   <br/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="编号："/><s:textfield id="rew" name="bno" labelposition="top" size="30"/>
   <table class="list_table" id="mytable" align="center" width="100%" id="tb">
		<tr bgcolor="#4A708B">
		    <th width="10%">选择</th>
		    <th width="15%">编号</th>
		    <th width="10%">产品名</th>
		    <th width="15%">批号</th>
		    <th width="10%">规格</th>			
			<th width="15%">重量(T)</th>
			<th width="0" style="display:none">数量</th>			
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
		<table align="center">
	    <tr>
				<td align="center">
					<s:submit disabled="flag" value="产品入库" />
				</td>
			</tr></table>
	    
	     
  </s:form>
 <script language="javascript">
   firstpage();
 </script>
</body>
</html>