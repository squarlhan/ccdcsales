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
	
    var count = 0; 

	function insertRecord(){
        count+=1;
        var tb = document.getElementById("tb");
        var tr = tb.insertRow();  
        
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();

		var select1 =  document.createElement("select");
		select1.setAttribute("id","deli_canku["+count+"]");
		select1.setAttribute("name","deli_canku["+count+"]");
		
		var select2 = document.createElement("select");
		select2.setAttribute("id","product["+count+"]");
		select2.setAttribute("name","product["+count+"]");
		
		var select3 = document.createElement("select");
		select3.setAttribute("id","specification["+count+"]");
		select3.setAttribute("name","specification["+count+"]");

		
		
		var textfield1 = document.createElement("input");
		textfield1.setAttribute("id","deli_num["+count+"]");
		textfield1.setAttribute("name","deli_num["+count+"]");
			

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
		td4.appendChild(textfield1);
	

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
			return true;
		}
		else
			return false;
	}

</script>
<s:head/>
</head>

<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>产品移库确认</h2></td>
  </tr>
 </table>
<s:form theme="simple" action="xsmoveproduct" onsubmit="return confirmbtn()">
<table  class="list_table" align="center" width="100%">
        <tr>
          <td><s:text name="发送日期:"/></td>
          <td><s:datetimepicker name="sendtime"  toggleType="explode" value="today"/></td> 
          <td align="left"><s:text name="编号:"/></td>
          <td align="left"><s:textfield name="bno"/></td>
        </tr>
       
        <tr>
         <td align="left"><s:text name="发货单位:"/></td>
         <td><s:select id="orgin" name="orgin" multiple="false" 
          label="选择发货单位"  list="fahuoList" listValue="name" listKey="name" /></td>
         <td align="left"><s:text name="到站:"/></td>
         <td align="left"><s:textfield name="aim"/></td>
        </tr>
        
        
      <tr>
         <td><s:text name="运输方式:"/></td>
         <td width=""><select id="delivertype" name="delivertype">
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
         <td width="">
         <s:select name="custom" label="客户名" labelposition="left" multiple="false" 
            list="customList"
            listKey="id" listValue="customName"/></td>
          <td align="left"><s:text name="现行价:"/></td>
          <td align="left"><s:textfield name="price"/></td>        
        </tr>
        <tr><td><s:text name="销售类型:"/></td>
        <td>
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
      	   <th width="">发货仓库</th>
      	   <th width="">产品</th>
      	   <th width="">规格</th>
      	   <th width="">发货数目</th>
      	</tr>
      <tr>
          <td><s:select id="deli_canku[0]" name="deli_canku[0]" multiple="false" label="选择发货仓库"
            list="cankuList" listValue="name" listKey="id" /></td>
            
          <td><s:select id="product[0]" name="product[0]" multiple="false" label="选择产品"
          list="productList" listValue="name" listKey="id"/></td>
          
          <td><s:select id="specification[0]" name="specification[0]" multiple="false" label="选择规格"
          list="specificationList" listValue="displayName" listKey="id"/></td>

          <td><s:textfield size="10" name="deli_num[0]" label="发货数目"/></td>
   
      </tr>
</table>
     <input type="button" name="addone" value="新加一条" onclick="insertRecord()"/>
     <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>
     <s:submit value="提交"/>
     <s:reset value="重置"/>
   <table align="center" width="100%">
  
   </table>
</s:form>
</body>
</html>