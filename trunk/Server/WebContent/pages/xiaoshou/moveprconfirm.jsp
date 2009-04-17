<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品移库确认</title>
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
function setweight(obj,line)
{
	var w = weights[obj.selectedIndex];
	document.getElementById("weight["+line+"]").value= w;
	var c = accDiv((document.getElementById("sumweight["+line+"]").value),w);
	//alert(c);
	if(c!=parseInt(c)){
		document.getElementById("deli_num["+line+"]").value=0;
	 	alert("请重新填写重量"); 
	}else
	document.getElementById("deli_num["+line+"]").value= c;
}

function setnumber(obj,line)
{
	var w = document.getElementById("weight["+line+"]").value;
	var n = accDiv(obj.value,w);
	if(n!=parseInt(n)){
		document.getElementById("deli_num["+line+"]").value=0;
	 	alert("请重新填写重量"); 
	}else
	//alert(c);
	document.getElementById("deli_num["+line+"]").value= n;
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

		var select1 =  document.createElement("select");
		select1.setAttribute("id","deli_canku["+count+"]");
		select1.setAttribute("name","deli_canku["+count+"]");
		
		var select2 = document.createElement("select");
		select2.setAttribute("id","product["+count+"]");
		select2.setAttribute("name","product["+count+"]");
		
		var select3 = document.createElement("select");
		select3.setAttribute("id","specification["+count+"]");
		select3.setAttribute("name","specification["+count+"]");
		select3.setAttribute("onchange","javascript:setweight(this," + count + ")");
		select3.onchange = function(){setweight(select3,count)};		
		
		var textfield1 = document.createElement("input");
		textfield1.setAttribute("id","deli_num["+count+"]");
		textfield1.setAttribute("name","deli_num["+count+"]");
		textfield1.setAttribute("size","10");
		textfield1.type = "hidden";

		var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","weight["+count+"]");
		textfield2.setAttribute("size","10");
		textfield2.type = "hidden";

		var textfield3 = document.createElement("input");
		textfield3.setAttribute("id","sumweight["+count+"]");
		textfield3.setAttribute("name","sumweight["+count+"]");
		textfield3.setAttribute("size","10");
		textfield3.setAttribute("onchange","javascript:setnumber(this," + count + ")");
		textfield3.onchange = function(){setnumber(textfield3,count)};	

		var orgincan=document.getElementById("deli_canku[0]");	
		var orginpro=document.getElementById("product[0]");
		var orginspe=document.getElementById("specification[0]");
		
		 for(var k=0;k<orgincan.children.length;k++){
             var opt3 = document.createElement("option");
             opt3.value = orgincan.children[k].value;
             opt3.text = orgincan.children[k].text;
             select1.options.add(opt3);             
            }
			
		 for(var i=0;i<orginpro.children.length;i++){
	          var opt=document.createElement("option");
	          opt.value=orginpro.children[i].value;
	          opt.text=orginpro.children[i].text;
	          select2.options.add(opt);
	          }
		 for(var j=0;j<orginspe.children.length;j++){
	          var opts=document.createElement("option");
	          opts.value=orginspe.children[j].value;
	          opts.text=orginspe.children[j].text;
	          select3.options.add(opts);
	          }
         
		td1.appendChild(select1);
		td2.appendChild(select2);
		td3.appendChild(select3);
		td4.appendChild(textfield3);
		td5.appendChild(textfield2);
		td6.appendChild(textfield1);
		select3.onchange(select3,count);
		textfield3.onchange(textfield3,count);
	

	}
	function deleteRecord(table){
		if(table.rows.length>2){
			table.deleteRow(table.rows.length-1);
			count--;
		}

	}
	function confirmbtn()
	{
		var cf = confirm("确认提交？");
		if(cf)
		{
			document.myform.action="xsmoveproduct.action";
			return true;
		}
		else
			return false;
	}
	function confirmPrintbtn()
	{
		var cf = confirm("确认打印？");
		if(cf)
		{
			document.myform.action="xsmoveproduct!print.action";
			return true;
		}
		else
			return false;
	}
	var weights = new Array(
			<s:iterator id="result" value="specificationList">
				<s:property value="#result.weight"/>,
			</s:iterator>
		0); 

