#include<vector>
#include<iostream>
#include<set>
#include<queue>
#include<cmath>
#include<string>

using namespace std;

struct suf_arr {
public:
	const short lsize = 256;
		//'z' - 'a' + 1;
	vector<int>  p;
	vector<vector<int>> c;
	int len;
	string str;
	suf_arr(string str) : str(str), len(str.size()) {

		c.push_back(vector<int>(len));
		p.resize(len);
		vector<int> count(lsize);

		for (int i = 0; i < len; i++)
			count[str[i]]++;
		for (int i = 1; i < lsize; i++)
			count[i] += count[i - 1];
		for (int i = 0; i < len; i++)
			p[--count[str[i]]] = i;

		c[0][p[0]] = 0;
		int label = 0;
		for (int i = 1; i < len; i++) {
			if (str[p[i]] != str[p[i - 1]]) 
				label++;
			c[0][p[i]] = label;
		}

		vector<int> cn(len), pn(len);
		for (int k = 1, j = 1; k < len; k <<= 1, j++) {
			c.push_back(vector<int>(len));

			for (int i = 0; i < len; i++) {
				pn[i] = p[i] - k;
				if (pn[i] < 0) pn[i] += len;
			}

			//cnt.assign(classes + 1, 0);
			count.clear();
			count.resize(label + 1, 0);

			for (int i = 0; i < len; i++)
				count[c[j-1][pn[i]]]++;
			for (int i = 1; i <= label; i++)
				count[i] += count[i - 1];
			for (int i = len - 1; i >= 0; i--)
				p[--count[c[j-1][pn[i]]]] = pn[i];

			cn[p[0]] = 0;
			label = 0;
			for (int i = 1; i < len; i++) {
				if (c[j-1][p[i]] != c[j-1][p[i - 1]]
					|| c[j-1][(p[i] + k) % len] != c[j-1][(p[i - 1] + k) % len])
					label++;
				cn[p[i]] = label;
			}

			for (int i = 0; i < len; i++)
				c[j][i] = cn[i];
		}
	}
	int lcp(int i, int j) {
		int ans = 0;
		for (int k = log2(len); k >= 0; k--)
			if (i < c[k].size() && k < c[k].size() && c[k][i] == c[k][j]) {
				ans += (1 << k);
				i += (1 << k);
				j += (1 << k);
			}
		return ans;
	}
};

int main() {
	freopen("count.in", "r", stdin);
	freopen("count.out", "w", stdout);
	string st;
	cin >> st;
	st += '$';
	suf_arr t(st);
	long long sum = 0;
	for (int i = 2; i < t.p.size(); i++) {
		sum -= t.lcp(t.p[i - 1], t.p[i]);
	}
	for (int i = 1; i < t.p.size(); i++) {
		sum += (st.size() - 1 - t.p[i]);
	}
	cout << sum;
}