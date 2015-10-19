package main

import (
	"fmt"
)

const (
	Sunday = iota // 0
	Monday
	Tuesday
	Wednesday
	Thurday
	Friday
	Saturday
)

func main() {
	fmt.Println(Sunday, Monday, Tuesday,
		Wednesday, Thurday, Friday,
		Saturday)
}
