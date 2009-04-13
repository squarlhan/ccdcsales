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
		        <h2 align="center" class="STYLE1">销售发货通知单</h2>
    			 <table width="90%" align="center" border="0" cellspacing="0">
    			 <tr>
    			 <td width="50%">
    			 <label class="STYLE3">区域：<%=request.getAttribute("domains")%></label>
                 </td>
                 <td width="24%">
    			 <label class="STYLE3">日期：<%=request.getAttribute("date")%></label>
                 </td>
                 <td width="26%">
    			 <label class="STYLE3">销售单号：<%=request.getAttribute("bno")%></label>
                 </td>
    			 </tr>
    			 </table>
    			 <table width="90%" align="center" border="0" cellspacing="0">
				 	<tr>
				    <td width="50%">
				    <label class="STYLE3">发货单位：<%=request.getAttribute("orgin") %></label>
				 	</td>
				    <td width="50%">
				    <label class="STYLE3">合同号：<%=request.getAttribute("cno") %></label>
				 	</td>
				    </tr>
    			</table>
                  <table width="90%" align="center" border="0" cellspacing="0">
				 	<tr>
				    <td width="50%">
				    <label class="STYLE3">购货单位：<%=request.getAttribute("customerName") %></label>
				 	</td>
				    <td width="24%">
				    <label class="STYLE3">运输方式：<%=request.getAttribute("delivertypeName") %></label>
				 	</td>
                    <td width="26%">
				    <label class="STYLE3">结算方式：<%=request.getAttribute("jstypeName") %></label>
				 	</td>
				    </tr>
				  </table>
                  <table width="90%" align="center" border="0" cellspacing="0">
				 	<tr>
				    <td>
				    <label class="STYLE3">备注：<%=request.getAttribute("memo") %></label>
				 	</td>
				    </tr>
				  </table>
		<form id="myform" action="xsliststorage.action" >
		  <table width="90%" border="1" align="center" bordercolor="#000000" cellspacing="0" bgcolor="#F0F0F0" >
	    	<tr>
			  <th width="15%"  scope="col">发货仓库</th>
		        <th width="25%"  scope="col">产品名称</th>
			    <th width="15%"  scope="col">规格</th>
			    <th width="15%"  scope="col">应发吨数</th>
			    <th width="15%"  scope="col">应发件数</th>
			    <th width="15%"  scope="col">单价</th>
			</tr>
			<s:iterator id="result" value="resultList" status="index">		
			<tr>
				<td align="center">
						<s:property value="#result.deli_canku" />
				</td>
				<td align="center">
						<s:property value="#result.product" />
				</td>
				<td align="center">
						<s:property value="#result.specification" />
				</td>
				<td align="center">
						<s:property value="#result.sumweight" />
				</td>
				<td align="center">
						<s:property value="#result.deli_num" />
				</td>
				<td align="center">
						<s:property value="#result.price" />
				</td>
			</tr>	        
			</s:iterator>	
		  </table>
			<table width="90%" align="center" border="0" cellspacing="0">
			    <tr>
			    <td>
			    <label class="STYLE3">审核人：</label>
			    </td>
			    <td>
			    <label class="STYLE3">内审部：</label>
			    </td>
			    </tr>
			</table>					
			<div>	
			<table align="center">
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="打印设置" id="button1" name="button1" onClick="setPrint();"/>
					</td>
					<td colspan="2" align="center">
						<input type="button" value="打印预览" id="button2" name="button2" onClick="previewPrint();"/>
					</td>
					<td colspan="1" align="center">
						<input type="button" value="打印" id="button3" name="button3" onClick="window.print();"/>
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