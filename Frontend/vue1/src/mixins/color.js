class Color {
  let
  _ok;
  let
  _originalInput;
  let
  _format;
  let
  _alpha;

  constructor (val) {
    if (typeof val === 'string') {

    } else {
      throw Error('value is not a string')
    }
  }

  isDark () {
    return this.getBrightness() < 128;
  }

  isLight () {
    return !this.isDark();
  }

  isValid () {
    return this._ok;
  }

  getOriginalInput () {
    return this._originalInput;
  }

  getFormat () {
    return this._format;
  }

  getAlpha () {
    return this._alpha;

  }
}

function _colorChange (data, oldHue) {
  let alpha = data && data.a
  let color

  if (data && data.hsl) {
    color =
  }
}

export default {
  props: ['value'],
  data () {
    return {
      val: _colorChange(this.value)
    }
  }
}
