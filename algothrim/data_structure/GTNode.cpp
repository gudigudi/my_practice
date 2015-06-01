// Generial tree node ADT
template<class Elem>
class GTNode {
public:
    GTNode(const Elem&);
    ~GTNode();

    Elem value();
    bool isLeaf();
    GTNode* parent();
    GTNode* leftmost_child();
    GTNode* rightmost_child();
    void setValue(Elem&);
    void insert_first(GTNode<Elem>* n);
    void insert_next(GTNode<Elem>* n);
    void remove_first();
    void remove_next();
};


// Generial tree ADT
template<class Elem>
class GenTree
{
public:
    GenTree ();
    virtual ~GenTree ();
    void clear();
    GTNode* root();
    // Combine two subtrees
    void newroot(Elem&, GTNode<Elem>*, GTNode<Elem>*);
    void print();
private:
    void printhelp(GTNode*);
};
