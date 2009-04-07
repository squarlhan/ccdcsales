<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置组权限</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(gid,selectvalue) 
{
    location.href= encodeURI("admingroupprimanager!reset.action?gid="+gid+"&selectvalue="+selectvalue); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var gid = (document.getElementById("groupId_"+rownum)).value;
		var reset = (document.getElementById("reset_"+rownum)).value;
		urlpara_modify(gid,reset);
	}
}
</script>

</head>

<body>
<h2>设置组权限</h2>
  <s:form id="myform" action="admingroupprimanager" theme="simple">
     <table class="list_table" align="center" width="640" id="tb">
       <tr bgcolor="#4A708B">
            <th width="10%">组号</th>
		    <th width="25%">组名</th>
		    <th width="20%">权限</th>
		    <th width="25%">重置</th>
			<th width="20%">操作</th>
	  </tr>
	  <s:iterator id="result" value="urlGPrivList" status="index">	
      <tr  align="center" bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
        <td><s:textfield id="groupId_%{#index.index}" name="groupId_%{#index.index}" value="%{#result.usergroup.id}"/></td>
        <td><s:textfield id="groupName_%{#index.index}" name="groupName_%{#index.index}" value="%{#result.usergroup.name}"/></td>
        <td><s:property value="#result.url"/></td>
        <td><s:select id="reset_%{#index.index}" name="reset_%{#index.index}"  multiple="false"
            list="#{'0':'请选择','1':'管理员','2':'无限制','3':'储运处','4':'中转库','5':'驻厂库','6':'计划员'}" 
            listKey="key" listValue="value" 
            headerValue="--请选择 --" theme="simple"/></td>
        <td><a href="javascript:confirm_modify(${index.index})">设置</a></td>
     </tr>
     </s:iterator>
  </table>

  </s:form>
  
</body>
</html>