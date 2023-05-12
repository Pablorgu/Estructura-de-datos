-------------------------------------------------------------------------------
-- Apellidos, Nombre: 
-- Titulacion, Grupo: 
--
-- Estructuras de Datos. Grados en Informatica. UMA.
-------------------------------------------------------------------------------

module AVLBiDictionary( BiDictionary
                      , empty
                      , isEmpty
                      , size
                      , insert
                      , valueOf
                      , keyOf
                      , deleteByKey
                      , deleteByValue
                      , toBiDictionary
                      , compose
                      , isPermutation
                      , orbitOf
                      , cyclesOf
                      ) where

import qualified DataStructures.Dictionary.AVLDictionary as D
import qualified DataStructures.Set.BSTSet               as S

import           Data.List                               (intercalate, nub,
                                                          (\\))
import           Data.Maybe                              (fromJust, fromMaybe,
                                                          isJust)
import           Test.QuickCheck


data BiDictionary a b = Bi (D.Dictionary a b) (D.Dictionary b a)

-- | Exercise a. empty, isEmpty, size

empty :: (Ord a, Ord b) => BiDictionary a b
empty = Bi (D.empty) (D.empty)

isEmpty :: (Ord a, Ord b) => BiDictionary a b -> Bool
isEmpty (Bi d1 d2) = if (D.isEmpty d1) && (D.isEmpty d2) then True else False

size :: (Ord a, Ord b) => BiDictionary a b -> Int
size (Bi d1 d2) = D.size d1

-- | Exercise b. insert

insert :: (Ord a, Ord b) => a -> b -> BiDictionary a b -> BiDictionary a b
insert k v b@(Bi dk dv) 
    | isEmpty b = Bi (D.insert k v D.empty) (D.insert v k D.empty)
    | not (D.isDefinedAt k dk) && not (D.isDefinedAt v dv) = (Bi (D.insert k v dk) (D.insert v k dv))
    | D.isDefinedAt k dk = (Bi (D.insert k v dk)) (D.insert v k (D.delete (fromJust (D.valueOf k dk)) dv))
    | D.isDefinedAt v dv = (Bi (D.insert k v (D.delete (fromJust (D.valueOf v dv)) dk)) (D.insert v k dv))
   
-- | Exercise c. valueOf

valueOf :: (Ord a, Ord b) => a -> BiDictionary a b -> Maybe b
valueOf k (Bi dk dv) = D.valueOf k dk

-- | Exercise d. keyOf

keyOf :: (Ord a, Ord b) => b -> BiDictionary a b -> Maybe a
keyOf v (Bi dk dv) = D.valueOf v dv

-- | Exercise e. deleteByKey

deleteByKey :: (Ord a, Ord b) => a -> BiDictionary a b -> BiDictionary a b
deleteByKey k (Bi dk dv) = (Bi (D.delete k dk) (D.delete (fromJust (D.valueOf k dk)) dv))

-- | Exercise f. deleteByValue

deleteByValue :: (Ord a, Ord b) => b -> BiDictionary a b -> BiDictionary a b
deleteByValue v (Bi dk dv) = (Bi (D.delete (fromJust (D.valueOf v dv)) dk) (D.delete v dv))

-- | Exercise g. toBiDictionary

toBiDictionary :: (Ord a, Ord b) => D.Dictionary a b -> BiDictionary a b
toBiDictionary d
  | not (isInyective (D.values d) []) = error "Not an inyective dictionary."
  | otherwise = aux (D.keysValues d) empty
    where
      aux [] (Bi dk dv) = (Bi dk dv)
      aux (t:ts) (Bi dk dv) = aux ts (insert (fst t) (snd t) (Bi dk dv))

isInyective :: (Ord b) => [b] -> [b] -> Bool
isInyective [] list = True
isInyective (x:xs) list 
  | elem x list = False
  | otherwise = isInyective xs (x:list)

-- | Exercise h. compose

compose :: (Ord a, Ord b, Ord c) => BiDictionary a b -> BiDictionary b c -> BiDictionary a c
compose (Bi dk1 dv1) (Bi dk2 dv2) = aux (D.keysValues dk1) dk2 empty
  where
    aux [] dk2 (Bi k v) = (Bi k v)
    aux (t:ts) dk2 (Bi k v) 
      | D.isDefinedAt (snd t) dk2 = aux ts dk2 (insert (fst t) (fromJust (D.valueOf (snd t) dk2)) (Bi k v))
      | otherwise = aux ts dk2 (Bi k v)

-- | Exercise i. isPermutation

isPermutation :: Ord a => BiDictionary a a -> Bool
isPermutation (Bi dk dv) = (D.keys dk) \\ (D.values dk) == [] 
 
{-
isPermutation (Bi dk dv) = aux (D.keys dk) (size dk) dk
  where
    aux [] dk = False
    aux (k:ks) 0 dk = aux ks (size dk) dk
    aux (k:ks) cnt dk 
      | val == k = True
      |
        where
          val = D.value 
-}
      




-- |------------------------------------------------------------------------


-- | Exercise j. orbitOf

orbitOf :: Ord a => a -> BiDictionary a a -> [a]
orbitOf = undefined

-- | Exercise k. cyclesOf

cyclesOf :: Ord a => BiDictionary a a -> [[a]]
cyclesOf = undefined

-- |------------------------------------------------------------------------


instance (Show a, Show b) => Show (BiDictionary a b) where
  show (Bi dk dv)  = "BiDictionary(" ++ intercalate "," (aux (D.keysValues dk)) ++ ")"
                        ++ "(" ++ intercalate "," (aux (D.keysValues dv)) ++ ")"
   where
    aux kvs  = map (\(k,v) -> show k ++ "->" ++ show v) kvs
