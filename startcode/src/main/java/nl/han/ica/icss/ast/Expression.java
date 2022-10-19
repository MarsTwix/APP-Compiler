package nl.han.ica.icss.ast;

import nl.han.ica.icss.ast.types.ExpressionType;

public abstract class Expression extends ASTNode {
    ExpressionType expressionType;
    public abstract ExpressionType getExpressionType();

    public abstract Literal getLiteral();
}
