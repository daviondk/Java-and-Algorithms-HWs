#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;
int main()
{
    int n, m, k, s;
    cin >> n >> m >> k >> s;
    s--;
    vector<pair<pair<int,int>,int>> vrtxs;
    for(int i = 0; i < m; i++) {
        int x, y, w;
        cin >> x >> y >> w;
        x--;
        y--;
        vrtxs.push_back(make_pair(make_pair(x, y), w));
    }
    
    vector<vector<int>> ans(k+1, vector<int>(n, 1000000001));
    ans[0][s] = 0;
    for(int i = 1; i <= k; i++) {
        for(auto x : vrtxs) {
            if(ans[i-1][x.first.first] < 1000000001)
                ans[i][x.first.second] =
                    min(ans[i][x.first.second], 
                            ans[i-1][x.first.first] + x.second);
        }
    }
    for(int i = 0; i < n; i++) {
        if(ans[k][i] < 1000000001)
            cout << ans[k][i] << endl;
        else
            cout << -1 << endl;
    }
}