package main

import (
	"fmt"
)

func main() {
	type Point [2]float32
	type Line [2]Point

	a := Point{1, 2}
	b := a
	c := Line{a, b}
	b[0] = 42
	s := "%v, %v, %v"
	fmt.Println(s, a, b, c)
}
