package main

import (
	"fmt"
)

func main() {
	s := [4]int{1, 2, 3, 4}
	t := s[1:3]
	fmt.Println(s[0], t, s[:3], t[1:])
	fmt.Println(len(s), cap(s))
	fmt.Println(len(t), cap(t))
}
