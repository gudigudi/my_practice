var error = new Error();

if (error.name) {
  console.log(error.name + ": " + error.message);
}

function throwit() {
  throw new Error('');
}

function catchit() {
  try {
    throwit();
  } catch(e) {
    console.log(e.stack);  // print stack trace
  }
}

catchit();
