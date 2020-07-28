# 游密直播管理RestAPI 调用 Demo For Java

## 运行说明
- Macos/Linux 如果使用Gradle

``` shell
cd complete
./gradlew build && java -jar build/libs/gs-spring-boot-0.1.0.jar
```

- Windows 如果使用Gradle
``` cmd
cd complete
gradlew.bat build && java -jar build/libs/gs-spring-boot-0.1.0.jar
```

- 如果使用maven

``` shell
cd complete
mvn package && java -jar target/gs-spring-boot-0.1.0.jar
```

## 测试说明

运行后可以通过浏览器访问这个url进行测试

```
http://127.0.0.1:8080/getPublishAddress/100
```

## 关联的客户端Demo
http://code.nxit.us:9889/RTC-C/electron-demo