var another = ["this", "is", "another", "array"];
var another2 = ["yet", "another", "array"];

var bigArray = [].concat(another, another2);

console.log(bigArray);
console.log(bigArray.slice(5, 9));
console.log(bigArray.splice(5, 2));
console.log(bigArray.splice(5, 0, "very", "new", "item", "here"));
