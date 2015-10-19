var person = {
  name: "Nicholas",
  age: 30
};

function displayInfo(){
  var count = 5;
  with(person){
    console.log(name + " is " + age);
    console.log("Count is " + count);
  }
}

displayInfo();
