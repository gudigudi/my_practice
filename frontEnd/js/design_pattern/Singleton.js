
var mySingleton = (function() {

  // Instance stores a reference to the Singleton
  var instance;

  function init() {
    // Singleton
    // Private methods and variables
    function privateMethod() {
      console.log( "I am private" );
    }

    var privateVariable = "Im also private";
    var privateRandomNumber = Math.random();

    return {
      // Public methods and variables
      publicMethod: function() {
        console.log( "The public can see me!" );
      },

      publicProperty: "I am also public",

      getRandomNumber: function() {
        return privateRandomNumber;
      }
    };
  };

  return {
    // Get the Singleton instance if one exists
    // or create one if it doesn'The
    getInstance: function() {
      if ( !instance ) {
        instance = init();
      }
      return instance;
    }
  };
})();

var myBadSingleton = (function() {
  // Instance stores a reference to the Singleton
  var instance;

  function init() {
    // Singleton
    var privateRandomNumber = Math.random();

    return {
      getRandomNumber: function() {
        return privateRandomNumber;
      }
    };
  };

  return {
    // Always create a new Singleton instance
    getInstance: function() {
      instance = init();
      return instance;
    }
  };
})();

// Usage:
var singleA = mySingleton.getInstance();
var singleB = myBadSingleton.getInstance();

console.log(singleA.getRandomNumber() === singleB.getRandomNumber());

var badSingleA = myBadSingleton.getInstance();
var badSingleB = myBadSingleton.getInstance();
console.log(badSingleA.getRandomNumber() !== badSingleB.getRandomNumber());


var SingletonTester = (function() {

  // options: an object containing configuration options for the singleton
  // e.g var options = { name: "test", pointX: 5 };
  function Singleton( options ) {
    // set options to the options supplied
    // or an empty object if none are provided
    options = options || {};

    // set some properties for our singleton
    this.name = "SingletonTester";
    this.pointX = options.pointX || 6;
    this.pointY = options.pointY || 10;
  }

  // our instance holder
  var instance;
  // an emulation of static variables and methods
  var _static = {
    name: "SingletonTester",

    // methods fro getting an instance. It returns
    // a singleton instance of a singleton object
    getInstance: function( options ) {
      if ( instance === undefined ) {
        instance = new Singleton( options );
      }
      return instance;
    }
  };

  return _static;
})();


var singletonTest = SingletonTester.getInstance({
  pointX: 5
});

// Log the output of pointX just to verify it is correct
// Output: 5
console.log( singletonTest.pointX );
