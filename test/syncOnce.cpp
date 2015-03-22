// call_once example

#include <iostream>    // std::cout
#include <thread>      // std::thread, std::this_thread::sleep_for
#include <mutex>       // std::call_once, std::once_flag

std::once_flag flag;
int Gid = 0;

void setup()
{
    Gid++;
    std::cout << "Called once " << Gid << std::endl;
}

void doprint()
{
    std::call_once(flag, setup);
}

int main(void)
{
    std::thread t1(doprint);
    std::thread t2(doprint);
    std::thread t3(doprint);
    std::thread t4(doprint);

    t1.join();
    t2.join();
    t3.join();
    t4.join();

    std::cout << "Gid: " << Gid << std::endl;

    return 0;
}
