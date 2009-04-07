
<%@ taglib prefix="s" uri="/struts-tags"%>

<p>Users</p>
<s:if test="users.size > 0">
	<table>
		<s:iterator value="users">
			<tr id="row_<s:property value="id"/>">
				<td>
					<s:property value="Id" />
				</td>
				<td>
					<s:property value="Name" />
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>

