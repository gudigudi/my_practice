false.toString();  // 'false'
[1, 2, 3].toString();  // '1, 2, 3'

function Foo() {}
Foo.bar = 1;
console.log(Foo.bar);  // 1

var obj = {
    bar: 1,
    foo: 2,
    baz: 3
};

obj.bar = undefined;
obj.foo = null;
delete obj.baz;

for (var i in obj) {
    if (obj.hasOwnProperty(i)) {
        console.log(i, ' ' + obj[i]);
    }
}
