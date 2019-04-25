#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

using namespace std;
vector<vector<pair<int,int>>> vrtxs;
vector<int> used;
vector<int> tin, tmin;
set<int> pts;

int t;
void dfs(int v, int grandd) {
    used[v] = 1;
    int cnt = 0;
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
                if(grandd >= 0 && tmin[u.first] >= tin[v]) {
                    pts.insert(v);
                }
                cnt++;
            }
            else {
                if(tmin[v] > tin[u.first]) {
                    tmin[v] = tin[u.first];
                }
            }
        }
    }
    used[v] = 2;
    if(cnt > 1) {
        if(grandd < 0) {
            pts.insert(v);
        }
    }
}

int col;
vector<int> color;
vector<int> used1;
void dfs1(int v, int cl, int dad) {
    used1[v] = true;
    for(auto u : vrtxs[v]) {
        if(u.second != dad) {
            if(!used1[u.first]) {
                if(tmin[u.first] >= tin[v]) {
                    col++;
                    int coll = col;
                    color[u.second] = coll;
                    dfs1(u.first, coll, u.second);
                }
                else {
                    color[u.second] = cl;
                    dfs1(u.first, cl, u.second);
                }
            } else if(tin[u.first] < tin[v])
                color[u.second] = cl;
        }
    }
}

int main() {
    t = 0;
    int n,m;
    cin >> n >> m;
    vrtxs.resize(n);
    used.resize(n, 0);
    tin.resize(n, INT_MAX);
    tmin.resize(n, INT_MAX);
    for(int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        vrtxs[x-1].push_back(make_pair(y-1, i));
        vrtxs[y-1].push_back(make_pair(x-1, i));
    }
    for(int v = 0; v < n; v++) {
        if(!used[v]) {
            dfs(v, -1);
        }
    }
    color.resize(m, 0);
    used1.resize(n, false);
    col = 0;
    for(int i = 0; i < n; i++) {
        if(!used1[i]){
            //col++;
            dfs1(i, col, -1);
        }
    }
    cout << col << endl;
    for(int i = 0; i < m; i++) {
        cout << color[i] << " ";
    }
    return 0;
}