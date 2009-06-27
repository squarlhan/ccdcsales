<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处液体产品入库单</title>
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
        r1=Number(arg1.toString().replace(".",""));
        r2=Number(arg2.toString().replace(".",""));
        return (r1/r2)*pow(10,t2-t1);
    }
}
function setweight(obj,line)
{
	var w = weights[obj.selectedIndex];
	document.getElementById("weight["+line+"]").value= w;
	var c = accDiv((document.getElementById("sumweight["+line+"]").value),w).toFixed(4);
	if(c!=parseInt(c).toFixed(4)){
		document.getElementById("number["+line+"]").value=0;
	 	alert("请重新填写重量"); 
	}else
	document.getElementById("number["+line+"]").value= parseInt(c);
}

function setnumber(obj,line)
{
	var w = document.getElementById("weight["+line+"]").value;
	var n =accDiv(obj.value,w).toFixed(4);
	if(n!=parseInt(n).toFixed(4)){
		document.getElementById("number["+line+"]").value=0;
	 	alert("请重新填写重量"); 
	}else
	document.getElementById("number["+line+"]").value= parseInt(n);
}

    var count = 0; 

	function insertRecord(){
        count+=1;
        
        var tb = document.getElementById("tb");
        var tr = tb.insertRow(-1);
        
		var td1 = tr.insertCell(-1);
		var td2 = tr.insertCell(-1);
		var td3 = tr.insertCell(-1);
		var td4 = tr.insertCell(-1);
		var td5 = tr.insertCell(-1);
		var td6 = tr.insertCell(-1);
		var td7 = tr.insertCell(-1);
		var td8 = tr.insertCell(-1);

		var select0 = document.createElement("select");
		select0.setAttribute("id","orgins["+count+"]");
		select0.setAttribute("name","orgins["+count+"]");
		
		var select1 = document.createElement("select");
		select1.setAttribute("id","product["+count+"]");
		select1.setAttribute("name","product["+count+"]");
		
		var select2 = document.createElement("select");
		select2.setAttribute("id","specification["+count+"]");
		select2.setAttribute("name","specification["+count+"]");
		select2.setAttribute("label",count);                                                
		select2.setAttribute("onchange","javascript:setweight(this," + select2.label + ")"); 
		select2.onchange = function(){setweight(select2,select2.label)};	                 
																							
		var textfield1 = document.createElement("input");									
		textfield1.setAttribute("id","pch["+count+"]");
		textfield1.setAttribute("name","pch["+count+"]");
		textfield1.setAttribute("size","10");
			
		var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","sumweight["+count+"]");
		textfield2.setAttribute("name","sumweight["+count+"]");
		textfield2.setAttribute("label",count);
		textfield2.setAttribute("size","5");
		textfield2.setAttribute("onchange","javascript:setnumber(this," + textfield2.label + ")");
		textfield2.onchange = function(){setnumber(textfield2,textfield2.label)};
		
		var textfield3 = document.createElement("input");
		textfield3.setAttribute("id","memo["+count+"]");
		textfield3.setAttribute("name","memo["+count+"]");
		textfield3.setAttribute("size","15");

		var textfield5 = document.createElement("input");
		textfield5.setAttribute("id","weight["+count+"]");
		textfield5.setAttribute("name","weight["+count+"]");
		textfield5.type = "hidden";
		
		var textfield4 = document.createElement("input");
		textfield4.setAttribute("id","number["+count+"]");
		textfield4.setAttribute("name","number["+count+"]");
		textfield4.type = "hidden";

		var orginorg=document.getElementById("orgins[0]");	
		var orginpro=document.getElementById("product[0]");
		var orginspe=document.getElementById("specification[0]");
		for(var i=0;i<orginorg.children.length;i++){
	          var opt=document.createElement("option");
	          opt.value=orginorg.children[i].value;
	          opt.text=orginorg.children[i].text;
	          select0.options.add(opt);
	          }
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

		td1.appendChild(select0); 
		td2.appendChild(select1);
		td3.appendChild(select2);
		td4.appendChild(textfield1);
		td5.appendChild(textfield2);
		td6.appendChild(textfield3);
		td7.appendChild(textfield4);
		td8.appendChild(textfield5);
		select2.onchange(select2,count);
		textfield2.onchange(textfield2,count);

	}
	function deleteRecord(table){
		if(table.rows.length>2){
			table.deleteRow(table.rows.length-1);
			count--;
		}

	}
	function checkRKNull()
	{
		var ele2 = document.getElementById("rew")
		if(ele2.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
		  alert("请输入编号!");
		  return false;
		}
		for(i=0;i<=count;i++)
		{	
			var pch = document.getElementById("pch["+i+"]");
			var sum = document.getElementById("sumweight["+i+"]");
			if(pch.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行批号!");
				return false;
			}
			if(pch.value.length>20)
			{
				alert("第"+(i+1)+"行批号不应超过20位!");
				return false;
			}
			if(sum.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行重量!");
				return false;
			}
			if(isNaN(sum.value))
			{
				alert("第"+(i+1)+"行重量应为数字！");
				return false;
			} 
		}
		if(confirm("确认入库操作？")){
			document.myform.action = "cycentrywarehouse.action";
			return true;
		}
		else
			return false;
	}
	function checkPrintNull()
	{
		var ele2 = document.getElementById("rew");
		if(ele2.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
		  alert("请输入编号!");
		  return false;
		}
		for(i=0;i<=count;i++)
		{	
			var pch = document.getElementById("pch["+i+"]");
			var sum = document.getElementById("sumweight["+i+"]");
			if(pch.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行批号!");
				return false;
			}
			if(pch.value.length>20)
			{
				alert("第"+(i+1)+"行批号不应超过20位!");
				return false;
			}
			if(sum.value.replace(/(^\s*)|(\s*$)/g,"")=="")
			{
				alert("请输入第"+(i+1)+"行重量!");
				return false;
			}
			if(isNaN(sum.value))
			{
				alert("第"+(i+1)+"行重量应为数字！");
				return false;
			} 
		}
		if(confirm("确认打印操作？")){
			document.myform.action = "cycentrywarehouse!print.action";
			return true;
		}
		else
			return false;
	}
	var weights = new Array(
			<s:iterator id="result" value="specificationsList">
				<s:property value="#result.weight"/>,
			</s:iterator>
		0); 
	function   previewPrint(){   
		  WB.ExecWB(7,1)   
		  }   
		    function   setPrint(){   
		  WB.ExecWB(8,1);   
		  }
