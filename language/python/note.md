# the Standard Library of Python #

## 1. Operating System Interface

The __os__ module provides dozens of functions for interacting with the
operating system:

``` python
>>> import os
>>> os.getcwd():    # return the current working directory
'/usr/local'
>>> os.chdir('/etc'):     # change current working directory to '/etc'
>>> os.system('mkdir hello'):   # run the command mkdir in the system shell
0
```


For daily file and directory management tasks, the __shutil__ module
provides a higher level that is easier to use.

``` python
>>> import shutil
>>> shutil.copyfile('data.db', 'archive.db')
>>> 'archive.db'
>>> shutil.move('build/executables', 'installdir')
>>> 'installdir'
```

## 2. File Wildcards

The glob module provides a function for making file lists from directory
wildcard searches:

``` python
import glob
glob.glob('*.py')
['primes.py', 'random.py', 'quote.py']
```

## 3. Command Line Arguments

Command line arguments are stored in the __sys__ module's arg attribute as
a list. For instance the following output results from running
__python demo.py one two three__ at the command line:

``` python
>>> import sys
>>> print(sys.argv)
['demo.py', 'one', 'two', 'three']
```

## 4. Error Output Redirection and Program Termination

The __sys__ module also has attributes for stdin, stdout, stderr.

``` python
>>> sys.stderr.write('Warning, log file not found starting a new one\n')
Warning, log file not found starting a new one
```

## 5. String Pattern Matching

The _re__ module provides regular expression tools for advanced string
processing. For complex matching and manipulation, regular expression offer
succinct, optimized solutions:

``` python
>>> import re
>>> re.findall(r'\bf[a-z]*', 'which foot or hand fell fastest')
['foot', 'fell', 'fastest']
```

## 6. Mathematics

The __math__ module gives access to the underlying C library functions for 
floating point math:

``` python
>>> import math
>>> math.cos(math.pi / 4)
0.70710678118654757
>>> math.log(1024, 2)
```

The __random__ module provides tools for making random selections:

``` python 
>>> import random
>>> random.choice(['apple', 'pear', 'banana'])
'apple'
>>> random.sample(range(100), 10)   # sampling without replacement
[30, 83, 16, 4, 8, 81, 41, 50, 18, 33]
>>> random.random()    # random float
0.17970987693706186
>>> random.randrange(6)    # random integer chosen from range(6)
4
```

## 7. Internet Access 

There are a number of modules for accessing the internet and processing 
internet protocols. Two of the simplest are __urllib.request__ for 
retrieving data from URLs and __smtplib__ for sending mail:

``` python 
>>> from urllib.request import urlopen
>>> for line in urlopen('http://tycho.usno.navy.mil/cgi-bin/timer.pl'):
...     line = line.decode('utf-8')  # Decoding the binary data to text.
...     if 'EST' in line or 'EDT' in line:  # look for Eastern Time
...         print(line)

<BR>Nov. 25, 09:43:32 PM EST

>>> import smtplib
>>> server = smtplib.SMTP('localhost')
>>> server.sendmail('soothsayer@example.org', 'jcaesar@example.org',
... ""To: jcaesar@example.org
... From: soothsayer@example.org
...
... Beware the Ides of March.
... """)
>>> server.quit()
```

