/*
 * code from linux/Document/cdrom
 */
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <linux/cdrom.h>

int main(int argc, char *argv[])
{
    char *program;
    char *device;
    int fd;
    int status;
    int verbose = 0;
    int slot = -1, x_slot;
    int total_slots_available;

    program = argv[0];

    ++argv;
    --argc;

    if (argc < 1 || argc > 3) {
        fprintf(stderr, "usage: %s [-v] <device> [<slot>]\n", program);
        fprintf(stderr, "        Slots are numbered 1 -- n.\n");
        exit(1);
    }

    if (strcmp(argv[0], "-v") == 0) {
        verbose = 1;
        ++argv;
        --argc;
    }

    device = argv[0];

    if (argc == 2)
        slot = atoi(argv[1]) - 1;

    // open device
    fd = open(device, O_RDONLY | O_NONBLOCK);
    if (fd < 0) {
        fprintf(stderr, "%s: open failed for `%s`: %s\n", program, device, strerror(errno));
        exit(1);
    }

    // Check CD player status
    total_slots_available = ioctl(fd, CDROM_CHANGER_NSLOTS);
    if (total_slots_available <= 1) {
        fprintf(stderr, "%s: Device `%s` is not an ATAPI compliant CD changer.\n", program, device);
        exit(1);
    }

    if (slot >= 0) {
        if (slot >= total_slots_available) {
            fprintf(stderr, "Bad slot number. Should be 1 -- %d.\n", total_slots_available);
            exit(1);
        }

        // load
        slot = ioctl(fd, CDROM_SELECT_DISC, slot);
        if (slot < 0) {
            fflush(stdout);
            perror("CDROM_SELECT_DISC");
            exit(1);
        }
    }
    return 0;
}
