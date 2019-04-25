#include<iostream>
#include<vector>
#include<algorithm>
#include<set>  

using namespace std;
int inf = 100000;

struct side {
    int start, end;
    int w;
};

vector<int> vertexes;
vector<int> own;
set<side*> sides;

int main() {
    int count;
    cin >> count;
    vertexes.resize(count, 0);
    own.resize(count, count + 1);
    
    for (int i = 0; i < count; i++) {
        for (int j = 0; j < count; j++) {
            int w;
            cin >> w;
            if (w != 100000) {
                side *new_side = new side;
                new_side->end = j;
                new_side->start = i;
                new_side->w = w;
                sides.insert(new_side);
            }
        }
    }
    int lastModify;
    bool modify = false;

    for (int i = 0; i < vertexes.size(); i++) {
        modify = false;
        for (auto side : sides) {
            if (vertexes[side->start] + side->w < vertexes[side->end]) {
                vertexes[side->end] = vertexes[side->start] + side->w;
                own[side->end] = side->start;
                lastModify = side->end;
                modify = true;
            }
        }
        if (!modify) break;
    }

    if (modify) {
        vector<int> color(vertexes.size(), 0);
        vector<int> buf;
        int cur = lastModify;
        while (color[cur] == 0) {
            color[cur] = 1;
            cur = own[cur];
        }
        while (color[cur] == 1) {
            color[cur] = 2;
            buf.push_back(cur + 1);
            cur = own[cur];
        }
        buf.push_back(cur + 1);

        cout << "YES" << endl;
        cout << buf.size()-1 << endl;
        for (int i = buf.size()-1; i > 0; i--) {
            cout << buf[i] << ' ';
        }
    }
    else {
        cout << "NO";
    }
    
    return 0;
}