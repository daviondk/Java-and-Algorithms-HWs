#include<vector>
#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

int main() {
	//freopen("*.in", "r", stdin);
	//freopen("*.out", "w", stdout);
	string t;
	string pt;
	cin >> pt;
	int n = pt.length();
	vector<int> p(n);
	p[0] = 0;
	for (int i = 1; i < n; i++) {
		p[i] = p[i - 1];
		while (p[i] != 0 && pt[i] != pt[p[i]])
			p[i] = p[p[i] - 1];
		if (pt[p[i]] == pt[i])
			p[i]++;
	}
	int mn = 0;
	int j = n - 1;
	cout << ((n % (n - p[j]) == 0) ? (n - p[j]) : n);
	return 0;
}