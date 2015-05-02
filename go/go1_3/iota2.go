package main

import (
	"fmt"
)

const (
	_        = iota             // iota = 0
	KB int64 = 1 << (10 * iota) // iota = 1
	MB                          // iota = 2
	GB
	TB
)

const (
	A, B = iota, iota << 10 // 0, 0 << 10
	C, D                    // 1, 1 << 10
)

func main() {
	fmt.Println(KB, MB, GB, TB, A, B, C, D)
}
