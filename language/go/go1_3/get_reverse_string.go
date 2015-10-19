package main

import (
	"fmt"
	"strings"
)

func main() {
	input := "I am a student"

	output := strings.Join(strings.Split(input, " "), " ")

	fmt.Println(output)
}
