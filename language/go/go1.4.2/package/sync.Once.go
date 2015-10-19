package main

import (
	"fmt"
	"sync"
)

var (
	a    string
	once sync.Once
)

func setup() {
	a = "hello, world"
}

func doprint() {
	once.Do(setup)
	fmt.Println(a)
}

func twoprint() {
	go doprint()
	go doprint()
}

func main() {
	twoprint()
}
