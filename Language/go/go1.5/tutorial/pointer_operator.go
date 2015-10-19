package main

import (
	"fmt"
)

func main() {
	var (
		a   int = 4
		b   int32
		c   float32
		ptr *int
	)

	fmt.Printf("1 - a variable type is = %T\n", a)
	fmt.Printf("2 - b variable type is = %T\n", b)
	fmt.Printf("3 - c variable type is = %T\n", c)

	ptr = &a
	fmt.Printf("value of a is %d\n", a)
	fmt.Printf("ptr is %d\n", *ptr)
}
