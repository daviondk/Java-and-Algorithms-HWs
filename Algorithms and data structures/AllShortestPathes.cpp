#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;
vector<vector<int>> list;
vector<bool> used;
void dfs(int i) {
    used[i] = true;
    for(auto &x : list[i]) {
        if(!used[x])
            dfs(x);
    }
}
int main()
{
    long long n, m, k, s;
    cin >> n >> m >> s;
    list.resize(n);
    used.resize(n);
	k = n;
    s--;
    vector<pair<pair<long long,long long>,long long>> vrtxs;
    for(long long i = 0; i < m; i++) {
        long long x, y, w;
        cin >> x >> y >> w;
        x--;
        y--;
        list[x].push_back(y);
        vrtxs.push_back(make_pair(make_pair(x, y), w));
    }
    
    vector<vector<long long>> ans(k*3+1, vector<long long>(n, 1e18*4+1));
    ans[0][s] = 0;
    for(long long i = 1; i <= n * 3; i++) {
        for(int j = 0; j < n; j++)
            ans[i][j] = ans[i-1][j];
        for(auto& x : vrtxs) {
            if(ans[i-1][x.first.first] < 1e18*4+1){
                ans[i][x.first.second] =
                    min(ans[i][x.first.second], 
                            ans[i-1][x.first.first] + x.second);
            }
        }
    }
    vector<int> mark(n, false);
    for(int j = 0; j < n; j++)
        for(int i = n+1; i <= n*3; i++)
            if(ans[i][j] != ans[n][j])
                mark[j] = true;
    for(int i = 0; i < n; i++)
        if(mark[i])
            dfs(i);
    for(long long i = 0; i < n; i++) {
		if(!mark[i] && !used[i]) {
			if(ans[n][i] < 1e18*4+1)
				cout << ans[n][i] << endl;
			else
				cout << "*" << endl;
		} else cout << "-" << endl;
    }
}