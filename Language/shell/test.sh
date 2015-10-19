#!/bin/bash

if [[ -f ~/.bash_profile ]]; then
    echo "Your have a .bash_profile. Things are file."
else
    echo "Yikes! You have no .bash_profile!"
fi
