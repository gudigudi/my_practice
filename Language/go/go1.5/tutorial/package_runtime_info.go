package main

import (
	"fmt"
	"runtime"
)

func main() {
	fmt.Println(runtime.Compiler)
	fmt.Println(runtime.GOARCH)
	fmt.Println(runtime.GOOS)
	fmt.Println(runtime.GOROOT())
	fmt.Println(runtime.NumCPU())
	fmt.Println(runtime.NumGoroutine())
	fmt.Println(runtime.Version())
}
