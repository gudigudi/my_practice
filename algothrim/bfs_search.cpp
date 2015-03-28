#include <bfs.h>

void bfs_search(Graph const &graph, int s,
                vector<int> &dist, vector<int> &pred) {
    const int n = graph.numVertices();
    pred.assign(n, -1);
    dist.assign(n, numeric_limits<int>::max());
    vector<vertexColor> color(n, White);

    dist[s] = 0;
    color[s] = Gray;

    queue<int> q;
    q.push(s);
    while(!q.empty()) {
        int u = q.front();

        for (VertexList::const_iterator ci = graph.begin(u);
            ci != graph.end(u); ++ci) {
            int v = ci->first;
            if (color[v] == White) {
                dist[v] = dist[u]+1;
                pred[v] = u;
                color[v] = Gray;
                q.push(v);
            }
        }

        q.pop();
        color[u] = Black;
    }
}
