<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置储运处关联工厂</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<h2>请选择该储运处关联的工厂</h2>
  <s:form theme="simple" action="adminsavecfr" >
<table  class="list_table" align="left" width="100%">
        <tr><td>&nbsp;</td><TD align="right"><s:submit value="确定" /></TD></tr>
		<tr bgcolor="#4A708B">
		    <th width="50%">工厂名称</th>
		    <th width="50%">工厂名称</th>
		</tr>
		<s:iterator id="result" value="cankus" status="index">		
			<s:if test="#index.getIndex()%2==0||#index.first"> 
                 <tr bgcolor="#ffffff"> 
            </s:if> 

            <td width="25%" align="center">
                <s:property value="#result.name" />             
                <s:checkbox name="cankupriv[%{#index.index}]" theme="simple" value="cankupriv.get(#index.getIndex())"/>
				</td> 
            <s:if test="#index.getIndex()%2==1||#index.last"> 
                 <tr> 
            </s:if> 			
	    </s:iterator>
  </table> 
  <br> <br> <br> <br> <br> <br> <br><br> <br> <br><br><br> <br> <br>
 </s:form>
</body>
</html>