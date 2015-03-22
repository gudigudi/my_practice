package main

import (
    "fmt"
)

func get_palindromic(num int) int {
    palindromic := 0
    while num != 0 {
        palindromic = 10 * palindromic + num % 10
        num /= 10
    }
    return palindromic
}

func main(){
    var largest_palindromic_num int
    isFined := false

    for i := 1000; i > 100; i-- {
        for j := 1000; j > 100; j-- {
            if i*j == get_palindromic(i*j) {
                isFined = true
                largest_palindromic_num = i*j
                break
            }
        }
        if isFined == true {
            break
        }
    }

    fmt.Printf("the number is: %d\n", largest_palindromic_num)
}
