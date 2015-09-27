package main

import (
	"fmt"
)

func main() {
	var a, b int = 100, 200
	var ret int

	ret = max(a, b)

	fmt.Printf("the number is : %d\n", ret)
}

func max(num1, num2 int) int {
	var result int

	if num1 > num2 {
		result = num1
	} else {
		result = num2
	}
	return result
}
