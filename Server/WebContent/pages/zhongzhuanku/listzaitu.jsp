<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转库在途产品信息</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >

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
	var aims = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.chuku.cankuByRkId.name'/>",
			</s:iterator>
		0);
	var wgts = new Array(
			<s:iterator id="result" value="resultList">
				<s:property value="(#result.number)*(#result.specifications.weight)"/>,
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

		td1.innerHTML = bnos[a];
		td2.innerHTML = prds[a];
		td3.innerHTML = pchs[a];
		td4.innerHTML = spfs[a];
		td5.innerHTML = aims[a];
		td6.innerHTML = wgts[a];
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
<h2>储运处在途产品信息</h2>

   <table class="list_table" id="mytable" align="center" width="100%" id="tb">
		<tr bgcolor="#4A708B">
		    <th width="10%">编号</th>
		    <th width="15%">产品名</th>
		    <th width="15%">批号</th>
		    <th width="15%">规格</th>
		    <th width="15%">去向</th>			
			<th width="10%">重量(T)</th>	
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
	    

 <script language="javascript">
   firstpage();
 </script>
</body>
</html>