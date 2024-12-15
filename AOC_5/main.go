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
	var total int
	rules, printedupdates := readInput()
	fmt.Println("rules", rules, "printedpages", printedupdates)

	for i := 0; i < len(printedupdates); i++ {

		if checkUpdatedPages(rules, printedupdates[i]) {
			fmt.Println("sube", printedupdates[i][(len(printedupdates[i])/2)])
			tmp, _ := strconv.Atoi(printedupdates[i][(len(printedupdates[i]) / 2)])
			total += tmp
			fmt.Println("summed", total, tmp, printedupdates[i], (len(printedupdates[i]) / 2))
		}

	}
	fmt.Println(total)
}

func readInput() ([][]string, [][]string) {
	var rules [][]string
	var printedpages [][]string
	var check bool

	input := os.Args[1]
	file, err := os.Open(input)

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {

		if scanner.Text() == "" {
			check = true
			continue
		}
		switch check {
		case true:
			printedpages = append(printedpages, strings.Split(scanner.Text(), ","))
		default:
			rules = append(rules, strings.Split(scanner.Text(), "|"))

		}

	}
	return rules, printedpages
}

func checkUpdatedPages(rules [][]string, printedupdate []string) bool {

	changed := false
	for i := 0; i < len(rules); i++ {

		for pageposition, pagina := range printedupdate {

			if string(rules[i][0]) == pagina {
				if len(printedupdate[:pageposition]) != 0 {
					for j, paginacheck := range printedupdate[:pageposition] {
						if rules[i][1] == paginacheck {
							printedupdate[pageposition], printedupdate[j] = printedupdate[j], printedupdate[pageposition]
							changed = true
							i=0
						}
					}

				}
			}
		}
	}
	if changed == true {
		fmt.Println("changed", printedupdate)
		return true
	}
	return false
}

func check(rule string, array []string) bool {
	for _, paginacheck := range array {
		if rule == paginacheck {
			return true
		}
	}
	return false
}
