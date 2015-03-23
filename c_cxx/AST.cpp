#include <string>
#include <vector>

// ExprAST-Base class for all expression nodes.
class ExprAST {
public:
    virtual ~ExprAST() {}
};

// NumberExprAST-Expression class for numeric literals like "1.0"
class NumberExprAST : public ExprAST {
    double Val;
public:
    NumberExprAST(double val) : Val(val) {}
};

// VariableExprAST-Expression classes for referencing a variable, like "a".
class VariableExprAST : public ExprAST {
    std::string Name;
public:
    VariableExprAST(const std::string &name) : Name(name) {}
};

// BinaryExprAST-Expression class for a binary operator.
class BinaryExprAST : public ExprAST {
    char Op;
    ExprAST *LHS, *RHS;
public:
    BinaryExprAST(char op, ExprAST *lhs, ExprAST *rhs)
        : Op(op), LHS(lhs), RHS(rhs) {}
};

// CallExprAST-Expression class for function calls.
class CallExprAST : public ExprAST {
    std::string Callee;
    std::vector<ExprAST*> Args;
public:
    CallExprAST(const std::string &Callee, std::vector<ExprAST*> &args)
        : Callee(callee), Args(args) {}
};

