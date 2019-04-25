#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <cmath>
#include <set>
#include <algorithm>
#include <random>
#include <iomanip>

using namespace std;

const unsigned long long P = 127;
int main()
{
	string s;
	cin >> s;
	vector<unsigned long long> hash(s.size() + 1);
	vector<unsigned long long> pow(s.size() + 1);
	unsigned long long curP = P;
	hash[0] = 0;
	pow[0] = 1;
	for (int i = 1; i <= s.size(); i++) {
		hash[i] = hash[i - 1] + curP * ((unsigned long long)s[i - 1]);
		pow[i] = curP;
		curP *= P;
	}
	int m;
	cin >> m;
	for (int i = 0; i < m; i++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;
		if ((b - a + 1 == d - c + 1) && (((hash[b] - hash[a - 1]) * pow[c]) == ((hash[d] - hash[c - 1])* pow[a]))) {
			cout << "Yes" << endl;
		}
		else {
			cout << "No" << endl;
		}
	}
	int n;

	return 0;
}