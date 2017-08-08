## 模块话数据处理

* `constants/types.js`
  * 为了避免变量名冲突，这个 js 文件是用来存储一些 mutations 需要使用到的方法名称。
* `actions/aAction.js`
  * 所有的 actions 全部放在这个文件夹里。
  * 我们可以使用 actions 异步请求数据，再使用 mutations 将请求到的数据提交给 state。
* `mutations/aMutation.js`
  * 所有的 mutations 全部放在这个文件夹里。
  * mutations 负责提交修改对应的 modules 中 state 的值。
* `getters/aGetter.js`
  * 所有的 getters 圈全部放在这个文件夹里。
* `modules/aModules.js`
  * 这就是一个模块，所有的 mutations actions getters 都会作为变量导入到这里，组装成一个完整的模块。
* `index.js`
  * 出口文件，所有的模版汇集到这里，在根节点下组装，再导出到 main.js 中。
* `../main.js`
  * 在 main.js 中引入store，完成整个模块制作。

### type.js
>这个文件负责定义 mutations 的方法名称。
 为避免命名冲突，需按照以下格式命名：
 event_moduleName_state

### actions && mutations
>actions 与 mutations 一般配合使用
actions 是负责处理异步方法的，所以用它来完成 ajax 请求；
mutations 是负责处理同步方法的，并且改变 state 的值的唯一方式就是使用 mutations 提交；
所以， 当 actions 完成请求获取数据后，再使用 mutations 提交给 state。

