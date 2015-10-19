var Objectmaker = function () {
    this.name = "This is it";
    var that = {};
    that.name = "And that's that";
    return that;
};

var o = new Objectmaker();
console.log(o.name);
