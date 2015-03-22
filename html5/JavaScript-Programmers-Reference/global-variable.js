function greet(myName) {
    myAlertString = "Hello " + myName;
    function doAlert() {
        console.log(myAlertString);
    }
    return doAlert;
}

var greetKitty = greet("Kitty");
greetKitty();
var greetMax = greet("Max");
greetMax();
greetKitty();
var greetLenore = greet("Lenore");
greetLenore();
greetKitty();
greetMax();
