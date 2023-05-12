-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º ETSI Informática. UMA
-- Práctica 1
--
-- Alumno: APELLIDOS, NOMBRE
-------------------------------------------------------------------------------

module EDPractica1 where

import           Test.QuickCheck

-------------------------------------------------------------------------------
-- Ejercicio 2 - intercambia
-------------------------------------------------------------------------------

intercambia :: (a,b) -> (b,a)
intercambia (x,y) = (y,x) -- completar

-------------------------------------------------------------------------------
-- Ejercicio 3 - ordena2 y ordena3
-------------------------------------------------------------------------------

-- 3.a
ordena2 :: Ord a => (a,a) -> (a,a)
ordena2 (x,y) = if x>y then (x,y) else (y,x)

p1_ordena2 x y = enOrden (ordena2 (x,y))
   where enOrden (x,y) = x <= y

p2_ordena2 x y = mismosElementos (x,y) (ordena2 (x,y))
   where
      mismosElementos (x,y) (x',y') =
           (x == x' && y == y') || (x == y' && y == x')

-- 3.b
ordena3 :: Ord a => (a,a,a) -> (a,a,a)
ordena3 (x,y,z) 
   |x>y && y>z = (x,y,z)
   |x>y && z>y = (x,z,y)
   |y>x && x>z = (y,x,z)
   |y>z && z>y = (y,z,x)
   |z>x && x>y = (z,x,y)
   |otherwise (z,y,x) 

-- 3.c
p1_ordena3 x y z = enOrden(ordena3(x,y,z))
   where enOrden(x,y,z) = x>y && y>z

p2_ordena3 x y z = mismosElementos3(x,y,z) (ordena2 (x,y))
   where
      mismosElementos3(x,y,z)(x',y',z') =
         (x==x' && y==y' && z==z') || (x==y' && y==z' && z==x')

-------------------------------------------------------------------------------
-- Ejercicio 4 - max2
-------------------------------------------------------------------------------

-- 4.a
max2 :: Ord a => a -> a -> a
max2 (x,y) --puto socuellamos
   |x>y = x
   |otherwise y

-- 4.b
-- p1_max2: el máximo de dos números x e y coincide o bien con x o bien con y.

p1_max2 = undefined

-- p2_max2: el máximo de x e y es mayor o igual que x y mayor o igual que y.

p2_max2 = undefined -- completar

-- p3_max2: si x es mayor o igual que y, entonces el máximo de x e y es x.

p3_max2 = undefined -- completar

-- p4_max2: si y es mayor o igual que x, entonces el máximo de x e y es y.

p4_max2 = undefined -- completar

-------------------------------------------------------------------------------
-- Ejercicio 5 - entre (resuelto, se usa en el ejercicio 7)
-------------------------------------------------------------------------------

entre :: Ord a => a -> (a, a) -> Bool
entre x (inf,sup) = inf <= x && x <= sup

-------------------------------------------------------------------------------
-- Ejercicio 6 
-------------------------------------------------------------------------------
iguales3 :: Eq a => (a,a,a) -> Bool
iguales3 (x,y,x) = if(x==y && y == z) then True else False

-------------------------------------------------------------------------------
-- Ejercicio 7 - descomponer
-------------------------------------------------------------------------------

-- Para este ejercicio nos interesa utilizar la función predefinida:
--
--              divMod :: Integral a => a -> a -> (a, a)
--
-- que calcula simultáneamente el cociente y el resto de la división entera:
--
--   *Main> divMod 30 7
--   (4,2)

-- 7.a
type TotalSegundos = Integer
type Horas         = Integer
type Minutos       = Integer
type Segundos      = Integer

descomponer :: TotalSegundos -> (Horas,Minutos,Segundos)
descomponer x = (horas, minutos, segundos)
   where
      horas = div % 3600
      minutos= div (mod%3600) 60
      segundos= mod (mod%3600) 60

-- 7.b
p_descomponer x = x>=0 ==> h*3600 + m*60 + s == x
                           && m `entre` (0,59)
                           && s `entre` (0,59)
          where (h,m,s) = descomponer x

-------------------------------------------------------------------------------
-- Ejercicio 8 - descomponer
-------------------------------------------------------------------------------
unEuro :: Double
unEuro = 166.386

pesetasAEuros:: Double->Double
pesetasAEuros x = (x/unEuro)

eurosAPesetas:: Double -> Double
eurosAPesetas x = (x*unEuro)


--p_inversas x = eurosAPesetas (pesetasAEuros x) == x

-------------------------------------------------------------------------------
-- Ejercicio 10 - raices
-------------------------------------------------------------------------------
raices:: Double->Double->Double->(Double,Double)
raices (a,b,c) = (((-b) + sqrt(4*a*c))/2,((-b) - sqrt(4*a*c))/2)

-------------------------------------------------------------------------------
-- Ejercicio 14 - potencia
-------------------------------------------------------------------------------

-- 14.a
potencia :: Integer -> Integer -> Integer
potencia b n = undefined -- completar

-- 14.b
potencia' :: Integer -> Integer -> Integer
potencia' b n = undefined -- completar

-- 14.c
p_pot b n =
   potencia b m == sol && potencia' b m == sol
   where sol = b^m
         m = abs n
-- 14.d
{-

Escribe en este comentario tu razonamiento:

-}
