#include<vector>
#include<iostream>
#include<string>
using namespace std;

int main() {
	//freopen("*.in", "r", stdin);
	//freopen("*.out", "w", stdout);
	string s;
	cin >> s;
	int n = s.length();
	vector<int> p(n);
	p[0] = 0;
	for (int i = 1; i < n; i++) {
		p[i] = p[i-1];
		while (p[i] != 0 && s[i] != s[p[i]])
			p[i] = p[p[i] - 1];
		if (s[p[i]] == s[i])
			p[i]++;
	}
	for (auto &x : p) {
		cout << x << " ";
	}
	return 0;
}