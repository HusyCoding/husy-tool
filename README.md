# 简介
husy-tool 是一个 Java 工具类库，收集一些工作中常用的通用工具。

目前该项目正处于持续收集整理中

# 项目结构

```
com.husytool
 ─core
 │	├─api
 │	│	├─APICode	//API响应码
 │	│	├─APIResult	//API响应对象
 │	│	├─Page	//分页对象
 │	├─lang
 │	│	├─ArraySortUtils	//数组排序工具
 │	│	├─NumberUtils	//数值操作工具
 │	│	├─StringUtils		//字符串操作工具
 │	├─util
 │	│	├─BeanCopierUtils	//基于Cglib实现的bean属性拷贝工具
 │	│	├─CollectionUtils	//Collection操作工具
 │	│	├─DateUtils		//日期操作工具
 │	│	├─EasypoiUtils	//基于EasyPoi实现的报表工具
 │	│	├─MapUtils		//Map操作工具
 │	│	├─RandomUtils	//随机工具
 ─web
 │	├─BeanTool 	//Spring Bean 对象工具
 │	├─GlobalExceptionHandler	//API全局异常处理器
 │	├─HttpServletUtils	//HttpServletRequest 工具处理类
 │	├─WebException	//API 接口异常类

```

# 优秀工具类推荐

* Apache common包
* Google Guava包
* Hutool包
