package main

import (
	"os"
	"text/template"
)

const tp1 = `
How many roads must {{.}} walk down
Before they call him {{.}}
`

func main() {
	tmp1 := new(template.Template)
	tmp1.Parse(tp1)
	tmp1.Execute(os.Stdout, "a man")
}
