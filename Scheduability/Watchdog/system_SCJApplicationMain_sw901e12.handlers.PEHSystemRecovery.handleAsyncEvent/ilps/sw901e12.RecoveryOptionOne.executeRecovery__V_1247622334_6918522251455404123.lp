/* Objective function */
max: +57 C2 +8 C3 +13 C4 +33 C5 +8 C7 +13 C8 +13 C9 +13 C10 +45 C11;

/* Constraints */
-C1 -C6 = -1;
+C6 +C11 = 1;
+C1 -C5 = 0;
+C3 -C4 +C5 -C10 = 0;
-C3 +C10 = 0;
-C2 +C4 = 0;
+C2 +C7 -C8 -C9 = 0;
-C7 +C8 = 0;
+C9 -C11 = 0;
-C7 +10000000 C9 >= 0;
-C3 +10000000 C4 >= 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11;
/* Mapping: 
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C1
    {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} -> C2
    {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} -> C6
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C5
    {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} -> C4
    {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} -> C3
    {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} -> C7
    {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} -> C9
    {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} -> C8
    {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} -> C10
    {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} -> C11
Max-Cost-Flow problem with cost vector: 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} + 57 {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} + 8 {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} + 13 {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} + 33 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} + 8 {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + 13 {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} + 13 {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} + 13 {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + 45 {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} = -1
{Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} + {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} = 1
{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} - {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} = 0
-{Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} = 0
-{Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} + {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} = 0
{Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} + {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} - {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} - {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} = 0
-{Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} = 0
{Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} - {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} = 0
-{Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + 10000000 {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} >= 0
10000000 {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} - {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} >= 0
*/
