package main

import (
	"fmt"
	"runtime"
	"strconv"
	"time"
)

type Sum []int

func (s Sum) Calculate(count, start, end int, flag string, ch chan int) {
	cal := 0

	for i := start; i <= end; i++ {
		for j := 1; j <= 3000000; j++ {
		}
		cal += i
	}

	s[count] = cal
	fmt.Println("flag : ", flag, ".")
	ch <- count
}

func (s Sum) LetsGo() {
	// runtime.NumCPU() can get CPU numbers, in here we set it 4
	const NCPU = 4
	const RANGE = 10000
	var ch = make(chan int)

	runtime.GOMAXPROCS(NCPU)
	for i := 0; i < NCPU; i++ {
		go s.Calculate(i, (RANGE/NCPU)*i+1, (RANGE/NCPU)*(i+1),
			strconv.Itoa(i+1), ch)
	}

	for i := 0; i < NCPU; i++ {
		<-ch
	}
}

func main() {
	var s Sum = make([]int, 4, 4)
	var sum int = 0
	var startTime = time.Now()

	s.LetsGo()

	for _, v := range s {
		sum += v
	}

	fmt.Println("sum is : ", sum, "take time : ", time.Now().Sub(startTime),
		" seconds.")
}
