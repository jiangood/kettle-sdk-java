# Intro
Kettle(pdi) Carte java sdk

# Usage
![版本](https://img.shields.io/maven-central/v/io.github.jiangood/kettle-sdk-java)
```xml
<dependency>
    <groupId>io.github.jiangood</groupId>
    <artifactId>kettle-sdk-java</artifactId>
    <version>2.0.0</version>
</dependency>
```

Demo
```java
KettleSdk sdk = new KettleSdk(url, repo, username, password);
SlaveServerStatus status = sdk.status();
```




# manage repository ?
install the carte plugin from https://github.com/jiangood/kettle-carte-plugin/releases

download and unzip to kettle dir


# dev note

## response entity reference

pentaho-kettle/engine/src/main/java/org/pentaho/di/www/
