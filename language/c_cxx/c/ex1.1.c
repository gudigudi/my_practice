#include <stdio.h>

/*
* Tell the compiler that we intend
* to use a function called show message.
* It has no arguments and return no value
* This is the "declaration".
*
*/

void show_message(void);
/*
* Another function, but this includes the body of
* the function. This is a "definition".
*/
int main(int argc, char const *argv[]) {
    int count;

    count = 0;
    while (count < 10) {
        show_message();
        count++;
    }

    return 0;
}

/*
* The body of the simple function.
* This is now a "definition".
*/
void show_message(){
    printf("hello\n");
}
