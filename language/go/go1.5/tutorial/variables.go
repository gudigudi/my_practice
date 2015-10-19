package main

import (
	"fmt"
)

var (
	a    int
	b    bool
	c, d int = 1, 2
	e, f     = 123, "hello"
	x, y int
)

func main() {
	g, h := 123, "hello"
	fmt.Println(x, y, a, b, c, d, e, f, g, h)
}
