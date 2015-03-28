#include "dfs.h"

void dfs_visit(Graph const &graph, int u,
               vector<int> &d, vector<int> &f,
               vector<int> &pred, vector<vertexColor> &color,
               int &ctr, list<EdgeLabel> &labels) {
    color[u] = Gray;
    d[u] = ++ctr;
    for(VertexList::const_iterator ci = graph.begin(u);
        ci != graph.end(); ++ci) {
        int v = ci->first;

        edgeType type = Cross;
        if (color[v] == White) { type = tree; }
        else if (color[v] == Gray) { type = Backward; }
        else { if (d[u] < d[v]) type = Forward; }
        labels.push_back(EdgeLabel(u, v, type));

        if (color[v] == White) {
            pred[v] = u;
            dfs_visit(graph, v, d, f, pred, color, ctr, labels);
        }
    }

    color[u] = Black;
    f[u] = ++ctr;
}

void dfs_search(Graph const &graph, int s,
                vector<int> &d, vector<int> &f,
                vector<int> &pred, list<EdgeLabel> &labels) {
    int ctr = 0;
    const int n = graph.numVectices();
    vector<vertexColor> color (n, White);
    d.assign(n, -1);
    f.assign(n, -1);
    pred.assign(n, -1);
    labels.clear();

    dfs_visit(graph, s, d, f, pred, color, ctr, labels);
    for (int u = 0; u < n; ++u) {
        if (color[u] == White) {
            dfs_visit(graph, u, d, f, pred, color, ctr, labels);
        }
    }
}
