<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规格管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(sid,sname,sweight,spack) 
{
    location.href= "adminspemanager!modify.action?id="+sid+"&name="+sname+"&weight="+sweight+"&packtype="+spack; 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var sid = (document.getElementById("sid_"+rownum)).value;
		var sname = (document.getElementById("sname_"+rownum)).value;
		var sweight = (document.getElementById("sweight_"+rownum)).value;
		var spack = (document.getElementById("spackType_"+rownum)).value;
		if(sname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("规格名不能为空 ");
		else
			if(sweight.replace(/(^\s*)|(\s*$)/g,"") == "")
				alert("重量不能为空 ");
			else
				if(isNaN(sweight))
					alert("重量必须为数字");
				else
					if(spack.replace(/(^\s*)|(\s*$)/g,"") == "")
						alert("包装方式不能为空");
					else
						urlpara_modify(sid,sname,sweight,spack);
	}
}
function urlpara_delete(sid) 
{
    location.href= encodeURI("adminspemanager!delete.action?id="+sid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var sid = (document.getElementById("sid_"+rownum)).value;
		urlpara_delete(sid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newsname = (document.getElementById("newsname")).value;
		var newsweight = (document.getElementById("newsweight")).value;
		var newswrapp = (document.getElementById("newswrapp")).value;
		if(newsname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("规格名不能为空 ");
			return false;
		}
		else
			if(newsweight.replace(/(^\s*)|(\s*$)/g,"") == "")
			{
				alert("重量不能为空 ");
				return false;
			}else
				if(isNaN(newsweight)){
					alert("重量必须为数字");
					return false;
				}
			else
				if(newswrapp.replace(/(^\s*)|(\s*$)/g,"") == ""){
					alert("包装方式不能为空");
					return false;
				}else
					return true;
	}else
		return false;
}
</script>
</head>
<body>
<h2 align="center">规格管理</h2>

  <s:form id="myform" name="myform" action="adminspemanager" theme="simple">
  	<table  class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="80">规格标识</th>
		    <th width="80">规格名称</th>
		    <th width="80">重量</th>
		    <th width="80">包装方式</th>
		    <th width="200">执行操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield size = "10" readonly="true" value="%{#result.id}" id="sid_%{#index.index}" name="sid_%{#index.index}"/></td>

        <td><s:textfield size = "16" id="sname_%{#index.index}" value="%{#result.name}" name="sname_%{#index.index}"/></td>
        
         <td><s:textfield size = "16" id="sweight_%{#index.index}" value="%{#result.weight}" name="sweight_%{#index.index}"/></td>
         
          <td><s:textfield size = "16" id="spackType_%{#index.index}" value="%{#result.packType}" name="spackType_%{#index.index}"/></td>
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
	 
		 <table  class="list_table" align="center">
		 	<tr>
		 	<td><label>规格名称:</label></td>
	        <td><s:textfield id="newsname" name="newsname"/></td>
	      </tr>
	      <tr>
	      	<td><label>重量:</label></td>
	        <td><s:textfield id="newsweight" name="newsweight"/></td>
	      </tr>
	      <tr>
	      	<td><label>包装方式:</label></td>
	        <td><s:textfield id="newswrapp" name="newswrapp"/></td>
	      </tr>
	      <tr>
	        <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="adminspemanager!add"/> </td>
	      </tr>
	      
		</table>
  </s:form>
</body>
</html>