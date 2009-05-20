<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处与工厂对应关系分配</title>
<style type="text/css" media="all">
@import "/Server/images/style.css";
</style>
</head>
<body>
<h2>请选择要分配工厂的储运处</h2>
  <s:form theme="simple">
<table  class="list_table" align="left" width="640">
		<tr bgcolor="#4A708B">
		    <th width="50%">储运处名</th>
		    <th width="50%">储运处名</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
			<s:if test="#index.getIndex()%2==0||#index.first"> 
                 <tr bgcolor="#ffffff"> 
            </s:if> 

            <td width="25%" align="center"> 
                 <a href="<s:url action="adminshowcfr"><s:param name="cycId" value="#result.id"/>
                 </s:url>"><s:property value="#result.name" />
				 </a></td>
            <s:if test="#index.getIndex()%2==1||#index.last"> 
                 <tr> 
            </s:if> 			
	    </s:iterator>
  </table>
 </s:form>
</body>
</html>