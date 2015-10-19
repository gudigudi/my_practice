package main

import (
	"log"
	"net/http"
	"sync"
)

func main() {
	p := new(Proxy)
	http.Handle("/", p)
	log.Fatal(http.ListenAndServe("localhost:8080", nil))
}

type Proxy struct {
	mu     sync.Mutex
	target string // "host:port"
}

func (p *Proxy) serveHTTP(w http.ResponseWriter, r *http.Request) {
	if r.URL.Path == "/_tipstatus" {
		p.serveStatus(w, r)
		return
	}
}

func (p *Proxy) serveStatus(w http.ResponseWriter, r *http.Request) {

}
