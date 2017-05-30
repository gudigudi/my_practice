### world rank
1. 这个世界可以被抽象为无数个节点及其相互间的链接
2. 节点的存在由其自身特性和与其他节点的链接方式及强度所确定，按照world rank的
排名标准的定义，即是权重\
3. 算法: 网络并行运算中稀疏矩阵分解的并行算法--\>简化计算量，缩短计算时间
补充: 不同子系统的计算结果的权重是不同的，根据一定的规律，可以避免不必要的计算量，
因为先前的计算结果的权重足够大或根据一定的规律可被视为足够大
4. 文件系统: 直接以分布式数据库的形式管理数据--\>分布式、可扩展、强备份
5. 集群:
```
master----|checkServer, check chunks id /  backup chunks database 
         |----chunkServer----|cacheServer cache clients database 
         |                  |----client----|data-map, database, data-pipe
         |                  |----client 
         |                  |----client 
         |
         |----chunkServer----|cacheServer 
                            |----client 
                            |----client 
```

6. 系统维护:
  1. 设计时做故障准备
  2. 冗余和错误恢复
  3. 廉价硬件切片
  4. 单软件版本
  5. 实现多重租赁
7. 系统进化方式:
```
1.    Web server ----> Database

2.    Page cache ----> Web server ----> Database 

3.    Page cache ----> Web server --------> Database 
                                 |
                                 |----> snippet cache 

                                  |----> snippet cache 
4.    Page cache ----> Web server |--------> Database 
                                  |----> Data cache 


                                  |----> snippet cache 
5.    Page cache ----> Web server |--------> DAL --------> Database 
                                  |----> Data cache(distributed cache) 

                                  |----> snippet cache 
6.    Page cache ----> Web server |--------> DAL --------> Database 
                                  |----> Data cache(distributed cache) 
                                  |
                                  |----> distributed file system

                                  |---->just like BigTable
                                  |
                                  |----> snippet cache                      | Database 
6.    Page cache ----> Web server |--------> DAL ---------------------------| Database 
                                  |----> Data cache(distributed cache)      | Database 
                                  |
                                  |----> distributed file system

                                  |---->just like BigTable               write
                                  |----> data cache(distributed cache) |-------> Database 
7.    Page cache ----> Web server |------------------------------------|
                                  |                                    |-------> Database 
                                  |                                       read  
                                  |----> snippet cache 
                                  |----> distributed file system 

8. what we can do better is th future!
```


```
server model:
                       first                                                          dates         
client, browser, etc --------> quick, short time, descributed small Servers ---------------------->
                       if error                                                       dates        |
                     --------> strong secury, quick, descributed middle Servers <----------------->|
                       last for safety                                                dates        |
                     --------> centered, strong secury, slow, big Servers <------------------------
```

网站运营:
大访问量 ----> 负载均衡(分流数据流), 冗余技术(备份防当机)
大储存量 ----> 分布式存储
访问速度 ----> 代理服务器，缓存 
服务器监控 

大数据产生的来源:

* 搜索引擎
* 社交网络
* 电子商务
* 互联网电视
* 游戏
* 移动互联网

爆发式增长:

* 实时数据
* 非结构花数据
* 机器数据

社会化趋势:

* UGC(用户生成内容)
* 用户行为
* 用户关系
* 大量的碎片化信息

大数据的特征:

* 体量大(volume): 
* 多样性(variety)
* 价值密度低(value)
* 速度(velocity)

大数据技术的挑战:

* 现有数据库处理技术
* 多样性数据的存储
* 实时数据处理技术
* 网络架构、数据中心以及运维

分析技术:

* 数据预处理
* 统计和分析
* 数据挖掘: 聚类、分类、关联、预测、统计学习、人工智能、机器学习、建模仿真

大数据技术: 

* 数据采集: 
* 数据存储: 半结构化数据、飞结构化数据

搜索引擎的定义:
以web相关技术为基础，对网络信息资源进行抓取与采集，建立索引数据库，并对搜索结果进
行排序，能依据用户需求查找相应信息的在线搜索系统

搜索的分类:

* 大搜索: 网页搜索
* 垂直搜索: 专业、分类搜索

* 元搜索引擎
* 集成搜索引擎

搜索引擎的基本步骤:
```
www ----> 爬取网页信息 ----> 信息获取 ----> 
分词 ----> 外链分析 ----> 数据存储 ---->
结果获取 ----> 相关度排序 ----> 效能优化
```

爬虫: 
要求: 全面、快速、非重
限制: 
* 有限的宽带和无限的网络
* 无法确定更新状态
* 不断变化的内容和URL
* 需要有礼貌的访问

信息的抽取:
* 页面分类
* 内容提取
* 解析页面
* 排重

分词:
```
分词的准确性 ----> 搜索结果的相关性排序(搜索结果是否让用户满意)
```
内容的深度理解:
客观索引(作者、URL、更新时间...) ----> 索引 <---- 内容索引(关键词、短语)

外联分析:
* 外链
* 静态权重(网页的相对重要程度)和动态权重(检索词相关的)

搜索引擎的补冲: 社区、百科(人肉搜索引擎)

算法:
CF协同过滤算法:
* 基于用户的过滤
  * 易于实现
  * 频繁计算
* 基于物品的过滤
  * 不需要频繁计算
聚类及相似度算法:
分类: 
* 欧几里得距离相似度
* 余弦相似度
* 调整余弦相似度
* 皮尔森相关性相似度
* 斯皮尔曼相关性相似度
* 基于谷本系数相似度 

基于知识的推荐算法
* 基于知识推理来产生推荐
  * 不依赖于用户评分
  * 不存在冷启动问题
  * 用户偏好发生变化时，不需要额外训练
* 推理方式
  * 规则推理
  * 本体推理
  * 案例推理

关联规则算法
* Apriori算法
* FP-Growth算法

分类算法: 贝叶斯分类SVM
画像建模: 用户行为
效用推荐: 可靠性、可得性
社交网络: N度好友理论





