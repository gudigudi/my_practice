#include <cstdio>

class File_handle
{
public:
    File_handle (const char* n, const char* rw) {
        f = fopen(n, rw);
        if (f == 0)
            printf("Error open file\n");// throw Open_failure(n);
    }
    virtual ~File_handle ();

private:
    FILE* f;
};
