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
set<int> pts;

int t;
void dfs(int v, int grandd) {
    used[v] = 1;
    int cnt = 0;
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
                if(grandd >= 0 && tmin[u] >= tin[v]) {
                    isBridge[v] = true;
                    pts.insert(v);
                }
                cnt++;
            }
            else {
                if(tmin[v] > tin[u]) {
                    tmin[v] = tin[u];
                }
            }
        }
    }
    used[v] = 2;
    if(cnt > 1) {
        if(grandd < 0) {
            isBridge[v] = true;
            pts.insert(v);
        }
    }
}

int main() {
    t = 0;
    int n,m;
    cin >> n >> m;
    vrtxs.resize(n);
    used.resize(n, 0);
    tin.resize(n, 12345678);
    tmin.resize(n, 12345678);
    isBridge.resize(m, false);
    for(int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        vrtxs[x-1].push_back(y-1);
        vrtxs[y-1].push_back(x-1);
    }
    for(int v = 0; v < n; v++) {
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
    cout << pts.size() << endl;
    for(auto x : pts) {
        cout << x+1 << " ";
    }
    return 0;
}