package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.ast.types.OperationType;

public class MultiplyOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Multiply";
    }

    @Override
    public ExpressionType getExpressionType() {
        operationType = OperationType.MULTIPLY;
        super.check();
        return expresionType;
    }

    @Override
    public Literal getLiteral() {
        if (lhs.getExpressionType() == ExpressionType.SCALAR && rhs.getExpressionType() == ExpressionType.SCALAR) {
            return new ScalarLiteral(((ScalarLiteral) lhs).value * ((ScalarLiteral) rhs).value);
        } else if (lhs.getExpressionType() == ExpressionType.SCALAR) {
            return multiplyWithNonScalar((ScalarLiteral) lhs, rhs);
        } else{
            return multiplyWithNonScalar((ScalarLiteral) rhs, lhs);
        }
    }

    private Literal multiplyWithNonScalar(ScalarLiteral lhs, Expression rhs) {
        switch (lhs.getExpressionType()) {
            case PIXEL:
                return new PixelLiteral(lhs.value * ((PixelLiteral) rhs).value);
            case PERCENTAGE:
                return new PercentageLiteral(lhs.value * ((PercentageLiteral) rhs).value);
            default:
                return null;
        }
    }
}
