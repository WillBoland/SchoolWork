CC = gcc
CFLAGS = -g
LDFLAGS = -g

all: hw04

hw04: hw04.o
	$(CC) $(CFLAGS) hw04.o -o hw04

clean:
	rm -f hw04 hw04.o test.out test.diff test.bin test.o test.hex

hw04.o: hw04.c

test:
	make clean --no-print-directory; \
	make --no-print-directory; \
	arm-none-eabi-as test.asm -o test.o; \
	arm-none-eabi-objcopy test.o -O binary --only-section=.text test.bin; \
	hexdump -x test.bin |sed -e 's/^[^ ]*//' > test.hex; \
	./hw04 < test.hex > test.out; \
	diff test.asm test.out > test.diff; \
	echo "";

