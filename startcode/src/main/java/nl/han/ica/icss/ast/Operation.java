package nl.han.ica.icss.ast;

import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.ast.types.OperationType;

import java.util.ArrayList;

public abstract class Operation extends Expression {

    public ExpressionType expresionType;
    public OperationType operationType;

    public Expression lhs;
    public Expression rhs;

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if(lhs != null)
            children.add(lhs);
        if(rhs != null)
            children.add(rhs);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if(lhs == null) {
            lhs = (Expression) child;
        } else if(rhs == null) {
            rhs = (Expression) child;
        }
        return this;
    }

    @Override
    public void check() {
        if(lhs instanceof Operation) {
            lhs.check();
        }else if(rhs instanceof Operation) {
            rhs.check();
        }
        String operation = operationType.toString().toLowerCase();
        if (lhs.getExpressionType() == ExpressionType.COLOR || rhs.getExpressionType() == ExpressionType.COLOR) {
            setError("Cannot " + operation + " color");
        }

        if(operationType == OperationType.ADD || operationType == OperationType.SUBTRACT){
            if (lhs.getExpressionType() == rhs.getExpressionType()) {
                expresionType = lhs.getExpressionType();
            }
            else {
                setError("Cannot " + operation + " " + lhs.getExpressionType() + " with " + rhs.getExpressionType());
            }
        }else{
            //multiply with numbers
            if (lhs.getExpressionType() == ExpressionType.SCALAR && rhs.getExpressionType() == ExpressionType.SCALAR) {
                expresionType = ExpressionType.SCALAR;
            }
            //multiply with a number
            else if (lhs.getExpressionType() == ExpressionType.SCALAR || rhs.getExpressionType() == ExpressionType.SCALAR) {
                if (lhs.getExpressionType() == ExpressionType.SCALAR) {
                    expresionType = rhs.getExpressionType();
                } else {
                    expresionType = lhs.getExpressionType();
                }
            } else {
                setError("Cannot " + operation + " " + lhs.getExpressionType() + " with " + rhs.getExpressionType());
            }
        }
    }

    @Override
    public ExpressionType getExpressionType() {
        return expresionType;
    }
}
