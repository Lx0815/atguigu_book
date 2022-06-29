# atguigu_book
尚硅谷book项目

# 项目技术
Maven3.6.3、 以及 pom.xml 里的一系列依赖

# 项目结构
├───.idea
├───src
│   ├───main
│   │   ├───java 源文件
│   │   │   └───book
│   │   │       ├───controller    控制层
│   │   │       ├───filter        过滤器层
│   │   │       ├───ioc           IOC容器
│   │   │       │   └───impl      IOC容器的实现类
│   │   │       ├───listener      监听器
│   │   │       ├───mapper        Mybatis 代理接口
│   │   │       ├───pojo          实体类
│   │   │       ├───service       service 层
│   │   │       ├───servlet       servlet 层
│   │   │       └───utils         工具类
│   │   ├───resources             资源包
│   │   └───webapp                webapp目录
│   │       ├───static            静态资源
│   │       │   ├───css           css 资源
│   │       │   ├───img           img 资源
│   │       │   ├───script        script 资源
│   │       │   └───uploads       上传文件的文件夹
│   │       └───WEB-INF           
│   │           └───pages         内含页面
│   └───test                      测试包
│       ├───java
│       └───resources
└───target 省略
