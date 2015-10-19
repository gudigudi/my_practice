/* what do to: test data type of C
 * author:     gudi huolingdfsq@gmail.com
 * date:       2015-1-19
 * One line to give the program's name and a brief description.
 Copyright (C) 2015 gudi huolingdfsq@gmail.com

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

#include <stdio.h>

int main(void)
{
    // int, long
    printf("sizeof(int): %ld\n", sizeof(int));
    printf("sizeof(long): %ld\n", sizeof(long));

    // float, double, long double
    printf("sizeof(float): %ld\n", sizeof(float));
    printf("sizeof(double): %ld\n", sizeof(double));
    printf("sizeof(long double): %ld\n", sizeof(long double));

    // char
    printf("sizeof(char): %ld\n", sizeof(char));

    return 0;
}

