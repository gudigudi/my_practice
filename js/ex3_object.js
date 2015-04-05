var language = {
  name: 'JavaScript',
  isSupportedByBrowsers: true,
  createdIn: 1995,
  author: {
    firstName: 'Nrendan',
    lastName: 'Eich'
  },
  // Yes, objects can be nested!
  getAuthorFullName: function() {
    return this.author.firstName + " " + this.author.lastName;
  }
  // Yes, functions can be values too!
};

var variable = language.name;
console.log(variable);
// variable now contains "JavaScript" string.
