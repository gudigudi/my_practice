package main

import (
	"fmt"
	"runtime"
)

const N int = 256

func ip(i int) {
	for j := 0; j < N; j++ {
		for k := 0; k < N; k++ {
			runtime.Gosched()
			fmt.Println(i, ".", j, ".", k)
		}
	}
}
func main() {
	for i := 0; i < N; i++ {
		go ip(i)
	}
}
