package main

import "fmt"

type Vertex struct {
	X, Y int
}

var (
	v1 = Vertex{1, 2}  // 类型为Vertex
	v2 = Vertex{X: 1}  // Y:0被省略
	v3 = Vertex{}      // X:0和Y:0
	p  = &Vertex{1, 2} // 类型为*Vertex
)

func main() {
	fmt.Println(v1, p, v2, v3)
}
