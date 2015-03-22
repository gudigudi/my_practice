package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println("Pick a number from 0 to 100.")
	fmt.Println("Your number is %d",
		sort.Search(100, func(i int) bool {
			fmt.Println("Is your number <= %d? ", i)
			var s string
			fmt.Scanf("%s\n", &s)
			return s != "" && s[0] == 'y'
		}))
}
