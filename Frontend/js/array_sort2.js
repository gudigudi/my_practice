function sorter(a, b){
  return a - b;
}

var array = [10, 23, 44, 58, 106, 235];
array.sort(sorter);
console.log(array);
