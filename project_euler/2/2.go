package main

import (
	"fmt"
)

func main() {
	var a uint64 = 1
	var b uint64 = 2
	var sum uint64 = a + b
	var tmp_b uint64

	for b < 4000000 {
		tmp_b = b
		b = a + tmp_b
		a = tmp_b

		sum += b
	}

	fmt.Println("sum is:", sum)
}
