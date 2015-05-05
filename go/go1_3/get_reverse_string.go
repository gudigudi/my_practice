package main

import (
	"fmt"
)

func main() {
	input := "I am a student"

	var l []string

	index := 0
	for i := 0; i < len(input); i++ {
		if input[i] != ' ' {
			append(l[index], input[i])
		}
	}

	for v := range l {
		fmt.Println(v)
	}
}
