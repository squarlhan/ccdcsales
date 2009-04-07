<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处产成品入库单</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script type="text/javascript">
   
var textNumber = 1; 


	function insertRecord(form, afterElement){
		window.alert("0");
		var select1 = document.createElement("s:select");
		window.alert("1");
		var select2 = document.createElement("s:select");
		var textfield1 = document.createElement("s:textfield");
		var textfield2 = document.createElement("s:textfield");
		var textarea = document.createElement("s:textarea");
		select1.setAttribute("id","productName"+textNumber);
		select1.setAttribute("name","product"+textNumber);
		select1.setAttribute("label","请选择产品名");
		select1.setAttribute("labelposition","left");
		select1.setAttribute("multiple","false");
		select1.setAttribute("list","productsList");
		select1.setAttribute("listValue","name");
		select1.setAttribute("listKey","id");
		select1.setAttribute("headerValue","--请选择 --");
		window.alert("2");
		select2.setAttribute("id","productStandard"+textNumber);
		select2.setAttribute("name","specification"+textNumber);
		select2.setAttribute("label","请选择产品规格");
		select2.setAttribute("labelposition","left");
		select2.setAttribute("multiple","false");
		select2.setAttribute("list","specificationsList");
		select2.setAttribute("listValue","name");
		select2.setAttribute("listKey","id");
		select2.setAttribute("headerValue","--请选择 --");

		textfield1.setAttribute("id","amount"+textNumber);
		textfield1.setAttribute("label","请填写袋数");
		textfield1.setAttribute("name","number"+textNumber);

		textfield2.setAttribute("id","batchNo"+textNumber);
		textfield2.setAttribute("label","请填写批号");
		textfield2.setAttribute("name","pch"+textNumber);

		textarea.setAttribute("id","remarks"+textNumber);
		textarea.setAttribute("label","备注");
		textarea.setAttribute("name","memo"+textNumber);
		window.alert("3");
		form.appendChild(select1);
		form.appendChild(select2);
		form.appendChild(textfield1);
		form.appendChild(textfield2);
		form.appendChild(textarea);
	
		window.alert("4");
		
		return false; 
		
	}
</script>
</head>

<body>

<table class="list_table"  id="myTable" align="center" width="100%">
  <tr align="center">
     <td><h2>储运处产成品入库单</h2></td>
  </tr>
   <tr align="center"><td>
  <s:form id="myForm" action="entrywarehouse">
   
       <s:select id="productName1" name="product1" label="请选择产品名" labelposition="left" multiple="false"
            list="productsList" listValue="name" listKey="id" headerValue="--请选择 --"/>

     <s:select id="productStandard1" name="specification1" label="请选择产品规格" labelposition="left" multiple="false"
            list="specificationsList" listValue="displayName" listKey="id" headerValue="--请选择 --"/>
	 <s:textfield id="amount1" label="请填写袋数" name="number1"/>
       <s:textfield id="batchNo1" label="请填写批号" name="pch1"/>
     <s:textarea id="remarks1" label="备注" name="memo1"/>
      <s:submit value="录入"/>
       <s:submit value="入库"  action="entrywarehouse!complete"/>
       <s:reset value="取消"/>

     <p>     
		<button onclick="insertRecord(this.form,this.parentNode)">增加表单</button>
	</p>
  </s:form>
 </td></tr>

  </table>
</body>
</html>