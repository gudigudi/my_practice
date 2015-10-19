package main

import (
	"fmt"
)

func main() {
	type bigint int64

	var x bigint = 100
	fmt.Println(x)

	x := 1234
	var b bigint = bigint(x)
	var b2 int64 = int64(b)

	var s myslice = []int{1, 2, 3}
	var s2 []int = s
}
