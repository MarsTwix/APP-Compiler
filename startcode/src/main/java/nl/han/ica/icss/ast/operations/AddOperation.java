package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.ast.types.OperationType;

public class AddOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Add";
    }

    @Override
    public ExpressionType getExpressionType() {
        operationType = OperationType.ADD;
        super.check();
        return expresionType;
    }

    @Override
    public Literal getLiteral() {
        switch (expresionType) {
            case PIXEL:
                return new PixelLiteral(((PixelLiteral) lhs).value + ((PixelLiteral) rhs).value);
            case PERCENTAGE:
                return new PercentageLiteral(((PercentageLiteral) lhs).value + ((PercentageLiteral) rhs).value);
            case SCALAR:
                return new ScalarLiteral(((ScalarLiteral) lhs).value + ((ScalarLiteral) rhs).value);
            default:
                return null;
        }
    }
}
