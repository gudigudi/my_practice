# API版本控制

## URI版本控制
```
curl https://example.com/api/v2/lists/3
```

## Header版本控制
```
curl https://example.com/api/lists/3 -H 'Accept: application/vnd.example.v2+json'
```

## 安全和向后兼容的API:
1. 添加新资源
2. 添加新的事件类型
3. 为响应添加新的属性
4. 改变属性的顺序
5. 向现有方法添加可选参数
