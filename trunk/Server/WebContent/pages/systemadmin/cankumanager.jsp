<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(cid,cname,ctype,caddress) 
{
    location.href= encodeURI("admincanmanager!modify.action?id="+cid+"&name="+cname+"&address="+caddress+"&type="+ctype); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var cid = (document.getElementById("cid_"+rownum)).value;
		var cname = (document.getElementById("cname_"+rownum)).value;
		var ctype = (document.getElementById("ctype_"+rownum)).value;
		var caddress = (document.getElementById("caddress_"+rownum)).value;
		if(cname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("仓库名不能为空 ");
		else
			if(caddress.replace(/(^\s*)|(\s*$)/g,"") == "")
				alert("仓库地址不能为空 ");
			else
				urlpara_modify(cid,cname,ctype,caddress);
	}
}
function urlpara_delete(cid) 
{
    location.href= encodeURI("admincanmanager!delete.action?id="+cid); 
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
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newcname = (document.getElementById("newcname")).value;
		var newcaddr = (document.getElementById("newcaddr")).value;
		if(newcname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("厂库名不能为空 ");
			return false;
		}
		else
			if(newcaddr.replace(/(^\s*)|(\s*$)/g,"") == "")
			{
				alert("地址名不能为空 ");
				return false;
			}
			else
				return true;
	}else
		return false;
}
</script>
</head>
<body>
<h2 align="center">仓库管理</h2>

  <s:form id="myform" name="myform" action="admincanmanager" theme="simple">
  	<table class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="80">仓库标识</th>
		    <th width="80">仓库名称</th>
		    <th width="80">仓库类型</th>
		    <th width="80">类型设置</th>
		    <th width="80">仓库地址</th>
		    <th width="200">执行操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield size = "10" readonly="true" value="%{#result.id}" id="cid_%{#index.index}" name="cid_%{#index.index}"/></td>

        <td><s:textfield size = "10" id="cname_%{#index.index}" value="%{#result.name}" name="cname_%{#index.index}"/></td>
        
         <td>
         	<s:textfield size = "10" readonly="true" value="%{#result.typeName}" id="ctypeName_%{#index.index}" name="ctypeName_%{#index.index}" />
         </td>
         <td>
			<s:select name="reset_%{#index.index}" id="reset_%{#index.index}" multiple="false" list="#{'0':'储运处','1':'驻厂库','2':'中转库'}" listKey="key" listValue="value"/>
		</td>
          <td><s:textfield size = "16" id="caddress_%{#index.index}" value="%{#result.address}" name="caddress_%{#index.index}"/></td>
        <td align="center" width="">

        	<a href="javascript:confirm_modify(${index.index})">修改</a>
        	&nbsp;  	
        	<a href="javascript:confirm_delete(${index.index})">删除</a> 
        </td>
      </tr>
      </s:iterator>
       </table>
  </s:form>
  <s:form theme="simple" onsubmit="return confirm_add()">
	  <table class="list_table" align="center">
		 	<tr>
		 	<td><label>仓库名称:</label></td>
	        <td><s:textfield id="newcname" name="newcname"/></td>
	      </tr>
	      <tr>
	      	<td><label>仓库类型:</label></td>
	         <td>
	         	<select id="newctype" name="newctype">
	         		<option value="0">储运处</option>
	         		<option value="1">驻广库</option>
	         		<option value="2">中转库</option>
	         	</select>
         	</td>
	      </tr>
	      <tr>
	      	<td><label>仓库地址:</label></td>
	        <td><s:textfield id="newcaddr" name="newcaddr"/></td>
	      </tr>
	      <tr>
	      <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="admincanmanager!add"/> </td>
	      </tr>
	      
		</table>
		
  </s:form>
</body>
</html>