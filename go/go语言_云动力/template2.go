package main

import (
	"os"
	"text/template"
)

const tp1 = `
知止{{range .}}而后能{{.}}, {{.}}{{end}}而后能得.
`

func main() {
	var 大学 = []string{"定", "静", "安", "虑"}
	tmp1 := template.New("")
	tmp1.Parse(tp1)
	tmp1.Execute(os.Stdout, 大学)
}
