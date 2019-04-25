#include<vector>
#include<iostream>
#include<string>
using namespace std;

int main() {
	//freopen("*.in", "r", stdin);
	//freopen("*.out", "w", stdout);
	string t;
	string pt;
	cin >> pt;
	cin >> t;
	int n = pt.length() + t.length() + 1;
	int m = pt.length();
	char c = 0;
	pt += c + t;
	vector<int> p(n);
	p[0] = 0;
	for (int i = 1; i < n; i++) {
		p[i] = p[i - 1];
		while (p[i] != 0 && pt[i] != pt[p[i]])
			p[i] = p[p[i] - 1];
		if (pt[p[i]] == pt[i])
			p[i]++;
	}
	vector<int> ans;
	for (int i = m; i < n; i++) {
		if (p[i] == m)
			ans.push_back(i - 2 * m + 1);
	}
	cout << ans.size() << endl;
	for (auto &x : ans) {
		cout << x << " ";
	}
	return 0;
}