</script>

</head>

<body>

<h2>储运处液体产品入库单</h2>
  <s:form id="myform"  theme="simple" target="_blank">
  <table class="list_table"  align="center" width="100%" id="tb0">
  	<tr>
  		<td><s:text name="编号："/></td>
       <td align="left"><s:textfield id="rew" name="bno" labelposition="top" size="50"/></td>
  	</tr>
  </table>
     <table class="list_table"  align="center" width="100%" id="tb">
       <tr bgcolor="#4A708B">
            <th>工厂库</th>
		    <th>选择产品</th>
		    <th>选择规格</th>
		    <th>填写批号</th>
			<th>填写重量(T)</th>
			<th>备注</th>
			<th style="display:none">袋数</th>
			<th style="display:none">单袋重量</th>
			
	  </tr>
     <tr  align="center" bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
       <td><s:select id="orgins[0]" name="orgins[0]" labelposition="left" multiple="false"
            list="orginsList" listValue="name" listKey="id" /></td>
       
       <td><s:select id="product[0]" name="product[0]" labelposition="left" multiple="false"
            list="productsList" listValue="name" listKey="id" /></td>

       <td><s:select id="specification[0]" name="specification[0]" labelposition="left" multiple="false"
            list="specificationsList" listValue="displayName" listKey="id" onchange="javascript:setweight(this,0)" label="0"/></td>

       <td><s:textfield size="10" id="pch[0]" name="pch[0]" /></td>
       
       <td><s:textfield size="5"  id="sumweight[0]" name="sumweight[0]" onchange="javascript:setnumber(this,0)"/></td>
       
       <td><s:textfield size="15"  id="memo[0]" name="memo[0]" /></td>
       
       <td style="display:none"><s:textfield id="number[0]" name="number[0]" label="入库数目"/></td>
       
       <td style="display:none"><s:textfield id="weight[0]" name="weight[0]" label="单重"/></td>
       
     </tr>
  </table>
  <table  align="center">
  <tr><td>
    <input type="button" name="addone" value="新加一条" onclick="insertRecord()" />
    <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>   
    <input value="入库"  type="submit" name="Submit" onclick="return checkRKNull()"/>
    <s:reset value="取消" theme="simple"/>
    <input value="打印表单"  type="submit" name="Submit" onclick="return checkPrintNull()"/>
 </td>
 </tr>
  </table>
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