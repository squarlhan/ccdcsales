<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="org.springframework.security.context.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
//var dog = new ActiveXObject("UMHControl");
//var dog = new ActiveXObject("UMHCONTROL.UMHControlCtrl.1");
//dog.command(1);
//dog.OperateDog();
//http://topic.csdn.net/u/20070828/08/bcd6f3b2-efdb-45d8-a4e5-c2427fc26a15.html
//alert(dog.AboutBox());
  window.onload = function(){
  	  //alert(tag);
	    document.UMHControl1.command =1;
	    errCode = document.UMHControl1.OperateDog();
    	if (errCode == 0)
    	{
    		window.status = "微狗存在!";
     	}
    	else
    	{
        	window.status = "请插入微狗!";
        	alert("请插入微狗!");
     	}
     	//check();
 }
 
 var tag = "tag";
 
 function check(username){

 	document.UMHControl1.command =2;
    document.UMHControl1.bytes=1;  
    document.UMHControl1.addr=0;
    document.UMHControl1.password=790813;
    errCode = UMHControl1.OperateDog();
   	if (errCode == 0)
	{
		var num = UMHControl1.memdata.charCodeAt(0);
   	    document.UMHControl1.bytes = num;
   	    document.UMHControl1.addr=1;
   	    UMHControl1.OperateDog();

		var nameInDog = UMHControl1.memdata;
		   //alert(nameInDog.length +  ' | ' + username.length );
		   //alert(typeof(nameInDog) + ' | ' + typeof(username));
		   //alert(nameInDog + " | " + username);
		   if (nameInDog != username){
			   alert("输入的用户名和加密狗中的用户名不符！");
			   return false;
		   }
		   return true;
	}
	else
	{
	 	alert("读取加密狗错误，错误码:"+errCode);  		
	 	return false;
	}

 }
</script>
<title>大成登陆页面</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";

@import "/Server/css/css.css";
</style>
</head>
<body>
<OBJECT id=UMHControl1 style="LEFT: 0px; TOP: 0px" height=0 width=0
	classid=clsid:C4B873F4-099F-4DD7-88D2-2EF97EFE03E8>
	<PARAM NAME="_Version" VALUE="65536">
	<PARAM NAME="_ExtentX" VALUE="2646">
	<PARAM NAME="_ExtentY" VALUE="1323">
	<PARAM NAME="_StockProps" VALUE="0">
</OBJECT>
<div id="rightmain">
<h2>大成登陆页面</h2>
<div id="login"><s:form action="j_spring_security_check"
	onsubmit="return check(this.j_username.value)">
	<table width="200" align="center" style="margin-left: 30">
		<tr>
			<td align="center"><s:textfield size="15" label="用户名"
				name="j_username" /></td>
		</tr>
		<tr>
			<td align="center"><s:password size="17" label="密码"
				name="j_password" /></td>
		</tr>
	</table>
	<br />
	<table width="150" align="center">
		<tr>
			<td width="80" align="center"><s:submit value="登陆"
				theme="simple" /></td>

			<td width="80" align="center"><s:reset value="取消" theme="simple" /></td>
		</tr>
	</table>
</s:form></div>
</div>

</body>
</html>