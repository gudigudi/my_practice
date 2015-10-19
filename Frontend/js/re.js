var variable = /[a-zA-Z_][a-zA-Z0-9_]*/;

print = function(str){
  console.log(str);
};

print(variable.test("hello"));
print(variable.test("world"));
print(variable.test("_main_"));
print(variable.test("0871"));
