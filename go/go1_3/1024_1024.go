package main

import (
	"fmt"
	"math/rand"
)

func main() {
	a := [1024]int{}
	b := [1024]int{}

	for i := range a {
		i = rand.Intn(1024)
		fmt.Printf("%d\t", i)
	}
	for i := range b {
		i = rand.Intn(1024)
		fmt.Printf("%d\t", i)
	}
	fmt.Println(analysis(a, b) / (i * j))
}

func analysis(a []int, b []int) {
	sum := 0

	for i := range a {
		for j := range b {
			sum += i + j
		}
	}

	return sum
}
