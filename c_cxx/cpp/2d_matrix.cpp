#include <iostream>
#include <cstdlib>
#define NUM 10

class matrix_2d
{
public:
    void set_element(int row, int col, int value) {
        matrix[row][col] = value;
    }
    int get_element(int row, int col) {
        return matrix[row][col];
    }
    bool is_double_linked(int row, int col) {
        if (matrix[row][col] == matrix[col][row]) {
            return true;
        }
        return false;
    }
private:
    int matrix[NUM][NUM];
};

int main(int argc, char *argv[])
{
    matrix_2d m;
    for (int i = 0; i < NUM; ++i) {
        for (int j = 0; j < NUM; ++j) {
            m.set_element(i, j, std::rand() % 10);
        }
    }
    for (int i = 0; i < NUM; ++i) {
        for (int j = 0; j < NUM; ++j) {
            std::cout << m.get_element(i, j) << '\t';
        }
        std::cout << std::endl;
    }
    // get double linked vertex numbers
    int num = 0;
    for (int i = 0; i < NUM; ++i) {
        for (int j = 0; j < i; ++j) {
            if (m.get_element(i, j) == m.get_element(j, i)) {
                num++;
            }
        }
    }
    std::cout << num << std::endl;

    return 0;
}
