#!/bin/bash

func1()
{
    echo "function 1" 
}

# use function func1
echo `func1`

echo $(func1)

