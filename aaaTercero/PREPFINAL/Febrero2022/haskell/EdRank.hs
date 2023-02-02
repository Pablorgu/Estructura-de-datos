-------------------------------------------------------------------------------
-- Student's name:
-- Student's group:
-- Identity number (DNI if Spanish/passport if Erasmus):
--
-- Data Structures. Grado en Informática. UMA.
-------------------------------------------------------------------------------

module EdRank where

import DataStructures.Graph.DiGraph 
import DataStructures.Dictionary.AVLDictionary as D
import Data.Maybe(fromJust)

threshold = 0.0001
createRankDict :: (Ord a) => DiGraph a -> D.Dictionary a Double
createRankDict node = foldr (`D.insert` 0) D.empty (vertices node)

distribute :: Ord a => DiGraph a -> a -> Double -> D.Dictionary a Double -> D.Dictionary a Double
distribute g v rank dict 
    |rank>threshold= updateValues g rankbysuc (successors g v) (D.insert v (rankby2+(fromJust (D.valueOf v dict))) dict)
    |otherwise = dict
        where
            rankby2 = rank*0.5
            rankbysuc = rankby2/ (fromIntegral(length (successors g v)))

updateValues :: Ord a => DiGraph a -> Double -> [a] -> D.Dictionary a Double -> D.Dictionary a Double
updateValues g rk [] dict = dict
updateValues g rk (x:xs) dict = distribute g x rk (updateValues g rk xs dict)

edRank :: Ord a => DiGraph a -> Double -> D.Dictionary a Double
edRank g rank = updateValues g rank (vertices g) (createRankDict g)