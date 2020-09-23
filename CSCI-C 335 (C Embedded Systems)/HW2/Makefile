AS = arm-none-eabi-as
GCC = arm-none-eabi-gcc
OPTIONS = -g -O1 -mcpu=cortex-m0 -mthumb 
SPECS = --specs=rdimon.specs -lc -lrdimon

all : test.o t1.o HW2.elf

HW2.elf :
	$(GCC) $(OPTIONS) $(SPECS) t1.o test.o -o HW2.elf  

test.o : 
	$(GCC) $(OPTIONS) -c test.c -c -o test.o

t1.o : 
	$(AS) -g -mcpu=cortex-m0 -mthumb t1.s -o t1.o 

clean: 
	rm *.o
	rm HW2.elf
	rm *.wav

test : HW2.elf     
	qemu-system-arm  -machine versatilepb -cpu cortex-m3 -nographic \
        -monitor null -serial null \
        -semihosting -kernel HW2.elf
