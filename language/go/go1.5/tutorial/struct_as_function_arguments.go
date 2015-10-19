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
	printBook(Book1)
	printBook(Book2)
}

func printBook(book Books) {
	fmt.Printf("Book title : %s\n", book.title)
	fmt.Printf("Book author : %s\n", book.author)
	fmt.Printf("Book subject : %s\n", book.subject)
	fmt.Printf("Book book_id : %d\n", book.book_id)
}
