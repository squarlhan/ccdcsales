<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工厂库液体产品入库单</title>
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


function setnumber(obj,line)
{
	
	var n =accDiv(obj.value,0.001).toFixed(4);
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
																							
		var select1 = document.createElement("select");
		select1.setAttribute("id","product["+count+"]");
		select1.setAttribute("name","product["+count+"]");
			
		var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","sumweight["+count+"]");
		textfield2.setAttribute("name","sumweight["+count+"]");
		textfield2.setAttribute("label",count);
		textfield2.setAttribute("size","15");
		textfield2.setAttribute("onchange","javascript:setnumber(this," + textfield2.label + ")");
		textfield2.onchange = function(){setnumber(textfield2,textfield2.label)};
		
		var textfield3 = document.createElement("input");
		textfield3.setAttribute("id","memo["+count+"]");
		textfield3.setAttribute("name","memo["+count+"]");
		textfield3.setAttribute("size","25");

		var textfield5 = document.createElement("input");
		textfield5.setAttribute("id","weight["+count+"]");
		textfield5.setAttribute("name","weight["+count+"]");
		textfield5.type = "hidden";
		
		var textfield4 = document.createElement("input");
		textfield4.setAttribute("id","number["+count+"]");
		textfield4.setAttribute("name","number["+count+"]");
		textfield4.type = "hidden";
	
		var orginpro=document.getElementById("product[0]");
		var orginspe=document.getElementById("specification[0]");
		 for(var i=0;i<orginpro.children.length;i++){
	          var opt=document.createElement("option");
	          opt.value=orginpro.children[i].value;
	          opt.text=orginpro.children[i].text;
	          select1.options.add(opt);
	          }

		td1.appendChild(select1);
		td2.appendChild(textfield2);
		td3.appendChild(textfield3);
		td4.appendChild(textfield4);
		td5.appendChild(textfield5);
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
		for(i=0;i<=count;i++)
		{	
			var sum = document.getElementById("sumweight["+i+"]");
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
			document.myform.action = "cycentrywarehouse!execute1.action";
			return true;
		}
		else
			return false;
	}
	
	function   previewPrint(){   
		  WB.ExecWB(7,1)   
		  }   
		    function   setPrint(){   
		  WB.ExecWB(8,1);   
		  }
</script>

</head>

<body>

<h2>工厂库液体产品入库单</h2>
  <s:form id="myform"  theme="simple" target="_blank">
  <table class="list_table"  align="center" width="100%" id="tb0">
  	<tr>
  	   <td>请选择入库的工厂库：</td>
       <td><s:select id="orgin" name="orgin" labelposition="left" multiple="false"
            list="orginsList" listValue="name" listKey="id" /></td>
  	</tr>
  </table>
     <table class="list_table"  align="center" width="100%" id="tb">
       <tr bgcolor="#4A708B">
		    <th>选择产品</th>
			<th>填写重量(T)</th>
			<th>备注</th>
			<th style="display:none">袋数</th>
			<th style="display:none">单袋重量</th>
			
	  </tr>
     <tr  align="center" bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
            
       <td><s:select id="product[0]" name="product[0]" labelposition="left" multiple="false"
            list="productsList" listValue="name" listKey="id" /></td>
       
       <td><s:textfield size="15"  id="sumweight[0]" name="sumweight[0]" onchange="javascript:setnumber(this,0)"/></td>
       
       <td><s:textfield size="25"  id="memo[0]" name="memo[0]" /></td>
       
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
 </td>
 </tr>
  </table>
  </s:form>
  <script language="javascript">
  function init(){
	  var obj1 = document.getElementById("sumweight[0]");
	  obj1.onchange(obj1);
  }
  init();
 </script>
</body>
</html>