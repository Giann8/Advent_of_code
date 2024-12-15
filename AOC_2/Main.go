package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {
	i := readInput()

	fmt.Println(i)
}

func checkLine(input []int) bool {
	previousDelta := 0
	a := input

	for i := 0; i < len(a)-1; i++ {

		if isUnsafe(a[i], a[i+1]) {
			return true
			fmt.Println("Unsafe", a[i], a[i+1])
		}
		if previousDelta == 0 {
			previousDelta = currentDeltaCalc(a[i], a[i+1])
		} else if (currentDeltaCalc(a[i], a[i+1]) < 0 && previousDelta > 0) || (currentDeltaCalc(a[i], a[i+1]) > 0 && previousDelta < 0) {
			return true
		}
	}

	return false
}

func checkAllPoss(input []int) bool {
	for i := 0; i < len(input); i++ {
		var a []int
		a = append(a, input[:i]...)
		a = append(a, input[i+1:]...)

		fmt.Println(a)
		if !checkLine(a) {
			return true
		}
	}
	return false
}

func isUnsafe(first, second int) bool {

	if first-second > 3 || second-first > 3 || second == first {
		return true
	}
	return false
}

func currentDeltaCalc(a1, a2 int) int {
	return a1 - a2
}

func readInput() int {
	file, err := os.Open("./input.txt")

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var total int

	for scanner.Scan() {
		var a1 []int

		tmp := strings.Split(scanner.Text(), " ")
		for i := range tmp {
			tmp1, _ := strconv.Atoi(tmp[i])
			a1 = append(a1, tmp1)
		}

		if !checkLine(a1) || checkAllPoss(a1) {
			total++
			fmt.Println("SAFE",a1)
		} else {
			fmt.Println("NOT SAFE",a1)

		}

	}
	return total
}
