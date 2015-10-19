package main

import (
	"fmt"
)

func main() {
	var a int = 20
	var ip *int

	ip = &a

	fmt.Printf("a value's address : %x\n", &a)
	fmt.Printf("ip value's address : %x\n", ip)
	fmt.Printf("*ip value's address : %d\n", *ip)

}
