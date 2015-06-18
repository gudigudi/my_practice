package main

import (
	"fmt"
	"os"
)

func main() {
	name := "test.txt"
	f, err := os.OpenFile(name, os.O_RDONLY, 0)
	if err != nil {
		return err
	}
	d, err := f.Stat()
	if err != nil {
		return err
	}
	fmt.Println(d)
}
