#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;
struct vrtx {
    int d;
    int no;
    int id;
    vrtx(int d){
        this->d = d;
    }
    vrtx(int d, int no){
        this->d = d;
        this->no = no;
    }
    const bool operator<(const vrtx& other) {
        return d < other.d;
    }
    const bool operator>(const vrtx& other) {
        return d > other.d;
    }
    const bool operator<=(const vrtx& other) {
        return d <= other.d;
    }
    const bool operator>=(const vrtx& other) {
        return d >= other.d;
    }
};

struct heap {
    vector<vrtx*> a;
    int size = 0;
    heap(){
        //a.push_back(vrtx(-1));
        vector<vrtx*> b(1, new vrtx(-1, -1));
        a = b;
        size = 0;
    }
    void push(int d, int no) {
        a.push_back(new vrtx(d, no));
        size++;
        siftup(size);
    }
 
    void siftup(int i) {
        while (i > 1 && *a[i / 2] > *a[i]) {
            swap(*a[i / 2], *a[i]);
            i = i / 2;
        }
        return;
    }
 
    void siftdown(int i) {
        int j;
        while (2 * i <= size) {
 
            j = i;
            if (*a[2 * i] < *a[j])
                j = 2 * i;
            if (2 * i + 1 <= size && *a[2 * i + 1] < *a[j])
                j = 2 * i + 1;
            if (j == i)
                break;
            swap(*a[j], *a[i]);
            i = j;
        }
    }
    vrtx* ex_min() {
        vrtx* ans = a[1];
        a[1] = a[size];
        size--;
        a.pop_back();
        if(size > 0){
            siftdown(1);
        }
        return ans;
        //return ans;
    }
    vrtx* del_el  (int i) {
        vrtx* ans = a[i];
        a[i] = a[size];
        size--;
        a.pop_back();
        vrtx* max_son(new vrtx(-2000000000));
        if(i*2 <= size)
            max_son = a[i*2];
        if(i*2+1 <= size && *a[i*2+1] < *max_son)
            max_son = a[i*2+1];
           
        if(*a[i] > *max_son)
            siftdown(i);
        else
            siftup(i);
        return ans;
    }
    int get_size() {
        return size;
    }
};

int main()
{
    int n,m;
    cin >> n >> m;
    //heap h(vector<int>(n, 2*1000000000), f);
    heap h;
    vector<vector<pair<int, int>>> vrtxs(n);
    for(int i = 0; i < m; i++) {
        int x, y, w;
        cin >> x >> y >> w;
        x--;
        y--;
        vrtxs[x].push_back(make_pair(y, w));
        vrtxs[y].push_back(make_pair(x, w));
    }
    vector<int> dist(n, 2*1000000000);
    dist[0] = 0;
    h.push(0, 0);
    while(h.get_size()) {
        vrtx* cur = h.ex_min();
        if(cur->d <= dist[cur->no]) {
            for(auto& neig : vrtxs[cur->no]) {
                if(dist[cur->no] + neig.second < dist[neig.first]) {
                    dist[neig.first] = dist[cur->no] + neig.second;
                    h.push(dist[neig.first], neig.first);
                }
            }
        }
    }
    for(int i = 0; i < n; i++) {
        cout << dist[i] << ' ';
    }
}