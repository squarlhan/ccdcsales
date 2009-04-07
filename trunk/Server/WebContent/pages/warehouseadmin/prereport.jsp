<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产成品进销存（发运）日报</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<s:head/>
</head>

<body>
<table class="list_table"  align="center" width="100%">
  <tr align="center">
     <td><h2>产成品进销存（发运）日报</h2></td>
  </tr>
 </table>
 <s:form action="cycgetreport.action">
  <table class="list_table"  align="center" width="50%" border="0"> 
 <tr>      
      <td><s:datetimepicker name="mydate" label="选择报表日期" toggleType="explode" value="today"/></td>      
 </tr>
<tr><td>  &nbsp; </td></tr>
 <tr>
    <td><s:select name="canku" label="选择仓库名" labelposition="left" multiple="false" 
            list="cangkusList"
            listKey="id" listValue="name"/>
    </td>
 </tr>
  <tr>
       <td align="left">
				<s:submit value="查看" />
	  </td>
  </tr>
  </table>
 </s:form>
</body>
</html>