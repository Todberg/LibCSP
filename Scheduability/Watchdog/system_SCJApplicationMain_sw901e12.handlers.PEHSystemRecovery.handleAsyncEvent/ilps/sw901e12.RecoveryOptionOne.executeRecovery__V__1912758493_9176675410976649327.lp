/* Objective function */
max: +13 C1 +57 C2 +8 C3 +13 C4 +13 C6 +8 C7 +13 C8 +33 C9 +45 C10;

/* Constraints */
-C5 -C11 = -1;
+C10 +C11 = 1;
+C5 -C9 = 0;
-C1 +C3 -C4 +C9 = 0;
-C3 +C4 = 0;
+C1 -C2 = 0;
+C2 -C6 +C7 -C8 = 0;
-C7 +C8 = 0;
+C6 -C10 = 0;
+1024 C6 -C7 >= 0;
+1024 C1 -C3 >= 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11;
/* Mapping: 
    {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} -> C1
    {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} -> C4
    {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} -> C3
    {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} -> C2
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C5
    {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} -> C6
    {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} -> C7
    {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} -> C8
    {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} -> C11
    {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} -> C10
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C9
Max-Cost-Flow problem with cost vector: 13 {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} + 57 {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} + 8 {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} + 13 {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} + 13 {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} + 8 {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + 13 {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} + 45 {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} + 33 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} = -1
{Edge:(#0 ENTRY : #8 EXIT)[CallString.EMPTY]} + {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} = 1
{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} - {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} = 0
{Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} - {Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} = 0
{Edge:(#4 basic(3) : #5 basic(4))[CallString.EMPTY]} - {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} + {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} - {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} = 0
-{Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} + {Edge:(#5 basic(4) : #6 basic(5))[CallString.EMPTY]} = 0
{Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} - {Edge:(#7 basic(6) : #8 EXIT)[CallString.EMPTY]} = 0
1024 {Edge:(#5 basic(4) : #7 basic(6))[CallString.EMPTY]} - {Edge:(#6 basic(5) : #5 basic(4))[CallString.EMPTY]} >= 0
1024 {Edge:(#2 basic(1) : #4 basic(3))[CallString.EMPTY]} - {Edge:(#3 basic(2) : #2 basic(1))[CallString.EMPTY]} >= 0
*/
