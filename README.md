# seata-oracle-1.3.0
seata1.3.0集成oracle简单实例
seata集成oracle遇到了几个问题：
(1)从1.0之后zip包就没有建表语句了，更何况是oracle版本的，我这里的sql语句是从源码里找到的，同时源码里还有其它几个版本的建表语句
(2)yml 注意nacos配置，需要特别注意的是1.0.0 版本配置项 seata.service .vgroup-mapping=default 1.1.0 更改为: seata.service.vgroupMapping .my_test_tx_group=default,其中my_test_tx_group代表程序所使用的事务分组
(3)seata oracle版本默认的日志序列化程序不可用， jackson 的新特性找不到，Seata 要求 jackson 版本2.9.9+，但是使用 jackson 2.9.9+ 版本会导致Spring Boot中使用的jackson API找不到，也就是jackson本身的向前兼容性存在问题。因此,建议大家将Seata的序列化方式切换到非 jackson 序列化方式，比如 kryo，配置项为client.undo.logSerialization = "kryo"
相应的maven依赖如下：
        <properties>
            <kryo-kryo.version>2.24.0</kryo-kryo.version>
            <kryo-serializers.version>0.45</kryo-serializers.version>
            <kryo.version>4.0.2</kryo.version>
        </properties>
        <dependency>
            <groupId>com.esotericsoftware.kryo</groupId>
            <artifactId>kryo</artifactId>
            <version>${kryo-kryo.version}</version>
        </dependency>
        <dependency>
            <groupId>de.javakaffee</groupId>
            <artifactId>kryo-serializers</artifactId>
            <version>${kryo-serializers.version}</version>
        </dependency>
        <dependency>
            <groupId>com.esotericsoftware</groupId>
            <artifactId>kryo</artifactId>
            <version>${kryo.version}</version>
        </dependency>
