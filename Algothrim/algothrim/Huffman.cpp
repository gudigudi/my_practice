

template<class Elem>
class HuffNode {        // Node abstract base class
public:
    virtual int weight() = 0;
    virtual bool isLeaf() = 0;
    virtual HuffNode* left() const = 0;
    virtual void setLeft(HuffNode*) = 0;
    virtual HuffNode* right() const = 0;
    virtual void setRight(HuffNode*) = 0;
};


template<class Elem>
class FreqPair {    // An element/frequency pair
private:
    Elem it;        // An element of some sort
    int freq;       // Frequency for the element
public:
    FreqPair(const Elem& e, int f)
    {
        it = e; freq = f;
    }
    ~FreqPair() {}
    int weight() { return freq; }
    Elem& val() { return it; }
};


template<class Elem>
class LeafNode: public HuffNode<Elem> {     // Leaf node subclass
private:
    FreqPair<Elem>* it;         // Frequency pair
public:
    LeafNode(const Elem& val, int freq)
    {
        it = new FreqPair<Elem>(val, freq);
    }
    int weight() { return it->weight(); }
    FreqPair<Elem>* val() { return it; }
    bool isLeaf() { return true; }
    virtual HuffNode* left() const { return NULL; }
    virtual void setLeft(HuffNode*) {}
    virtual HuffNode* right() const { return NULL; }
    virtual void setRight(HuffNode*) {}
};


template<class Elem>                // Internal node subclass
class IntlNode : public HuffNode<Elem> {
private:
    HuffNode<Elem>* lc;
    HuffNode<Elem>* rc;
    int wgt;
public:
    IntlNode(HuffNode<Elem>* l, HuffNode<Elem>* r)
    {
        wgt = l->weight() + r->weight();
        lc = l; rc = r;
    }
    int weight() { return wgt; }
    bool isLeaf() { return false; }
    HuffNode<Elem>* left() const { return lc; }
    void setLeft(HuffNode<Elem>* b) { lc = (HuffNode*)b; }
    HuffNode<Elem>* right() const { return rc; }
    void setRight(HuffNode<Elem>* b) { rc = (HuffNode*)b; }
};


/*
 * HuffTree
 */
template<class Elem>
class HuffTree {
private:
    HuffNode<Elem>* theRoot;
public:
    HuffTree(Elem& val, int freq)
    {
        theRoot = new IntlNode<Elem>(val, freq);
    }
    HuffTree(HuffTree<Elem>* l, HuffTree<Elem>* r)
    {
        theRoot = new IntlNode<Elem>(l->root(), r->root());
    }
    ~HuffTree() {}
    HuffNode<Elem>* root() { return theRoot; }
    int weight() { return theRoot->weight(); }
};
