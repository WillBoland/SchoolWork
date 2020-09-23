//
// dump.c
//
void dump_memory(void *p, int len)
{
  //top portion formatting
  printf("%-12s %-6s %-9s %-10s %-12s %-12s %-10s", "address", "char", "hexCh", "short", "integer", "float", "doubleFloat\n");

  int i;
  for (i = 0; i < len; i++) {
    //all different types we wanted to print
    unsigned char current = *(unsigned char *)(p + i);
    unsigned char symbol = current;
    short sCurrent = *(short *)(p + i);
    int iCurrent = *(int *)(p + i);
    float fCurrent = *(float *)(p + i);
    double dCurrent = *(double *)(p + i);

    //unprintable char
    if(current < 32 || current > 126) {
      symbol = '?';
    }
    //formatting based off of what we wanted to print for memory dyump
    if(i % 8 == 0) {
      printf("%8p %2c 0x%-02x \t %+5hu %+14d %+14e %+14e \n", p + i, symbol, current, sCurrent, iCurrent, fCurrent, dCurrent);
    } else if(i % 4 == 0) {
      printf("%8p %2c 0x%-02x \t %+5hu %+14d %+14e \n", p + i, symbol, current, sCurrent, iCurrent, fCurrent);
    } else if(i % 2 == 0) {
      printf("%8p %2c 0x%-02x \t %+5hu \n", p + i, symbol, current, sCurrent, iCurrent, fCurrent, dCurrent);
    } else {
      printf("%8p %2c 0x%-02x \n", p + i, symbol, current, sCurrent, iCurrent, fCurrent, dCurrent);
    }
  }
}
