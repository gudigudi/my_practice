#include <boost/thread/thread.hpp>
#include <iostream>

using namespace std;

void hello_world()
{
    cout << "Hello world, I'm a thread!" << endl;
}

int main()
{
    boost::thread my_thread(&hello_world);

    my_thread.join();

	return 0;
}
