function Person( name ) {
  this.name = name;
}

Person.prototype.getName = function() {
  return this.name;
};

function User( name, password ) {
  this.name = name;
  this.password = password;
};

User.prototype = new Person();
User.prototype.getPassword = function () {
  return this.password;
};
