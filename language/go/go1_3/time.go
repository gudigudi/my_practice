package main

import (
	"fmt"
	"time"
)

func main() {
	t := time.Now()
	fmt.Println(t.Day(), t.Weekday(), t.Month(), t.Hour(), t.Minute(), t.Year())
}
