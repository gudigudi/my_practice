package main

import (
	"fmt"
)

type Books struct {
	title   string
	author  string
	subject string
	book_id int
}

func main() {
	var Book1, Book2 Books

	// Description of Book1
	Book1.title = "Golang in learn"
	Book1.author = "www.golang.com"
	Book1.subject = "golang tutorial"
	Book1.book_id = 6495407

	// Description of Book2
	Book2.title = "Python as your first PL"
	Book2.author = "www.python.com"
	Book2.subject = "Python tutorial"
	Book2.book_id = 6495700

	// Print messages of Book1 and Book2
	fmt.Printf("Book 1 title : %s\n", Book1.title)
	fmt.Printf("Book 1 author : %s\n", Book1.author)
	fmt.Printf("Book 1 subject : %s\n", Book1.subject)
	fmt.Printf("Book 1 book_id : %d\n", Book1.book_id)

	fmt.Printf("Book 2 title : %s\n", Book2.title)
	fmt.Printf("Book 2 author : %s\n", Book2.author)
	fmt.Printf("Book 2 subject : %s\n", Book2.subject)
	fmt.Printf("Book 2 book_id : %d\n", Book2.book_id)
}
