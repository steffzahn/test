package main

import "fmt"
import "unsafe"
import "reflect"
import "os"
import "math/cmplx"
import "strconv"

func sum(a []int) int {
	z := 0
	for ix := range a {
		z += a[ix]
	}
	a = append(a, 9, 8)
	return z
}

type Stringer interface {
	String() string
}

func toString(any interface{}) string {
	if v, ok := any.(Stringer); ok {
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
	i := 4
	p := &i
	var x uintptr = uintptr(unsafe.Pointer(p))
	fmt.Println(i, x, reflect.TypeOf(x))
	if a, b := 9, "WTF"; i < 5 {
		i = a
		fmt.Println(b)
	}
	var v complex128 = 12 + 3i
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
}
