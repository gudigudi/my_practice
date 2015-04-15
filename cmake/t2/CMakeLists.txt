PROJECT(HELLO)
ADD_SUBDIRECTORY(src bin)

# install COPYRIGHT, README
INSTALL(FILES COPYRIGHT README DESTINATION share/doc/cmake/t2)
# install runhello.sh
INSTALL(PROGRAMS runhello.sh DESTINATION bin)
# install doc/hello.txt
INSTALL(DIRECTORY doc/ DESTINATION share/doc/cmake/t2)
