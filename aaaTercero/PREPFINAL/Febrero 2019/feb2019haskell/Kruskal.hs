----------------------------------------------
-- Estructuras de Datos.  2018/19
-- 2º Curso del Grado en Ingeniería [Informática | del Software | de Computadores].
-- Escuela Técnica Superior de Ingeniería en Informática. UMA
--
-- Examen 4 de febrero de 2019
--
-- ALUMNO/NAME:
-- GRADO/STUDIES:
-- NÚM. MÁQUINA/MACHINE NUMBER:
--
----------------------------------------------

module Kruskal(kruskal, kruskals) where

import qualified DataStructures.Dictionary.AVLDictionary as D
import qualified DataStructures.PriorityQueue.LinearPriorityQueue as PQ
import DataStructures.Graph.DictionaryWeightedGraph

kruskal :: (Ord a, Ord w) => WeightedGraph a w -> [WeightedEdge a w]
kruskal g = aux (diccionrep (vertices g)) (crearcola (edges g)) []
    where
        aux dict p l 
            |PQ.isEmpty p = l
            |(representante vs dict) /= (representante vd dict)= aux (D.insert (representante vd dict) vs dict) (PQ.dequeue p) (((PQ.first p)):l)
            |otherwise = aux dict (PQ.dequeue p) l
                where
                    (WE vs w vd) = PQ.first p

crearcola::(Ord a, Ord w)=>[WeightedEdge a w]->PQ.PQueue (WeightedEdge a w)
crearcola l = foldr (PQ.enqueue) PQ.empty l

diccionrep::(Ord a)=>[a] -> D.Dictionary a a
diccionrep l= auxdl l D.empty
    where
        auxdl [] diccrep = diccrep
        auxdl (y:ys) diccrep = auxdl ys (D.insert y y diccrep)

representante::(Ord a)=>a -> D.Dictionary a a ->a
representante v dict 
    |v == valor = v
    |otherwise = representante valor dict
        where 
            Just valor = D.valueOf v dict 

-- Solo para evaluación continua / only for part time students
kruskals :: (Ord a, Ord w) => WeightedGraph a w -> [[WeightedEdge a w]]
kruskals = undefined
