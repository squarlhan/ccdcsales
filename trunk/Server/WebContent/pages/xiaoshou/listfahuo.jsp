<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发货单管理</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript">

function confirm_delete(bno)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		urlpara_delete(bno);
	}
}


function urlpara_delete(bno) 
{
    location.href= encodeURI("xslistxsfh!delete.action?id="+bno); 
} 

//下面开始分页
 
    var perpage = 10;
	
	var orgs = new Array(
		    <s:iterator id="result" value="xsfhlist">
			    "<s:property value='#result.canku.name'/>",
		    </s:iterator>
	    0); 
	var prds = new Array(
			<s:iterator id="result" value="xsfhlist">
				"<s:property value='#result.product.name'/>",
			</s:iterator>
		0);
	var wgts = new Array(
			<s:iterator id="result" value="xsfhlist">
				<s:property value="#result.number*#result.specification.weight"/>,
			</s:iterator>
		0);
	var spfs = new Array(
			<s:iterator id="result" value="xsfhlist">
				"<s:property value='#result.specification.displayName'/>",
			</s:iterator>
		0);
	var aims = new Array(
		    <s:iterator id="result" value="xsfhlist">
			    "<s:property value='#result.xsfahuoxx.customer.customName'/>",
		    </s:iterator>
	    0);	
	var bnos = new Array(
			<s:iterator id="result" value="xsfhlist">
				"<s:property value='#result.id'/>",
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

	    var a1 = document.createElement("a");
		a1.setAttribute("href","javascript:confirm_delete("+bnos[a]+")");
		a1.innerHTML = "删除";
	    
	    td1.innerHTML = orgs[a];
	    td2.innerHTML = prds[a];
	    td3.innerHTML = wgts[a];
	    td4.innerHTML = spfs[a];
	    td5.innerHTML = aims[a];
	    td6.appendChild(a1);
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
<h2>发货单管理</h2>
<s:form theme="simple" action="xslistxsfh.action">
  
     <table id="mytable" class="list_table"  align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="15%">源仓库</th>
		    <th width="15%">产品名</th>
		    <th width="15%">重量(T)</th>
		    <th width="15%">规格</th>
		    <th width="15%">客户</th>
		    <th width="15%">操作</th>
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