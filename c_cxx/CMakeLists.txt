cmake_minimum_required(VERSION 2.8.4)
project(C_CXX)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    test_create_pthread.c)

add_executable(C_CXX ${SOURCE_FILES})