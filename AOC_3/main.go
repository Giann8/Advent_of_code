package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"regexp"
	"strconv"
)

func main() {

	re := regexp.MustCompile(`(mul\(([0-9]+),([0-9]+)\))|(don't\(\))|(do\(\))`)
	input:= readInput()

	submatch := re.FindAllStringSubmatch(input, -1)
	//submatch := re.FindAllStringSubmatch(`xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))`, -1)

	//sub2 := re.FindAllString(`xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))`, -1)
	sub2 := re.FindAllString(input, -1)

	real2 := eval2(submatch, sub2)
	fmt.Println(real2)

}

func eval(submatch [][]string) int {
	var real int
	for _, i := range submatch {
		var l []int
		fmt.Println(i)
		for z, j := range i {
			if z != 0 {
				tmp, _ := strconv.Atoi(j)
				l = append(l, tmp)
			}
		}
		real += l[0] * l[1]
	}
	return real
}
func eval2(submatch [][]string, sub2 []string) int {
	enable := true
	var real int
	for z, i := range sub2 {
		var f []int
		fmt.Println(i)
		if i == "don't()" {
			enable = false
			continue
		} else if i == "do()" {
			enable = true
			continue
		} else {
			for l, j := range submatch[z] {
				if l > 1 {
					tmp, _ := strconv.Atoi(j)

					f = append(f, tmp)
				}
			}
		}
		if enable {
			real += f[0] * f[1]
		}
	}
	return real
}

func readInput() string {
	file, err := os.Open("./input.txt")

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var superstring string
	for scanner.Scan() {

		superstring += scanner.Text()

	}
	return superstring
}
