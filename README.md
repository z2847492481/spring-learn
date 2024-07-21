# AOP
aop的实现：
- JDK or cglib代理
- aspectj maven编译插件 
  - 代理静态方法
  - 跟spring无关
- aspectj weaver agent 
  - 可以突破代理的限制：代理静态方法、代理嵌套方法
  - 跟spring无关