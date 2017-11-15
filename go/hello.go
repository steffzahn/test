package main

import "fmt"
import "unsafe"
import "reflect"
import "os"
import "math/cmplx"

func sum(a []int) int {
	z := 0
	for ix := range a {
		z += a[ix]
	}
	a = append(a, 9, 8)
	return z
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
}
