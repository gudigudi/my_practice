package main

import (
	"fmt"
	"time"
)

func main() {
	start := time.Now()
	N := 1024
	for i := 0; i < N; i++ {
		fmt.Println(i)
	}
	end := time.Now()
	delta := end.Sub(start)
	fmt.Println("for loop took time: %s", delta)
}
