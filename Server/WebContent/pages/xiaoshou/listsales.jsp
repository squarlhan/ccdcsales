<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售总量及明细</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript">

//下面开始分页
 
    var perpage = 10;
	
	var caks = new Array(
		    <s:iterator id="result" value="listchukumx">
			    "<s:property value='#result.chuku.cankuByCankuId.name'/>",
		    </s:iterator>
	    0); 
	var pchs = new Array(
			<s:iterator id="result" value="listchukumx">
				"<s:property value='#result.pch'/>",
			</s:iterator>
		0);
	var prds = new Array(
			<s:iterator id="result" value="listchukumx">
				"<s:property value='#result.products.name'/>",
			</s:iterator>
		0);
	var spfs = new Array(
			<s:iterator id="result" value="listchukumx">
				"<s:property value='#result.specifications.displayName'/>",
			</s:iterator>
		0);
	var wgts = new Array(
			<s:iterator id="result" value="listchukumx">
				<s:property value="#result.number*#result.specifications.weight"/>,
			</s:iterator>
		0);	
	var cuts = new Array(
			<s:iterator id="result" value="listchukumx">
				"<s:property value='#result.chuku.custom.customName'/>",
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
	    td1.innerHTML = caks[a];
	    td2.innerHTML = pchs[a];
	    td3.innerHTML = prds[a];
	    td4.innerHTML = spfs[a];
	    td5.innerHTML = wgts[a];
	    td6.innerHTML = cuts[a];
		}

	function firstpage()
	{
		var mytable = document.getElementById("mytable");		
		while(mytable.rows.length>1){
			mytable.deleteRow(mytable.rows.length-1);
		}
		var stopnum;
		if(prds.length<=perpage+1){
			stopnum = prds.length-1;		
		}else{
			stopnum = perpage;			
		}	
		for(var a=0;a<stopnum;a++){
			createrow(mytable,a);
		    
		}
		var current = document.getElementById("current");
		current.value = 1;
		var sum = document.getElementById("sum");
		sum.value = Math.ceil((prds.length-1)/perpage);
		
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
			stopnum = prds.length-1;
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
		for(var a=startnum;a<prds.length-1;a++){
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
<s:head/>
</head>

<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>销售总量及明细</h2></td>
  </tr>
 </table>
<s:form action="xslistsales!listBetween.action">
	<table class="list_table" align="center" width="100%">
	  <tr align="center">
	  	<td width="20%">起始时间</td>
	  	<td width="20%"><s:datetimepicker name="begindate" label="1" toggleType="explode" value="today" theme="simple"/></td>
	  	<td width="20%">截止时间</td>
	  	<td width="20%"><s:datetimepicker name="enddate" label="2" labelposition="left" toggleType="explode" value="today" theme="simple"/></td>
	     <td width="20%"><s:submit value="查看销售详情" theme="simple"/></td>
	  </tr>
	 </table>

 	<table class="list_table" align="center" border="1" width="100%">
	 <tr bgcolor="#4A708B">
	   <th width="25%">产品名称</th>
	   <th width="25%">重量(T)</th>
	 </tr>
	 	<s:iterator id="pr_qux" value="cmxList" status="index"> 
	    <tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	         <td align="center">
				   <s:property value="#pr_qux.prdid.name" />
			 </td>
			 <td align="center">
				   <s:property value="#pr_qux.ckt" />
			 </td>
			 </tr>
			 </s:iterator>
	 </table>
	 
    <table class="list_table" id="mytable" align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="10%">发货仓库</th>
		    <th width="10%">批号</th>
		    <th width="10%">产品名</th>
		    <th width="10%">规格</th>
		    <th width="5%">重量</th>
			<th width="25%">去向</th>
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
