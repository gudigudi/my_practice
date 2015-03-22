package main

import "fmt"

func main() {
	x := 1

	switch i := x.(type) {
	case nil:
		fmt.Println("x is nil")
	case int:
		fmt.Println("x is int")
	case float64:
		fmt.Println("x is float64")
	default:
		fmt.Println("don't know the type")
	}
}
