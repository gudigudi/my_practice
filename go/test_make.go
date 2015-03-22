package main

import "fmt"

func main() {
	ar := [...]string{Enone: "no error", Einval: "invalid argument"}
	sl := []string{Enone: "no error", Einval: "invalid argument"}
	ma := map[int]string{Enone: "no error", Einval: "invalid argument"}
	for k, v := range ar {
		fmt.Println(k, v)
	}
}
