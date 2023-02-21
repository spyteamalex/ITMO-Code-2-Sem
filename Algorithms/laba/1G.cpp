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

#define MOD 1'000'000'007

struct Triple {
    ull a, b, c;

    Triple() : a(1), b(1), c(1) {}

    Triple(ull a, ull b, ull c) : a(a), b(b), c(c) {}

    ull operator*(const Triple & that) const{
        return (a % MOD * that.a % MOD + b % MOD * that.b % MOD + c % MOD * that.c % MOD) % MOD;
    }
};

//зависимость зависимость последних трех элементов отрезка от последних трех элементов перед отрезком
struct Relations {
    string name;
    Triple triple1, triple2, triple3;

    Relations(Triple triple1, Triple triple2, Triple triple3, string name = "") : name(name), triple1(triple1), triple2(triple2), triple3(triple3) {}

    ull operator ()() const {
        return (triple3.a % MOD + triple3.b % MOD + 2 * triple3.c % MOD) % MOD;
    }
    Triple operator *(const Triple & that) const {
        return {
                Triple(triple1.a, triple2.a, triple3.a) * that,
                Triple(triple1.b, triple2.b, triple3.b) * that,
                Triple(triple1.c, triple2.c, triple3.c) * that
                };
    }

    Relations operator *(const Relations & that) const {
        return {
                *this * that.triple1,
                *this * that.triple2,
                *this * that.triple3
                };
    }
};
Relations BEGIN{ Triple{0, 0, 0}, Triple{0, 0, 0}, Triple{1, 0, 0}, "begin" };
Relations DIRECT{ Triple{0, 1, 0}, Triple{0, 0, 1}, Triple{1, 1, 1}, "direct" };
Relations BANNED{ Triple{0, 1, 0}, Triple{0, 0, 1}, Triple{0, 0, 0}, "banned" };
Relations ZERO{ Triple{1, 0, 0}, Triple{0, 1, 0}, Triple{0, 0, 1}, "zero" };

#define T Relations
//#define ZERO RELATIONS
#define FUNC(a,b) a * b

vector<T> tree_data;
vector<bool> banned_data;

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

void tree_set(int n, int l, int r, int qn, int size) {
    if (r - l == 1) {
        banned_data[qn] = !banned_data[qn];
        if (banned_data[qn]) {
            tree_data[n] = BANNED;
        } else {
            if (l == 0) {
                tree_data[n] = BEGIN;
            } else if (l < size) {
                tree_data[n] = DIRECT;
            } else {
                tree_data[n] = ZERO;
            }
        }
        return;
    }
    int m = (l + r) / 2;
    if (l <= qn && qn < m) {
        tree_set(2 * n + 1, l, m, qn, size);
    } else {
        tree_set(2 * n + 2, m, r, qn, size);
    }
    tree_data[n] = FUNC(tree_data[2 * n + 1], tree_data[2 * n + 2]);
}

void tree_build(int n, int l, int r, int size) {
    if (r - l == 1) {
        if (l == 0) {
            tree_data[n] = BEGIN;
        } else if (l < size) {
            tree_data[n] = DIRECT;
        } else {
            tree_data[n] = ZERO;
        }
        return;
    }
    int m = (l + r) / 2;
    tree_build(2 * n + 1, l, m, size);
    tree_build(2 * n + 2, m, r, size);
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

    int n = read<int>(), m = read<int>();
    n++;
    int n_ext = up_to_pow2(n);
    tree_data.resize(4 * n_ext, ZERO);
    banned_data.resize(n_ext, false);
    tree_build(0, 0, n_ext, n);
    cout << tree_query(0, 0, n_ext, 0, n)() << "\n";
    for (int i = 0; i < m; i++) {
        tree_set(0, 0, n_ext, read<int>(), n);
        cout << tree_query(0, 0, n_ext, 0, n)() << "\n";
    }
    cout.flush();
    return 0;
}
