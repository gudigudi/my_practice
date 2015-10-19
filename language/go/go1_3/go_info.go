package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Println(os.Getenv("GOROOT"))
	fmt.Println(os.Getenv("GOBIN"))
	fmt.Println(os.Getenv("GO_VERSION"))
	fmt.Println(os.Getenv("GOPATH"))
}
