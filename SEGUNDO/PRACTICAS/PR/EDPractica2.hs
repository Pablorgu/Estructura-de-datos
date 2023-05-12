-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º ETSI Informática. UMA
-- Práctica 2
--
-- Alumno: APELLIDOS, NOMBRE
-------------------------------------------------------------------------------

module Practica2 where

import           Data.List
import           Test.QuickCheck

-------------------------------------------------------------------------------
-- Ejercicio 2 - máximoYResto
-------------------------------------------------------------------------------

borra :: Eq a=>a -> [a] -> [a]
borra m [] = []
borra m (y:ys) 
    | m == y = ys
    |otherwise = y:(borra m ys)

-- complejidad O(2n)
máximoYRestoOrden :: Ord a => [a] -> (a,[a])
máximoYRestoOrden xs = (m, borra m xs)
    where m = maximum  xs 


-- complejidad O(n)
máximoYResto :: Ord a => [a] -> (a,[a])
máximoYResto [ ] = error "no está definido"
máximoYResto  [x] = (x, [])
máximoYResto  (x:xs) 
    | x>m = (x,rs ++ [x])
    | otherwise = (m, rs ++ [x])
    where
        (m, rs) = máximoYResto xs
-------------------------------------------------------------------------------
-- Ejercicio 3 - reparte
-------------------------------------------------------------------------------

reparte :: [a] -> ([a], [a])
reparte [] = ([], [])
raparte [x] = ([x], [])
reparte (x:y:xs) = (x:ps, y:ss)
    where (ps, ss) = reparte ys

-------------------------------------------------------------------------------
-- Ejercicio 4 - distintos
-------------------------------------------------------------------------------

distintos :: Eq a => [a] -> Bool
distintos [] = True
distintos (x:xs) = x  `notElem` xs && distintos xs -- f x (distintos xs)


-------------------------------------------------------------------------------
-- Ejercicio 13 - hoogle
-------------------------------------------------------------------------------

-- Hoogle (https://www.haskell.org/hoogle/) es un buscador para el API de Haskell.
-- Usa Hoogle para encontrar información sobre las funciones 'and', y 'zip'

desconocida :: Ord a => [a] -> Bool
desconocida xs = and [ x <= y | (x, y) <- zip xs (tail xs) ]

-------------------------------------------------------------------------------
-- Ejercicio 14 - inserta y ordena
-------------------------------------------------------------------------------

-- 14.a - usando takeWhile y dropWhile
inserta :: Ord a => a -> [a] -> [a]
inserta x xs = takeWhile (<x) xs ++ [x] ++ dropWhile (<x) xs

-- 14.b - mediante recursividad
insertaRec :: Ord a => a -> [a] -> [a]
insertaRec x [] = [x]
insertaRec x (y:ys)
    | x <= y = x:y:ys
    |otherwise = insertaRec x ys

-- 14.c

-- La línea de abajo no compila hasta que completes los apartados
-- anteriores.

-- p1_inserta x xs = desconocida xs ==> desconocida (inserta x xs)
ordenaRec :: Ord a => [a] -> [a]
ordenaRec [] = []
ordenaRec (x:xs) = inserta x (ordenaRec xs)

-- 14.e - usando foldr
ordena :: Ord a => [a] -> [a]
--ordena = foldr f inserta [] xs
ordena xs = []

-- 14.f
prop_ordena_OK = undefined

-------------------------------------------------------------------------------
-- Ejercicio 21 - nub
-------------------------------------------------------------------------------

-- 21.a
nub' :: Eq a => [a] -> [a]
nub' = undefined

-- 21.b
p_nub' xs = nub xs == nub' xs

-- 21.c (distintos se define en el ejercicio 2)

p_sinRepes xs = distintos (nub' xs)

{-

Escribe aquí tu razonamiento de por qué p_sinRepes no
es suficiente para comprobar que nub' es correcta:




-}

-- 21.d
todosEn :: Eq a => [a] -> [a] -> Bool
ys `todosEn` xs = all (`elem` xs) ys

p_sinReps' = undefined
