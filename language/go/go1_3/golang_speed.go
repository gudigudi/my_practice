package main

import (
	"fmt"
	"runtime"
	"time"
)

type runs uint

const i = 30000000

func recur(t runs) {
	if t > 0 {
		recur(t - 1)
	}
}

func goroutine(t runs, c chan<- bool) {
	if t > 0 {
		go goroutine(t-1, c)
	} else {
		c <- true
	}
}

func main() {

	runtime.GOMAXPROCS(runtime.NumCPU())

	var starttime time.Time
	var stoptime time.Time

	fmt.Println("\nLets do a loop of", i, "times of doing nothing.")
	starttime = time.Now()
	var t runs
	for t = 0; t < i; t++ {
	}
	stoptime = time.Now()
	fmt.Println("Time needed:", float32(stoptime.UnixNano()-starttime.UnixNano())/1000000000.0, "sec.")

	fmt.Println("\nSimple recursion:")
	starttime = time.Now()
	recur(i)
	stoptime = time.Now()
	fmt.Println("Time needed:", float32(stoptime.UnixNano()-starttime.UnixNano())/1000000000.0, "sec.")

	fmt.Println("\nWorking with go-routines and a channel than")
	starttime = time.Now()
	done := make(chan bool)
	go goroutine(i, done)
	<-done
	stoptime = time.Now()
	fmt.Println("Time needed:", float32(stoptime.UnixNano()-starttime.UnixNano())/1000000000.0, "sec.")

	fmt.Println("\n")
}
