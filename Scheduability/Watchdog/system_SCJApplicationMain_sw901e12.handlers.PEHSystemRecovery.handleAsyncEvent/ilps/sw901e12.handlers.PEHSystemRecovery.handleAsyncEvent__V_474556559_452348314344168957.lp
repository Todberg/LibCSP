/* Objective function */
max: +11 C3 +21 C4 +25 C6 +25 C7 +43298 C8;

/* Constraints */
-C2 -C5 = -1;
+C2 +C4 = 1;
+C5 -C6 -C7 = 0;
-C3 +C7 = 0;
+C1 -C4 +C6 = 0;
+C3 -C8 = 0;
-C1 +C8 = 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8;
/* Mapping: 
    {Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} -> C2
    {Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} -> C1
    {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} -> C4
    {Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} -> C3
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C5
    {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} -> C6
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C7
    {Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} -> C8
Max-Cost-Flow problem with cost vector: 0 {Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} + 21 {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} + 11 {Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} + 25 {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} + 25 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 43298 {Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} - {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} = -1
{Edge:(#0 ENTRY : #6 EXIT)[CallString.EMPTY]} + {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} = 1
{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
{Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} - {Edge:(#5 basic(3) : #6 EXIT)[CallString.EMPTY]} + {Edge:(#1 basic(0) : #5 basic(3))[CallString.EMPTY]} = 0
{Edge:(#2 basic(1) : #3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V))[CallString.EMPTY]} - {Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} = 0
-{Edge:(#4 SPLIT : #5 basic(3))[CallString.EMPTY]} + {Edge:(#3 invoke(sw901e12.RecoveryOptionOne.executeRecovery()V) : #4 SPLIT)[CallString.EMPTY]} = 0
*/
