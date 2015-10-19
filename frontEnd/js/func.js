// example for function in js

var square = function(x) {
  return x * x;
}

console.log(square(12));

var makeNoise = function() {
  console.log("Pling!");
};

makeNoise();

var power = function(base, exponent) {
  var result = 1;
  for(var count = 0; count < exponent; count++)
    result *= base;
  return result;
};

console.log(power(2, 10));

// Scope
var x = "outside";

var f1 = function() {
  var x = "inside f1";
};
f1();
console.log(x);

var f2 = function() {
  x = "inside f2";
};
f2();
console.log(x);

// nested Scope
var landscape = function() {
  var result = "";

  var flat = function(size) {
    for(var count = 0; count < size; count++) {
      result += "_";
    }
  };

  var mountain = function(size) {
    result += "/";
    for(var count = 0; count < size; count++)
      result += "'";
    result += "\\";
  };

  flat(3);
  mountain(4);
  flat(6);
  mountain(1);
  flat(1);
  return result;
};

console.log(landscape());


function power(base, exponent) {
  if (exponent == undefined)
    exponent = 2;
  var result = 1;
  for(var count = 0; count < exponent; count++)
    result *= base;
  return result;
}
console.log(power(4));
console.log(power(4, 3));
