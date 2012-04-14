import Data.Array(accumArray, (!))
import Data.List(map)
import Control.Monad.State(get, put, evalState)

googlerese = 
	"ejp mysljylc kd kxveddknmc re jsicpdrysi"
	++ "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd"
	++ "de kr kd eoya kw aej tysr re ujdr lkgc jv"
	++ "qz"
	
normal = 
	"our language is impossible to understand"
	++ "there are twenty six factorial possibilities"
	++ "so it is okay if you want to just give up"
	++ "zq"
	
toAssociationList [] [] = []
toAssociationList (g:gs) (n:ns) = (fromEnum g, n):(toAssociationList gs ns)
	
findMapping gs ns = accumArray (\c c' -> c') '.' (32, 122) (toAssociationList gs ns)

translate mapping ln = map (\c -> (mapping ! (fromEnum c))) ln

solve' mapping lns i t 
	| (i > t) = []
	| otherwise = ("Case #" ++ (show i) ++ ": " ++ (translate mapping (head lns))):(solve' mapping (tail lns) (i + 1) t)

solve lns = solve' (findMapping googlerese normal) (tail lns) 1 (read (head lns) :: Int)
		
main = interact (unlines . solve . lines)