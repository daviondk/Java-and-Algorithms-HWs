#include<vector>
#include<iostream>
#include<iomanip>
#include<set>
#include<stack>
#include<algorithm>
#include<cmath>
#include<string>
#include<map>

using namespace std;
vector<multiset<int>> w, rw;
vector<int> component;
vector<int> visted;


stack<int> ord;
void dfs1(int v) {
	visted[v] = true;
	for (int next : w[v])
		if (!visted[next])
			dfs1(next);
	ord.push(v);
}

int compc = 1;
void dfs2(int v) {
	visted[v] = true;
	component[v] = compc;
	for (int next : rw[v])
		if (!visted[next])
			dfs2(next);
}
int n, m;
int inv(int i) {
	if (i >= n) {
		return i - n;
	}
	return i + n;
}
int main() {
	cin >> n >> m;
	w.resize(2 * n);
	rw.resize(2 * n);
	visted.resize(2 * n, false);
	component.resize(2 * n, 0);
	map<string, int> names;
	vector<string> names_inv(2 * n);
	for (int i = 0; i < n; i++) {
		string s;
		cin >> s;
		names["+" + s] = i;
		names["-" + s] = i + n;
		names_inv[i] = s;
		names_inv[i + n] = s;
	}
	for (int i = 0; i < m; i++) {
		string u, mid, v;
		cin >> u >> mid >> v;

		//w[inv(names[u])].insert(names[v]);
		//w[inv(names[v])].insert(names[u]);
		w[names[u]].insert(names[v]);
		rw[names[v]].insert(names[u]);


		w[inv(names[v])].insert(inv(names[u]));
		rw[inv(names[u])].insert(inv(names[v]));

		//rw[names[v]].insert(inv(names[u]));
		//rw[names[u]].insert(inv(names[v]));
	}

	for (int i = 0; i < 2 * n; i++) {
		if (!visted[i]) {
			dfs1(i);
		}
	}

	for (int i = 0; i < 2 * n; i++) {
		visted[i] = false;
	}
	while (!ord.empty()) {
		if (!visted[ord.top()]) {
			dfs2(ord.top());
			compc++;
		}
		ord.pop();
	}
	vector<int> choice;
	for (int i = 0; i < n; i++) {
		if (component[i] > component[i + n]) {
			choice.push_back(i);
		}
		else if (component[i] == component[i + n]) {
			cout << -1;
			return 0;
		}
	}
	cout << choice.size() << endl;
	for (auto &i : choice) {
		cout << names_inv[i] << endl;
	}
}