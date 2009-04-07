<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处产成品入库单</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<%
	//ArrayList list = (ArrayList)request.getSession().getAttribute("productsList");
%>
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
		
		var textfield1 = document.createElement("input");
		textfield1.setAttribute("id","pch["+count+"]");
		textfield1.setAttribute("name","pch["+count+"]");
			
		var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","number["+count+"]");
		textfield2.setAttribute("name","number["+count+"]");
		
		var textfield3 = document.createElement("input");
		textfield3.setAttribute("id","memo["+count+"]");
		textfield3.setAttribute("name","memo["+count+"]");
			
		var orginpro=document.getElementById("product[0]");
		var orginspe=document.getElementById("specification[0]");
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
         
		td1.appendChild(select1);
		td2.appendChild(select2);
		td3.appendChild(textfield1);
		td4.appendChild(textfield2);
		td5.appendChild(textfield3);

	}
	function deleteRecord(table){
		if(table.rows.length>2){
			table.deleteRow(table.rows.length-1);
			count--;
		}

	}
	function checkNull()
	{
		var ele = document.getElementById("wer");
		var ele2 = document.getElementById("rew")
		if(ele.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
		  alert("请输入负责人!");
		  return false;
		}
		if(ele2.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
		  alert("请输入编号!");
		  return false;
		}
		for(i=0;i<=count;i++)
		{	
			var pch = document.getElementById("pch["+i+"]");
			var num = document.getElementById("number["+i+"]");
			if(pch.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行批号!");
				return false;
			}
			if(pch.value.length!=9)
			{
				alert("第"+(i+1)+"行批号应为9位!");
				return false;
			}
			if(num.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行数量!");
				return false;
			}
			if(isNaN(num.value))
			{
				alert("第"+(i+1)+"行袋数应为数字！");
				return false;
			} 
		}
		if(confirm("确认入库？"))
			return true;
		else
			return false;
	}
</script>

</head>

<body>


<h2>储运处产成品入库单</h2>

  <s:form id="myform" action="cycentrywarehouse" theme="simple" onsubmit="return checkNull()">
  <table class="list_table"  align="center" width="320" id="tb0">
  	<tr bgcolor="#4A708B">
  		<th width="160">负责人</th>
		<th width="160">编号</th>
  	</tr>
  	<tr>
  		<td><s:textfield id="wer" name="rkfzr" labelposition="top"/></td>
       <td><s:textfield id="rew" name="bno" labelposition="top"/></td>
  	</tr>
  </table>
     <table class="list_table"  align="center" width="640" id="tb">
       <tr bgcolor="#4A708B">
		    <th width="10%">选择产品</th>
		    <th width="10%">选择规格</th>
		    <th width="20%">填写批号</th>
			<th width="20%">填写袋数</th>
			<th width="20%">备注</th>
			
	  </tr>
     <tr  align="center" bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
       <td><s:select id="product[0]" name="product[0]" labelposition="left" multiple="false"
            list="productsList" listValue="name" listKey="id" /></td>

       <td><s:select id="specification[0]" name="specification[0]" labelposition="left" multiple="false"
            list="specificationsList" listValue="displayName" listKey="id" /></td>

       <td><s:textfield size="15" id="pch[0]" name="pch[0]" /></td>
       
       <td><s:textfield size="15"  id="number[0]" name="number[0]" /></td>
       
       <td><s:textfield size="20"  id="memo[0]" name="memo[0]" /></td>
       
     </tr>
  </table>
 
  <table align="center">
  <tr><td>
    <input type="button" name="addone" value="新加一条" onclick="insertRecord()" />
    <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>
    <s:submit value="入库" theme="simple"/>
    <s:reset value="取消" theme="simple"/>
 </td>
 </tr>
  </table>
  </s:form>
  
</body>
</html>