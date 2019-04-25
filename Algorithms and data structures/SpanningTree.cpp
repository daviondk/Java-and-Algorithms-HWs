#include<vector>
#include<iostream>
#include<iomanip>
#include<set>
#include<algorithm>
#include<cmath>

using namespace std;

struct vec2 {
	int x, y;
	bool used;
	double min_edge;
	vec2() {
		used = false;
		min_edge = 1e9;
		cin >> x >> y;
	}
};

double getDistance(const vec2& f, const vec2& s) {
	return sqrt((f.x - s.x) * (f.x - s.x) + (f.y - s.y) * (f.y - s.y));
}

int main() {
	int n;
	cin >> n;
	vector<vec2> points(n);
	double length = 0;
	points[0].min_edge = 0;

	for (int i = 0; i < n; i++) {
		vec2* min = nullptr;
		for (auto &cur : points) {
			if (!cur.used && (!min || cur.min_edge < min->min_edge)) {
				min = &cur;
			}
		}
		min->used = true;
		length += min->min_edge;
		for (auto& v : points) {
			double curDistance = getDistance(*min, v);
			if (curDistance < v.min_edge) {
				v.min_edge = curDistance;
			}
		}
	}
	cout << fixed;
	cout << setprecision(10) << length;
}