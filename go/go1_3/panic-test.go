package main

import "fmt"

var (
	pt = fmt.Printf
	ef = fmt.Errorf
)

func try(fn func(), catch func(e interface{})) {
	defer func() {
		if e := recover(); e != nil {
			catch(e)
		}
	}()
	fn()
}

func main() {
	// 直接使用
	try(func() {
		panic("foo")
		panic("bar")
		panic("baz")
	}, func(e interface{}) {
		pt("catch %v\n", e)
	})

	// panic转成error
	func() (err error) {
		try(func() {
			panic("foo")
			panic("bar")
			panic("baz")
		}, func(e interface{}) {
			err = e.(error)
		})
		if err != nil {
			return
		}
		// ok
		// ...
		return nil
	}()
}
