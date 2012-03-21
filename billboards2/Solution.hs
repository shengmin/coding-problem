{-
	@author ShengMin Zhang
	@revision 1.0
	 - try out Haskell for fun
	 - greedy
-}

import Data.Array
import Data.List

main = interact (solve . lines)

getSum bs i n inc sum
	| i > n = sum
	| otherwise = getSum bs (i + inc) n inc (sum - (bs ! i))
	
getBestSum bs istart n k best totalsum
	| istart > k = best
	| otherwise = 
		let sum = getSum bs istart n (k + 1) totalsum
		in 
			if sum > best 
			then getBestSum bs (istart + 1) n k sum totalsum 
			else getBestSum bs (istart + 1) n k best totalsum
	
parseInput lns = 
	let 
		[n1, k1] = words (head lns)
		ls = map (\ln -> read ln :: Int) (tail lns)
		n = (read n1 :: Int)
		k = (read k1 :: Int)
	in
		(listArray (0, n) (0:ls), sum ls, n, k)
		
solve lns = 
	let
		(bs, sum, n, k) = parseInput lns
	in
		(show (getBestSum bs 0 n k 0 sum)) ++ "\n"
	
