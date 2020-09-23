OBJECTS = poly.o dump.o

CC = gcc
CFLAGS = -g

all : poly

poly : $(OBJECTS)
	$(CC) $(CFLAGS) $(OBJECTS) -o poly

clean :
	rm -f poly $(OBJECTS)

poly.o : poly.c
dump.o : dump.c
