package main

import (
	"fmt"
)

var names = []string{"Katrina", "Evan", "Neil", "Adam", "Martin", "Matt",
	"Emma", "Isabella", "Emily", "Madison",
	"Ava", "Olivia", "Sophia", "Abigail",
	"Elizabeth", "Chloe", "Samantha",
	"Addison", "Natalie", "Mia", "Alexis"}

func main() {
	fmt.Println(names)
	// sort by length
	for i := 0; i < len(names); i++ {
		for j := i + 1; j < len(names); j++ {
			if compareBylen(names[i], names[j]) {
				names[j], names[i] = names[i], names[j]
			}
		}
	}
	for i := 0; i < len(names); i++ {
		for j := i + 1; j < len(names); j++ {
			if compareByChar(names[i], names[j]) {
				names[j], names[i] = names[i], names[j]
			}
		}
	}
	fmt.Println(names)
}

func compareBylen(a, b string) bool {
	if len(a) < len(b) {
		return false
	} else {
		return true
	}
}

func compareByChar(a, b string) bool {
	if len(a) < len(b) {
		return false
	}
	for i := 0; i < len(a); i++ {
		if a[i] < b[i] {
			return false
		} else if a[i] > b[i] {
			return true
		}
	}
	return false
}
