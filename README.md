# 简介
husy-tool 是一个 Java 工具类库，收集一些工作中常用的通用工具。

目前该项目正处于持续收集整理中

# 项目结构

```
com.husytool
 ─core
 │	├─lang
 │	│	├─ArraySortUtils    // 数组排序工具
 │	│	├─NumberUtils       // 数值操作工具
 │	│	├─StringUtils       // 字符串操作工具
 │	├─util
 │	│	├─BeanCopierUtils   // 基于Cglib实现的bean属性拷贝工具
 │	│	├─CollectionUtils   // Collection操作工具
 │	│	├─DateUtils         // 日期操作工具
 │	│	├─EasypoiUtils      // 基于EasyPoi实现的报表工具
 │	│	├─HttpClientUtils   // HttpClient工具类
 │	│	├─JacksonUtils      // Jackson工具类
 │	│	├─MapUtils          // Map操作工具
 │	│	├─MaskUtils         // 数据脱敏工具
 │	│	├─PropertiesUtils   // 配置文件工具类
 │	│	├─RandomUtils       // 随机工具
 │	│	├─RegExUtils        // 常用正则校验工具
 ─web
 │	├─api
 │	│	├─GlobalExceptionHandler    // 全局异常处理器
 │	│	├─PageEntity                // 自定义分页对象
 │	│	├─ResponseCode              // 自定义API响应码
 │	│	├─ResponseEntity            // 自定义API响应对象
 │	│	├─WebException              // 自定义异常
 │	├─config
 │	│	├─JsonConverterConfig       // JSON解析器配置
 │	├─util
 │	│	├─ApplicationContextUtils   // Spring上下文工具类
 │	│	├─HttpServletUtils          // HttpServletRequest 工具处理类
```

# 优秀工具类推荐

* Apache common包
* Google Guava包
* Hutool包
