package main

import (
	"fmt"
	"github.com/tylertreat/BoomFilters"
)

func main() {
	sbf := boom.NewDefaultScalableBloomFilter(0.01)

	sbf.Add([]byte(`a`))
	if sbf.Test([]byte(`a`)) {
		fmt.Println("contains a")
	}

	if !sbf.TestAndAdd([]byte(`a`)) {
		fmt.Println("doesn's contains b")
	}

	if sbf.Test([]byte(`b`)) {
		fmt.Println("now it contains b!")
	}

	// Restore to initial state.
	sbf.Reset()
}
