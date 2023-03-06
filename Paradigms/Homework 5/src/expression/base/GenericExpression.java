package expression.base;

import expression.ToMiniString;

public interface GenericExpression<Res> extends ToMiniString {
    Priority getPriority();
    Res eval(int x, int y, int z);
}
