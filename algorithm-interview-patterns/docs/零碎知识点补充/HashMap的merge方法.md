
# HashMap.merge() 方法详解


`HashMap` 的 `merge()` 方法是 Java 8 引入的一个非常强大的原子操作。它的核心作用是：**根据给定的 Key，要么把新值存进去，要么根据某种规则把新值和旧值“合并”在一起。**  
简单来说，它就是 `get`、`put` 和 `if‑else` 逻辑的优雅缩写。

---

## 1. 方法原型与参数

```java
map.merge(key, value, (oldValue, newValue) -> {
    // 合并逻辑
    return resultl;
});
```

- **`key`**：要操作的键。
- **`value`**：如果该键不存在（或对应值为 `null`），直接存入该值。
- **`remappingFunction`**（Lambda 表达式）：如果键已经存在，则执行此函数。它接收两个参数：旧值 (`oldValue`) 和新值 (`newValue`)。

---

## 2. 核心逻辑（白话版）

1. **Key 不存在？** 直接把 `value` 存进去。
2. **Key 已存在？** 执行 Lambda 表达式
    - 返回 `null` → 删除该键。
    - 返回非 `null` → 用返回值替换旧值。

---

## 3. 典型使用场景

### 场景 A：计数器（最常用）

**旧写法：**

```java
Integer count = map.get(word);
if (count == null) {
    map.put(word, 1);
} else {
    map.put(word, count + 1);
}
```

**`merge` 写法：**

```java
// 如果 word 不存在，存入 1；如果存在，旧值 + 1
map.merge(word, 1, (oldVal, newVal) -> oldVal + newVal);
// 更简洁的写法（方法引用）
map.merge(word, 1, Integer::sum);
```

### 场景 B：数据初始化与累加（如购物车）

```java
// 假设这是双 11 凑单：如果商品已存在，价格累加
map.merge("iPhone17", 7999.0, Double::sum);
```

### 场景 C：配置文件的合并

当有两组配置（默认配置和用户自定义配置）时，可使用 `merge` 将用户配置覆盖或融合进默认配置。

```java
defaultConfig.mergeAll(userConfig, (def, user) -> user);
```

---

## 4. 为什么使用 `merge()`？（核心优势）

- **代码简洁**：告别繁琐的 `if (map.get(k) == null)` 检查。
- **原子性**：在 `ConcurrentHashMap` 中，`merge` 是线程安全的原子操作，避免了多线程环境下的竞态条件。
- **Null 值处理**：自动处理原本值为 `null` 的情况，比手动判断更安全。
- **灵活的合并逻辑**：通过 Lambda 可自定义任意合并规则，支持复杂对象的累加或替换。

--- 

> **小贴士**：在需要对 `Map` 进行“获取‑更新‑写入”操作时，优先考虑 `merge()`，尤其是在并发环境下使用 `ConcurrentHashMap` 时，可显著提升代码可读性和安全性。  
