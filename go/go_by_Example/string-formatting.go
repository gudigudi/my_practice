package main

import (
	"fmt"
	"os"
)

type point struct {
	x, y int
}

func main() {
	p := point{1, 2}
	fmt.Println("%v", p)

	fmt.Println("%+v", p)

	fmt.Println("%#v", p)

	fmt.Println("%T", p)
	fmt.Println("%t", true)
	fmt.Println("%d", 123)

	fmt.Println("%b", 14)
	fmt.Println("%c", 33)
	fmt.Println("%x", 456)
	fmt.Println("%f", 78.9)

	fmt.Println("%s", "\"string\"")

	fmt.Println("%q", "\"string\"")
	fmt.Println("%x", "hex this")

}
