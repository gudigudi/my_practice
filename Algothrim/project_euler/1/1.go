package main

import "fmt"

func main() {
	a := 3
	b := 5
	upper_num := 10
	sum := 0
	i := 1
	j := 1

	for i = 1; i*a < upper_num; i++ {
		sum += i * a
	}
	for j = 1; j*b < upper_num; i++ {
		sum += j * b
	}

	fmt.Println("sum is: %d", sum)
}
