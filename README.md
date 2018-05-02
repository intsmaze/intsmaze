# intsmaze
intsmaze项目不仅仅是一个开发架构，致力于提供大中型系统的脚手架，快速进行业务开发，免去项目集成新技术的时间成本。<br> 
* 基础框架为RBCA用户权限，采用前后端分离开发模式，RBCA模块采用基本的springMVC+mybatis架构；
* 其余后端模块采用jetty服务与SpringBoot服务来满足不同层次的需求；
* 服务间交互采用dubbo的RPC，同时引入了storm的DRPC进行服务的支撑；
* 消息中间件提供kafka与RocketMQ消息中间件，通过配置的方式灵活选择任意中间件，无需侵入源码修改； 
* 源码插桩技术监控每条消息的处理流程；
* flume技术手机日志信息，还原分布式会话调用链。
### 交流QQ群：

!(https://github.com/intsmaze/intsmaze/tree/master/image/intsmaze1.png)
