// integer
console.log(1 === 1.0);
console.log(1 + 1.0);

// float
console.log(0.1 + 0.2 === 0.3);  // float Error: false

// Math
console.log(Math.pow(2, 53));
console.log(Math.pow(2, 53) + 1);

var x = 0.5;
for (var i = 0; i < 25; i++) x = x * x;
console.log(x);

// get Max-num in Number
console.log(Number.MAX_VALUE);
console.log(Number.MIN_VALUE);

console.log(123e3);

console.log(Infinity);

console.log(isFinite(Infinity));

// String
var str = "Hello, world!";
console.log(str);

var s = '\u00A9';
console.log(s);

var s = "\uD834\uDF06";
console.log(s);
console.log(s.length);
console.log(s.charAt(0));
console.log(s.charCodeAt(0));
