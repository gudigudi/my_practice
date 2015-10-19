#include "tcp_demo.h"

static int count = 10;  // epoll and thread number, default 10
static int verbose;  // debug

struct gepoll *ge;

struct map_list_head mh = {
        .head = NULL,
        .tail = NULL,
};

static void usage(void)
{
    printf("Usage: ./tcp or ./tcp -v -n optarg\n");
}

static int unlimit(void)
{
    struct rlimit rlim;
    rlim.rlim_cur = rlim.rlim_max = 1 << 16;
    return setrlimit(RLIMIT_NOFILE, &rlim);
}

