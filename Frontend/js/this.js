// 定义一个人, 名字为jack
var jack = {
  name: "jack",
  age: 26
}
// 定义另一个人, 名字为abruzzi
var abruzzi = {
  name: "abruzzi",
  age: 26
}

// 定义一个全局的函数对象
function printName() {
  return this.name;
}

// 设置printName的上下文为jack, 此时的this为jack
console.log(printName.call(jack));
console.log(printName.call(abruzzi));

