var myTest = true;
function testScope() {
    if (true) {
        var myTest = "I am changed!";
    }
    console.log(myTest);
}

testScope();
console.log(myTest);
