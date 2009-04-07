
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
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
			  xmlHttp.open("GET","jsonTest.action",true);
			  xmlHttp.send(null);
			document.html.body.username.value += 'change';
		}
	</script>
</head>
<body>
<input type="text" name="id" value="1"
	onchange="idchange(this.value)" />
<%=org.springframework.security.context.SecurityContextHolder.getContext().getAuthentication().toString() %>
<input type="text" name="username" value="name" />
</body>
</html>