#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
vector<vector<int>> vrtxs;
vector<char> used;
vector<int> topsort;
bool cycle;
void dfs(int v) {
    used[v] = 1;
    for(auto u : vrtxs[v]) {
        if(!used[u]) {
            dfs(u);
        }
        else if(used[u] == 1) {
            cycle = true;
        }
    }
    topsort.push_back(v);
    used[v] = 2;
}

int main() {
    int n,m;
    cin >> n >> m;
    cycle = false;
    vrtxs.resize(n);
    used.resize(n,0);
    for(int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        vrtxs[x-1].push_back(y-1);
    }
    for(int v; v < n; v++) {
        if(!used[v]) {
            dfs(v);
        }
    }
    if(cycle) {
        cout << "-1";
        return 0;
    }
    for(int i = n - 1; i >= 0; i--) {
        cout << topsort[i] + 1 << " ";
    }
    return 0;
}