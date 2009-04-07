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
		
		var select1 = document.createElement("select");
		select1.setAttribute("id","product["+count+"]");
		select1.setAttribute("name","product["+count+"]");
		
		var select2 = document.createElement("select");
		select2.setAttribute("id","specification["+count+"]");
		select2.setAttribute("name","specification["+count+"]");
		
		var select3 = document.createElement("select");
		select3.setAttribute("id","pch["+count+"]");
		select3.setAttribute("name","pch["+count+"]");
			
		var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","number["+count+"]");
		textfield2.setAttribute("name","number["+count+"]");
			
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
		td4.appendChild(textfield2);

	}
	function deleteRecord(table){
		if(table.rows.length>2){
			table.deleteRow(table.rows.length-1);
			count--;
		}

	}
	function checkNull()
	{
		var ele = document.getElementById("bno");
		if(ele.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
		  alert("请输入编号!");
		  return false;
		}
		if(ele.value.length>12)
		{
		  alert("编号应不超过12位!");
		  return false;
		}
		for(i=0;i<=count;i++)
		{	
			var num = document.getElementById("number["+i+"]");
			if(num.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行袋数!");
				return false;
			}
			if(isNaN(num.value))
			{
				alert("第"+(i+1)+"行袋数应为数字！");
				return false;
			} 
		}
		if(confirm("确认装车？"))
			return true;
		else
			return false;
	}
</script>
</head>
<body>
<h2>储运处产成品装车明细</h2>
<h3>储运处可用产品明细</h3>
<table class="list_table"  align="center" width="640">
  <tr bgcolor="#4A708B">
	<th width="25%">产品批次号</th>
	<th width="20%">产品名</th>
	<th width="20%">规格</th>
	<th width="10%">数目</th>
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
		<s:property value="#result.number" />
	  </td>
	  <td align="center">
		<s:property value="#result.saleTypeName" />
	  </td>
    </tr>
  </s:iterator>
</table>


  <s:form id="myform" action="cycdeliverywarehouse!sale" onsubmit="return checkNull()">
  
  
  <table class="list_table"  id="tbt" align="center" width="100%">
   <tr>
           <!-- <td><s:text name="请选择目标仓库"></s:text>
  	       </td>
  	       <td><s:select name="cankuaim" list="cankusList" label="请选择目标仓库"
  	       labelposition="left" multiple="false" listKey="id" listValue="name" theme="simple" headerValue="--请选择 --"/>
  	       </td>  -->
  	       <td><s:text name="请选择客户:"></s:text></td>
  	       <td><s:select id="custom" name="custom" label="请选择客户" labelposition="left" multiple="false"
            list="customList" listValue="customName" listKey="id" theme="simple"/></td>
            <td><s:text name="请填写编号:"></s:text></td>
  	       <td><s:textfield label="请填写编号" name="bno" size="15" theme="simple"></s:textfield>
  	       </td>
  	</tr>
  </table>
  	<table class="list_table"  id="tb" align="center" width="100%">
  	
		<tr bgcolor="#4A708B">
		    <th width="120">选择产品</th>
		    <th width="120">选择规格</th>
		    <th width="120">填写批号</th>
			<th width="120">填写袋数</th>
		</tr>

     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
        <td><s:select id="product[0]" name="product[0]" label="请选择产品名" labelposition="left" multiple="false"
            list="productsList" listValue="name" listKey="id" theme="simple"/></td>

        <td><s:select id="specification[0]" name="specification[0]" label="请选择产品规格" labelposition="left" multiple="false"
            list="specificationsList" listValue="displayName" listKey="id" theme="simple"/></td>

        <td><s:select id="pch[0]" name="pch[0]" label="请填写批号" labelposition="left" multiple="false"
            list="pchList"  theme="simple"/></td>

        <td><s:textfield id="number[0]" label="请填写袋数" name="number[0]" theme="simple"/></td>

      </tr>
     </table><div align="center">
       <input type="button" name="addone" value="新加一条" onclick="insertRecord()"/>
       <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>
       <s:submit value="装车" theme="simple"/>
       <s:reset value="取消" theme="simple"/>
</div>
  </s:form>
</body>
</html>