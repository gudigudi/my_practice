var Person = function (name) {
    this.name = name;
    this.say = function () {
        return "I am " + this.name;
    };
};

var adam = new Person("Adam");
adam.say();
console.log(adam.say());
