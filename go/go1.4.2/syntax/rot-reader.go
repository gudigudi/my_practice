package main

import (
	"io"
	"os"
	"strings"
)

type rot13Reader struct {
	r io.Reader
}

func (rr *rot13Reader) Read(p []byte) (n int, err error) {
	for k, v := range p {
		v++
	}
	return 1, nil
}

func main() {
	s := strings.NewReader("Lbh penpxrq gur pbqr!")
	io.Copy(os.Stdout, &r)
}
