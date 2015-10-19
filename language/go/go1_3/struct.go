package main

import (
	"fmt"
)

type person struct {
	name string
	age  int
}

func main() {
	var P person

	P.name = "Astaxie"
	P.age = 25

	fmt.Printf("The person's name is %s , age is %d", P.name, P.age)
}
