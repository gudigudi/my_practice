package main

import (
	"fmt"
)

// return the max of many numbers
func get_max(list []int) int {
	max := list[0]
	for i := range list {
		if i > max {
			max = i
		}
	}
	return max
}

func main() {
	list := []int{1, 2, 3, 4, 5, 6, 7, 8}
	fmt.Println("The max is: ", get_max(list))
}
