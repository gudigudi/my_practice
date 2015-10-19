var events = require('events');

var eventEmitter = new events.EventEmitter();

var connectHandler = function connected() {
  console.log('connect success');

  // toggle data_received event
  eventEmitter.emit('data_received');
};

