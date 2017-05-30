### Basic Type ###
``` go
bool

string

int  int8  int16  int32  int64
uint uint8 uint16 uint32 uint64 uintptr

byte    // uint8

rune    // int32, present as a Unicode key

float32 float64

complex64 complex128
```

**type conversion**
``` go
int(3.14)    
float64(1)
uint(3.14)
```

### basic syntax ###
**switch**
1. `switch`中自动包含`break`, 若要继续下一选项, 则需以fallthrough语句结束
2. 没有条件的`switch`同`switch true`一样, 这一构造使得可以用更清晰的形式来编写长的 if-then-else 链

**defer**
defer 语句会延迟函数的执行直到上层函数返回。
延迟调用的参数会立刻生成，但是在上层函数返回前函数都不会被调用
延迟的函数调用被压入一个栈中。当函数返回时， 会按照后进先出(stack)的顺序调用被延迟的函数调用

**struct(结构体)**
*结构体指针*
结构体字段可以通过结构体指针来访问。
通过指针间接的访问是透明的

**range**
for循环的range格式可以对slice或者map进行迭代循环

