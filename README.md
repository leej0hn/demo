# spring boot 项目demo,用于生成 archetype
## maven生成本项目脚手架教程：
http://blog.csdn.net/a5518007/article/details/62885432

spring-boot demo project
 集成mybatis,dubbo,service模块为dubbo .
  分成4个层次
  使用的是spring-boot.version 1.4.1.RELEASE,
 ## commons 公共对象，exception,model,vo
 ## parent 依赖包的聚合
 ## persistence 持久层集成mybatis
 ## service 集成了dubbo,test下有消费者
 ## service-api 接口
 ## utils 工具类，简单封装了okhttp 3.0
 ## web 使用的是spring推荐的模板引擎 thymeleaf ，优点是前后端可以很好的分离，理念界面即原型，目前是thymeleaf是2版本，缺点：渲染性能不好，3版本性能会着重升级。
