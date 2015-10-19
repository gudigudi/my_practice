#ifndef GETOPT_H
#define GETOPT_H

/* exported variables */
extern int opterr, optind, optopt;
extern char *optarg;

/* exported function */
int getopt(int, char **, char *);

#endif
