

示例代码中，`SqlSession` 会自动关闭是因为使用了 Java 7 引入的 **try-with-resources** 语句。这种语法结构确保了在 try 代码块执行完毕后，所有实现了 `AutoCloseable` 接口的资源都会被自动关闭。

### 1. **try-with-resources 语句**

`try-with-resources` 语句是一种自动资源管理（Automatic Resource Management，简称 ARM）的机制，它要求在 try 块中声明的资源必须实现 `AutoCloseable` 接口。`SqlSession` 实现了 `AutoCloseable` 接口，因此可以在 `try-with-resources` 语句中使用。

### 2. **`SqlSession` 的 `close()` 方法**

在 `try-with-resources` 语句中，当 try 块中的代码执行完毕后，无论是正常执行结束还是因为异常提前结束，都会自动调用资源的 `close()` 方法。

例如：

```java
try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
    // 使用 session 执行数据库操作
    MyMapper mapper = session.getMapper(MyMapper.class);
    List<MyEntity> entities = mapper.selectAll();
    // 其他数据库操作
} catch (Exception e) {
    e.printStackTrace();
}
// 这里 session 会自动关闭，无需手动调用 close()
```

在这个示例中：

- `try` 块中声明了 `SqlSession` 类型的 `session` 变量。
- 当 `try` 块执行完毕，或者遇到异常时，`SqlSession` 的 `close()` 方法会被自动调用，释放数据库连接等资源。

### 3. **AutoCloseable 接口**

`SqlSession` 实现了 `AutoCloseable` 接口，该接口只有一个方法：`void close() throws Exception;`。`try-with-resources` 语句会在 try 块执行完毕后自动调用这个 `close()` 方法。

### 4. **传统方式（手动关闭）**

如果不使用 `try-with-resources`，你需要手动关闭 `SqlSession`，通常是在 `finally` 块中进行：

```java
SqlSession session = null;
try {
    session = MyBatisUtil.getSqlSessionFactory().openSession();
    // 使用 session 执行数据库操作
    MyMapper mapper = session.getMapper(MyMapper.class);
    List<MyEntity> entities = mapper.selectAll();
    // 其他数据库操作
} catch (Exception e) {
    e.printStackTrace();
} finally {
    if (session != null) {
        session.close(); // 手动关闭 session
    }
}
```

在这个例子中，`session.close()` 在 `finally` 块中手动调用，以确保在任何情况下（无论是否发生异常）都能关闭 `SqlSession`。

### 总结

在上面使用的 `try-with-resources` 语法中，`SqlSession` 会在块结束时自动关闭，这是因为它实现了 `AutoCloseable` 接口。这种机制可以减少手动管理资源的负担，并且降低了因忘记关闭资源而导致的资源泄漏风险。