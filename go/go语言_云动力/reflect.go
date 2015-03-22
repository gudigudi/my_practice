package main

import (
	"fmt"
	"reflect"
)

func main() {
	var x float64 = 3.14
	fmt.Println("type:", reflect.TypeOf(x))
	fmt.Println("value:", reflect.ValueOf(x))
}
