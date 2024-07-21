# AOP
aop的实现：
- JDK or cglib代理
- aspectj maven编译插件 
  - 代理静态方法
  - 跟spring无关
- aspectj weaver agent 
  - 可以突破代理的限制：代理静态方法、代理嵌套方法
  - 跟spring无关

# 工具
arthas的jad命令反编译class文件，可以看到代理类的源码
asm 动态生成字节码技术