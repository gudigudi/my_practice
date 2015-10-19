
template <class Comparable>
Comparable maxSubSum( const vector<Comparable> & a, int left, int right )
{
    Comparable maxLeftBorderSum = 0, maxRightBorderSum = 0;
    Comparable leftBorderSum = 0, rightBorderSum = 0;
    int center = (left + right) / 2;

    if (left == right) {
        return a[left] > 0 ? a[left] : 0;
    }

    Comparable maxLeftSum = maxSubSum(a, left, center);
    Comparable maxRightSum = maxSubSum(a, center+1, right);

    for (int i = center; i >= left; --i) {
        leftBorderSum += a[i];
        if (leftBorderSum > maxLeftBorderSum) {
            maxLeftBorderSum = leftBorderSum;
        }
    }

    return max3( maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
}

template <class Comparable>
Comparable maxSubsequenceSum( const vector<Comparable> & a)
{
    return a.size() > 0 ? maxSubSum(a, 0, a.size()-1) : 0;
}
