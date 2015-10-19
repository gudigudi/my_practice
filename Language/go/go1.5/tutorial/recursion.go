package main

import (
	"fmt"
)

func main() {
	var i int = 15
	fmt.Printf("%d's factorial number is %d\n", i, factorial(i))
}

func factorial(x int) (result int) {
	if x == 0 {
		result = 1
	} else {
		result = x * factorial(x-1)
	}
	return
}
