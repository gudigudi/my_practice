/*
 * 八皇后问题
 *
 * 算法:
 *   要求: 在8*8的棋盘上，放置8个皇后，每个皇后不在彼此所在行，列或斜线上
 * 因为每行上必有一个皇后
 * 假设第1个皇后放在第1行的n列(0<n<LENGTH), 将该皇后的行，列与斜线标识为false，
 * 在标识为Y的区域放置第2个皇后，如此...
 * 若失败，则回溯
 */

#include <stdio.h>

#define LENGTH 8
#define Y 0
#define N 1

struct queen_t{
    int x;    // 皇后所在行
    int y;    // 皇后所在列
};

struct queen_t queens[LENGTH];  // 皇后
bool chess[LENGTH][LENGTH]; // 棋盘

void init_chess();  // 初始化chess
void show_chess();  // 展示chess
void init_queen();
void show_queen();
void set_chess_choosed(int row, int col);  // 设置棋盘上不可取的格子

int main(void)
{
    init_chess();    // 初始化棋盘
    show_chess();    // 展示初始化后的棋盘
    init_queen();    // 初始化皇后，每行各有一个皇后
	show_queen();    // 展示初始化后的皇后

    // 从第一个皇后开始
    int choosing = 0;
    for (queens[choosing].y = 0;  queens[choosing].y < LENGTH; queens[choosing].y++) {
        set_chess_choosed(queens[choosing].x, queens[choosing].y);

    }

    return 0;
}

void init_chess()
{
    for (int i = 0; i < LENGTH; ++i) {
        for (int j = 0; j < LENGTH; ++j) {
            chess[i][j] = true;
        }
    }
}

void show_chess()
{
    for (int i = 0; i < LENGTH; ++i) {
        for (int j = 0; j < LENGTH; ++j) {
            printf("%d  ", (int)chess[i][j]);
        }
        printf("\n");
    }
}

void init_queen()
{
    for (int i = 0; i < LENGTH; ++i) {
        queens[i].x = i;
        queens[i].y = -1;
    }
}

void show_queen()
{
    for (int i = 0; i < LENGTH; ++i) {
        printf("(%d, %d)  ", queens[i].x, queens[i].y);
    }
    printf("\n");
}

void set_chess_choosed(int row, int col)
{
    for (int i = 0; i < LENGTH; ++i) {
        for (int j = 0; j < LENGTH; ++j) {
            if (i == row ||
                j == col ||
                i+j == row+col ||
                i-j == row-col
                ) {           // chess[i][j]与chess[row][col]同行，同列或者在斜线上
                chess[i][j] = false;
            }
        }
    }
}
