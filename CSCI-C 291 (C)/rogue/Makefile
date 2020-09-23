IDIR =../include
CC=gcc
CFLAGS= -I$(IDIR) -g -O0

ODIR=.

LIBS=-lncurses

_DEPS =
DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))

_OBJ = main.o tile.o game.o room.o key.o save.o
OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))

$(ODIR)/%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

all: rogue

rogue: $(OBJ)
	gcc -o $@ $^ $(CFLAGS) $(LIBS)

.PHONY: clean

clean:
	rm -f *~ core $(INCDIR)/*~
	rm -f rogue
	rm -f *.o
	rm -f *.game
	rm -rfd ./saves/

etags:
	find . -type f -iname "*.[ch]" | xargs etags --append
