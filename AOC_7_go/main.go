package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"

	"time"
)

func main() {
	defer timer("main")()
	riga := readInput()
	fmt.Println(evalAll(riga))

}

func readInput() [][]string {
	input := os.Args[1]
	file, err := os.Open(input)

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var valori [][]string
	for scanner.Scan() {

		valori = append(valori, strings.Split(scanner.Text(), ": "))

	}
	return valori
}

func evalAll(righe [][]string) int {
	var total int
	for _, riga := range righe {
		tmp, _ := strconv.Atoi(riga[0])
		total += evaluator(tmp, parserValori(riga[1]))

	}
	return total
}

//calcola tutti i possibili valori e li passa a isPossible

func evaluator(risultato int, valori []int) int {

	if calc2(risultato, valori) {
		//fmt.Println("se po fa", risultato, valori)

		return risultato

	}

	//fmt.Println(valori, calc((risultato-valori[len(valori)-1])/concat(valori[len(valori)-1]), valori[:len(valori)-1]) == (risultato-valori[len(valori)-1])/concat(valori[len(valori)-1]))

	return 0
}

func parserValori(valori string) []int {
	var valoriinteri []int
	for _, v := range strings.Split(valori, " ") {
		tmp, _ := strconv.Atoi(v)
		valoriinteri = append(valoriinteri, tmp)
	}
	return valoriinteri
}

func calc(risultato int, operandi []int) int {

	if len(operandi) == 1 {
		return operandi[0]
	}

	//fmt.Println(operandi, calc((risultato-operandi[len(operandi)-1])/concat(operandi[len(operandi)-1]), operandi[:len(operandi)-1]) == (risultato-operandi[len(operandi)-1])/concat(operandi[len(operandi)-1]))
	if calc((risultato-operandi[len(operandi)-1])/concat(operandi[len(operandi)-1]), operandi[:len(operandi)-1]) == (risultato)/concat(operandi[len(operandi)-1]) {
		return calc((risultato-operandi[len(operandi)-1])/concat(operandi[len(operandi)-1]), operandi[:len(operandi)-1])*concat(operandi[len(operandi)-1]) + operandi[len(operandi)-1]
	} else if calc(risultato/operandi[len(operandi)-1], operandi[:len(operandi)-1]) <= risultato/operandi[len(operandi)-1] {
		return calc(risultato/operandi[len(operandi)-1], operandi[:len(operandi)-1]) * operandi[len(operandi)-1]
	} else {
		return calc(risultato-operandi[len(operandi)-1], operandi[:len(operandi)-1]) + operandi[len(operandi)-1]
	}

	//if calc(risultato-operandi[len(operandi)-1], operandi[:len(operandi)-1]) >= 0 && risultato-operandi[len(operandi)-1] == calc(risultato-operandi[len(operandi)-1], operandi[:len(operandi)-1]) {
	//}

}

func calc2(risultato int, operandi []int) bool {
	if len(operandi) == 1 {
		return risultato == operandi[len(operandi)-1]
	}
	//fmt.Println(operandi[:len(operandi)-1], operandi[len(operandi)-1], risultato)
	if calc2(risultato/operandi[len(operandi)-1], operandi[:len(operandi)-1]) && risultato%operandi[len(operandi)-1] == 0 {
		//fmt.Println("Mull", operandi[:len(operandi)-1], operandi[len(operandi)-1], risultato,risultato/operandi[len(operandi)-1]*operandi[len(operandi)-1])

		return true
	}
	if calc2(risultato-operandi[len(operandi)-1], operandi[:len(operandi)-1]) || (calc2((risultato-operandi[len(operandi)-1])/concat(operandi[len(operandi)-1]), operandi[:len(operandi)-1]) && (risultato-operandi[len(operandi)-1])%concat(operandi[len(operandi)-1]) == 0) {
		//fmt.Println("Add", operandi[:len(operandi)-1], operandi[len(operandi)-1], risultato)

		return true
	}

	return false
}

func timer(name string) func() {
	start := time.Now()
	return func() {
		fmt.Printf("%s took %v\n", name, time.Since(start))
	}
}

func concat(number int) int {
	digits := 1
	for number != 0 {
		number /= 10
		digits *= 10
	}
	return digits

}

func mulAll(result int, valori []int) bool {
	mul := 1
	for _, v := range valori {
		mul *= v
	}
	return mul < result
}

func conc2(risultato, number int) int {
	r, _ := strconv.Atoi(string(risultato) + string(number))
	return r
}
