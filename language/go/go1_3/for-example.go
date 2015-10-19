package main

import (
	"fmt"
)

func main() {
	sum := 0
	for i := 0; i < (2 << (3 + 10 + 10 + 10)); i++ {
		sum += i
	}
	fmt.Println("sum is equal to ", sum)
}
