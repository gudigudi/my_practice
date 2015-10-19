package main

import (
	"fmt"
)

var (
	initInt    int    = 1
	initString string = "hello "
)

func init() {
	initInt = 2
}

func main() {
	fmt.Println("After init function: ")
	fmt.Println("initInt is ", initInt)
	fmt.Println("initString is ", initString)
}
