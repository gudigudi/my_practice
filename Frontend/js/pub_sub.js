var PubSub = {

  // subscribe
  subscribe: function(name, callback) {
    this.list = {}
    this.list[name] = callback;
  },
  // publish
  publish: function(name, data) {
    this.list[name](data)
  }
};

PubSub.subscribe('event', function(data) {
  alert(data);
});

PubSub.publish('event', '发送一个数据');
