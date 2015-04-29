

class Driver1 : public Driver  // 某个驱动
{
public:
    Driver1 (Register);  // 构造函数
    int read(char*, int n);
    bool reset();
    Status check();
    virtual ~Driver1 ();
    // 实现细节
private:
    /* data */
};
