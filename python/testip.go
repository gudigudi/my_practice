package main

import (
	"fmt"
)

func main() {
	ip := 256 * 256 * 16
	for i := 0; i < ip; i++ {
		fmt.Println(i)
	}
}
