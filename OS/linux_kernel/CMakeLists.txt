cmake_minimum_required(VERSION 3.3)
project(linux_kernel)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    buffered_io.c
    execl.c
    fork.c
    hello.c
    pipe.c
    select.c
    thread.c
    UDS_client.c
    UDS_server.c
    poll.c
    writev.c
    readv.c
    mmap.c)

add_executable(linux_kernel ${SOURCE_FILES})