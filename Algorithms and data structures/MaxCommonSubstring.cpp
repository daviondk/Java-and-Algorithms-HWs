#include<vector>
#include<iostream>
#include<algorithm>
#include<string>
#include<queue>
#include<set>
#include<map>
#include<stack>
using namespace std;

set<int> check(string &a, string &b, int i) {
	set<unsigned long long> hashes;
	map<unsigned long long, int> m;
	vector<unsigned long long> ps(i + 1);
	unsigned long long cur = 0;
	unsigned long long p = 31, curp = 1;
	set<int> ans;

	unsigned long long bbb = 515546546465;
	if (i == 0) {
		return ans;
	}
	ps[i - 1] = 1;
	for (int j = i - 2; j >= 0; j--) {
		//for (int j = 0; j < i; j++) {
		curp *= p;
		ps[j] = curp;
	}
	for (int j = 0; j < i; j++) {
		//ps[j] = curp;
		cur += a[j] * ps[j];
		//curp *= p;
	}
	hashes.insert(cur);
	m[cur] = 0;
	for (int j = 0; j < a.size() - i; j++) {
		cur = (p * (cur - curp * a[j]) + a[j + i]);
		hashes.insert(cur);
		m[cur] = j + 1;
	}
	cur = 0;
	for (int j = 0; j < i; j++) {
		cur += b[j] * ps[j];
	}
	if (hashes.find(cur) != hashes.end()) {
		bool val = true;
		for (int j = 0; j < (min(i, 5)); j++) {
			if (a[m[cur] + j] != b[j]) {
				val = false;
				break;
			}
		}
		if (val) {
			ans.insert(0);
		}
	}
	for (int j = 0; j < b.size() - i; j++) {
		cur = (p * (cur - curp * b[j]) + b[j + i]);
		if (hashes.find(cur) != hashes.end()) {
			bool val = true;
			for (int x = 0; x < (min(i, 5)); x++) {
				if (a[m[cur] + x] != b[j + x + 1]) {
					val = false;
					break;
				}
			}
			if (val) {
				ans.insert(j + 1);
			}
		}
	}
	return ans;
}

pair<set<int>, int> getLCS(string& a, string& b) {
	int len = min(a.size(), b.size());
	if (a.size() > b.size()) {
		swap(a, b);
	}
	int l = 0;
	int r = len + 1;
	while (l < r - 1) {
		int m = (l + r) / 2;
		if (m == 0 || check(a, b, m).size() > 0)
			l = m;
		else
			r = m;
	}
	return make_pair(check(a, b, l), l);
}
int main() {
	freopen("common.in", "r", stdin);
	freopen("common.out", "w", stdout);
	/*int n;
	vector<string> s(n);
	for (int i = 0; i < n; i++) {
		cin >> s[i];
	}*/
	int k;
	//cin >> k;
	k = 2;
	string s1;
	cin >> s1;
	if (k == 0) {
		cout << s1;
		return 0;
	}
	for (int i = 1; i < k; i++) {
		string s2;
		cin >> s2;
		if (s1.size() > s2.size()) {
			swap(s1, s2);
		}
		pair<set<int>, int> curr = getLCS(s1, s2);
		set<string> se;
		for (auto &x : curr.first) {
			se.insert(s2.substr(x, curr.second));
		}
		if (curr.first.size() > 0) {
			s1 = *se.begin();
		}
		else {
			s1 = "";
		}
	}
	cout << s1;
	return 0;
}