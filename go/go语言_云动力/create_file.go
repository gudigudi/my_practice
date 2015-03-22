package main

import (
	"fmt"
	"os"
)

func main() {
	f, err := os.Create("ip.txt")
	if err != nil {
		fmt.Println("cannot Create file")
	}
	defer f.Close()

	num := 2 * 2 * 2 * 2 * 2
	for i := 0; i < num; i++ {
		for j := 0; j < num; j++ {
			for k := 0; k < num; k++ {
				for m := 0; m < num; m++ {
					fmt.Printf("%d.%d.%d.%d\n", i, j, k, m)
				}
			}
		}
	}
}
