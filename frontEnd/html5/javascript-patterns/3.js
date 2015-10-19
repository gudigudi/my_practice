function outer(a, b) {
    var c = 1,
        d = 2,
        inner;
    if (a > b) {
        inner = function() {
            return {
                r: c - d
            };
        };
    } else {
        inner = function() {
            return {
                r: c + d
            };
        };
    }
    return inner;
}
