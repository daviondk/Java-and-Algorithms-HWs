#include<vector>
#include<iostream>
#include<string>
#include<algorithm>
using namespace std;

int main() {
	//freopen("*.in", "r", stdin);
	//freopen("*.out", "w", stdout);
	string s;
	cin >> s;
	int n = s.length();
	vector<int> z(n);
	z[0] = 0;
	int l = 0, r = 0;

	for (int i = 1; i < n; i++) {
		int j = 0;
		int k = i;
		if (i <= r) {
			j = min(z[i - l], r - i + 1);
		}
		for (; k + j < n && s[k + j] == s[j]; j++);
		z[i] = j;
		if (j + i - 1 > r) {
			l = i;
			r = i + j - 1;
		}
	}

	for (int i = 1; i < n; i++) {
		cout << " " << z[i];
	}
	return 0;
}