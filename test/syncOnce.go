package main

import (
	"fmt"
	"sync"
	"time"
)

var once sync.Once
var Gid int

func setup() {
	Gid++
	fmt.Println("Called once")
}

func doprint() {
	once.Do(setup)
	fmt.Println("doprint()...")
}

func main() {
	go doprint()
	go doprint()
	go doprint()
	go doprint()

	time.Sleep(time.Second)
	fmt.Println("Gid: ", Gid)
}
