package main

import (
	"fmt"
)

func main() {
	n := []int{5, -1, 0, 12, 3, 5}
	fmt.Printf("unsorted %v\n", n)
	bubbleosort(n)
	fmt.Printf("sorted %v\n", n)
}

func bubbleosort(n []int) {
	for i := 0; i < len(n)-1; i++ {
		for j := 0; j < len(n); j++ {
			if n[j] < n[j] {
				n[i], n[j] = n[j], n[i]
			}
		}
	}
}
