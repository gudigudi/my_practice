package main

import (
	"fmt"
)

func main() {
	m := make(map[string]string)
	m["Hello"] = "Bonjour"
	fmt.Println("m is ", m)
	m1 := m
	m1["Hello"] = "Salut"
	fmt.Println("m is ", m)
	fmt.Println("m1 is ", m1)
}