</script>
<s:head/>
</head>

<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>产品移库确认</h2></td>
  </tr>
 </table>
<s:form theme="simple" id="myform" target="_blank">
<table align="center" width="100%">
        <tr>
          <td align="left"><s:text name="发送日期:"/></td>
          <td align="left"><s:datetimepicker name="sendtime"  toggleType="explode" value="today"/></td> 
          <td align="left"><s:text name="编号:"/></td>
          <td align="left"><s:textfield name="bno"/></td>
        </tr>
       
        <tr>
         <td align="left"><s:text name="发货单位:"/></td>
         <td align="left"><s:select id="orgin" name="orgin" multiple="false" 
          label="选择发货单位"  list="fahuoList" listValue="name" listKey="name" /></td>
         <td align="left"><s:text name="到站:"/></td>
         <td align="left"><s:textfield name="aim"/></td>
        </tr>
        
        
      <tr>
         <td align="left"><s:text name="运输方式:"/></td>
         <td align="left"><select id="delivertype" name="delivertype">
              <option value="0">公路运输</option>
	          <option value="1">铁路运输</option>
	          <option value="2">海运</option>      
	          <option value="3">自提</option>   
	          <option value="4">其他</option>  
         </select></td>   
         <td align="left"><s:text name="到货仓库:"/></td>
         <td align="left"><s:select id="aimcanku" name="aimcanku" multiple="false" label="选择目标仓库"
            list="cankuList" listValue="name" listKey="id" /></td>      
      </tr>
      
       <tr>
         <td align="left"><s:text name="客户名称:"/></td>
         <td align="left">
         <s:select name="custom" label="客户名" labelposition="left" multiple="false" 
            list="customList"
            listKey="id" listValue="customName"/></td>
          <td align="left"><s:text name="现行价:"/></td>
          <td align="left"><s:textfield name="price"/></td>        
        </tr>
        <tr><td align="left"><s:text name="销售类型:"/></td>
        <td align="left">
         <select id="saletype" name="saletype">
              <option value="1">内销</option>
	          <option value="2">定向</option>
	          <option value="3">外销</option>
	          <option value="4">不合格</option>            
         </select></td>
         <td align="left"><s:text name="备注:"/></td>
          <td align="left"><s:textarea name="memo"/></td></tr>
</table>
<table  class="list_table" id="tb" align="center" width="80%">
      	<tr bgcolor="#4A708B">
      	   <th width="10%">发货仓库</th>
      	   <th width="15%">产品</th>
      	   <th width="15%">规格</th>
      	   <th width="10%">重量(T)</th>
      	   <th width="20%" style="display:none">单重</th>
      	   <th width="30%" style="display:none">发货数目</th>
      	</tr>
      <tr>
          <td><s:select id="deli_canku[0]" name="deli_canku[0]" multiple="false" label="选择发货仓库"
            list="cankuList" listValue="name" listKey="id" /></td>
            
          <td><s:select id="product[0]" name="product[0]" multiple="false" label="选择产品"
          list="productList" listValue="name" listKey="id"/></td>
          
          <td><s:select id="specification[0]" name="specification[0]" multiple="false" label="选择规格"
          list="specificationList" listValue="displayName" listKey="id" onchange="javascript:setweight(this,0)"/></td>
          <td><s:textfield size="10" name="sumweight[0]" id="sumweight[0]" label="发货总重" onchange="javascript:setnumber(this,0)"/></td>
          <td style="display:none"><s:textfield size="10" name="weight[0]" id="weight[0]" label="单个重量" value="0.025"/></td>
          <td style="display:none"><s:textfield size="10" name="deli_num[0]" id="deli_num[0]" label="发货数目"/></td>
   
      </tr>
</table>
     <input type="button" name="addone" value="新加一条" onclick="insertRecord()"/>
     <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>
   <table border="0" align="center" width="100%">
   		<tr>
  		<td align="center">
			<input value="提交"  type="submit" name="Submit" onclick="return confirmbtn()"/>
			<s:reset value="重置" />
			<input value="打印表单"  type="submit" name="Submit" onclick="return confirmPrintbtn()"/>
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