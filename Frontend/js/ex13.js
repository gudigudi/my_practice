var functionState = {};

function expensiveOperation() {
    var startTime = new Date().getMilliseconds();
    while ((new Date().getMilliseconds() - startTime) < 100) {
        // TODO: ...
    }

    if (!functionState.isFinished) {
        // ...
        setTimeout(expensiveOperation(), 10);
    }
}
