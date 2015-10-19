function testScope() {
    var myTest = true;
    if (true) {
        var myTest = "I am changed!";
    }
    console.log(myTest);
}

testScope();
