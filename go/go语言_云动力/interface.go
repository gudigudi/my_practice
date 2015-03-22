package main

import (
	"fmt"
)

type Duck float32

func (Duck) Quack() string {
	return "嘎"
}

type Educk complex128

func (Educk) Quack() string {
	return "叮咚"
}

type Quacker interface {
	Quack() string
}

func main() {
	var d Duck = 0.
	var e Educk = 0i
	var q Quacker
	fmt.Println(d, e, q)
	q = d
	fmt.Println(q.Quack())
	q = e
	fmt.Println(q.Quack())
}
