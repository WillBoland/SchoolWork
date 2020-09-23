//C335/Spring 2020   Homework04   Will Boland   woboland
//03/28/2020

//Disassembler for HW4

//--------------------
//MARK: Include Statements
#include <stdio.h>

//--------------------
//MARK: Macros
#define ADD_REG 6
#define PROC_REG 16
#define ADD 0
#define rm(x) (((x) >> 3) & 0x7)
#define rn(x) (((x) >> 6) & 0x7)
#define rd(x) (((x) >> 0) & 0x7)
#define opc(x) (((x) >> 9) & 0x1)
#define opcode(x) (((x) >> 6) & 0xf)
#define isOp(x) (((x) >> 10) & 0x1f)

//--------------------
//MARK: Constants
const char *regnames[] = {"r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15", "lr", "pc"};
const char *opname[] = {"ands", "eors", "lsls", "lsrs", "asrs", "adcs", "sbcs", "rors", "tst", "rsbs", "cmp", "cmn", "orrs", "muls", "bics", "mvns"};

//--------------------
//MARK: Function Prototypes
void printProcReg(int inst);
void printAddReg(int inst);
void printOther(int inst);

//--------------------
//MARK: Functions
//----------------------------------------------------------------------------
/*
 * Executes on start of program. Decodes ARM Thumb- 2 machine code instructions.
 * - Returns: an int that signifies the end of program execution
 */
int main(void) {
  //Start of all .asm instructions
  printf(".text\n.syntax unified\n.thumb\n");
  //as outlined in HW08 instructions
  int inst;
  while (scanf("%x", &inst) == 1) {
    printf("\t");
    switch(isOp(inst)) {
      case PROC_REG:
        printProcReg(inst);
        break;
      case ADD_REG:
        printAddReg(inst);
	break;
      default:
        printOther(inst);
	break;
    }
  }
  return 1;
}

//----------------------------------------------------------------------------
void printProcReg(int inst) {
  printf("%s %s,%s\n", opname[opcode(inst)], regnames[rd(inst)], regnames[rm(inst)]); //Proc Reg Operation
}

//----------------------------------------------------------------------------
void printAddReg(int inst) {
  if(opc(inst) == ADD) {
	printf("adds %s,%s,%s\n", regnames[rd(inst)], regnames[rm(inst)], regnames[rn(inst)]); //Add
      } else if (opc(inst) == 1) {
	printf("subs %s,%s,%s\n", regnames[rd(inst)], regnames[rm(inst)], regnames[rn(inst)]); //Subtract
      }
}

//----------------------------------------------------------------------------
void printOther(int inst) {
  printf(".hword 0x%x\n", inst); //we didn't do every command
}
