var adult = {age: 26},
    retrievedProperty = adult.age;

adult.toString = function() {
   return "I'm " + this.age;
};
var stringRepresentation = adult.toString();


var child = Object.create(adult);
// this way of creating Objects lets us easily replace the default Object.
// prototype with the one we want. In this case ...
child.age = 8;

var stringRepresentation = child.toString();
