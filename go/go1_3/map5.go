package main

import "fmt"

var (
	coins = 50
	users = []string{
		"Matthew", "Sarah", "Augustus", "Heidi", "Emilie",
		"Peter", "Giana", "Adriano", "Aaron", "Elizabeth",
	}
	distribution = make(map[string]int, len(users))
)

func main() {
	coinsForUser := func(name string) int {
		var total int
		for i := 0; i < len(name); i++ {
			switch string(name[i]) {
			case "a", "A":
				total++
			case "e", "E":
				total++
			case "i", "I":
				total += 2
			case "o", "O":
				total += 3
			case "u", "U":
				total += 4
			default:
			}
		}
		return total
	}

	for _, name := range users {
		v := coinsForUser(name)
		if v > 0 {
			v = 10
		}
		distribution[name] = v
		coins -= v
	}
	fmt.Println(distribution)
	fmt.Println("Coins left: ", coins)
}
