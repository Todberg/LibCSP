/* Objective function */
max: +134 C1 +4 C3 +7 C4 +7 C6 +4 C7 +2 C8 +21 C9 +16 C10 +134 C11 +10 C14 +24 C15 +28 C16 +C18 +4 C19 +135 C20;

/* Constraints */
-C2 -C17 = -1;
+C9 +C17 = 1;
+C2 -C15 = 0;
-C4 -C6 +C14 +C15 = 0;
+C4 -C10 = 0;
+C5 -C8 = 0;
-C3 -C7 +C13 = 0;
+C7 -C18 = 0;
+C12 -C19 = 0;
+C3 -C16 = 0;
-C14 +C19 = 0;
+C6 -C9 +C16 = 0;
-C1 +C10 = 0;
+C8 -C11 = 0;
+C18 -C20 = 0;
+C1 -C5 = 0;
+C11 -C13 = 0;
-C12 +C20 = 0;
+10 C3 +10 C6 -C14 >= 0;

/* Integer definitions */
int C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20;
/* Mapping: 
    {Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} -> C1
    {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} -> C2
    {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} -> C3
    {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} -> C4
    {Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} -> C5
    {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} -> C6
    {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} -> C7
    {Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} -> C8
    {Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} -> C9
    {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} -> C10
    {Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} -> C11
    {Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} -> C12
    {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} -> C14
    {Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} -> C13
    {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} -> C15
    {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} -> C16
    {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} -> C17
    {Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} -> C18
    {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} -> C19
    {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} -> C20
Max-Cost-Flow problem with cost vector: 134 {Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} + 4 {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} + 0 {Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} + 7 {Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} + 7 {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} + 4 {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} + 2 {Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} + 21 {Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} + 16 {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} + 134 {Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} + 0 {Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} + 0 {Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} + 10 {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} + 24 {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} + 28 {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} + 0 {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} + 1 {Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} + 4 {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} + 135 {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]}
Flow
-{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} = -1
{Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} + {Edge:(#0 ENTRY : #15 EXIT)[CallString.EMPTY]} = 1
{Edge:(#0 ENTRY : #1 basic(0))[CallString.EMPTY]} - {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
-{Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} - {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} + {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#1 basic(0) : #2 basic(1))[CallString.EMPTY]} = 0
{Edge:(#2 basic(1) : #3 basic(2))[CallString.EMPTY]} - {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} = 0
{Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} - {Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} = 0
-{Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} - {Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} + {Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} = 0
{Edge:(#9 basic(6) : #10 basic(7))[CallString.EMPTY]} - {Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} = 0
{Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} - {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} = 0
{Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} - {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} = 0
-{Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} + {Edge:(#16 basic(9) : #17 basic(11))[CallString.EMPTY]} = 0
{Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} - {Edge:(#13 basic(12) : #15 EXIT)[CallString.EMPTY]} + {Edge:(#11 basic(10) : #13 basic(12))[CallString.EMPTY]} = 0
-{Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} + {Edge:(#3 basic(2) : #4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;))[CallString.EMPTY]} = 0
-{Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} + {Edge:(#6 basic(4) : #7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z))[CallString.EMPTY]} = 0
{Edge:(#10 basic(7) : #12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V))[CallString.EMPTY]} - {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} = 0
{Edge:(#4 invoke(sw901e12.Module.getModulePinger()Lsw901e12/comm/ModulePinger;) : #5 SPLIT)[CallString.EMPTY]} - {Edge:(#5 SPLIT : #6 basic(4))[CallString.EMPTY]} = 0
{Edge:(#7 invoke(sw901e12.comm.ModulePinger.didReceiveResponseFromModule()Z) : #8 SPLIT)[CallString.EMPTY]} - {Edge:(#8 SPLIT : #9 basic(6))[CallString.EMPTY]} = 0
-{Edge:(#14 SPLIT : #16 basic(9))[CallString.EMPTY]} + {Edge:(#12 invoke(sw901e12.comm.ModulePinger.resetDidReceiveResponseFlag()V) : #14 SPLIT)[CallString.EMPTY]} = 0
10 {Edge:(#9 basic(6) : #11 basic(10))[CallString.EMPTY]} + 10 {Edge:(#2 basic(1) : #13 basic(12))[CallString.EMPTY]} - {Edge:(#17 basic(11) : #2 basic(1))[CallString.EMPTY]} >= 0
*/
