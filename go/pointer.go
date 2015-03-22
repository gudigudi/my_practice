package main

import "fmt"

func main() {
	var i1 = 5
	fmt.Println("An integer: %d, its location in memory: %p\n", i1, &i1)
	var intP *int
	intP = &i1
	fmt.Println("The value at memory location %p is %d", intP, *intP)
}
