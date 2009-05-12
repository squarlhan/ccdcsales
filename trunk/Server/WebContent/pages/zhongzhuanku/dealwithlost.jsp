<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转处损耗产品处理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
	
	function urlpara(pch,pid,spid,lostvalue) 
	{
		
	    location.href= "zzkdealwithlost.action?pch="+pch+"&pid="+pid+"&spid="+spid+"&lostvalue="+lostvalue; 
	} 
	function confirmbtn(rownum)
	{
		var pch = (document.getElementById("pch_"+rownum)).value;
		var pid = (document.getElementById("pid_"+rownum)).value;
		var spid = (document.getElementById("spid_"+rownum)).value;
		var lost = (document.getElementById("lost_"+rownum)).value;
		if(lost.replace(/(^\s*)|(\s*$)/g,"")=="")
		{
			alert("请填写损耗数量");
			return;
		}
		if(isNaN(lost))
		{
			alert("只允许输入数字！");
			return;
		}
		if(confirm("确认损耗？"))
		{
			urlpara(pch,pid,spid,lost);
			return;
		}
	}

	//下面开始分页
	 
    var perpage = 10;

    var pchs = new Array(
		    <s:iterator id="result" value="resultList">
			    "<s:property value='#result.id.pch'/>",
		    </s:iterator>
	    0); 	
    var prds = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.products.name'/>",
			</s:iterator>
		0);
	
	var spfs = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.specifications.displayName'/>",
			</s:iterator>
		0);
	var pids = new Array(
		    <s:iterator id="result" value="resultList">
			    <s:property value="#result.products.id"/>,
		    </s:iterator>
	    0); 
	var sids = new Array(
		    <s:iterator id="result" value="resultList">
			    <s:property value="#result.specifications.id"/>,
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
	    var td7 = tr.insertCell(-1);
	    
	    var textfield1 = document.createElement("input");
		textfield1.setAttribute("name","pch_"+a);
		textfield1.setAttribute("id","pch_"+a);
		textfield1.setAttribute("size","10");
		textfield1.onfocus=function(){textfield1.blur();};
		textfield1.value = pchs[a];

		var textfield2 = document.createElement("input");
		textfield2.setAttribute("name","pname_"+a);
		textfield2.setAttribute("id","pname_"+a);
		textfield2.setAttribute("size","15");
		textfield2.onfocus=function(){textfield2.blur();};
		textfield2.value = prds[a];

		var textfield3 = document.createElement("input");
		textfield3.setAttribute("name","spname_"+a);
		textfield3.setAttribute("id","spname_"+a);
		textfield3.setAttribute("size","15");
		textfield3.onfocus=function(){textfield3.blur();};
		textfield3.value = spfs[a];

		var textfield4 = document.createElement("input");
		textfield4.setAttribute("name","lost_"+a);
		textfield4.setAttribute("id","lost_"+a);
		textfield4.setAttribute("size","5");

		var a1 = document.createElement("a");
		a1.setAttribute("href","javascript:confirmbtn("+a+")");
		a1.innerHTML = "损耗";

		var textfield5 = document.createElement("input");
		textfield5.setAttribute("name","pid_"+a);
		textfield5.setAttribute("id","pid_"+a);
		textfield5.setAttribute("size","0");
		textfield5.setAttribute("readonly","true");
		textfield5.value = pids[a];
		textfield5.type = "hidden";

		var textfield6 = document.createElement("input");
		textfield6.setAttribute("name","spid_"+a);
		textfield6.setAttribute("id","spid_"+a);
		textfield6.setAttribute("size","0");
		textfield6.setAttribute("readonly","true");
		textfield6.value = sids[a];
		textfield6.type = "hidden";

		td1.appendChild(textfield1);
		td2.appendChild(textfield2);
		td3.appendChild(textfield3);
		td4.appendChild(textfield4);
		td5.appendChild(a1);
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
<h2>中转处损耗产品处理</h2>
<h3>中转处所有产品明细</h3>
<s:form id="myform" action="zzkdealwithlost" theme="simple" >
<table class="list_table" id="mytable" align="center" width="640">
  <tr bgcolor="#4A708B">
	<th >批次号</th>	
	<th >产品名</th>	
	<th >规格</th>
	<th >损耗</th>
	<th >操作</th>
	<th style="display:none">产品ID</th>
	<th style="display:none">规格ID</th>
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
</s:form>
 <script language="javascript">
   firstpage();
 </script>
</body>
</html>