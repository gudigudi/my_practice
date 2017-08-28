// get {x,y} positions from event.
function getControlPosition (e) {
  return offsetXYFromParentOf(e)
}

// get from offsetParent
function offsetXYFromParentOf (e) {
  const offsetParent = e.target.offsetParent || document.body
  const offsetParentRect = e.offsetParent === document.body ? {left: 0, top: 0} : offsetParent.getBoundingClientRect()

  const x = e.clientX + offsetParent.scrollLeft - offsetParentRect.left
  const y = e.clientY + offsetParent.scrollTop - offsetParentRect.top

  return {x, y}
}

function createCoreData (lastX, lastY, x, y) {
  const isStart = !this.$_.isNumber(lastX)

  if (isStart) {
    return {
      deltaX: 0, deltaY: 0,
      lastX: x, lastY: y,
      x: x, y: y
    }
  } else {
    return {
      deltaX: x - lastX, deltaY: y - lastY,
      lastX: lastX, lastY: lastY,
      x: x, y: y
    }
  }
}

export default {getControlPosition, offsetXYFromParentOf, createCoreData}
