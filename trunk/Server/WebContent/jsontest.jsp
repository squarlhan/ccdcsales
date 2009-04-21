
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content=0>
<script language="javascript">
var xmlHttp = false;
try{
    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    //alert("You are using Microsoft Internet Explorer5.");
}catch(e){
    try{
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
       // alert("You are using Microsoft Internet Explorer.");
    }catch(e){
        xmlHttp = false;
        //alert("ERROR!");
    }
}
if(!xmlHttp && typeof XMLHttpRequest != 'undefined'){
    xmlHttp = new XMLHttpRequest();
    //alert("You are not using Microsoft Internet Explorer.");
}
	
function xmlHandle(){
	alert("start xmlHandle:"+xmlHttp.readyState);
	if(xmlHttp.readyState==4) {
		  alert("test:"+xmlHttp.responseText);
		  var obj1 = eval('('+xmlHttp.responseText+')');
		  
		  var st = document.getElementById("st");
		  alert("st"+obj1);
		  var cuts = document.getElementById("cuts");
		  alert("cuts"+obj1);
		  for(j=0;j<cuts.options.length;j++){
			  if ((cuts.options[j].nodeName == "OPTION")||(cuts.options[j].nodeName == "option")){		                    
				  cuts.options[j]= null;            
			            }
			  cuts.value = null;
			  cuts.options[j] = null;
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
	  }
}
function getOs()   
{   
   var OsObject = "";   
   if(navigator.userAgent.indexOf("MSIE")>0) {   
        return "MSIE";
   }
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){   
        return "Firefox";
   }
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {   
        return "Safari";
   }
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){   
        return "Camino"; 
   }
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){   
        return "Gecko"; 
   }   
} 
		function idchange(value){
			  var btype=getOs();
			  xmlHttp.onreadystatechange = (btype!="Firefox")?(xmlHandle):(xmlHandle());
			  xmlHttp.open("GET","getcustomers.action?start="+value,true);
			  xmlHttp.send(null);
			  xmlHttp.onreadystatechange = (btype!="Firefox")?(xmlHandle):(xmlHandle());
		}
	</script>
</head>
<body>
<form action="s" name="f"><input id="kw" type="text"
	maxlength="100" size="42" name="start" onkeyup="idchange(this.value)" />
<select id="cuts" name="cuts"
	onChange="document.getElementById('kw').value=this.options[this.selectedIndex].value" />
	<input id="sb" type="submit" value="Jilin" />
	<span id="hp"> </span>
	<div id="sd_1239786648646"
		style="width: 363px; top: 27px; display: block;">
	<table id="st" cellspacing="0" cellpadding="2">
	</table>
	</div></form>
<input type="text" name="id" value="1" onchange="idchange(this.value)" />
<input type="text" name="username" value="name" />
</body>
</html>