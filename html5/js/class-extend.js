function Person(name) {
    if (name != undefined) {
        this.name = name;
    } else {
        this.name = "StrayBugs";
    }
    this.age = 22;
}

Person.prototype.sayName = function() {
    alert(this.name);
};

function Man() {}

// 第一种
Man.prototype = Person();
var man = new Man();

function Man(name) {
    // 每个实例都可以有自己的名字
    Person.call(this, name)

    //子类增加的属性
    this.job = 'student';
}

var man1 = new Man();
var man2 = new Man('Jesse');

alert(man1.name);
alert(man2.name);

alert(man1.sayName);
alert(man2.sayName);
