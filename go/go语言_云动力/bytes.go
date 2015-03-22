package main

import (
	"bytes"
	"fmt"
	"strings"
)

func main() {
	s := "你Go了吗?"
	fmt.Println(bytes.Count([]byte(s), nil))
	fmt.Println(strings.Count(s, ""))
	fmt.Println(bytes.Split([]byte(s), nil))
	fmt.Println(strings.Split(s, ""))
}
