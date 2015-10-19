package main

func main(){
    num := 20
    isFound := false
    the_num := num + 1

    while !isFound {
        for i := 2; i < num; i++ {
            if the_num % i != 0 {
                the_num++
                break
            }
        }
    }
}
