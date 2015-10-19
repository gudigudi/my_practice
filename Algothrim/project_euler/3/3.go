package main

import (
	"fmt"
)

func main() {
	var num uint64 = 600851475143
	var smallest_prime_factor uint64 = 2

	for num%smallest_prime_factor != 0 {
		smallest_prime_factor++
	}

	fmt.Println("largest_prime_factor is:", num/smallest_prime_factor)
}
