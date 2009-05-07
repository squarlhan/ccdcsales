<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处库存</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript">
	function print(){
		document.myform.action = "cycprintwarehouse!print.action";
		return true;
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
	var wgts = new Array(
			<s:iterator id="result" value="resultList">
				<s:property value="(#result.number)*(#result.specifications.weight)"/>,
			</s:iterator>
		0);
	var stps = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.saleTypeName'/>",
			</s:iterator>
		0);
	var stus = new Array(
			<s:iterator id="result" value="resultList">
				"<s:property value='#result.statusName'/>",
			</s:iterator>
		0);

	function firstpage()
	{
		var mytable = document.getElementById("mytable");		
		while(mytable.rows.length>1){
			mytable.deleteRow(mytable.rows.length-1);
		}
		if(pchs.length<=perpage+1){
			for(var a=0;a<pchs.length-1;a++){
				var tr = mytable.insertRow(1);  		        
				var td1 = tr.insertCell(-1);
				var td2 = tr.insertCell(-1);
				var td3 = tr.insertCell(-1);
				var td4 = tr.insertCell(-1);
				var td5 = tr.insertCell(-1);
				var td6 = tr.insertCell(-1);
				td1.innerHTML = pchs[a];
				td2.innerHTML = prds[a];
				td3.innerHTML = spfs[a];
				td4.innerHTML = wgts[a];
				td5.innerHTML = stps[a];
				td6.innerHTML = stus[a];
			}		
		}else{
			for(var a=0;a<perpage;a++){
				var tr = mytable.insertRow(1);  		        
				var td1 = tr.insertCell(-1);
				var td2 = tr.insertCell(-1);
				var td3 = tr.insertCell(-1);
				var td4 = tr.insertCell(-1);
				var td5 = tr.insertCell(-1);
				var td6 = tr.insertCell(-1);
				td1.innerHTML = pchs[a];
				td2.innerHTML = prds[a];
				td3.innerHTML = spfs[a];
				td4.innerHTML = wgts[a];
				td5.innerHTML = stps[a];
				td6.innerHTML = stus[a];
			}
		}		
		var current = document.getElementById("current");
		current.value = 1;
		var sum = document.getElementById("sum");
		sum.value = Math.ceil((pchs.length-1)/perpage);
		
		var last = document.getElementById("last");
		last.disabled=true;
		var next = document.getElementById("next");
		if(sum!=1){
			next.disabled=false;
		}else{
			next.disabled=true;
		}
		
	}
	function lastpage()
	{
		
		
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
		if(current+1==sum){
			stopnum = pchs.length-1;
	    }else{
	    	stopnum = startnum+10;
	    }
		alert(sum);
	    alert(stopnum);
		for(var a=startnum;a<stopnum;a++){
		    var tr = mytable.insertRow(1);  		        
		    var td1 = tr.insertCell(-1);
		    var td2 = tr.insertCell(-1);
		    var td3 = tr.insertCell(-1);
		    var td4 = tr.insertCell(-1);
		    var td5 = tr.insertCell(-1);
		    var td6 = tr.insertCell(-1);
		    td1.innerHTML = pchs[a];
		    td2.innerHTML = prds[a];
		    td3.innerHTML = spfs[a];
		    td4.innerHTML = wgts[a];
		    td5.innerHTML = stps[a];
		    td6.innerHTML = stus[a];
		}
		var last = document.getElementById("last");
		last.disabled=false;
		document.getElementById("current").value++;
		var next = document.getElementById("next");
		if(current+1==sum){
			next.disabled=true;
		}else{
			next.disabled=false;
		}
	}
	function finalpage()
	{
		var mytable = document.getElementById("mytable");
		while(mytable.rows.length>1){
			mytable.deleteRow(mytable.rows.length-1);
		}
		var sum = document.getElementById("sum").value;		
		if(sum>1){
			var startnum = (sum-1)*perpage;
			if(startnum!=0){
			    for(var a=startnum;a<pchs.length-1;a++){
				    var tr = mytable.insertRow(1);  		        
				    var td1 = tr.insertCell(-1);
				    var td2 = tr.insertCell(-1);
				    var td3 = tr.insertCell(-1);
				    var td4 = tr.insertCell(-1);
				    var td5 = tr.insertCell(-1);
				    var td6 = tr.insertCell(-1);
				    td1.innerHTML = pchs[a];
				    td2.innerHTML = prds[a];
				    td3.innerHTML = spfs[a];
				    td4.innerHTML = wgts[a];
				    td5.innerHTML = stps[a];
				    td6.innerHTML = stus[a];
				}
		    }
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
<h2>储运处库存状态</h2>
<s:form id="myform" theme="simple">
<table class="list_table"  align="center" width="100%" >
 <tr>      
	    <td width=""><s:datetimepicker name="mydate" label="选择报表日期" toggleType="explode" theme="simple"/></td>      
	    <td width="">
				<s:submit value="查库存" theme="simple" action="cyclistallstorage!getInfoByDate" />
				<s:submit value="查入库单" theme="simple" action="cyccheckin!getInfoByDate"/>
				<s:submit value="查出库单" theme="simple" action="cyccheckout!getInfoByDate"/>
				<input value="打印库存单"  type="submit" onclick="return print()"/>
		</td>

 </tr>
</table>


  
     <table class="list_table"  align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="25%">产品名</th>
		    <th width="15%">内销</th>
		    <th width="15%">外销</th>
		    <th width="15%">定向</th>
		    <th width="15%">待检</th>
			<th width="15%">不合格</th>
		</tr>
		<s:iterator id="result" value="pmxList" status="index">		
			<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	           <td align="center">
					<s:property value="#result.prdid.name" />
			   </td>
			   <td align="center">
					<s:property value="#result.nxt" />
			   </td>
			   <td align="center">
					<s:property value="#result.wxt" />
			   </td>
			   <td align="center">
					<s:property value="#result.dxt" />
			   </td>
			   <td align="center">
					<s:property value="#result.djt" />
			   </td>
			   <td align="center">
					<s:property value="#result.bhgt" />
			   </td>
	        </tr>
	    </s:iterator>
	</table>

     <!--<table class="list_table"  align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="20%">批号</th>
		    <th width="20%">产品名</th>
		    <th width="15%">规格</th>
		    <th width="10%">重量</th>
			<th width="10%">销售类型</th>
			<th width="10%">状态</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
			<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	           <td align="center">
					<s:property value="#result.id.pch" />
			   </td>
			   <td align="center">
					<s:property value="#result.products.name" />
			   </td>
			   <td align="center">
					<s:property value="#result.specifications.displayName" />
			   </td>
			   <td align="center">
					<s:property value="(#result.number)*(#result.specifications.weight)" />
			   </td>
			   <td align="center">
					<s:property value="#result.saleTypeName" />
			   </td>
			   <td align="center">
					<s:property value="#result.statusName" />
			   </td>
	        </tr>
	        
	    </s:iterator>
	</table>-->
	<table id="mytable" class="list_table"  align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="20%">批号</th>
		    <th width="20%">产品名</th>
		    <th width="15%">规格</th>
		    <th width="10%">重量</th>
			<th width="10%">销售类型</th>
			<th width="10%">状态</th>
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