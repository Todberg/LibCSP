/* Objective function */
max: +57 C1 +8 C2 +13 C3 +13 C4 +33 C5 +8 C8 +13 C9 +13 C10 +45 C11;

/* Constraints */
-C6 -C7 = -1;
+C7 +C11 = 1;
-C5 +C6 = 0;
-C4 +C5 +C8 -C9 = 0;
+C4 -C8 = 0;
-C1 +C9 = 0;
+C1 +C2 -C3 -C10 = 0;
-C2 +C10 = 0;
+C3 -C11 = 0;
-C2 +1024 C3 >= 0;
-C8 +1024 C9 >= 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11;
/* Mapping: 
    {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} -> C1
    {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} -> C3
    {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} -> C2
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C5
    {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} -> C4
    {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} -> C7
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C6
    {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} -> C9
    {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} -> C8
    {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} -> C11
    {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} -> C10
Max-Cost-Flow problem with cost vector: 57 {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} + 8 {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + 13 {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} + 13 {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + 33 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} + 8 {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} + 13 {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} + 45 {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} + 13 {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} - {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = -1
{Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} + {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} = 1
-{Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} - {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} + {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} = 0
{Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} - {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} + {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} = 0
{Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} - {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} + {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} - {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} = 0
-{Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} = 0
{Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} - {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} = 0
1024 {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} - {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} >= 0
1024 {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} - {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} >= 0
*/
