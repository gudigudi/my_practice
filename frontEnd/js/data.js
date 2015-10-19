
/*
Methods
 */

var doh = "Doh";
console.log(typeof doh.toUpperCase); // function
console.log(doh.toUpperCase()); // DOH

var mack = [];
mack.push("Mack");
mack.push("the", "Knife");
console.log(mack);

console.log(mack.join(" "));
console.log(mack.pop());
console.log(mack);

/*
 * Object
 */
var day1 = {
  squirrel: false,
  events: ["work", "touched tree", "pizza", "running",
        "television"]
};
console.log(day1.squirrel);
console.log(day1.wolf);
day1.wolf = false;
console.log(day1.wolf);
