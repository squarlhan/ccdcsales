<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="java.util.*"%>
<html>
	<head>
	<%
	List list = new ArrayList();
	list.add("1");
	list.add("2");
	list.add("3");
	list.add("4");
	list.add("5");
	list.add("6");
	 %>
		<script>
			//不论是struts1还是struts2来说，它们的标签都是对html标签的一个封装，它们的底层都是html，所以
			//在你创建select的option时要创建html的option不能用它们的标签形式，它们的标签只不过是自己封装的
			//并且要在文件头用<@taglib>来引入，至于是s：xx的s是自己定义的，就是taglib中的prefix=""这个；你自己也可以定义你的标签，如果你想学习自定义标签我可以给你我写的一些。
			//如果你传如的要是list就把这个list在页面上接受，然后在javascript中用循环写入option
			//如果你不想刷新页面实现动态更新就用ajax，如果哪里不会可以问我
			//在js中可以用jsp的java标签
			quireSel = function(){
				var se = document.getElementById("sel");
				//alert("se.value"+se.value);
				//alert(sel.options.length);
				for(var i=0;i< <%=list.size()%>;i++){
					var opt = document.createElement("option");//var opt = createOption(i);可以用这个代替
						
					opt.appendChild(document.createTextNode(i)); //设置option的text属性
					//opt.text = i;这样写是错误的，应为对于刚创建的opt对象来说，不存在父节点，text是他的子节点，所以要用document.creatTextNode(value)来创建
					opt.setAttribute("value",i); //设置option的value
					alert(opt.value+" ! "+opt.text);//调试js一般用alert去看程序执行到了哪里，有点像debug
					
					se.appendChild(opt);
					//如果opt.text=1写在这里就是可以的因为现在opt已经有了dom的父节点，可以为其添加属性
					
					
				}
			}

				function createOption(text){
					var opt=document.createElement("option");
					opt.setAttribute('value',text);
    				opt.appendChild(document.createTextNode(text));
					return opt;
				}
		</script>

	</head>
	<body>
		<input type="button" value="test" onclick="quireSel()">
		<select id="sel">
		</select>
	</body>
</html>
