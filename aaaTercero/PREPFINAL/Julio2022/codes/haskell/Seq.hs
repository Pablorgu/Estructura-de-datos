-------------------------------------------------------------------------------
-- Student's name:
-- Student's group:
-- Identity number (DNI if Spanish/passport if Erasmus):
--
-- Data Structures. Grado en Informática. UMA.
-------------------------------------------------------------------------------

module Seq (Seq (..),
    addSingleDigit
) where

data Seq a = Empty | Node a (Seq a) deriving (Eq, Show)

-- ESCRIBE TU SOLUCIÓN DEBAJO ----------------------------------------------
-- WRITE YOUR SOLUTION BELOW  ----------------------------------------------
-- EXERCISE 1

addSingleDigit :: (Integral a) => a -> Seq a -> Seq a
addSingleDigit d xs = fromInt ((aux xs ((cont xs)-1)) + d)
    where
        cont Empty = 0
        cont (Node _ sig) = 1 + cont sig
        aux Empty _ = 0
        aux (Node x sig) c = (x*(10^c)) + (aux sig (c-1))
        fromInt n = aux1 n Empty
          where
            aux1 n xs
              | n < 10    = Node n xs
              | otherwise = aux1 n' (Node d xs)
              where (n', d) = divMod n 10
-- 10984332 + d 
-- 10000000
--  0000000
--   900000
--    80000