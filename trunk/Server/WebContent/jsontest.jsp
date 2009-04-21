
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
		  //div = document.getElementById("sd_1239786648646");
		  //div.innerHTML = obj1;
		  st = document.getElementById("st");
		  cuts = document.getElementById("cuts");
		  for(j=0;j<cuts.options.length;j++){
			  if ((cuts.options[j].nodeName == "OPTION")||(cuts.options[j].nodeName == "option")){		                    
				  cuts.options[j]= null;            
			            }
			  cuts.value = null;
			  cuts.options[j] = null;
			  alert("del:"+j); 
		  }
		  for(i=0;i<obj1.length;i++){
			  var tr = st.insertRow(-1);
			  var td1 = tr.insertCell(-1);
			  td1.innerHTML = obj1[i][0];
			  var opts = document.createElement("option");
			  opts.value = obj1[i][1];
	          opts.text = obj1[i][0];
	          if(i<cuts.childNodes.length)cuts.options[i]=opts;
	          else cuts.options.add(opts);   
		  }

		  
		  alert(obj1[0][0]);
		  //xmlHttp.responseText;
	      //alert(obj.name);
	      //alert(obj1.name);
	  }
	};


		
	
		function idchange(value){
			alert('here');
			  xmlHttp.open("GET","getcustomers.action?start="+value,true);
			  xmlHttp.send(null);
			//document.html.body.username.value += 'change';
		}
	</script>
</head>
<body>
<form action="s" name="f">

<input id="kw" type="text" maxlength="100" size="42" name="start" autocomplete="on" onchange="idchange(this.value)" />
<select id="cuts" name="cuts" onChange="document.getElementById('kw').value=this.options[this.selectedIndex].value"/>
<input id="sb" type="submit" value="Jilin"/>
<span id="hp">
</span>
<div id="sd_1239786648646" style="width: 363px; top: 27px;display: block;">
<table id="st" cellspacing="0" cellpadding="2">
</table>
</div>
</form>
<input type="text" name="id" value="1"
	onchange="idchange(this.value)" />
<input type="text" name="username" value="name" />
</body>
</html>