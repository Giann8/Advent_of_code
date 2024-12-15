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
	fmt.Println(readInput())
}

func isUnsafe(a []int) bool {
	alreadyelim := false
	encr, decr := 0, 0
	errors := 0
	possibleErrors:=0
	for i := 0; i < len(a)-1; i++ {
		if checkNums(a[i], a[i+1]) {
			//fmt.Println((i+2 < len(a) && canEliminate(a[i], a[i+1], a[i+2]) && (!alreadyelim)) && (i > 0 && canEliminate(a[i-1], a[i], a[i+1]) || i == 0 ))
			if i+2>len(a) && ((i>0 && canEliminate(a[i-1], a[i], a[i+1])))&&!alreadyelim {
				alreadyelim = true
			}else if (i+2 < len(a) && canEliminate(a[i], a[i+1], a[i+2]) && (!alreadyelim)) && (i > 0 && canEliminate(a[i-1], a[i], a[i+1]) || i == 0 ){
				alreadyelim = true
			} else {
				fmt.Println("not deleted")
				errors++
				possibleErrors++
			}
		}

		if a[i] > a[i+1] {
			decr++
		} else if a[i] < a[i+1] {
			encr++
		}

	}

	if !checkEncrDecr(encr, decr, alreadyelim) {
		fmt.Println("encrease decrease problem",encr,decr)
		return true
	}

	fmt.Println(errors,possibleErrors)

	if possibleErrors==0 && errors==1{
		errors--
	}

	if errors > 0 {
		return true
	}

	return false
}

func checkEncrDecr(encr, decr int, alreadyElim bool) bool {
	if (((encr >= 1 && decr == 1) || (decr >= 1 && encr == 1)) && !alreadyElim) || (encr >= 1 && decr == 0) || (decr >= 1 && encr == 0) {
		return true
	}
	return false
}

/*func checkEncrDecr2(a []int)int{
	encr,decr:=0,0
	var firstencr bool

	for i:=0;i<len(a)-1;i++{
		if a[i]
		}

	if firstencr{
		return decr
	}
	return encr
}*/

func checkNums(a1, a2 int) bool {
	if a1-a2 > 3 || a2-a1 > 3 || a2 == a1 {
		return true
	}
	return false
}

func canEliminate(a1, a2, a3 int) bool {
	if !checkNums(a1, a3) {
		fmt.Println("deletable", a2, "from", a1, a3)
		return true
	} else if !checkNums(a2, a3) {
		fmt.Println("deletable", a1, "from", a2, a3)
		return true
	}
	return false
}

func readInput() int {
	file, err := os.Open("./input.txt")
	total := 0

	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		var a1 []int

		tmp := strings.Split(scanner.Text(), " ")
		for i := range tmp {
			tmp1, _ := strconv.Atoi(tmp[i])
			a1 = append(a1, tmp1)
		}
		if isUnsafe(a1) {
			fmt.Println(a1,"NOT SAFE")
		} else {
			fmt.Println(a1, "SAFE")
			total++
		}

	}
	return total
}
