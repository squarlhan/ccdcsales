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
		        <h2 align="center" class="STYLE1">产&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;装&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单</h2>
   			 <table width="90%" align="center" border="0" cellspacing="0">
   			   <tr>
    			 <td width="50%">
    			 <label class="STYLE3">编号：<%=request.getAttribute("bno")%></label>
                 </td>
   			   </tr>
		     </table>
    			 <table width="90%" align="center" border="0" cellspacing="0">
				 	<tr>
				    <td width="50%">
				    <label class="STYLE3">发货单位：<%=request.getAttribute("printorgin")%></label>
				 	</td>
				    <td width="30%">
				    <label class="STYLE3"><%=request.getAttribute("date") %></label>
				 	</td>
				    <td width="20%">
				    <label class="STYLE3">化验单：&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;份</label>
				    </td>
				    </tr>
</table>
		<form id="myform" action="cyclistuncheckProducts.action" >
		 <table width="90%" border="2" align="center" bordercolor="#000000" cellspacing="0" bgcolor="#F0F0F0" >
	    	<tr>
				<th width="16%"  scope="col">产品名称</th>
		        <th width="11%"  scope="col">规格</th>
			    <th width="14%"  scope="col">包装形式</th>
			    <th width="17%"  scope="col">吨数</th>
			    <th width="15%"  scope="col">批号</th>
			</tr>
			<s:iterator id="result" value="resultList" status="index">		
			<tr>
				<td align="center">
						<s:property value="#result.productsName" />
				</td>
				<td align="center">
						<s:property value="#result.specificationName" />
				</td>
				<td align="center">
						<s:property value="#result.packType" />
				</td>
				<td align="center">
						<s:property value="#result.sum" />
				</td>
				<td align="center">
						<s:property value="#result.pchName" />
				</td>
			</tr>	        
			</s:iterator>
			<tr> 
            	<td align="center">
                <label>备注：</label>
                </td>
                <td colspan="5">
                <label><%=request.getAttribute("memo")%></label>
                </td>
            </tr>	
		  </table>
          <table width="90%" align="center" border="0" cellspacing="0">
			    <tr>
			    <td width="50%" >
			    <label class="STYLE3">联系电话：0431-7860677</label>
			    </td>
			    <td width="50%" >
			    <label class="STYLE3">传真：0431-7860667</label>
			    </td>
		    </tr>
			</table>
			<table width="90%" align="center" border="0" cellspacing="0">
			    <tr>
			    <td>
			    <label class="STYLE3">装车负责人：</label>
			    </td>
			    <td>
			    <label class="STYLE3">装车验收人：</label>
			    </td>
			    </tr>
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