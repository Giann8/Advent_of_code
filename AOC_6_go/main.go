package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

type Guard struct {
	riga                 int
	colonna              int
	direction            rune
	directionjustchanged int
}

func main() {
	matrix, guard := setUp()

	var options [][][]rune

	options = simulateMovement(guard, matrix)

	for i := 0; i < len(options)-10; i++ {
		fmt.Println("| ---", i+1, "---  |")
		printMatrix(options[i])
	}
	printMatrix(matrix)

	fmt.Println("- NUMERO OPZIONI:", len(options), " -")

}

func printMatrix(matrix [][]rune) {
	righette := " --"
	for i := 0; i < len(matrix[0]); i++ {
		righette += "-"
	}
	fmt.Print(righette, "\n")

	for _, v := range matrix {

		fmt.Println("|", string(v), "|")
	}
	fmt.Print(righette, "\n")
}

func getTotalPassed(matrix [][]rune) int {
	total := 0
	for _, line := range matrix {
		for _, runa := range line {
			if runa == 'x' {
				total++
			}
		}
	}
	return total
}

func readInput() [][]rune {
	input := os.Args[1]
	file, err := os.Open(input)

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var matrix [][]rune
	for scanner.Scan() {

		matrix = append(matrix, []rune(scanner.Text()))

	}
	return matrix
}

func moveGuard(guard Guard, matrix [][]rune, simulation bool) (Guard, [][]rune) {
	var options [][]rune
	fmt.Println("non posso girare", guard.directionjustchanged)
	switch guard.direction {
	case '^':
		if checkStaticObj(rune(matrix[guard.riga-1][guard.colonna])) {

			guard.riga--
			guard.directionjustchanged = 0
			if !simulation {
				tmp, newguard := setUp()
				newguard.directionjustchanged = guard.directionjustchanged
				tmp[guard.riga][guard.colonna] = 'o'
				//printMatrix(tmp)
				//fmt.Println(guard)

				if simulate2(newguard, tmp) {
					options = tmp
				}
			}

		} else {
			guard = changeDirection(guard)
			matrix[guard.riga][guard.colonna] = '+'
		}
		break
	case '>':
		if checkStaticObj(rune(matrix[guard.riga][guard.colonna+1])) {
			guard.colonna++
			guard.directionjustchanged = 0
			if !simulation {
				tmp, newguard := setUp()
				newguard.directionjustchanged = guard.directionjustchanged
				tmp[guard.riga][guard.colonna] = 'o'
				//printMatrix(tmp)
				//fmt.Println(guard)

				if simulate2(newguard, tmp) {
					options = tmp
				}
			}

		} else {
			guard = changeDirection(guard)
			matrix[guard.riga][guard.colonna] = '+'
		}
		break
	case '<':
		if checkStaticObj(rune(matrix[guard.riga][guard.colonna-1])) {
			guard.colonna--
			guard.directionjustchanged = 0
			if !simulation {
				tmp, newguard := setUp()
				newguard.directionjustchanged = guard.directionjustchanged
				tmp[guard.riga][guard.colonna] = 'o'
				//printMatrix(tmp)
				//fmt.Println(guard)

				if simulate2(newguard, tmp) {
					options = tmp
				}
			}

		} else {
			guard = changeDirection(guard)
			matrix[guard.riga][guard.colonna] = '+'
		}
		break
	case 'v':
		if checkStaticObj(rune(matrix[guard.riga+1][guard.colonna])) {
			guard.riga++
			guard.directionjustchanged = 0

			if !simulation {
				tmp, newguard := setUp()
				newguard.directionjustchanged = guard.directionjustchanged
				tmp[guard.riga][guard.colonna] = 'o'
				//printMatrix(tmp)
				//fmt.Println(guard)
				if simulate2(newguard, tmp) {
					options = tmp
				}
			}

		} else {
			guard = changeDirection(guard)
			matrix[guard.riga][guard.colonna] = '+'
		}

		break
	}

	return guard, options
}

func checkStaticObj(runa rune) bool {
	switch runa {
	case '#', 'o':
		return false
	default:
		return true

	}
}

func findGuard(matrix [][]rune) Guard {
	guard := Guard{}
	for i, v := range matrix {
		for j, lettera := range v {
			if lettera == '^' {
				guard.colonna = j
				guard.riga = i
				guard.direction = lettera
				matrix[i][j] = 'x'
			}
		}
	}
	return guard
}

