#include <fcntl.h>
#include <stdio.h>
#include <sched.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <errno.h>
#include <time.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/shm.h>

#define MAX_FN_SIZE         256
#define MAX_BUF_SIZE        256
#define DATA_BUF_SIZE       256
#define NR_CPUS             512
#define MAX_TASK_NUM        2048
#define MIN_INTERVAL        5   // seconds
#define ERR_DATA_BUFFER_SIZE    3   // Three 8-byte.
#define PARA_FIELD_NUM      5
#define MASK_SIZE           (NR_CPUS/64)
#define PATH_FORMAT         "/sys/devices/system/cpu/cpu%d/err_inject/"

#define vbprintf if (verbose) printf

typedef unsigned long u64;
typedef unsigned int u32;
typedef union err_type_info_u {
    struct {
        u64 mode    : 3,        // 0-2
            err_inj     : 3,    // 3-5
            err_sev     : 2,    // 6-7
            err_struct  : 5,    // 8-12
            struct_hier : 3,    // 13-15
            reserved    : 48;   // 16-63
    } err_type_info_u;
    u64 err_type_info;
} err_type_info_t;
typedef union err_struct_info_u {
    struct {
        u64 siv     : 1,    // 0
            c_t     : 2,    // 1-2
            cl_p    : 3,    // 3-5
            cl_id   : 3,    // 6-8
            cl_dp   : 1,    // 9
            reserved1   : 22,   // 10-31
            tiv     : 1,    // 32
            trigger : 4,    // 33-36
            trigger_pl  : 3,    // 37-36
            reserved2   : 24,   // 40-63
    } err_struct_info_cache;
    struct {
        u64 siv     : 1,    // 0
            tt      : 2,    // 1-2
            tc_tr   : 2,    // 3-4
            tr_slot : 8,    // 5-12
            reserved1   : 19,   // 13-31
            tiv     : 1,    // 32
            trigger : 4,    // 33-36
            trigger_pl  : 3,    // 37-39
            reserved2   : 24;   // 40-63
    } err_struct_info_tlb;
    struct {
        u64 siv     : 1,    // 0
            regfile_id  : 4,    // 1-4
            reg_num : 7,    // 5-11
            tiv     : 1,    // 32
            trigger : 4,    // 33-36
            trigger_pl  : 3,    // 37-39
            reserved2   : 24;   // 40-63
    } err_struct_info_register;
    struct {
        u64  reserved;
    } err_struct_info_bus_processor_interconnect;
    u64 err_struct_info;
} err_struct_info_t;
typedef union err_data_buffer_u {
    struct {
        u64 trigger_addr;   // 0-63
        u64 inj_addr;       // 64-127

    };
};


int verbose;
int sched_setaffinity(pid_t pid, unsigned int len, unsigned long *mask);

int log_info(int cpu, const char *fmt, ...)
{
    FILE *log;
    char fn[MAX_FN_SIZE];
    char buf[MAX_BUF_SIZE];
    va_list args;

    sprintf(fn, "%d.log", cpu);
    log = fopen(fn, "a+");
    if (log == NULL) {
        perror("Error open:");
        return -1;
    }

    va_start(args, fmt);
    vprintf(fmt, args);
    memset(buf, 0, MAX_BUF_SIZE);
    vsprintf(buf, fmt, args);
    va_end(args);

    fwrite(buf, sizeof(buf), 1, log);
    fclose(log);

    return 0;
}

