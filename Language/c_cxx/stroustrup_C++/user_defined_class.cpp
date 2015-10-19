

class complex
{
    double re, im;  // representation: two doubles
public:
    complex (double r, double i) : re{r}, im{i} {}
    complex (double r) : re{r}, im{0} {}
    complex () : re{0}, im{0} {}

    double real() const { return re; }
    void real(double d) { re = d; }
    double imag() const { return im; }
    void imag(double i) { im = i; }

    complex operator+=(complex z) { return {re+=z.re, im+=z.im}; }
    complex operator-=(complex z) { return {re-=z.re, im-=z.im}; }
    complex operator*=(complex);
    complex operator/=(complex);
};
