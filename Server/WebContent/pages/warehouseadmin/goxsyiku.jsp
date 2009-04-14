<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处产成品装车明细</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
function accDiv(arg1,arg2){
    var t1=0,t2=0,r1,r2;
    try{t1=arg1.toString().split(".")[1].length}catch(e){}
    try{t2=arg2.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""))
        r2=Number(arg2.toString().replace(".",""))
        return (r1/r2)*pow(10,t2-t1);
    }
}
function   ForDight(Dight,How)   
{   
  Dight   =   Math.round   (Dight*Math.pow(10,How))/Math.pow(10,How);   
  return   Dight;   
}
function setweight(obj,line)
{
	var w = weights[obj.selectedIndex];
	document.getElementById("weight["+line+"]").value= w;
	var c = ForDight(accDiv((document.getElementById("sumweight["+line+"]").value),w),3);
	//alert(c);
	if(c!=parseInt(c)){
		document.getElementById("number["+line+"]").value=0;
	 	alert("请重新填写重量"); 
	}else
	document.getElementById("number["+line+"]").value= c;
}

function setnumber(obj,line)
{
	var w = document.getElementById("weight["+line+"]").value;
	var n = ForDight(accDiv(obj.value,w),3);
	if(n!=parseInt(n)){
		document.getElementById("number["+line+"]").value=0;
	 	alert("请重新填写重量"); 
	}else
	//alert(c);
	document.getElementById("number["+line+"]").value= n;
}

    var count = 0; 

	function insertRecord(){
        count+=1;
        var tb = document.getElementById("tb");
        var tr = tb.insertRow();  
        
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		var td6 = tr.insertCell();
		
		var select1 = document.createElement("select");
		select1.setAttribute("id","product["+count+"]");
		select1.setAttribute("name","product["+count+"]");
		
		var select2 = document.createElement("select");
		select2.setAttribute("id","specification["+count+"]");
		select2.setAttribute("name","specification["+count+"]");
		select2.onchange = function(){setweight(select2,count)};	
		
		var select3 = document.createElement("select");
		select3.setAttribute("id","pch["+count+"]");
		select3.setAttribute("name","pch["+count+"]");
			
	/*	var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","number["+count+"]");
		textfield2.setAttribute("name","number["+count+"]");*/

		var textfield3 = document.createElement("input");
		textfield3.setAttribute("id","sumweight["+count+"]");
		textfield3.setAttribute("name","sumweight["+count+"]");
		textfield3.setAttribute("size","15");
		textfield3.setAttribute("onchange","javascript:setnumber(this," + count + ")");
		textfield3.onchange = function(){setnumber(textfield3,count)};

		var textfield4 = document.createElement("input");
		textfield4.setAttribute("id","weight["+count+"]");
		textfield4.setAttribute("name","weight["+count+"]");
		textfield4.type = "hidden";
		
		var textfield5 = document.createElement("input");
		textfield5.setAttribute("id","number["+count+"]");
		textfield5.setAttribute("name","number["+count+"]");
		textfield5.type = "hidden";
		
		var orginpro=document.getElementById("product[0]");
		var orginspe=document.getElementById("specification[0]");
		var orginpch=document.getElementById("pch[0]");
		 for(var i=0;i<orginpro.children.length;i++){
	          var opt=document.createElement("option");
	          opt.value=orginpro.children[i].value;
	          opt.text=orginpro.children[i].text;
	          select1.options.add(opt);
	          }
		 for(var j=0;j<orginspe.children.length;j++){
	          var opts=document.createElement("option");
	          opts.value=orginspe.children[j].value;
	          opts.text=orginspe.children[j].text;
	          select2.options.add(opts);
	          }
		 for(var k=0;k<orginpch.children.length;k++){
	          var optp=document.createElement("option");
	          optp.value=orginpch.children[k].value;
	          optp.text=orginpch.children[k].text;
	          select3.options.add(optp);
	          }
		td1.appendChild(select1);
		td2.appendChild(select2);
		td3.appendChild(select3);
		td4.appendChild(textfield3);
		td5.appendChild(textfield4);
		td6.appendChild(textfield5);

		select2.onchange(select2,count);
		textfield3.onchange(textfield3,count);

	}
	function deleteRecord(table){
		if(table.rows.length>2){
			table.deleteRow(table.rows.length-1);
			count--;
		}

	}

	function checkNullandNum()
	{	
		var ele = document.getElementById("bno");
		if(ele.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
		  alert("请输入编号!");
		  return false;
		}
		if(ele.value.length>16)
		{
			alert("编号应不超过16位");
			return false;
		}
		for(i=0;i<=count;i++)
		{	
			var tmp = document.getElementById("number["+i+"]");
			if(tmp.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入袋数!");
				return false;
			}
			if(isNaN(tmp.value))
			{
				alert("只允许输入数字！");
				return false;
			}
		}
		var tnum = 0;
		for(var i=0;i<=count;i++)
			tnum += Number(document.getElementById("number["+i+"]").value);
		if(tnum!=Number(document.getElementById("tnumber").value))
		{
			alert("数量不符");
			
			return false;
		}
		if(confirm("确认装车？"))
			return true;
		else
			return false;
	}
	var weights = new Array(
			<s:iterator id="result" value="specificationsList">
				<s:property value="#result.weight"/>,
			</s:iterator>
		0); 
