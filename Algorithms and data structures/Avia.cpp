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
int n;
vector<vector<int>> matrix;
vector<int> component;
vector<int> visted;
int cursz;

stack<int> ord;
void dfs1(int v) {
	visted[v] = true;
	//for (int next : w[v])
	for(int next = 0; next < n; next++)
		if (!visted[next] && matrix[v][next] <= cursz)
			dfs1(next);
	ord.push(v);
}

int compc = 1;
void dfs2(int v) {
	visted[v] = true;
	component[v] = compc;
	//for (int next : rw[v])
	for (int next = 0; next < n; next++)
		if (!visted[next] && matrix[next][v] <= cursz)
			dfs2(next);
}


bool check(int sz) {
	compc = 1;
	for (int i = 0; i < n; i++) {
		visted[i] = false;
		component[i] = 0;
	}
	cursz = sz;

	for (int i = 0; i < n; i++) {
		if (!visted[i]) {
			dfs1(i);
		}
	}

	for (int i = 0; i < n; i++) {
		visted[i] = false;
	}
	while (!ord.empty()) {
		if (!visted[ord.top()]) {
			dfs2(ord.top());
			compc++;
		}
		ord.pop();
	}
	if (compc == 2) {
		return true;
	}
	return false;
}

int main() {
	freopen("avia.in", "r", stdin);
	freopen("avia.out", "w", stdout);
	cin >> n;
	visted.resize(n);
	component.resize(n);
	matrix.resize(n, vector<int>(n));
	int left = 0, right = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> matrix[i][j];
			if (matrix[i][j] > right) {
				right = matrix[i][j];
			}
		}
	}
	while (right - left > 1) {
		int mid = (right + left) / 2;
		if (check(mid)) {
			right = mid;
		}
		else {
			left = mid;
		}
	}
	cout << right;
}