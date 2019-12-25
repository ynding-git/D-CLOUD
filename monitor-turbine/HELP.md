# 断路器聚合监控 

Hystrix Dashboard首页提到监控端点/turbine.stream，它是用来监控集群的。
从端点的命名来看，它需要引入Turbine，通过它来汇集监控信息，
并将聚合后的信息提供给Hystrix Dashboard来集中展示和监控。

使用rabbitmq收集监控数据