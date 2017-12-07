### IDEA 构建 java web项目两种方式
####  一、普通方式构建
#### 二、 通过gradle 构建
* 普通方式构建

>  首先新建一个工程，选择Java Enterprise， 接着选择Web Application，点击next，这样web项目就创建完成了。 截图如下：

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/01_step1.png)
> 接下来需要配置tomcat（没有tomcat的话，先去官网下载一个) ， 配置步骤如下：

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/01_step02.png)

> 然后将项目部署到tomcat

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/01_step03.png)

> 点击fix之后：

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/01_step04.png)

> 点击右下角ok，接着切换到主界面，点击三角形就可以运行项目了。

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/01_step05.png)



* 通过gradle方式构建

>  首先新建一个工程，选择Gradle， 接着选择java 、web，点击next，这样web项目就创建完成了。 截图如下：

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/02_step01.png)

> 紧接着填写项目工程的名字：

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/02_step02.png)

> 最后按照下面勾选，并且选择你的本地gradle路径（没有gradle的先去下载。。）

!["截图1"](https://raw.githubusercontent.com/ge1944633835/source-material/master/javaweb/02_step03.png)

> 这一步完成之后，就可以参照第一种构建方式的后面步骤，都是一样的，配置tomcat，部署项目到tomcat 并且运行项目，这里就不重复了～












