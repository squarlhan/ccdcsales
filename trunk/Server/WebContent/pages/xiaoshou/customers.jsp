<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<table id="selectable">
	<s:iterator id="result" value="customers">
		<tr>
			<td><s:property value='#result.customName' /></td>
		</tr>
	</s:iterator>
</table>