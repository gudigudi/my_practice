package main

import (
	"log"
	"net"
)

func main() {
	ln, err := net.Listen("tcp", ":8989")
	if err != nil {
		log.Fatal(err)
	}
	for {
		conn, err := ln.Accept()
		if err != nil {
			continue
		}
		go func() {
			// handle connection
			_ = conn
		}()
	}
}
