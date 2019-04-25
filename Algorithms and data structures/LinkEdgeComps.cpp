#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

using namespace std;
vector<vector<pair<int, int>>> vrtxs;
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
        if(u.second != grandd) {
            if(!used[u.first]) {
                dfs(u.first, u.second);
                if(tmin[v] > tmin[u.first]) {
                    tmin[v] = tmin[u.first];
                }
                if(tmin[u.first] > tin[v]) {
                    isBridge[u.second] = true;
                }
            }
            else {
                if(tmin[v] > tin[u.first]) {
                    tmin[v] = tin[u.first];
                }
            }
        }
    }
    used[v] = 2;
}
int col;
vector<int> color;
void dfs1(int v) {
    color[v] = col;
    for(auto u : vrtxs[v]) {
        if(!color[u.first] && !isBridge[u.second]) {
            dfs1(u.first);
        }
    }       
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
        vrtxs[x-1].push_back(make_pair(y-1, i));
        vrtxs[y-1].push_back(make_pair(x-1, i));
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
    color.resize(n, 0);
    col = 0;
    for(int i = 0; i < n; i++) {
        if(!color[i]){
            col++;
            dfs1(i);
        }
    }
    cout << col << endl;
    for(int i = 0; i < n; i++) {
        cout << color[i] << " ";
    }
    return 0;
}