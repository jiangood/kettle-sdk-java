# 设计：kettle-sdk-java 从 tmgg 迁移到 jiangood

## 背景

项目原归属 `tmgg` 组织，已转移至 `jiangood` 名下（git 远程已指向 `github.com/jiangood/kettle-sdk-java.git`）。仓库内所有 `tmgg` 相关引用需要完整迁移为 `jiangood`。

## 目标

- 发布坐标改为新 groupId `io.github.jiangood`。
- 所有 Java 包名从 `io.github.tmgg.kettle.sdk` 迁移为 `io.github.jiangood.kettle.sdk`。
- 文档、SCM、开发者、插件链接等元数据全部指向 jiangood。

## 改动范围

### 1. `pom.xml`

- `<groupId>`：`io.github.tmgg` → `io.github.jiangood`
- `<version>`：`1.0.2` → `2.0.0`
- `<url>`：`https://github.com/tmgg/kettle-sdk-java` → `https://github.com/jiangood/kettle-sdk-java`
- `<developers>`：`name` → `jiangood`，`email` 保持 `410518072@qq.com`
- `<scm>`：connection / developerConnection / url 全部指向 `jiangood/kettle-sdk-java`

### 2. `README.md`

- 版本徽章坐标：`io.github.tmgg/kettle-sdk-java` → `io.github.jiangood/kettle-sdk-java`
- 依赖坐标 `<groupId>`：`io.github.tmgg` → `io.github.jiangood`
- 插件链接：`https://github.com/tmgg/kettle-carte-plugin` → `https://github.com/jiangood/kettle-carte-plugin`

### 3. Java 源码与测试

全部 `package` / `import` 声明中的 `io.github.tmgg` 替换为 `io.github.jiangood`，并移动物理目录：

- `src/main/java/io/github/tmgg/` → `src/main/java/io/github/jiangood/`
- `src/test/java/io/github/tmgg/` → `src/test/java/io/github/jiangood/`

涉及约 30 个文件，均为直接替换，无逻辑改动。

## 验证

- `mvn compile` 通过。
- `mvn package -DskipTests` 通过（测试类依赖真实 Carte 服务器，不本地执行）。
- 全仓库 `rg "tmgg"` 无残留（git 历史除外）。

## 注意事项（代码外）

- Maven Central 上旧的 `io.github.tmgg` 构件保留；新 groupId `io.github.jiangood` 需在 Sonatype/Maven Central 名下完成命名空间注册校验后方可 `deploy`。
- 新坐标 + 包名是破坏性变更，故版本从 2.0.0 起。