func changeDirection(guard Guard) Guard {
	guard.directionjustchanged++
	//fmt.Println("changed direction", guard.directionjustchanged)
	switch guard.direction {
	case '^':
		guard.direction = '>'
		break
	case '>':
		guard.direction = 'v'
		break
	case 'v':
		guard.direction = '<'
		break
	case '<':
		guard.direction = '^'
		break

	}
	return guard
}

func simulateMovement(guard Guard, matrix [][]rune) [][][]rune {
	var options [][][]rune

	for {
		var tmp [][]rune
		if guard.colonna == len(matrix[0])-1 || (guard.colonna == 0) || (guard.riga == 0) || (guard.riga == len(matrix)-1) {

			break
		}
		guard, tmp = moveGuard(guard, matrix, false)
		switch guard.direction {
		case '^', 'v':
			if matrix[guard.riga][guard.colonna] == '-' {
				matrix[guard.riga][guard.colonna] = '+'
			} else if matrix[guard.riga][guard.colonna] != '+' {
				matrix[guard.riga][guard.colonna] = '|'
			}
			break
		case '<', '>':
			if matrix[guard.riga][guard.colonna] == '|' {
				matrix[guard.riga][guard.colonna] = '+'

			} else if matrix[guard.riga][guard.colonna] != '+' {
				matrix[guard.riga][guard.colonna] = '-'
			}
			break
		}

		notnoted := false
		if tmp != nil {
			if len(options) != 0 {
				for _, v := range options {
					if checkAlreadyNoted(tmp, v) {
						notnoted = true
					}
				}
			}
			if !notnoted {
				options = append(options, tmp)
			}

		}
	}
	return options
}

func setUp() ([][]rune, Guard) {
	mappa := readInput()
	guard := findGuard(mappa)
	return mappa, guard
}

func checkIfLoop(guard Guard, matrix [][]rune) bool {
	if guard.colonna != len(matrix[0])-1 && (guard.colonna != 0) && (guard.riga != 0) && (guard.riga != len(matrix)-1) {
		switch guard.direction {
		case '^':
			if rune(matrix[guard.riga-1][guard.colonna]) == '|' || ((rune(matrix[guard.riga][guard.colonna+1]) == '+') && rune(matrix[guard.riga-1][guard.colonna]) == '#') {
				fmt.Println("Loop engaged")
				return true
			}
			break
		case 'v':
			if rune(matrix[guard.riga+1][guard.colonna]) == '|' || ((rune(matrix[guard.riga][guard.colonna-1]) == '+') && rune(matrix[guard.riga+1][guard.colonna]) == '#') {
				fmt.Println("Loop engaged")
				return true
			}
			break

		case '<':
			if rune(matrix[guard.riga][guard.colonna-1]) == '-' || ((rune(matrix[guard.riga+1][guard.colonna]) == '+') && rune(matrix[guard.riga][guard.colonna-1]) == '#') {
				fmt.Println("Loop engaged")
				return true
			}
			break
		case '>':
			if rune(matrix[guard.riga][guard.colonna+1]) == '-' || ((rune(matrix[guard.riga-1][guard.colonna]) == '+') && rune(matrix[guard.riga][guard.colonna+1]) == '#') {
				fmt.Println("Loop engaged")
				return true
			}
			break
		}
	}
	return false
}

func simulate2(guard Guard, matrix [][]rune) bool {
	for {

		if guard.colonna == len(matrix[0])-1 || (guard.colonna == 0) || (guard.riga == 0) || (guard.riga == len(matrix)-1) {
			break
		}

		guard, _ = moveGuard(guard, matrix, true)
		if guard.directionjustchanged >= 2 {
			return false
		}
		switch guard.direction {
		case '^', 'v':
			if matrix[guard.riga][guard.colonna] == '-' {
				matrix[guard.riga][guard.colonna] = '+'
			} else if matrix[guard.riga][guard.colonna] != '+' {
				matrix[guard.riga][guard.colonna] = '|'
			}
			break
		case '<', '>':
			if matrix[guard.riga][guard.colonna] == '|' {
				matrix[guard.riga][guard.colonna] = '+'

			} else if matrix[guard.riga][guard.colonna] != '+' {
				matrix[guard.riga][guard.colonna] = '-'
			}
			break
		}
		if checkIfLoop(guard, matrix) {

			fmt.Println(guard)
			printMatrix(matrix)
			return true
		}
	}
	return false
}
func checkAlreadyNoted(tmp [][]rune, matrix [][]rune) bool {

	for i, riga := range matrix {
		for j := range riga {
			if tmp[i][j] != riga[j] {
				return false
			}
		}
	}

	return true
}
