
// create a XMLHttpRequest example object
var xhr = new XMLHttpRequest();

xhr.onreadystatechange = function() {
  var completed = 4;
  if (xhr.readyState === completed) {
    if (xhr.state === 200) {
      // do something
    } else {
      // do something
    }
  }
};

xhr.open('GET', '/endpoint', true);

xhr.send(null);

xhr.open('POST', encodeURI('someURL'));
xhr.setRequestHeader('Content-Type', 'application/x-www.form-urlencoded');
xhr.onload = function() {};
xhr.send(encodeURI('dataString'));


