package main

import "fmt"

func main() {
	for i := 0; i < 1024; i++ {
		for j := 0; j < 1024; j++ {
			for k := 0; k < 1024; k++ {
				fmt.Println(i, j, k)
			}
		}
	}
}
