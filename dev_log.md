# Dev_log

**Model**：负责数据和业务逻辑，与数据库的交互以及数据处理。

**View**：负责界面，展示数据给用户。

**Controller**：负责处理用户请求，调用Model进行数据操作，并将结果传递给View进行展示。

#### 1. **项目进展和里程碑**

text

```markdown
✅ 2024-06-15 - 完成用户认证模块
✅ 2024-06-18 - 集成支付接口
🔄 2024-06-20 - 正在进行性能优化
```

#### 2. **技术决策记录**

```markdown
## 为什么选择MySQL而不是MongoDB？

**考虑因素：**
- 数据结构相对固定
- 需要复杂的事务支持
- 团队对SQL更熟悉

**最终决定：** MySQL 8.0
```

#### 3. **遇到的问题和解决方案**

```
## 问题：文件上传大小限制

**错误信息：** MaxUploadSizeExceededException
**解决方案：**
1. 在application.yml中配置：
   spring:
     servlet:
       multipart:
         max-file-size: 10MB
         max-request-size: 10MB
2. 在Nginx中也要相应调整
```

#### 4. **架构设计思考**

```
## 微服务拆分方案

**当前单体应用的问题：**
- 部署时间长
- 技术栈升级困难

**拆分计划：**
- [ ] 用户服务 (user-service)
- [ ] 订单服务 (order-service) 
- [ ] 商品服务 (product-service)
```

#### 5. **代码重构记录**

```
## 重构用户查询逻辑

**之前：**
- 一个500行的巨大方法
- 难以测试和维护

**重构后：**
- UserQueryService: 处理查询条件
- UserRepository: 数据访问
- UserMapper: 对象映射
```

# 项目开发日志 - {OIDC服务}

##  详细记录

### 2025-10-3

**进展：** 学习OIDC、
**技术决策：**

- 
- 

**学习收获：** [OIDC.md](./OIDC.md)

**遇到的问题：**

