/*
goroutine vs C thread
test goroutine running time

*/

package main

func forfunc(forNum int) {
	sum := 0
	for i := 0; i < forNum; i++ {
		sum += i
	}
}

func main() {

	var goroutineTime int = 2 << 10

	for i := 0; i < goroutineTime; i++ {
		go forfunc(goroutineTime)
	}
}
