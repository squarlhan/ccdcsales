<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
function xiaoshuo(arg1,arg2){
	alert("arg1: "+arg1);
    var t,t0,t1,r1;
    try
    {
    	t0=arg1.toString().split(".")[0];
        t1=arg1.toString().split(".")[1];
    }catch(e){}
    alert("t1: "+t1);
    if(t1.length<=arg2)
    with(Math){
        r1=arg1;
    }else{
        t=t1.substring(0,3);
        r1=Number(t0+"."+t);
    }
    alert("r1: "+r1);
}
	function confirmbtn()
	{
		var cf = confirm("确认入库？");
		if(cf)
			return true;
		else
			return false;
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


	function urlpara_delete(cid) 
	{
	    location.href= encodeURI("cyccheckinwarehouse!delete.action?id="+cid); 
	} 

	//下面开始分页
	 
    var perpage = 10;

    var cids = new Array(
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
    var wts = new Array(
		    <s:iterator id="result" value="resultList">
			    <s:property value="#result.specifications.weight*#result.number"/>,
		    </s:iterator>
	    0); 	
	
	function createrow(mytable,a)
	{
		if(a>=10)a=a%10;
		var tr = mytable.insertRow(1);  		        
	    var td1 = tr.insertCell(-1);
	    var td2 = tr.insertCell(-1);
	    var td3 = tr.insertCell(-1);
	    var td4 = tr.insertCell(-1);
	    var td5 = tr.insertCell(-1);
	    var td6 = tr.insertCell(-1);
	    var td7 = tr.insertCell(-1);
	    
	    var textfield1 = document.createElement("input");
		textfield1.setAttribute("name","resultList["+a+"].Id");
		textfield1.setAttribute("id","cid_"+a);
		textfield1.setAttribute("size","10");
		textfield1.onfocus=function(){textfield1.blur();};
		textfield1.value = cids[a];
		textfield1.onfocus=function(){textfield1.blur()};

		var select1 = document.createElement("select");
		select1.setAttribute("name","resultList["+a+"].saleType");
		var option1 = document.createElement("option");
		var option2 = document.createElement("option");
		var option3 = document.createElement("option");
		var option4 = document.createElement("option");
		option1.value = "0";
		option1.text = "请选择";
		option2.value = "1";
		option2.text = "内销";
		option3.value = "2";
		option3.text = "定向";
		option4.value = "3";
		option4.text = "外销";
		select1.options.add(option1);
		select1.options.add(option2);
		select1.options.add(option3);
		select1.options.add(option4);
		
		var select2 = document.createElement("select");
		select2.setAttribute("name","resultList["+a+"].status");
		var option5 = document.createElement("option");
		var option6 = document.createElement("option");
		var option7 = document.createElement("option");
		option5.value = "0";
		option5.text = "待检";
		option6.value = "1";
		option6.text = "合格";
		option7.value = "2";
		option7.text = "不合格";
		select2.options.add(option5);
		select2.options.add(option6);
		select2.options.add(option7);
		
		var a1 = document.createElement("a");
		a1.setAttribute("href","javascript:confirm_delete("+a+")");
		a1.innerHTML = "删除";
		
		td1.appendChild(textfield1);
		td2.innerHTML = prds[a];
		td3.innerHTML = pchs[a];
		td4.innerHTML = wts[a];
		td5.appendChild(select1);
		td6.appendChild(select2);
		td7.appendChild(a1);
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
<h2>欢迎进入大成仓储管理信息系统储运处管理页面！</h2>
<s:form theme="simple" action="cyccheckinwarehouse" onsubmit="return confirmbtn()">
  <table class="list_table" id="mytable" align="center" width="640"> 
		<tr bgcolor="#4A708B">
		    <th>编号</th>
		    <th>产品名</th>
		    <th>批号</th>
		    <th>重量(T)</th>
			<th>销售类型</th>
			<th>是否合格</th>
		    <th>操作</th>
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
					<s:submit disabled="flag" value="检查产品入库" />
				</td>
			</tr>
			</table>

 
   </s:form>
   <script language="javascript">
   firstpage();
 </script>
</body>
</html>