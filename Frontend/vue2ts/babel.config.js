module.exports = {
  presets: [["@vue/cli-plugin-babel/preset", { useBuiltIns: "entry" }]],
  "plugins": [
    ["import", {
      "libraryName": "antd",
      "libraryDirectory": "es",
      "style": "css" // `style: true` 会加载 less 文件
    }]
  ]
}