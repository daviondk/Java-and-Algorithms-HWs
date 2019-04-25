#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

using namespace std;
vector<vector<int>> vrtxs;
vector<int> used;
vector<int> tin, tmin;
vector<bool> isBridge;
map<pair<int, int>, int> edges;

int t;
void dfs(int v, int grandd) {
    used[v] = 1;
    tin[v] = t;
    tmin[v] = tin[v];
    t++;
    
    for(auto u : vrtxs[v]) {
        if(u != grandd) {
            if(!used[u]) {
                dfs(u, v);
                if(tmin[v] > tmin[u]) {
                    tmin[v] = tmin[u];
                }
                if(tmin[u] > tin[v]) {
                    isBridge[edges[make_pair(u,v)]] = true;
                }
            }
            else {
                if(tmin[v] > tin[u]) {
                    tmin[v] = tin[u];
                }
            }
        }
    }
    used[v] = 2;
}

int main() {
    t = 0;
    int n,m;
    cin >> n >> m;
    vrtxs.resize(n);
    used.resize(n, 0);
    tin.resize(n, 1234567);
    tmin.resize(n, 1234567);
    isBridge.resize(m, false);
    for(int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        vrtxs[x-1].push_back(y-1);
        vrtxs[y-1].push_back(x-1);
        edges[make_pair(x-1, y-1)] = i;
        edges[make_pair(y-1, x-1)] = i;
    }
    for(int v; v < n; v++) {
        if(!used[v]) {
            dfs(v, -1);
        }
    }
    int k = 0;
    for(bool x : isBridge) {
        if(x) {
            k++;
        }
    } 
    cout << k << endl;
    set<int> bridges;
    for(auto x : edges) {
        if(isBridge[x.second]) {
            isBridge[x.second] = false;
            bridges.insert(x.second + 1);
        }
    }
    for(auto x : bridges) {
        cout << x << endl;
    }
    return 0;
}