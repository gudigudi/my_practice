package main

import (
	"fmt"
	"reflect"
)

type S struct {
	x int64
	y float64
	z string
}

func main() {
	var s S
	fmt.Println(reflect.TypeOf(s))
	fmt.Println(reflect.TypeOf(s).PkgPath())
	fmt.Println(reflect.TypeOf(s).String())
}
