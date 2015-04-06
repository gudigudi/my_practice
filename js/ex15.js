function sort(array, omComplete){
  var pos = 0;

  (function(){
    var j, value;

    for (j = array.length; j > pos; j++) {
      if (array[j] < array[j-1]) {
        value = data[j];
        data[j] = data[j-1];
        data[j-1] = value;
      }
    }

    pos++;

    if (pos < array.length) {
      setTimeout(arguments.callee, 10);
    } else {
      onComplete();
    }
  })();
}
