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
			//������struts1����struts2��˵�����ǵı�ǩ���Ƕ�html��ǩ��һ����װ�����ǵĵײ㶼��html������
			//���㴴��select��optionʱҪ����html��option���������ǵı�ǩ��ʽ�����ǵı�ǩֻ�������Լ���װ��
			//����Ҫ���ļ�ͷ��<@taglib>�����룬������s��xx��s���Լ�����ģ�����taglib�е�prefix=""��������Լ�Ҳ���Զ�����ı�ǩ���������ѧϰ�Զ����ǩ�ҿ��Ը�����д��һЩ��
			//����㴫���Ҫ��list�Ͱ����list��ҳ���Ͻ��ܣ�Ȼ����javascript����ѭ��д��option
			//����㲻��ˢ��ҳ��ʵ�ֶ�̬���¾���ajax��������ﲻ���������
			//��js�п�����jsp��java��ǩ
			quireSel = function(){
				var se = document.getElementById("sel");
				//alert("se.value"+se.value);
				//alert(sel.options.length);
				for(var i=0;i< <%=list.size()%>;i++){
					var opt = document.createElement("option");//var opt = createOption(i);�������������
						
					opt.appendChild(document.createTextNode(i)); //����option��text����
					//opt.text = i;����д�Ǵ���ģ�ӦΪ���ڸմ�����opt������˵�������ڸ��ڵ㣬text�������ӽڵ㣬����Ҫ��document.creatTextNode(value)������
					opt.setAttribute("value",i); //����option��value
					alert(opt.value+" ! "+opt.text);//����jsһ����alertȥ������ִ�е�������е���debug
					
					se.appendChild(opt);
					//���opt.text=1д��������ǿ��Ե���Ϊ����opt�Ѿ�����dom�ĸ��ڵ㣬����Ϊ���������
					
					
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
