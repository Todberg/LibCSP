/* Objective function */
max: +21 C1 +166 C2 +7 C3 +C7 +4 C8 +2 C10 +4 C11 +165 C12 +165 C14 +7 C15 +10 C16 +4 C17 +24 C18 +28 C19
 +16 C20;

/* Constraints */
-C9 -C13 = -1;
+C1 +C13 = 1;
+C9 -C18 = 0;
-C3 -C15 +C16 +C18 = 0;
+C15 -C20 = 0;
+C4 -C10 = 0;
+C6 -C8 -C11 = 0;
-C7 +C11 = 0;
+C5 -C17 = 0;
+C8 -C19 = 0;
-C16 +C17 = 0;
-C1 +C3 +C19 = 0;
-C14 +C20 = 0;
+C10 -C12 = 0;
-C2 +C7 = 0;
-C4 +C14 = 0;
-C6 +C12 = 0;
+C2 -C5 = 0;
+10 C3 +10 C8 -C16 >= 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20;
/* Mapping: 
    {Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} -> C1
    {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} -> C2
    {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} -> C3
    {Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} -> C4
    {Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} -> C5
    {Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} -> C6
    {Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} -> C7
    {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} -> C8
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C9
    {Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} -> C10
    {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} -> C11
    {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} -> C13
    {Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} -> C12
    {Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} -> C14
    {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} -> C16
    {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} -> C15
    {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} -> C17
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C18
    {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} -> C20
    {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} -> C19
Max-Cost-Flow problem with cost vector: 21 {Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} + 166 {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} + 7 {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} + 0 {Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} + 0 {Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} + 0 {Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} + 4 {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} + 1 {Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} + 2 {Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} + 4 {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} + 165 {Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} + 165 {Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} + 10 {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} + 7 {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + 4 {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} + 24 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 16 {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} + 28 {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} = -1
{Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} + {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} = 1
{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} - {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
{Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} - {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} = 0
-{Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} + {Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} = 0
{Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} - {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} - {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} = 0
-{Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} + {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} = 0
{Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} - {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} = 0
{Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} - {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} = 0
-{Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} = 0
-{Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} + {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} + {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} = 0
-{Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} + {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} = 0
{Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} - {Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} = 0
{Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} - {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} = 0
-{Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} + {Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} = 0
-{Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} + {Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} = 0
{Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} - {Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} = 0
10 {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} + 10 {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} - {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} >= 0
*/
