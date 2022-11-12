module SetMultiMap (
    SetMultiMap
    , empty
    , isEmpty
    , size
    , isDefinedAt
    , insert
    , valuesOf
    , deleteKey
    , deleteKeyValue
    , filterValues
    , fold
)where

import Data.List (intercalate)
import Test.QuickCheck

import qualified DataStructures.Set.LinearSet as S
-- Si hace falta importo más librerías

-- Los nodos estan ordenados por clave
-- No hay claves repetidas
-- No hay claves que tengan asociado un conjunto vacío

-- 'a' sería el tipo de la clave y 'b' el tipo de los valores que estarán guardados en un conjunto
data SetMultiMap a b = Empty | Node a (S.Set b) (SetMultiMap a b) deriving Eq

m1 :: SetMultiMap String Int 
m1 = Node "alfredo" (mkSet [9]) (
     Node "juan" (mkSet [0,1,8]) (
     Node "maria" (mkSet [4,-6,8])
     Empty))

mkSet :: Eq a => [a] -> S.Set a
mkSet = foldr S.insert S.empty
-- Defino los métodos
-- empty
empty :: SetMultiMap a b
empty = Empty

-- isEmpty
isEmpty :: SetMultiMap a b -> Bool
isEmpty Empty = True
isEmpty _ = False

-- size
size :: SetMultiMap a b -> Integer
size Empty = 0
size (Node k vs t) = 1 + size t

-- isDefinedAt
isDefinedAt :: (Ord a, Eq a) => a -> SetMultiMap a b -> Bool
isDefinedAt k Empty = False
isDefinedAt k (Node r vs t) 
    |k == r = True
    |otherwise = isDefinedAt k t


-- insert
insert :: (Ord a, Eq b) => a -> b -> SetMultiMap a b -> SetMultiMap a b
insert xs k Empty  = Node xs (S.insert k S.empty) Empty
insert xs k (Node r vs t) 
    |xs>r = Node r vs(insert xs k t)
    |xs<r = Node xs (S.insert k S.empty) (Node r vs t)
    |otherwise = Node r (S.insert k vs) t

-- valuesOf
valuesOf :: (Ord a, Eq b) => a -> SetMultiMap a b -> S.Set b
valuesOf k (Node v vs t) 
    |k>v =valuesOf k t
    |k == v = vs
    |otherwise = S.empty

-- deleteKey
deleteKey :: (Ord a, Eq b) => a -> SetMultiMap a b -> SetMultiMap a b
deleteKey k Empty = Empty
deleteKey k (Node x xs t)
    |k>x = Node x xs (deleteKey k t)
    |k==x = t
    |otherwise = (Node x xs t)

-- deleteKeyValue
deleteKeyValue :: (Ord a, Eq b) => a -> b -> SetMultiMap a b -> SetMultiMap a b
deleteKeyValue _ _ Empty = Empty;
deleteKeyValue k v (Node x xs t)
    |k>x = Node x xs (deleteKeyValue k v t)
    |k==x && (S.size xs ==1) = t
    |k==x && (S.size xs >1) = Node x (S.delete v xs) t
    |otherwise = Node x xs t

-- filterValues
filterValues :: (Ord a, Eq b) => (b -> Bool) -> SetMultiMap a b -> SetMultiMap a b
filterValues p Empty = Empty
filterValues p (Node x xs t) = Node a filtrados (filterValues p sig)
    where filtrados = S.fold (\y res ->if p y then S.insert y res else res) S.empty b

fold :: (Ord a, Eq b) => (a -> b -> c -> c) -> c -> SetMultiMap a b -> c
fold f z ms = recSetMultiMap ms
    where
        recSetMultiMap Empty = z
        recSetMultiMap (Node k s ms)
            | S.isEmpty s = recSetMultiMap ms 
            | otherwise = f k v (recSetMultiMap (Node k s' ms))
            where
                (v,s') = pickOne s
                pickOne s = (v,S.delete v s)
                    where v = head $ S.fold (:) [] s

instance (Show a, Show b) => Show(SetMultiMap a b) where
    show Empty = "{}"
    show ms = intercalate "\n" (showKeyValues ms)
        where
            showKeyValues Empty = []
            showKeyValues (Node k s ms) = (show k ++ "-->" ++ show s) : showKeyValues ms

instance (Ord a, Arbitrary a, Eq b, Arbitrary b) => Arbitrary (SetMultiMap a b)
    where
        arbitrary = do
        xs <- listOf arbitrary
        ys <- listOf arbitrary
        return (foldr (uncurry insert) empty (zip xs ys))