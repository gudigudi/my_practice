var smartFib = (function makeFib() {
    var fibsequence = [0, 1, 1];
    var fib = function fib(n) {
        if (fibsequence[n]) {
            return fibsequence[n];
        }
        fibN = fib(n - 1) + fib(n - 2);
        fibsequence[n] = fibN;
        return fibsequence[n];
    };
    return fib;
}());

Function.prototype.decorate = function Decorate(params) {
    return params.decorator(this, params.initialData);
};

var cache = function cache(lambda, initial) {
    return function cacheRunner(n) {
        if (initial[n] !== undefined) {
            return initial[n];
        } else {
            initial[n] = lambda(n);
            return initial[n];
        }
    };
};

var decoratedFib = function fib(n) {
    return decoratedFib(n - 1) + decoratedFib(n - 2);
}.decorate({
    decorator: cache,
    initialData: [0, 1, 1]
});


