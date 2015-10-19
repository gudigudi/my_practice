var stack = new Array();

stack['first'] = 3.1415926;
stack['second'] = "okay then.";
stack['third'] = new Date();

for (var item in stack) {
    console.log(typeof stack[item]);
}

