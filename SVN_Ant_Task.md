http://ant.apache.org/antlibs/svn/index.html
http://subclipse.tigris.org/svnant.html
Example:
<?xml version="1.0" encoding="UTF-8"?>


&lt;project name="Server" default="redeploy" basedir="."&gt;


> 

&lt;description&gt;


> > 重新在Jboss中部署Server程序，实现的方法是touch应用程序中的
> > 根中的web.xml
> > > 

&lt;/description&gt;



> <!-- 设置属性，需要根据自己的路径进行修改  -->
> 

&lt;property name="src" location="src" /&gt;


> 

&lt;property name="build" location="build/classes" /&gt;


> <property name="jboss"
> > location="E:\Dcsh\jboss-5.0.0.GA\server\default\deploy\Server.war" />


> 

&lt;target name="compile" description="compile the source "&gt;


> > 

&lt;javac srcdir="${src}" destdir="${build}" /&gt;



> 

&lt;/target&gt;



> 

&lt;target name="copy" depends="compile"&gt;


> > 

&lt;copy todir="${jboss}/WEB-INF/classes"&gt;


> > > 

&lt;fileset dir="${build}" &gt;


> > > > 

&lt;include name="\*\*/\*.xml" /&gt;


> > > > 

&lt;include name="\*\*/\*.class" /&gt;



> > > 

&lt;/fileset&gt;



> > 

&lt;/copy&gt;




> 

&lt;copy todir="${jboss}"&gt;


> > 

&lt;fileset dir="./WebContent"&gt;


> > > 

&lt;include name="\*\*/\*.xml" /&gt;


> > > 

&lt;include name="\*\*/\*.jsp" /&gt;



> > 

&lt;/fileset&gt;



> 

&lt;/copy&gt;



> 

&lt;/target&gt;



> 

&lt;target name="redeploy" depends="copy"&gt;


> > 

&lt;touch file="${jboss}/WEB-INF/web.xml" /&gt;



> 

&lt;/target&gt;





&lt;/project&gt;

