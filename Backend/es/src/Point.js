class Point {
  constructor (x, y) {
    this.x = x;
    this.y = y;
  }

  r () {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }
}

let p = new Point(1, 1);

console.log(p.r());