</script>
</head>
<body>
<h2>储运处产成品装车明细</h2>
<s:form theme="simple">
    <table class="list_table"  align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="25%">产品名</th>
		    <th width="15%">数量</th>
		    <th width="15%">重量</th>
		    <th width="15%">规格</th>
		    <th width="15%">编号</th>
		    <th width="15%">客户</th>
		</tr>
		<s:iterator id="result" value="xsykmx" status="index">		
			<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	           <td align="center">
					<s:property value="#result.product.name" />
			   </td>
			   <td align="center">
					<s:property value="#result.number" />
			   </td>
			   <td align="center">
					<s:property value="(#result.number)*(#result.specification.weight)" />
			   </td>
			   <td align="center">
					<s:property value="#result.specification.displayName" />
			   </td>
			   <td align="center">
					<s:property value="#result.xsyikuxx.bno" />
			   </td>
			   <td align="center">
					<s:property value="#result.xsyikuxx.customer.customName" />
			   </td>
	        </tr>
	    </s:iterator>
	</table>
 </s:form>
<h3>储运处可用产品明细</h3> 
<table class="list_table"  align="center" width="640">
  <tr bgcolor="#4A708B">
	<th width="25%">产品批次号</th>
	<th width="20%">产品名</th>
	<th width="20%">规格</th>
	<th width="10%">重量</th>
	<th width="15%">销售类型</th>
  </tr>
  <s:iterator id="result" value="resultList" status="index">	
    <tr bgcolor='<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>'>
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
    </tr>
  </s:iterator>
</table>
<s:form id="myform" action="cycdeliverywarehouse!yiku" theme="simple" onsubmit="return checkNullandNum()">
  <table class="list_table"  id="tbt" align="center" width="100%">
   <tr>
          <td><s:text name="请选择目标仓库"></s:text>
  	       </td>
  	       <td><s:select name="cankuaim" list="cankusList" label="请选择目标仓库"
  	       labelposition="left" multiple="false" listKey="id" listValue="name" theme="simple" headerValue="--请选择 --"/>
  	       </td>
  	       <td><s:text name="客户："/></td>
  	       <td><s:select id="custom" name="custom" label="客户" labelposition="left" multiple="false"
            list="customList" listValue="customName" listKey="id" /></td>
  	       <td><s:text name="编号："></s:text>
  	       </td>
  	       <td><s:textfield name="bno" theme="simple" size="15"></s:textfield>
  	       </td>
  	</tr>
  </table>
  	<table class="list_table"  id="tb" align="center" width="100%">
  	
		<tr bgcolor="#4A708B">
		    <th width="120">选择产品</th>
		    <th width="120">选择规格</th>
		    <th width="120">填写批号</th>
			<th width="120">填写重量</th>
			<th width="20%" style="display:none">袋数</th>
			<th width="20%" style="display:none">单带重量</th>
		</tr>

     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
        <td><s:select id="product[0]" name="product[0]" label="请选择产品名" labelposition="left" multiple="false"
            list="productsList" listValue="name" listKey="id" /></td>

        <td><s:select id="specification[0]" name="specification[0]" label="请选择产品规格" labelposition="left" multiple="false"
            list="specificationsList" listValue="displayName" listKey="id" onchange="javascript:setweight(this,0)"/></td>

        <td><s:select id="pch[0]" name="pch[0]" label="请填写批号" labelposition="left" multiple="false"
            list="pchList" /></td>

         <td><s:textfield size="15"  id="sumweight[0]" name="sumweight[0]" onchange="javascript:setnumber(this,0)"/></td>
		 <!-- 
        <td><s:textfield id="number[0]" label="请填写袋数" name="number[0]"/></td>
		 -->
		<td style="display:none"><s:textfield id="number[0]" name="number[0]" label="入库数目"/></td>
       
		<td style="display:none"><s:textfield id="weight[0]" name="weight[0]" label="单重"/></td>
      </tr>
      <tr style="display:none"><td><s:textfield name="index" value="%{index}"></s:textfield><td></tr>
      <tr style="display:none"><td><s:textfield name="tnumber" value="%{xsykmx.number}"></s:textfield><td></tr>
     </table>
     <div align="center">
       <input type="button" name="addone" value="新加一条" onclick="insertRecord()"/>
       <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>
       <s:submit value="装车"/>
       <s:reset value="取消"/>
       </div>
  </s:form>
   <script language="javascript">
  function init(){
	  var obj = document.getElementById("specification[0]");
	  obj.onchange(obj);
	  var obj1 = document.getElementById("sumweight[0]");
	  obj1.onchange(obj1);
  }
  init();
 </script>
</body>
</html>