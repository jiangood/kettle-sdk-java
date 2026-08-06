# kettle-sdk-java 迁移 tmgg → jiangood 实施计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 将仓库内所有 `tmgg` 相关引用（Maven 坐标、SCM 元数据、文档、Java 包名）完整迁移为 `jiangood`，并发布为新版本 2.0.0。

**Architecture:** 机械重命名任务，无逻辑改动。分三部分处理：`pom.xml` 发布元数据、`README.md` 文档引用、Java 源码/测试包名与目录结构。每部分独立提交，最后统一构建验证。

**Tech Stack:** Maven (Java 8)，git。

**Spec:** `docs/superpowers/specs/2026-08-06-kettle-sdk-transfer-tmgg-to-jiangood-design.md`

## Global Constraints

- 替换规则：`tmgg` → `jiangood`（如 `io.github.tmgg` → `io.github.jiangood`；URL 中 `github.com/tmgg` → `github.com/jiangood`）。
- 版本号：`1.0.2` → `2.0.0`。
- 开发者：name=`jiangood`，email=`410518072@qq.com` 保持不变。
- `kettle-carte-plugin` 链接同步指向 `github.com/jiangood`。
- 不修改任何业务逻辑，仅重命名。
- 本机为 Windows / PowerShell 5.1；测试类依赖真实 Carte 服务器，不执行测试，仅编译验证。
- 全仓库最终不得残留 `tmgg`（git 历史除外）。

---

### Task 1: 迁移 pom.xml 发布元数据

**Files:**
- Modify: `pom.xml`

**Interfaces:**
- Consumes: 无
- Produces: 新坐标 `io.github.jiangood:kettle-sdk-java:2.0.0`，后续 Task 3 依赖的构建基础

- [ ] **Step 1: 修改 `<groupId>` 与 `<version>`**

编辑 `pom.xml`：

```xml
<groupId>io.github.jiangood</groupId>
<artifactId>kettle-sdk-java</artifactId>
<version>2.0.0</version>
```

- [ ] **Step 2: 修改 `<url>`**

```xml
<url>https://github.com/jiangood/kettle-sdk-java</url>
```

- [ ] **Step 3: 修改 `<developers>`**

```xml
<developer>
    <name>jiangood</name>
    <email>410518072@qq.com</email>
</developer>
```

- [ ] **Step 4: 修改 `<scm>`**

```xml
<connection>scm:git:git:github.com/jiangood/kettle-sdk-java.git</connection>
<developerConnection>scm:git@github.com:jiangood/kettle-sdk-java.git</developerConnection>
<url>https://github.com/jiangood/kettle-sdk-java/tree/master</url>
```

- [ ] **Step 5: 验证 pom 无残留**

Run:
```powershell
rg "tmgg" pom.xml
```
Expected: 无输出（退出码 1）。

- [ ] **Step 6: 提交**

```bash
git add pom.xml
git commit -m "build: 迁移 Maven 坐标与元数据到 jiangood, 版本 2.0.0"
```

---

### Task 2: 迁移 README.md 文档引用

**Files:**
- Modify: `README.md`

**Interfaces:**
- Consumes: 无
- Produces: 面向新使用者的坐标与链接文档

- [ ] **Step 1: 修改版本徽章**

`README.md` 第 5 行：

```markdown
![版本](https://img.shields.io/maven-central/v/io.github.jiangood/kettle-sdk-java)
```

- [ ] **Step 2: 修改依赖坐标**

`README.md` 第 8 行：

```xml
<groupId>io.github.jiangood</groupId>
```

- [ ] **Step 3: 修改插件链接**

`README.md` 第 24 行：

```markdown
install the carte plugin from https://github.com/jiangood/kettle-carte-plugin/releases
```

- [ ] **Step 4: 验证 README 无残留**

Run:
```powershell
rg "tmgg" README.md
```
Expected: 无输出（退出码 1）。

- [ ] **Step 5: 提交**

```bash
git add README.md
git commit -m "docs: 更新 README 坐标为 jiangood"
```

---

### Task 3: 迁移 Java 包名与目录结构

**Files:**
- Move: `src/main/java/io/github/tmgg/` → `src/main/java/io/github/jiangood/`
- Move: `src/test/java/io/github/tmgg/` → `src/test/java/io/github/jiangood/`
- Modify: `src/**/*.java`（约 30 个文件，仅 `package`/`import` 声明）

**Interfaces:**
- Consumes: 无
- Produces: 包 `io.github.jiangood.kettle.sdk` 及其子包，供后续编译验证

- [ ] **Step 1: 用 git mv 移动 main 源码目录**

```powershell
git mv src/main/java/io/github/tmgg src/main/java/io/github/jiangood
```

- [ ] **Step 2: 用 git mv 移动 test 源码目录**

```powershell
git mv src/test/java/io/github/tmgg src/test/java/io/github/jiangood
```

- [ ] **Step 3: 批量替换包名引用（保留行尾与 UTF-8 无 BOM）**

```powershell
$files = Get-ChildItem -Path src -Recurse -Filter *.java
foreach ($f in $files) {
    $content = [System.IO.File]::ReadAllText($f.FullName)
    $new = $content.Replace('io.github.tmgg', 'io.github.jiangood')
    if ($new -ne $content) {
        [System.IO.File]::WriteAllText($f.FullName, $new, (New-Object System.Text.UTF8Encoding($false)))
    }
}
```

- [ ] **Step 4: 验证内容替换完整**

Run:
```powershell
rg "io\.github\.tmgg" src
```
Expected: 无输出（退出码 1）。

- [ ] **Step 5: 抽查包声明正确**

Run:
```powershell
Get-Content src/main/java/io/github/jiangood/kettle/sdk/KettleSdk.java -TotalCount 10
```
Expected: 第 1 行为 `package io.github.jiangood.kettle.sdk;`，import 中不含 `tmgg`。

- [ ] **Step 6: 提交**

```bash
git add -A
git commit -m "refactor: 包名 io.github.tmgg 迁移为 io.github.jiangood"
```

---

### Task 4: 构建验证与残留检查

**Files:**
- 无改动（纯验证）

**Interfaces:**
- Consumes: Task 1-3 的全部改动
- Produces: 迁移完成的确认

- [ ] **Step 1: 编译验证**

Run:
```powershell
mvn compile -q
```
Expected: BUILD SUCCESS，无编译错误。

- [ ] **Step 2: 打包验证（跳过测试）**

Run:
```powershell
mvn package -DskipTests -q
```
Expected: BUILD SUCCESS，生成 `target/kettle-sdk-java-2.0.0.jar` 及 sources/javadoc jar。

- [ ] **Step 3: 全仓库残留检查**

Run:
```powershell
rg "tmgg" .
```
Expected: 仅 `docs/superpowers/specs/` 设计文档与 `.git/` 历史中包含（git 历史不受影响），工作区源码、pom、README 均无残留。若业务文件有命中，需在退出前确认并清理。

- [ ] **Step 4: 最终提交（如有未提交改动）**

```bash
git status
```
若干净则跳过；否则提交剩余改动。

---

## Self-Review 记录

- **Spec 覆盖**：pom.xml（groupId/version/url/developer/scm）→ Task 1；README（徽章/坐标/链接）→ Task 2；Java 包名 + 目录 → Task 3；编译与打包验证 → Task 4。全部覆盖。
- **占位符扫描**：无 TBD/TODO，所有命令与内容完整。
- **类型一致性**：命名规则统一为 `tmgg` → `jiangood`，版本 2.0.0，无跨任务签名冲突。
