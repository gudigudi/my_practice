var o = new Object();

console.log(o.constructor === Object);

var o = new Object(1);
console.log(o.constructor === Number);
console.log(o.toFixed(2));

var o = new Object("I am a string");
console.log(o.constructor === String);

console.log(typeof o.substring);

var o = new Object(true);
console.log(o.constructor === Boolean);
