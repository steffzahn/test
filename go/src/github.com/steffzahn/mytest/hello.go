package main

import "fmt"
import "unsafe"
import "reflect"
import "os"
import "bufio"
import "math/cmplx"
import "strconv"
import "log"

func sum(a []int) int {
	z := 0
	for ix := range a {
		z += a[ix]
	}
	a = append(a, 9, 8)
	return z
}

type stringer interface {
	String() string
}

func toString(any interface{}) string {
	if v, ok := any.(stringer); ok {
		return v.String()
	}
	switch v := any.(type) {
	case int:
		return strconv.Itoa(v)
	case float64:
		return strconv.FormatFloat(v, 'g', -1, 32)
	}
	return "???"
}

func main() {
	log.Println("Hello starting")
	i := 4
	p := &i
	var x = uintptr(unsafe.Pointer(p))
	fmt.Println(i, x, reflect.TypeOf(x))
	if a, b := 9, "WTF"; i < 5 {
		i = a
		fmt.Println(b)
	}
	var v = 12 + 3i
	fmt.Printf("%v %T", v, v)
	fmt.Println(os.Getenv("PORT"), os.Getenv("IP"))
	fmt.Println(i, 1i)
	fmt.Println(cmplx.Sqrt(-2))
	arr := []int{3, 4, 6, 7}
	fmt.Println(sum(arr[:2]))
	fmt.Println(arr)
	fmt.Println(toString(33))
	xi := interface{}(struct{ s string }{"WTF"})
	switch xi.(type) {
	case int:
		fmt.Println("int")
	case float64:
		fmt.Println("float64")
	default:
		fmt.Println("???")
	}
	f, err := os.Create("bla")
	if err != nil {
		log.Fatal(err)
	} else {
		f.WriteString("Dummy")
		f.Close()
	}
	f, err = os.Open("bla")
	if err != nil {
		log.Fatal(err)
	} else {
		defer f.Close()
		scanner := bufio.NewScanner(f)

		for scanner.Scan() { // internally, it advances token based on sperator
			fmt.Println(scanner.Text())  // token in unicode-char
			fmt.Println(scanner.Bytes()) // token in bytes

		}
	}
}
