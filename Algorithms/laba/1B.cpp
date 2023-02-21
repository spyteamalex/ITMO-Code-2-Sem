#include <bits/stdc++.h>

using namespace std;

#define ll long long
#define ull unsigned ll

#define T vector<int>
#define ZERO T()
#define FUNC(a,b) merge(a,b)

vector<int> merge(const vector<int>& a, const vector<int>& b) {
    vector<int> res;
    int i = 0;
    int j = 0;
    while (i < a.size() && j < b.size()) {
        int candidat = a[i] < b[j] ? a[i++] : b[j++];
        if (res.empty() || res.back() != candidat) {
            res.push_back(candidat);
        }
    }
    while (i < a.size()) {
        int candidat = a[i++];
        if (res.empty() || res.back() != candidat) {
            res.push_back(candidat);
        }
    }
    while (j < b.size()) {
        int candidat = b[j++];
        if (res.empty() || res.back() != candidat) {
            res.push_back(candidat);
        }
    }
    return std::move(res);
}

vector<T> tree_data;
vector<int> values;

//[ql, qr)
T tree_query(int n, int l, int r, int ql, int qr) {
    if (ql <= l && r <= qr) {
        return tree_data[n];
    }
    if (qr <= l || r <= ql) {
        return ZERO;
    }
    int m = (l + r) / 2;
    return FUNC(tree_query(2 * n + 1, l, m, ql, qr), tree_query(2 * n + 2, m, r, ql, qr));
}

void tree_set(int n, int l, int r, int qn, int value) {
    if (r - l == 1) {
        tree_data[n] = { value };
        values[qn] = value;
        return;
    }
    int m = (l + r) / 2;
    if (l <= qn && qn < m) {
        tree_set(2 * n + 1, l, m, qn, value);
    } else {
        tree_set(2 * n + 2, m, r, qn, value);
    }
    tree_data[n] = FUNC(tree_data[2 * n + 1], tree_data[2 * n + 2]);
}

void tree_build(int n, int l, int r) {
    if (r - l == 1) {
        if (l < values.size()) {
            tree_data[n] = { values[l] };
        }
        return;
    }
    int m = (l + r) / 2;
    tree_build(2 * n + 1, l, m);
    tree_build(2 * n + 2, m, r);
    tree_data[n] = FUNC(tree_data[2 * n + 1], tree_data[2 * n + 2]);
}

int up_to_pow2(int v) {
    if (!(v & (v - 1))) {
        return v;
    }
    int pow = 0;
    while (v) {
        pow++;
        v >>= 1;
    }
    return 1 << pow;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie();
    ifstream in("permutation.in");
    ofstream out("permutation.out");
    int n;
    in >> n;
    values.reserve(n);
    for (int i = 0; i < n; i++) {
        int v;
        in >> v;
        values.push_back(v);
    }
    n = up_to_pow2(n);
    tree_data.resize(4 * n);
    tree_build(0, 0, n);
    int m;
    in >> m;
    for (int i = 0; i < m; i++) {
        int cmd;
        in >> cmd;
        int x;
        in >> x;
        x--;
        int y;
        in >> y;
        if (cmd == 1) {
            tree_set(0, 0, n, x, y);
        } else if (cmd == 2) {
            auto req = tree_query(0, 0, n, x, y);
            out << (req.back() == req.size() ? "YES" : "NO") << "\n";
        }
    }
    in.close();
    out.close();
    return 0;
}
