var myParent = {
    a: 10,
    b: 50
};

var myChild = Object.create(myParent);
var myGrandChild = Object.create(myChild);

alert(myGrandChild.a);
myParent.a = 20;
alert(myGrandChild.a);
alert(myChild.a);
