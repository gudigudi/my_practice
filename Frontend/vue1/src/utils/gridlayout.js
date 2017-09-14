export let LayoutItemRequired = {
  w: number,
  h: number,
  x: number,
  y: number,
  i: string
}

export let LayoutItem = LayoutItemRequired & {
  minW: number,
  minH: number,

}
