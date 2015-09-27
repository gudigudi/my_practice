package main

import (
	"fmt"
)

func main() {
	var numbers []int
	printSlice(numbers)

	// append a item to slice
	numbers = append(numbers, 0)
	printSlice(numbers)

	// add multiple items to slice
	numbers = append(numbers, 2, 3, 4)
	printSlice(numbers)

	// create slice numbers1 : two cap of numbers
	numbers1 := make([]int, len(numbers), cap(numbers)*2)
	copy(numbers1, numbers)
	printSlice(numbers1)
}

func printSlice(x []int) {
	fmt.Printf("len=%d cap=%d slice=%v\n", len(x), cap(x), x)
}
