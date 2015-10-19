
function Lecture(name, teacher) {
  this.name = name;
  this.teacher = teacher;
}

Lecture.prototype.display = function() {
  return this.teacher + " si teaching " + this.name;
}

function Schedule(lectures) {
  this.lecture = lectures;
}

Schedule.prototype.display = function() {
  var str = "";

  for (var i = 0; i < this.lecture.length; i++) {
    str += this.lecture[i].display + " ";
  }

  return str;
}

var mySchedule = new Schedule([
  new Lecture("Gym", "Mr. Smith"),
  new Lecture("Math", "Mrs. Jones"),
  new Lecture("English", "TBD")
]);

console.log(mySchedule.display());
