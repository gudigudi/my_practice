/*
 * get a task execute time
 */
#include <iostream>
#include <string>
#include <time.h>

using namespace std;

int main()
{
    const string TIME_MESSAGE_1 = "The elapsed time was ";
    const string TIME_MESSAGE_2 = " seconds.";
    const string CLOSE_WINDOW_PROMPT =
        "Please press the Enter key to close this output window:";
    time_t start_time,
           finish_time,
           elapsed_time;

    start_time = time(NULL);

    // task execute

    finish_time = time(NULL);
    elapsed_time = finish_time - start_time;

    cout << TIME_MESSAGE_1 << elapsed_time << TIME_MESSAGE_2 << endl;
    cout << endl << endl << CLOSE_WINDOW_PROMPT;
    cin.get();

	return 0;
}
