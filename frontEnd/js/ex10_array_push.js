var array = [];

array.push(1);
array.push(2);
array.push(3);

array.push("four");
array.push("five");

array.push(3.1415926);

console.log(array);

for (var item in array) {
    console.log(array[item], typeof array[item]);
}

