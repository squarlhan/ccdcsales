# 技术细节 #
开始的时候不能工作是因为使用了AspectJ作为AOP的实现，AspectJ需要在编译的字节码里面添加特殊的代码。
还有一个小问题：
> 使用AspectJ之后，@Override不能正常编译，现在的ServiceImpl代码中删除了所有的@Override的Annotation。估计和AsjectJ的版本有关系，使用的AspectJ版本为1.5.3。
> 此问题是由于ＪＤＫ的版本导致的，ＪＤＫ１.５对于@Override方法不能用在接口上。

# 使用方式 #
桌面上有一个部署的快捷方式，此快捷方式调用 ant运行 E:\Dcsh\ANT\_AUTO下的脚本build.xml自动部署。所作的工作如下：
  1. 下载代码
  1. 使用AspectJ编译器编译
  1. Copy编译好的文件到JBoss目录下
  1. Touch项目部署目录下的web.xml，使JBoss自动从部署项目。


# 自动部署脚本 #

'
<?xml version="1.0" encoding="UTF-8"?>


&lt;project name="Server" default="redeploy" basedir="."&gt;


> 

&lt;typedef resource="org/tigris/subversion/svnant/svnantlib.xml" /&gt;



> <!--支持AOP的程序 -->
> 

&lt;property name="build.compiler" value="org.aspectj.tools.ant.taskdefs.Ajc11CompilerAdapter" /&gt;



> 

&lt;property name="ajc" value="org.aspectj.tools.ant.taskdefs.Ajc11CompilerAdapter"/&gt;



> 

&lt;description&gt;


> > 重新在Jboss中部署Server程序，实现的方法是touch应用程序中的
> > 根中的web.xml
> > > 

&lt;/description&gt;



> <!-- 设置属性，需要根据自己的路径进行修改  -->
> 

&lt;property name="src" location="Server/src" /&gt;


> 

&lt;property name="build" location="Server/build/classes" /&gt;


> 

&lt;property name="web" location="Server/WebContent" /&gt;


> <property name="jboss"
> > location="D:/OpenTools/jboss-5.0.0.GA/server/default/deploy/Server.war" />



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


> > > 

&lt;fileset dir="${src}"&gt;


> > > > 

&lt;include name="\*\*/\*.xml"/&gt;



> > > 

&lt;/fileset&gt;



> > 

&lt;/copy&gt;




> 

&lt;copy todir="${jboss}"&gt;


> > 

&lt;fileset dir="${web}"&gt;


> > > 

&lt;include name="\*\*/\*.xml" /&gt;


> > > 

&lt;include name="\*\*/\*.jsp" /&gt;


> > > 

&lt;include name="\*\*/\*.css" /&gt;


> > > 

&lt;include name="\*\*/\*.js" /&gt;


> > > 

&lt;include name="\*\*/\*.jar" /&gt;



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



> 

&lt;target name="compile" depends="checkout"&gt;


> > <!--depends="checkout"  com/dcsh/market/service/	-->
> > 

&lt;javac srcdir="${src}" excludes="com/dcsh/market/service/\*Impl.java" destdir="${build}" source="1.5"&gt;


> > > 

&lt;compilerarg line="-XnotReweavable"/&gt;


> > > > 

&lt;classpath&gt;



> > > 

&lt;path location="${build}"/&gt;


> > > 

&lt;fileset dir="Server/WebContent/WEB-INF/lib"&gt;


> > > > 

&lt;include name="\*.jar" /&gt;



> > > 

&lt;/fileset&gt;


> > > 

&lt;fileset dir="D:/Project/大成/Code/devlib"&gt;


> > > > 

&lt;include name="\*.jar" /&gt;



> > > 

&lt;/fileset&gt;




> 

&lt;/classpath&gt;


> 

&lt;/javac&gt;



> 

&lt;javac srcdir="${src}" includes="com/dcsh/market/service/\*Impl.java" destdir="${build}" source="1.5"&gt;


> > 

&lt;classpath&gt;


> > > 

&lt;path location="${build}"/&gt;


> > > 

&lt;fileset dir="Server/WebContent/WEB-INF/lib"&gt;


> > > > 

&lt;include name="\*.jar" /&gt;



> > > 

&lt;/fileset&gt;


> > > 

&lt;fileset dir="D:/Project/大成/Code/devlib"&gt;


> > > > 

&lt;include name="\*.jar" /&gt;



> > > 

&lt;/fileset&gt;




> 

&lt;/classpath&gt;


> 

&lt;/javac&gt;






> 

&lt;/target&gt;



> 

&lt;target name="checkout"&gt;


> > 

&lt;svn&gt;


> > > 

&lt;checkout url="http://ccdcsales.googlecode.com/svn/trunk/" destPath="."/&gt;



> > 

&lt;/svn&gt;



> 

&lt;/target&gt;






&lt;/project&gt;



'