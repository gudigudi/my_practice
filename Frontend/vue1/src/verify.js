const verify = {
  phone: {
    rule: /^(13|14|15|17|18)\d{9}$/,
    tip: 'please input correct phone number'
  },
  email: {
    rule: /^([a-zA-Z0-9]+[_|\_|\.|-]?)*[]/,
    tip: 'input correct email'
  },
  other: function (checkStr) {
    if (checkStr === '') return 'not empty'
    return true
  }
}
