package main

import (
	"fmt"
)

func main() {
	fmt.Printf("%T\n", 'a')

	var c1, c2 rune = '\u6211', '们'
	fmt.Println(c1 == '我', string(c2) == "\xe4\xbb\xac")
}
