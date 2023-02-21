#include <bits/stdc++.h>

using namespace std;

template<class T>
T read(){
    T t;
    cin >> t;
    return t;
}

#define ll long long
#define ull unsigned ll

#define T int
#define ZERO 1'000'000'001
#define FUNC min

vector<T> tree_data;

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

void tree_set(int n, int l, int r, int qn, T value) {
    if (r - l == 1) {
        tree_data[n] = value;
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

void tree_build(int n, int l, int r, const vector<T>& data) {
    if (r - l == 1) {
        tree_data[n] = l < data.size() ? data[l] : ZERO;
        return;
    }
    int m = (l + r) / 2;
    tree_build(2 * n + 1, l, m, data);
    tree_build(2 * n + 2, m, r, data);
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

    int n = read<int>();
    vector<int> values;
    values.reserve(n);
    for (int i = 0; i < n; i++) {
        values.push_back(read<int>());
    }
    n = up_to_pow2(n);
    tree_data.resize(4 * n);
    tree_build(0, 0, n, values);
    while (!cin.eof()) {
        auto req = read<string>();
        if (req == "set") {
            int i = read<int>() - 1;
            int x = read<int>();
            tree_set(0, 0, n, i, x);
        } else if (req == "min") {
            int i = read<int>() - 1, j = read<int>();
            cout << tree_query(0, 0, n, i, j) << "\n";
        }
    }
    cout.flush();
    return 0;
}
