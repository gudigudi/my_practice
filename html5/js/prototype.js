function Foo() {
    this.value = 42;
}
Foo.prototype = {
    method: function() {}
};

function Bar() {}

// 设置Bar的prototype属性为Foo的实例对象
Bar.prototype = new Foo();
Bar.prototype.foo = 'Hello World';

// 修正Bar.prototype.constructor 为Bar本身
Bar.prototype.constructor = Bar;

var test = new Bar();  // 创建Bar的一个新实例

// 原型链

