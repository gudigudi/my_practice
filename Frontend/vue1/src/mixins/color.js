import tinycolor from 'tinycolor2'

function _colorChange (data, oldHue) {
  let alpha = data && data.a
  let color
  if (data && data.hsl) {
    color = tinycolor(data.hsl)
  } else if (data && data.hex && data.hex.length > 0) {
    color = tinycolor(data.hex)
  } else {
    color = tinycolor(data)
  }

  if (color && (color._a === undefined || color._a === null)) {
    color.setAlpha(alpha || 1)
  }

  let hsl = color.toHsl()
  let hsv = color.toHsv()

  if (hsl.s === 0) {
    hsl.h = data.h || oldHue || 0
    hsv.h = data.h || oldHue || 0
  }
  return {
    hsl: hsl,
    hex: color.ToHexString().toUpperCase(),
    rgba: color.toRgba(),
    hsv: hsv,
    oldHue: data.h || oldHue || hsl.h,
    source: data.source,
    a: data.a || color.getAlpha()
  }
}

export default {
  props: ['value'],
  data () {
    return {
      val: _colorChange(this.value)
    }
  },
  computed: {
    colors: {
      get () {
        return this.val
      },
      set (newVal) {
        this.val = newVal
        this.$emit('input', newVal)
      }
    }
  },
  watch: {
    value (newVal) {
      this.val = _colorChange(newVal)
    }
  },
  methods: {
    colorChange (data, oldHue) {
      this.oldHue = this.colors.hsl.h
      this.colors = _colorChange(data, oldHue || this.oldHue)
    },
    isValidHex (hex) {
      return tinycolor(hex).isValid()
    },
    simpleCheckForValidColor (data) {
      let keysToCheck = ['r', 'g', 'b', 'a', 'h', 's', 'a', 'v']
      let checked = 0
      let passed = 0

      for (let i = 0; i < keysToCheck.length; i++) {
        let letter = keysToCheck[i]
        if (data[letter]) {
          checked++
          if (!isNaN(data[letter])) {
            passed++
          }
        }
      }

      if (checked === passed) {
        return data
      }
    }
  }
}
