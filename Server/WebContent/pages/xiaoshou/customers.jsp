<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

[
<s:iterator id="result" value="customers">
["<s:property value='#result.customName'/>", "<s:property value='#result.id'/>"],
</s:iterator>
]