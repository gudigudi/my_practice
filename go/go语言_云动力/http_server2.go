package main

import (
	"fmt"
	"net/http"
)

func hi(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "%s 你好", r.URL.Path[1:])
	fmt.Fprintf(w, "%s", r.UserAgent())
}
func main() {
	http.HandleFunc("/", hi)
	http.ListenAndServe(":1234", nil)
}
