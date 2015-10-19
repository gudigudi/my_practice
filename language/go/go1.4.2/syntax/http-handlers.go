package main

import (
	"log"
	"net/http"
)

type String string

type Struct struct {
	Greeting string
	Punct    string
	Who      string
}

func main() {
	// http.Handle calls here
	http.Handle("/string", String("I'm a frayed knot."))
	http.Handle("/struct", &Struct{"Hello", ":", "Gopher!"})

	log.Fatal(http.ListenAndServe("localhost:4000", nil))
}
