<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置组成员</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<h2>请选择组进行设置</h2>
  <s:form theme="simple">
<table class="list_table" align="left" width="640">
		<tr bgcolor="#4A708B">
		    <th width="25%">组名</th>
		    <th width="25%">组名</th>
		    <th width="25%">组名</th>
		    <th width="25%">组名</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
			<s:if test="#index.getIndex()%4==0||#index.first"> 
                 <tr bgcolor="#ffffff"> 
            </s:if> 

            <td width="25%" align="center"> 
                 <a href="<s:url action="adminshowgroupmember"><s:param name="groupId" value="#result.id"/>
                 </s:url>"><s:property value="#result.name" />
				 </a></td> 
            <s:if test="#index.getIndex()%4==3||#index.last"> 
                 <tr> 
            </s:if> 			
	    </s:iterator>
  </table>
 </s:form>
</body>
</html>