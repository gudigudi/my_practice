package main

import (
	"fmt"
)

var (
	c = make(chan int, 10)
	a string
)

func f() {
	a = "hello, world"
	c <- 0
}

func main() {
	go f()
	<-c
	fmt.Println("a is ", a)
	fmt.Println("c is ", c)
}
