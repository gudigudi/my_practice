#include <stdio.h>              /* for fprintf() and EOF */
#include <string.h>             /* for strchr()  */
#include "getopt.h"             /* consistency check */

/* variables */
int opterr = 1;                 /* getopt prints errors if this is on */
int optind = 1;                 /* token pointer */
int optopt;                     /* option character passed back to user */
char *optarg;                   /* flag argument (or value) */

/* function */
int getopt(int argc, char **argv, char *opt)
{
    static int sp = 1;
    register char *cp;

    if (sp == 1) {
        if (optind >= argc || argv[optind][0] != '-' || argv[optind][1] == '\0')
            return EOF;
        else if (strcmp(argv[optind], "--") == 0) {
            optind++;
            return EOF;
        }
    }

    optopt = argv[optind][sp];

    if (optopt == ':' || (cp = strchr(opts, optopt)) == NULL)
    {
        if (opterr)
            fprintf(stderr, "%s: invalid option -- '%c'\n", argv[0], optopt);
        /* if no characters left in this token, move to next token */
        if (argv[optind][++sp] == '\0') {
            optind++;
            sp = 1;
        }
        return '?';
    }

    if (*++cp == ':') {
        /* if a value is expected, get it */
        if (argv[optind][sp + 1] != '\0')
            optarg = argv[optind++] + (sp + 1);
        else if (++optind >= argc) {
            if (opterr)
                fprintf(stderr, "%s: option requires an argument == '%c'\n", argv[0], optopt);
            sp = 1;
            return '?';
        }
        else
    }
}
