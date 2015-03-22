var sentence = 'This is one sentence. This is a sentence with a list of items:'
    + 'cherries, orange, apples, bananas. That was the list of items.';
var start = sentence.indexOf(':');
var end   = sentence.indexOf('.', start+1);

var listStr = sentence.substring(start+1, end);

console.log(start, end, listStr);
