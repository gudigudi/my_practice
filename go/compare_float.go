package main

import (
	"fmt"
	"math"
)

func IsEqual(f1, f2, p float64) bool {
	return math.Abs(f1-f2) < p
}
func main() {
	i := 1.001
	j := 1.002
	if IsEqual(i, j, 0.0001) {
		fmt.Println("OK!")
	} else {
		fmt.Println("not OK!")
	}
}
