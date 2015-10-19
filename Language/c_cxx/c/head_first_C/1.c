#include <stdio.h>

int main(void)
{
    int card_count = 11;
    if (card_count > 10)
        puts("The deck is hot. Increase bet.");

    int c = 10;
    while(c > 0) {
        puts("I must not write code in class");
        c--;
    }

    char ex[20];
    puts("Enter boyfriend's name: ");
    scanf("%19s", ex);
    printf("Dear %s.\n\n\tYou're history.\n", ex);

    char suit = 'H';
    switch (suit) {
        case 'C':
            puts("Clubs");
            break;
        case 'D':
            puts("Diamonds");
            break;
        case 'H':
            puts("Hearts");
            break;
        default:
            puts("Spades");
    }

    return 0;
}
