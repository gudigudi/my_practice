

// Each of the following options will create a new empty object;
var newObject = {};

// ES 3 compatible approaches
// 1. Dot syntax
// Set properties
var value = newObject.someKey;

// 2. Square bracket syntax
// Set properties
newObject["someKey"] = "Hello World";
// Get properties
var value = newObject["someKey"];


// ES 5
// 3. Object properties
Object.defineProperty( newObject, "someKey", {
  value: "for more control of the property's behavior",
  writable: true,
  enumerable: true,
  configurable: true
});

// If the above feels a little difficult to read, a short-hand could
// be written as follows:
var defineProp = function( obj, key, value ) {
  var config = {
    value: value,
    writable: true,
    enumerable: true,
    configurable: true
  };
  Object.defineProperty( obj, key,config);
};

// To use, we then create a new empty "person" object
var person = Object.create( Object.prototype );

// Populate the object with properties
defineProp( person, "car", "Delorean" );
defineProp( person, "dateOfBirth", "1981" );
defineProp( person, "hasBeard", false );

console.log( person );

// 4. Object.defineProperties
// Set properties
Object.defineProperties( newObject, {

  "someKey": {
    value: "Hello World",
    writable: true
  },

  "anotheKey": {
    value: "Foo bar",
    writable: false
  }
});

// Usage:
//
// Create a race car driver that inherits from the person object
var driver = Object.create( person );

// Set some properties for the driver
defineProp( driver, "topSpeed", "100mph" );

// Get an inherits property (1981)
console.log( driver.dateOfBirth );

// Get the property we set (100mph)
console.log( driver.topSpeed );
