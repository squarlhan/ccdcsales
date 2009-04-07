<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发货单位信息管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(fid,fname) 
{
    location.href= encodeURI("adminfahuomanager!modify.action?id="+fid+"&name="+fname); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var fid = (document.getElementById("id_"+rownum)).value;
		var fname = (document.getElementById("name_"+rownum)).value;
		if(fname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("发货单位名称不能为空 ");
		else
			urlpara_modify(fid,fname);
	}
}
function urlpara_delete(fid) 
{
    location.href= encodeURI("adminfahuomanager!delete.action?id="+fid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var fid = (document.getElementById("id_"+rownum)).value;
		urlpara_delete(fid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newfname = (document.getElementById("newfname")).value;
		if(newfname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("发货单位名称不能为空 ");
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
<h2 align="center">发货单位信息管理</h2>
  
  <s:form id="myform" name="myform" action="adminfahuomanager" theme="simple">
  	<table class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="120">发货单位标识</th>
		    <th width="120">发货单位名称</th>
		    <th width="120">执行操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield readonly="true" value="%{#result.id}" id="id_%{#index.index}" name="id_%{#index.index}"/></td>

        <td><s:textfield id="name_%{#index.index}" value="%{#result.name}" name="name_%{#index.index}"/></td>
        <td align="center" width="">

        	<a  href="javascript:confirm_modify(${index.index})">修改</a>
        	&nbsp;
        	<a href="javascript:confirm_delete(${index.index})">删除</a> 
        </td>
      </tr>
      </s:iterator>
       </table>
  </s:form>
  <s:form onsubmit="return confirm_add()">
	 
		 <table class="list_table" align="center">
		 	<tr>
		 	<td><label>发货单位名称:</label></td>
	        <td><s:textfield id="newfname" name="newfname" theme="simple"/></td>
	        <td align="center"><s:submit value="添加" action="adminfahuomanager!add"/> </td>
	      </tr>
		</table>
  </s:form>
</body>
</html>