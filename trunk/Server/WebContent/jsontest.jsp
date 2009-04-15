
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var xmlHttp;

	  xmlHttp=new XMLHttpRequest();
	xmlHttp.onreadystatechange=function(){
	if(xmlHttp.readyState==4) {
		  alert(xmlHttp.responseText);
		  //var obj = {
			//  	"name":100,
			 // 	"age":10
			 // };
		  var obj1 = eval('('+xmlHttp.responseText+')');
		  //xmlHttp.responseText;
	      //alert(obj.name);
	      alert(obj1.name);
	  }
	};


		
	
		function idchange(value){
			alert('here');
			  xmlHttp.open("GET","getcustomers.action",true);
			  xmlHttp.send(null);
			document.html.body.username.value += 'change';
		}
	</script>
</head>
<body>
<form action="s" name="f">
<input id="kw" type="text" maxlength="100" size="42" name="wd" autocomplete="off" onchange="idchange(this.value)" />
<input id="sb" type="submit" value="Jilin"/>
<span id="hp">
</span>
<div id="sd_1239786648646" style="display: none; width: 363px; top: 27px;">
<table id="st" cellspacing="0" cellpadding="2">
</table>
</div>
</form>
<input type="text" name="id" value="1"
	onchange="idchange(this.value)" />
<%=org.springframework.security.context.SecurityContextHolder.getContext().getAuthentication().toString() %>
<input type="text" name="username" value="name" />
</body>
</html>