package main

import (
	"fmt"
	"math"
	"sort"
)

func main() {
	is := sort.IntSlice{3, 1, 1, 5, 9, 2, 6}
	ss := sort.StringSlice{"士", "农", "工", "商"}
	fs := sort.Float64Slice{math.Inf(-1), math.Inf(+1), math.NaN()}

	sort.Sort(is)
	sort.Sort(ss)
	sort.Sort(fs)

	fmt.Println(is, ss, fs)
}
