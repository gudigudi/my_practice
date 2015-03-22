/*
a test about gcd algorithm
*/

package main

import (
	"fmt"
)

func gcd(p int, q int) int {
	if q == 0 {
		return p
	}
	r := p % q
	return gcd(q, r)
}
func main() {
	p, q := 54, 36
	fmt.Printf("gcd(%d, %d) is %d\n", p, q, gcd(p, q))
}
