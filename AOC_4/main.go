package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func main() {
	pattern := "MAS"
	revpattern := "SAM"
	matrix := readInput()
	totalword := 0

	leftmap := leftDiagonal(matrix, pattern)
	revleftmap := leftDiagonal(matrix, revpattern)
	rightmap := rightDiagonal(matrix, pattern)
	revrightmap := rightDiagonal(matrix, revpattern)

	totalword += checkMaps(leftmap, rightmap)
	totalword += checkMaps(revleftmap, rightmap)
	totalword += checkMaps(revleftmap, revrightmap)
	totalword += checkMaps(leftmap, revrightmap)

	/*for _, i := range matrix {
		totalword += checkLineWord(i, pattern)
		totalword += reverseCheck(i, pattern)

	}*/
	//totalword += sameColumnCheck(matrix, revpattern)
	//totalword += sameColumnCheck(matrix, pattern)

	fmt.Println("tot", totalword)
}

func readInput() []string {
	input := os.Args[1]
	file, err := os.Open(input)

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var matrix []string
	for scanner.Scan() {

		matrix = append(matrix, scanner.Text())

	}
	return matrix
}

/*func checkLineWord(line string, pattern string) int {

	z := 0
	wordfound := 0
	for i := 0; i < len(line); i++ {
		if line[i] == pattern[z] {
			z++
			for j := i + 1; j < len(line); j++ {

				if line[j] == pattern[z] {
					if z == len(pattern)-1 {
						fmt.Println("WORD found")
						z = 0
						wordfound++
						break
					}
				} else {
					z = 0
					break
				}
				z++
			}
		}
	}
	return wordfound

}

/*func reverseCheck(line string, patternrev string) int {
	var pattern string
	for i := len(patternrev) - 1; i >= 0; i-- {
		pattern += string(patternrev[i])
	}
	return checkLineWord(line, pattern)

}

/*func sameColumnCheck(matrix []string, pattern string) int {

	wordfound := 0
	for linenum, line := range matrix {
		for i := 0; i < len(line); i++ {
			z := 0

			if line[i] == pattern[z] {
				z++
				for j := linenum + 1; j < len(matrix); j++ {

					if matrix[j][i] == pattern[z] {
						if z == len(pattern)-1 {
							fmt.Println("WORD found sameColumn", z)
							z = 0
							wordfound++
							break
						}
						z++
					} else {
						z = 0
						break
					}
					fmt.Println(z)
				}
			}
		}
	}
	return wordfound

}*/

func leftDiagonal(matrix []string, pattern string) [][]int {
	var xcenterfound [][]int
	for linenum, line := range matrix {
		for i := 0; i < len(line); i++ {
			z := 0

			if line[i] == pattern[z] && i >= len(pattern)-1 {
				var parola []int
				parola = append(parola, i)
				z++
				position := i - 1
				for j := linenum + 1; j < len(matrix); j++ {
					//fmt.Println(z,string(matrix[j]),position,matrix[j][position] == pattern[z],string(matrix[j][position]))

					if matrix[j][position] == pattern[z] {
						parola = append(parola, position)
						if z == len(pattern)-1 {
							fmt.Println("WORD found leftDiagonal", z, pattern)
							z = 0
							appended:=[]int{linenum,parola[1]}
							xcenterfound=append(xcenterfound, appended)
							break
						}
						z++
						position--
					} else {
						z = 0
						break
					}
				}
			}
		}
	}
	fmt.Println(xcenterfound)
	return xcenterfound
}

func rightDiagonal(matrix []string, pattern string) [][]int {
	var xcenterfound [][]int
	for linenum, line := range matrix {
		for i := 0; i < len(line); i++ {
			z := 0

			if line[i] == pattern[z] && i <= len(matrix)-len(pattern) {
				var parola []int
				parola = append(parola, i)
				z++
				position := i + 1
				for j := linenum + 1; j < len(matrix); j++ {

					if matrix[j][position] == pattern[z] {
						parola = append(parola, position)
						if z == len(pattern)-1 {
							fmt.Println("center found rightDiagonal", z, pattern)
							z = 0
							appended:=[]int{linenum,parola[1]}
							xcenterfound=append(xcenterfound, appended)
							break
						}
						z++
						position++
					} else {
						z = 0
						break
					}
					fmt.Println(z)
				}
			}
		}
	}
	fmt.Println(xcenterfound)
	return xcenterfound
}

func checkMaps(arr1, arr2 [][]int) int {
	wordcount := 0
	
	for _, v := range arr1 {
		for _,z:= range arr2 {
			if v[0]==z[0]&& v[1]==z[1] {
				wordcount++
			}
		}
		
	}

	return wordcount
}
