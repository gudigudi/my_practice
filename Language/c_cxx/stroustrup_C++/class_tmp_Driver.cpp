

class Driver
{
public:
    Driver ();
    virtual int read(char* p, int n) = 0;  // 从设备中读取最多n个字符到p
                                           // 返回读到的字符总数
    virtual bool reset() = 0;  // 重置设备
    virtual Status check() = 0;  // 读取状态
    virtual ~Driver ();

private:
    /* data */
};
