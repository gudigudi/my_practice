package main

import (
	"fmt"
	"math/rand"
)

func main() {
	const num = 256 * 256
	var m1 [num][num]int

	var m2 [num][num]int
	sum := 0
	var i, j int

	for i = 0; i < num; i++ {
		for j = 0; j < num; j++ {
			m1[i][j] = rand.Intn(10)
			m2[i][j] = rand.Intn(10)
			sum += m1[i][j] * m2[i][j]
		}
	}
	fmt.Println("average is:", sum/(num*num))
}
