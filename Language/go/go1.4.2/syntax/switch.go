/*
use switch to replace if-elif-else, a good design
*/

package main

import (
	"fmt"
)

func unhex(c byte) byte {
	switch {
	case '0' <= c && c <= '9':
		return c - '0'
	case 'a' <= c && c <= 'f':
		return c - 'a' + 10
	case 'A' <= c && c <= 'F':
		return c - 'A' + 10
	}
	return 0
}
func main() {
	var i byte = 0
	for ; i < (2<<7 - 1); i++ {
		fmt.Println(unhex(i))
	}
}
