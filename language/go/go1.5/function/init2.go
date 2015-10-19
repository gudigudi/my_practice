package main

import (
	"fmt"
	"runtime"
)

var m map[int]string = map[int]string{
	1: "A",
	2: "B",
	3: "C",
}
var info string

func init() {
	fmt.Println("Map: %v", m)
	info = fmt.Sprintf("OS: %s, Arch: %s", runtime.GOOS, runtime.GOARCH)
}
func main() {
	fmt.Println(info)
}
