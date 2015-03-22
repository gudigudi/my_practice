#include <stdio.h>

int get_palindromic(int num) {
    int palindromic = 0;
    while (num != 0) {
        palindromic = 10 * palindromic + num%10;
        num /= 10;
    }
    return palindromic;
}

int main(void)
{
    int largest_palindromic_num;
    bool isFined = false;

    for (int i = 1000; i > 100; i--) {
        for (int j = 1000; j > 100; j--) {
            if (i*j == get_palindromic(i*j)) {
                isFined = true;
                largest_palindromic_num = i*j;
                break;
            }
        }
        if (isFined == true)
            break;
    }

    printf("the number is: %d\n", largest_palindromic_num);

    return 0;
}
