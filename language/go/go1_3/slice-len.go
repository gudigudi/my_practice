package main

import (
	"fmt"
)

func main() {
	cities := []string{
		"Sanat Monica",
		"San Diego",
		"San Francisco",
	}
	fmt.Println(len(cities))
	countries := make([]string, 42)
	fmt.Println(len(countries))
}
