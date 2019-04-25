#include<vector>
#include<iostream>
#include<set>

using namespace std;

struct hip {
	int size = 1;
	int id;
	hip* own = NULL;

	hip* getOwn() {
		if (own) return own->getOwn();
		return this;
	}

	bool operator == (hip & other) {
		return getOwn()->id == other.getOwn()->id;
	}

	bool add(hip * other) {
		other = other->getOwn();
		hip* cur = getOwn();
		if (cur->id == other->id) return false;
		if (cur->size > other->size) {
			other->add(this); return true;
		}
		other->size += cur->size;
		cur->own = other;
		return true;
	}
};

struct vec3 {
	int x, y;
	long long size;
	vec3(int x, int y, long long s) : x(x), y(y), size(s) {}

};
bool operator < (const vec3 &left, const vec3 &right) { return left.size < right.size; }
int main() {

	int n, m;
	cin >> n >> m;
	multiset<vec3> ways;
	vector<hip> hips(n);
	for (int i = 0; i < n; i++) {
		hips[i].id = i;
	}
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--; b--;
		ways.insert(vec3(a, b, c));
	}

	long long s = 0;
	for (auto e : ways) {
		if (hips[e.x].add(&hips[e.y])) s += e.size;
	}

	cout << s;

}