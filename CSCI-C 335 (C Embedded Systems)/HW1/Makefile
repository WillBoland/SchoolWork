CC = gcc
CFLAGS = -g
LDFLAGS = -g

all: rpn

rpn: rpn.o
	$(CC) $(CFLAGS) rpn.o -o rpn

clean :
	rm -f rpn rpn.o

rpn.o: rpn.c
