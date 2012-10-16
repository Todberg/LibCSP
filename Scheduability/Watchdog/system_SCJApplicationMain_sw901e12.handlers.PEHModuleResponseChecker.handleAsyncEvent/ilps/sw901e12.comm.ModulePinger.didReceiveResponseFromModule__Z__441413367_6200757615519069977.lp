/* Objective function */
max: +34 C2;

/* Constraints */
-C1 -C3 = -1;
+C1 +C2 = 1;
-C2 +C3 = 0;

/* Integer definitions */
int C1,C2,C3;
/* Mapping: 
    {Edge:(#0 ENTRY : #2 EXIT)[CallString.EMPTY]} -> C1
    {Edge:(#1 basic(0) : #2 EXIT)[CallString.EMPTY]} -> C2
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C3
Max-Cost-Flow problem with cost vector: 0 {Edge:(#0 ENTRY : #2 EXIT)[CallString.EMPTY]} + 34 {Edge:(#1 basic(0) : #2 EXIT)[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #2 EXIT)[CallString.EMPTY]} - {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = -1
{Edge:(#0 ENTRY : #2 EXIT)[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 EXIT)[CallString.EMPTY]} = 1
-{Edge:(#1 basic(0) : #2 EXIT)[CallString.EMPTY]} + {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = 0
*/
