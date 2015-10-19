var print = function(str){
  return console.log(str);
};

var pattern = /\w{4}(\d{4})(\w{2})/;
var result = pattern.exec("yunn0871cg");
print("city code = " + result[1] + ", county code = " + result[2]);
result = pattern.exec("shax0917cc");
print("city code = " + result[1] + ", county code = " + result[2]);

