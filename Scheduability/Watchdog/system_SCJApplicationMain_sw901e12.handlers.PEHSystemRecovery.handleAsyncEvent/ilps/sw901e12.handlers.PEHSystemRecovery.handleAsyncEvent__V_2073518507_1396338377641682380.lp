/* Objective function */
max: +43269 C1 +21 C4 +25 C5 +11 C6 +25 C7;

/* Constraints */
-C3 -C8 = -1;
+C3 +C4 = 1;
-C5 -C7 +C8 = 0;
-C6 +C7 = 0;
+C2 -C4 +C5 = 0;
-C1 +C6 = 0;
+C1 -C2 = 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8;
/* Mapping: 
    {Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} -> C1
    {Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} -> C2
    {Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} -> C3
    {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} -> C4
    {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} -> C5
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C7
    {Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} -> C6
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C8
Max-Cost-Flow problem with cost vector: 43269 {Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} + 0 {Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} + 21 {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} + 25 {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} + 11 {Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} + 25 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} - {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = -1
{Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} + {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} = 1
-{Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
{Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} - {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} + {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} = 0
-{Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} + {Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} = 0
{Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} - {Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} = 0
*/
