<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>打印明细表</title>
		<style type="text/css" media="all">
		<!--
		.STYLE1 {
			font-family: "华文宋体"
		}
		.STYLE3 {font-family: "华文宋体"; font-weight: bold; }
		-->
		@media print {
			div {
				display: none
			}
		}
		</style>
		<script language="javascript">

			  function   previewPrint(){   
			  	WB.ExecWB(7,1)   
			  }   
			  function   setPrint(){   
			 	 WB.ExecWB(8,1);   
			  }
		</script>
	</head>

	<body>
		<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
			id=WB width=0>
		</OBJECT>
		        <h2 align="center" class="STYLE1">大&nbsp;&nbsp;成&nbsp;&nbsp;集&nbsp;&nbsp;团&nbsp;&nbsp;库 &nbsp;&nbsp;存&nbsp;&nbsp;单</h2>
   			 <table width="90%" align="center" border="0" cellspacing="0">
   			   <tr>
    			 <td width="50%">
    			 <label class="STYLE3">仓库名称：<%=request.getAttribute("printcanku")%></label>
                 </td>
    			 <td width="50%" align="center">
                 <label class="STYLE3">日期：<%=request.getAttribute("printmydate")%></label>
                 </td>
   			   </tr>
		     </table>	
		<form id="myform" action="zzklistallstorage!getInfoByDate.action" >
		 <table width="90%" border="2" align="center" bordercolor="#000000" cellspacing="0" bgcolor="#F0F0F0" >
	    	<tr>
				<th width="16%"  scope="col">产品名称</th>
		        <th width="11%"  scope="col">内销</th>
			    <th width="14%"  scope="col">外销</th>
			    <th width="17%"  scope="col">定向</th>
			</tr>
			<s:iterator id="result" value="pmxList" status="index">		
			<tr>
				<td align="center">
						<s:property value="#result.prdid.name" />
				</td>
				<td align="center">
						<s:property value="#result.nxt" />
				</td>
				<td align="center">
						<s:property value="#result.wxt" />
				</td>
				<td align="center">
						<s:property value="#result.dxt" />
				</td>
			</tr>	        
			</s:iterator>
		  </table>				
			<div>	
			<table align="center">
				<tr>
					<td colspan="2" align="center">
					  <input type="button" value="打印设置" id="button1" name="button1" onclick="setPrint();"/>
					</td>
					<td colspan="2" align="center">
					  <input type="button" value="打印预览" id="button2" name="button2" onclick="previewPrint();"/>
					</td>
					<td colspan="1" align="center">
					  <input type="button" value="打印" id="button3" name="button3" onclick="window.print();"/>
					</td>
					<td colspan="1" align="center">
						<input type="submit" value="返回" id="return" name="return"/>
					</td>
				</tr>
			</table>
			</div>	
		</form>
	</body>
</html>