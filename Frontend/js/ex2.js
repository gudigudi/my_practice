// Define two simple functions
var add_2 = function(x) {
  return x + 2;
}
var double = function(x) {
  return 2 * x;
}

// map is cool function that accepts 2 arguments:
//   func    the function to call
//   list    a array of values to call func on
var map = function(func, list) {
  var output = [];    // output list
  for (var idx in list) {
      output.push(func(list[idx]));
  }
  return output;
};

// We use map to apply a function to an entire list
// of inputs to "map" them to a list of corresponding outputs
console.log(map(add_2, [5, 6, 7]));  // => [7, 8, 9]
console.log(map(double, [5, 6, 7])); // => [10, 12, 14]
