package main

import (
	"fmt"
)

type print fmt.Println

func main() {
	var c, c1, c2, c3 chan int
	var i1, i2 int
	select {
	case i1 = <-c1:
		print("received ", i1, " from c1")
	case c2 <- i2:
		print("sent ", i2, " to c2")
	case i3, ok := <-c3:
		if ok {
			print("received ", i3, " from c3")
		} else {
			print("c3 is closed")
		}
	default:
		print("no communication")
	}
	for {
		select {
		case c <- 0:
		case c <- 1:
		}
	}
	select {}
}
