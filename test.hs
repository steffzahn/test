rest a b | a<b = a
         | otherwise=rest (a-b) b

ggT a 0 = a
ggT a 1 = 1
ggT a b = ggT b (rem a b)

nimm n [] =[]
nimm 0 ys = []
nimm n (x:xs) = x : (nimm ( n-1) xs)

wuwu2 x a b 20 ys = ys
wuwu2 x a b n ys  | ((a+b)*0.5)^2>x =  wuwu2 x a ((a+b)*0.5) (n+1) (((a+b)*0.5) : ys)
                  | ((a+b)*0.5)^2<x =  wuwu2 x ((a+b)*0.5) b (n+1) (((a+b)*0.5) : ys)
                  | otherwise = ((a+b)*0.5) : ys

wuwu x | 0>x = undefined
       | otherwise = wuwu2 x 0 (x+1) 0 [0,x+1]


quicksort [] = []  
quicksort (head:tail) = 
    let
        smallerNumbers = quicksort (filter (<=head) tail)  
        biggerNumbers = quicksort (filter (>head) tail)
    in
        smallerNumbers ++ [head] ++ biggerNumbers  
