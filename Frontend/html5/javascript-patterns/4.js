var dog = {};

dog.name = "Benji";

dog.getName = function () {
    return dog.name;
};

dog.getName = function () {
    return "Fido";
};

delete dog.name;

dog.say = function () {
    return "Woof!";
};
dog.fleas = true;

var dog = {
    name: "Benji",
    getName: function () {
        return this.name;
    }
};

var car = {goes: "far"};

var car = new Object();
car.goes = "far";


