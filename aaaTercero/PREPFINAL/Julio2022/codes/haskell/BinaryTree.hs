-------------------------------------------------------------------------------
-- Student's name:
-- Student's group:
-- Identity number (DNI if Spanish/passport if Erasmus):
--
-- Data Structures. Grado en Informática. UMA.
-------------------------------------------------------------------------------

module BinaryTree
  ( BinaryTree
  , empty
  , isEmpty
  , insert
  , mkBST
  -- | todo
  , subTreesInRange -- EXERCISE 2
  ) where

data BinaryTree a = Empty
                  | Node a (BinaryTree a) (BinaryTree a)
                  deriving Show


-- ESCRIBE TU SOLUCIÓN DEBAJO ----------------------------------------------
-- WRITE YOUR SOLUTION BELOW  ----------------------------------------------
-- EXERCISE 2

subTreesInRange :: Ord a => BinaryTree a -> a -> a -> Integer
subTreesInRange b min max = fst(aux b min max)
  where
    aux Empty min max = (0, True)
    aux t@(Node x l r) min max
      | isEmpty l && isEmpty r = if (x >= min) && (x <= max) then (1, True) else (0, False)
      | x<min = (fst (aux r min max), False)
      | x>max = (fst (aux l min max), False)
      | (snd (aux l min max)) && (snd (aux r min max)) = (1 + (fst (aux l min max)) + (fst (aux r min max)), True)
      | otherwise = ((fst (aux l min max)) + (fst (aux r min max)), False)


-- | NO MODIFICAR A PARTIR DE AQUÍ --------------------------------------------
-- | DO NOT MODIFY CODE BELOW      --------------------------------------------

empty :: BinaryTree a
empty  = Empty

isEmpty :: BinaryTree a -> Bool
isEmpty Empty = True
isEmpty _     = False

insert :: Ord a => a -> BinaryTree a -> BinaryTree a
insert x' Empty  =  Node x' Empty Empty
insert x' (Node x lt rt)
    | x'<x       = Node x (insert x' lt) rt
    | x'>x       = Node x lt (insert x' rt)
    | otherwise  = Node x' lt rt

mkBST :: Ord a => [a] -> BinaryTree a
mkBST xs  = foldl (flip insert) empty xs
