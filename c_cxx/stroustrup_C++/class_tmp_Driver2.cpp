

class Driver2 : public Driver
{
public:
    Driver2 (Register);  // 构造函数
    int read(char*, int n);
    bool reset();
    Status check();
    // 实现细节
    virtual ~Driver2 ();

private:
    /* data */
};
