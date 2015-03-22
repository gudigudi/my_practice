// Vector.h

class Vector
{
public:
    Vector(int s);
    double& operator[](int);
    int size();
private:
    double* elem;
    int sz;
};

