/*
test iota
author: huolingdfsq@gmail.com
date: 2015.1.23
*/

package main

import (
	"fmt"
)

func main() {
	const (
		_        = iota
		KB int64 = 1 << (10 * iota)
		MB
		GB
		TB
	)
	fmt.Println(KB)
	fmt.Println(MB)
	fmt.Println(GB)
	fmt.Println(TB)
}
