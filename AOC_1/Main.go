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

	a1, a2 := readInput()

	qSort(a2, 0, len(a2))
	//fmt.Println(a2)
	qSort(a1, 0, len(a1))
	//fmt.Println(a1)
	mappa := mapCreator(a1, a2)
	for v, k := range mappa {
		fmt.Println(v, k)
	}
fmt.Println(aSum2(mappa))
	//fmt.Println(aSum(a1, a2))

}

func qSort(a []int, i, f int) {

	if f-i > 1 {
		m := partition(a, i, f)

		qSort(a, i, m)
		qSort(a, m+1, f)
	}

}

func partition(a []int, i, f int) int {

	perno := a[i]
	dx := f
	sx := i

	for sx < dx {

		dx--
		for a[dx] > perno {
			dx--
		}

		sx++
		for a[sx] <= perno && sx < dx {
			sx++
		}
		if sx < dx {
			a[sx], a[dx] = a[dx], a[sx]
		}

	}
	a[i], a[dx] = a[dx], a[i]
	return dx
}

func aSum(a1 []int, a2 []int) int {
	var sum int
	for i, number := range a1 {

		if number > a2[i] {
			sum += number - a2[i]

		} else {
			sum += a2[i] - number
		}
	}

	return sum
}

func readInput() ([]int, []int) {
	file, err := os.Open("./input.txt")
	var a1, a2 []int

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		tmp := strings.Split(scanner.Text(), "   ")
		tmp1, _ := strconv.Atoi(tmp[0])
		tmp2, _ := strconv.Atoi(tmp[1])

		a1 = append(a1, tmp1)
		a2 = append(a2, tmp2)

	}
	return a1, a2
}

func mapCreator(a1 []int, a2 []int) map[int]int {
	var mappaNum = make(map[int]int)
	var alreadyused int
	for _, j := range a1 {
		if j != alreadyused {
			alreadyused = j
			mappaNum[j] = 0
		}

	}

	for k := range mappaNum {
		for i := 0; i < len(a2); i++ {
			if k == a2[i] {
				mappaNum[k] += 1
			}
		}
	}
	return mappaNum
}

func aSum2(mappa map[int]int)int{
	var sum int

	for numero,valore:=range mappa{

		sum+=numero*valore
	}

	return sum
}