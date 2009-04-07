<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售发货通知单</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>

<script language = "javascript" >
	
    var count = 0; 
	function setweight(obj,line)
	{
		var w = weights[obj.selectedIndex];
		alert(document.getElementById("weight["+line+"]"));
		document.getElementById("weight["+line+"]").value= w;
	}

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
		var td7 = tr.insertCell();
		var td8 = tr.insertCell();

		var select1 =  document.createElement("select");
		select1.setAttribute("id","deli_canku["+count+"]");
		select1.setAttribute("name","deli_canku["+count+"]");
		
		var select2 = document.createElement("select");
		select2.setAttribute("id","product["+count+"]");
		select2.setAttribute("name","product["+count+"]");
		
		var select3 = document.createElement("select");
		//select3.setAttribute("id","specification["+count+"]");
		//select3.setAttribute("name","specification["+count+"]");
		select3.id = "specification["+count+"]";
		select3.name = "specification["+count+"]";
		
		//select3.onchange = "javascript:setweight(this," + count + ")";
		//select3.se.onchange = "javascript:setweight(this," + count + ")";
		select3.setAttribute("onchange","javascript:alert('hello');");


		
		
		var textfield1 = document.createElement("input");
		textfield1.setAttribute("id","deli_num["+count+"]");
		textfield1.setAttribute("name","deli_num["+count+"]");
		textfield1.setAttribute("size","10");
			
		var textfield2 = document.createElement("input");
		textfield2.setAttribute("id","price["+count+"]");
		textfield2.setAttribute("name","price["+count+"]");
		textfield2.setAttribute("size","10");

		var textfield3 = document.createElement("input");
		textfield3.setAttribute("id","myid["+count+"]");
		textfield3.setAttribute("value",count);

		var textfield4 = document.createElement("input");
		textfield3.setAttribute("id","weight["+count+"]");

		var textfield5 = document.createElement("input");
		textfield3.setAttribute("id","sumweight["+count+"]");

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
		td1.appendChild(textfield3);
		td2.appendChild(select1);
		td3.appendChild(select2);
		td4.appendChild(select3);
		td5.appendChild(textfield1);
		td6.appendChild(textfield4);
		td7.appendChild(textfield5);
		td8.appendChild(textfield2);

		select3.setAttribute("selectedIndex",count);
		select3.onchange = "javascript:setweight(this," + count + ")";
		alert(select3.onchange);
		//eval(select3.onchange);		
		//alert(1);
		//eval("alert(1);");
		

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

	var weights = new Array(1,2,3,4,5,6,6);


</script>
<s:head/>
</head>

<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>销售发货通知单</h2></td>
  </tr>
 </table>
<s:form theme="simple" action="xssalesnotification" onsubmit="return confirmbtn()">
<table  align="center" width="100%">
      <tr>
      <td><s:text name="区域:"/><select id="domains" name="domains">
              <option value="东北区">东北区</option>
	          <option value="华北区">华北区</option>
	          <option value="华中区">华中区</option>  
	          <option value="西南区">西南区</option>
	          <option value="西北区">西北区</option>
	          <option value="华东区">华东区</option>  
	          <option value="华南区">华南区</option>   
         </select>
      </td>    
         <td><s:text name="日期:"/><s:date name="mydate" format="yyyy-MM-dd"/></td>     
            
          <td><s:text name="销售单号:"/><s:textfield name="bno"/></td>
      </tr>
</table>
    
<table align="center" width="100%">
   <tr> 
        <td><s:text name=" 发货单位: "/><s:select id="orgin" name="orgin" multiple="false" 
          label="选择发货单位"  list="fahuoList" listValue="name" listKey="name" />
         </td>
         <td><s:text name=" 合同号: "/><s:textfield  name="cno"/>
         </td>
   </tr>
   <tr>
        <td><s:text name=" 购货单位: "/><s:select name="customer" label="客户名" labelposition="left" multiple="false" 
            list="customList"
            listKey="id" listValue="customName"/></td>
         <td><s:text name="销售类型:"/>
         <select id="saletype" name="saletype">
              <option value="1">内销</option>
	          <option value="2">定向</option>
	          <option value="3">外销</option>
	          <option value="4">不合格</option>            
         </select></td>
        
   </tr>
   <tr> <td><s:text name=" 运输方式: "/><select id="delivertype" name="delivertype">
              <option value="0">公路运输</option>
	          <option value="1">铁路运输</option>
	          <option value="2">海运</option>      
	          <option value="3">自提</option>   
	          <option value="4">其他</option>      
         </select></td>
       
         </tr>
         <tr><td><s:text name=" 结算方式: "/><select id="jstype" name="jstype">
              <option value="0">现金</option>
	          <option value="1">电汇/转账</option>
	          <option value="2">承兑汇票</option>      
         </select></td>
          <td><s:text name=" 备注: "/><s:textarea name="memo"/></td>
         </tr>
</table>
<table  class="list_table" id="tb" align="center" width="80%">
      	<tr bgcolor="#4A708B">
      	   <th width="">ID</th>
      	   <th width="">发货仓库</th>
      	   <th width="">产品</th>
      	   <th width="">规格</th>
      	   <th width="">发货数目</th>
      	   <th width="">单重</th>
      	   <th width="">重量</th>
      	   <th width="">单价</th>
      	   
      	</tr>
      	  <tr>
      	  <td><s:textfield id="myid[0]" value="0"></s:textfield>
          <td><s:select id="deli_canku[0]" name="deli_canku[0]" multiple="false" label="选择发货仓库"
            list="cankuList" listValue="name" listKey="id" /></td>
            
          <td><s:select id="product[0]" name="product[0]" multiple="false" label="选择产品"
          list="productList" listValue="name" listKey="id"/></td>
          
          <td><s:select id="specification[0]" name="specification[0]" multiple="false" label="选择规格"
          list="specificationList" listValue="displayName" listKey="id" onchange="javascript:setweight(this,0)"/></td>

          
          <td><s:textfield size="10"  name="deli_num[0]" label="发货数目" /></td>
          <td><s:textfield size="10" id="weight[0]" name="weight[0]" label="单重" value="0.025"/></td>
          <td><s:textfield size="10" id="sumweight[0]" name="sumweight[0]" label="总重"/></td>
          <td><s:textfield size="10"  name="price[0]" label="单价"/></td>
      </tr>
  </table>
     <input type="button" name="addone" value="新加一条" onclick="insertRecord()"/>
     <input type="button" name="dele" value="删除" onclick="deleteRecord(tb)"/>
   <table border="0" align="center" width="100%">
       <tr>
       <td align="center"><s:text name="审核人: "/><s:property value="%{myshr.description}"/></td>
       <td align="center"><s:text name="内审部: "/><s:property value="%{mynhr.description}"/></td>
       </tr>
       <tr><td align="center"><s:submit value="提交"/>
     </td><td align="center"><s:reset value="重置"/></td></tr>
   </table>
</s:form>
<script language = "javascript" >
  function init(){
	  var obj = document.getElementById("specification[0]");
	  obj.onchange(obj);
  }
  init();
</script>

</body>

</html>