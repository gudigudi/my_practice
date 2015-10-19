function myJavaScriptFunction() {
    var start = new Date().getMilliseconds();

    // some expensive code is here
    var stop = new Date().getMilliseconds();
    var executionTime = stop - start;
    console.log("myJavaScriptFunction() executed in " + executionTime +
          " milliseconds");
}

myJavaScriptFunction();
