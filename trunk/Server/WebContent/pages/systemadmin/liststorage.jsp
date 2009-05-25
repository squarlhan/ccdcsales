<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript">
	function print(){
		document.myform.action = "adminprintwarehouse.action";
		return true;
	}
	//下面开始分页
	 
    var perpage = 10;
	
	var prds = new Array(
		    <s:iterator id="pr_memo" value="resultList">
			    "<s:property value='#pr_memo.prdid.name'/>",
		    </s:iterator>
	    0); 
	var nxt = new Array(
			<s:iterator id="pr_memo" value="resultList">
				<s:property value="#pr_memo.nxt"/>,
			</s:iterator>
		0);
	var wxt = new Array(
			<s:iterator id="pr_memo" value="resultList">
				<s:property value="#pr_memo.wxt"/>,
			</s:iterator>
		0);
	var djt = new Array(
			<s:iterator id="pr_memo" value="resultList">
				<s:property value="#pr_memo.djt"/>,
			</s:iterator>
		0);
	var dxt = new Array(
			<s:iterator id="pr_memo" value="resultList">
				<s:property value="#pr_memo.dxt"/>,
			</s:iterator>
		0);
	var bhgt = new Array(
			<s:iterator id="pr_memo" value="resultList">
				<s:property value="#pr_memo.bhgt"/>,
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
	    td1.innerHTML = prds[a];
	    td2.innerHTML = nxt[a];
	    td3.innerHTML = wxt[a];
	    td4.innerHTML = djt[a];
	    td5.innerHTML = dxt[a];
	    td6.innerHTML = bhgt[a];
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	var prds_2 = new Array(
		    <s:iterator id="pr" value="resultList">
			    "<s:property value='#pr.prdid.name'/>",
		    </s:iterator>
	    0); 
	var rkt = new Array(
			<s:iterator id="pr" value="resultList">
				<s:property value="#pr.rkt"/>,
			</s:iterator>
		0);
	var ckt = new Array(
			<s:iterator id="pr" value="resultList">
				<s:property value="#pr.ckt"/>,
			</s:iterator>
		0);
	var kct = new Array(
			<s:iterator id="pr" value="resultList">
				<s:property value="#pr.kct"/>,
			</s:iterator>
		0);

	function createrow_2(mytable_2,a)
	{
		var tr = mytable_2.insertRow(1);  		        
	    var td1 = tr.insertCell(-1);
	    var td2 = tr.insertCell(-1);
	    var td3 = tr.insertCell(-1);
	    var td4 = tr.insertCell(-1);

	    td1.innerHTML = prds_2[a];
	    td2.innerHTML = rkt[a];
	    td3.innerHTML = ckt[a];
	    td4.innerHTML = kct[a];
	}
	function firstpage_2()
	{
		var mytable_2 = document.getElementById("mytable_2");		
		while(mytable_2.rows.length>1){
			mytable_2.deleteRow(mytable_2.rows.length-1);
		}
		var stopnum_2;
		if(prds_2.length<=perpage+1){
			stopnum_2 = prds.length-1;		
		}else{
			stopnum_2 = perpage;			
		}	
		for(var a=0;a<stopnum_2;a++){
			createrow_2(mytable_2,a);
		}
		var current_2 = document.getElementById("current_2");
		current_2.value = 1;
		var sum_2 = document.getElementById("sum_2");
		sum_2.value = Math.ceil((prds_2.length-1)/perpage);
		
		var last_2 = document.getElementById("last_2");
		last_2.disabled=true;
		var next_2 = document.getElementById("next_2");
		if(sum_2.value>1){
			next_2.disabled=false;
		}else{
			next_2.disabled=true;
		}
		var myfinal_2 = document.getElementById("final_2");
		if(sum_2.value==0){
			myfinal_2.disabled=true;
		}
		
	}
	function lastpage_2()
	{
		var current_2 = document.getElementById("current_2").value;
		var sum_2 = document.getElementById("sum_2").value;
		var mytable_2 = document.getElementById("mytable_2");
		while(mytable_2.rows.length>1){
			mytable_2.deleteRow(mytable_2.rows.length-1);
		}
		var startnum_2 = (current_2-2)*perpage;
		var stopnum = (current_2-1)*perpage;	
		if(current_2>=2){
		    for(var a=startnum_2;a<stopnum_2;a++){
		    	createrow_2(mytable_2,a);
		    }
		}
		var next_2 = document.getElementById("next_2");
		next.disabled=false;		
		var last_2 = document.getElementById("last_2");
		if(current_2==2){
			last_2.disabled=true;
		}else{			
			last_2.disabled=false;
		}
		document.getElementById("current_2").value--;
	}
	function nextpage_2()
	{
		var current_2 = document.getElementById("current_2").value;
		var sum_2 = document.getElementById("sum_2").value;
		var mytable_2 = document.getElementById("mytable_2");
		while(mytable_2.rows.length>1){
			mytable_2.deleteRow(mytable_2.rows.length-1);
		}
		var startnum_2 = current_2*perpage;
		var stopnum_2;
		if(current_2==sum_2-1){
			stopnum_2 = prds_2.length-1;
	    }else{
	    	stopnum_2 = startnum_2+perpage;
	    }
		for(var a=startnum_2;a<stopnum_2;a++){
			createrow_2(mytable_2,a);
		}
		var last_2 = document.getElementById("last_2");
		last_2.disabled=false;		
		var next_2 = document.getElementById("next_2");
		if(current_2==sum_2-1){
			next_2.disabled=true;
		}else{		
			next_2.disabled=false;
		}
		document.getElementById("current_2").value++;
	}
	function finalpage_2()
	{
		var mytable_2 = document.getElementById("mytable_2");
		while(mytable_2.rows.length>1){
			mytable_2.deleteRow(mytable_2.rows.length-1);
		}
		var sum_2 = document.getElementById("sum_2").value;		
		var startnum_2 = (sum_2-1)*perpage;
		for(var a=startnum_2;a<prds_2.length-1;a++){
			createrow_2(mytable_2,a);
			}	
		document.getElementById("current_2").value = sum_2;
		var last_2 = document.getElementById("last_2");
		if(sum_2!=1){
			last_2.disabled=false;
		}else{
			last_2.disabled=true;
		}
		var next_2 = document.getElementById("next_2");
		next_2.disabled=true;
	}
	
</script>
<s:head/>
</head>
<body>
<h2>库存状态</h2>


<s:form id="myform" theme="simple">
  <table align="center" width="80%" border="0" class="list_table"> 
 <tr>
    <td><s:text name="日期:"/></td>      
	<td><s:datetimepicker theme="simple" name="date" label="选择报表日期" toggleType="explode"/></td>      
	<td><s:text name="仓库:"/></td>
	<td><s:select  theme="simple" name="canku" label="选择仓库名" labelposition="left" multiple="false" 
            list="cangkusList" listKey="id" listValue="name"/>
    </td>
  
    <td align="left">
		  <s:submit value="查看" theme="simple" action="adminliststorage!execute1"/></td>
	<td><input value="打印库存单"  type="submit" onclick="return print()"/></td>
 </tr>
</table>

 <table class="list_table" id="mytable_2" align="center" border="1" width="100%">
 <tr bgcolor="#4A708B">
     <th>产品名称</th>
     <th>入库量(T)</th>
     <th>出库量(T)</th>
     <th>工厂库存(T)</th>
 </tr>
 </table>
 <div align="center">
       <input type="button" id="first_2" value="第一页" onclick="javascript:firstpage_2()"/>
       <input type="button" id="last_2" value="上一页" onclick="javascript:lastpage_2()"/>
       <input type="button" id="next_2" value="下一页" onclick="javascript:nextpage_2()"/>
       <input type="button" id="final_2" value="最后一页" onclick="javascript:finalpage_2()"/>
	       第<input align="MIDDLE" type="text" size="2" readonly="readonly" id="current_2"/>页 &nbsp;
	       共<input align="MIDDLE" type="text" size="2" readonly="readonly" id="sum_2"/>页
	</div>
	<br/>
   <table class="list_table" id="mytable" align="center" border="1" width="100%" >
  <tr bgcolor="#4A708B">
    <th width="25%">产品名称</th>
    <th>内销</th>
    <th>外销</th>
    <th>待检</th>
    <th>定向</th>
    <th>不合格</th>
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
   firstpage_2();
 </script>
</body>
</html>