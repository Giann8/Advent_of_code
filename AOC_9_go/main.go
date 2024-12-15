package main

import (
	"bufio"
	"log"
	"os"
)


func main(){
	
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