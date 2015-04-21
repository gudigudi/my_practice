'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function() {

  it("should do something", function() {

  });

});

describe('PhoneCat controllers', function(){

  describe('PhoneListCtrl', function(){

    it('should create "phone" model with 3 phones', function(){
      var scope = {};
      ctrl = new PhoneListCtrl(scope);

      expect(scope.phones.length).toBe (3);
    });
  });
});