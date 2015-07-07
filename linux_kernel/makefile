
# example makefile for linux kernel development

KERNEL_INCLUDE= /usr/src/linux-headers-3.19.0-22-generic/include
CC=gcc
CFLAGS= -O -Wall -I$(KERNEL_INCLUDE)

# Target
OBJS= hello.o

all: $(OBJS)
	$(CC) $(CFLAGS) -c $<

install:
	insmod $(OBJS)

uninstall:
	rmmod hello

.PHONY: clean
clean:
	rm -f *.o
