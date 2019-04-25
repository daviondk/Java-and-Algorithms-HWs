#include <vector>
#include <set>
#include <iostream>
#include <stack>

using namespace std;
vector<multiset<int>> w, rw;
vector<int> component;
vector<int> visted;


stack<int> ord;
void dfs1(int v) {
    visted[v] = true;
    for (int next : w[v])
        if(!visted[next])
            dfs1(next);
    ord.push(v);
}

int compc = 1;
void dfs2(int v) {
    visted[v] = true;
    component[v] = compc;
    for (int next : rw[v])
        if(!visted[next])
            dfs2(next);
}
vector<set<int>> s;
int main() {
    int n, m;
    cin >> n >> m;

    s.resize(12345);
    component.resize(n, 0);
    visted.resize(n, false);
    w.resize(n);
    rw.resize(n);

    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;
    
        w[a].insert(b);
        rw[b].insert(a);
    }
    for (int i = 0; i < n; i++){
        if(!visted[i]) {
            dfs1(i);
        }
    }

    for(int i = 0; i < n; i++) {
        visted[i] = false;
    }
    while (!ord.empty()) {
        if(!visted[ord.top()]) {
            dfs2(ord.top());
            compc++;
        }
        ord.pop();
    }
    int counter = 0;
    for (int i = 0; i < n; i++)
        for (int j : w[i])
            if (component[i] != component[j])
                s[component[i]].insert(component[j]);
                
    int res = 0;
    for(int i = 0; i < s.size(); i++) {
        res+=s[i].size();
    }
    cout << res;
}