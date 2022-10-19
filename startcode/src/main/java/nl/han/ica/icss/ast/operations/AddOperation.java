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
    public void check() {
        operationType = OperationType.ADD;
        super.check();
    }

    @Override
    public Literal getLiteral() {
        switch (expresionType) {
            case SCALAR:
                return new ScalarLiteral(lhs.getLiteral().getValue() + rhs.getLiteral().getValue());
            case PIXEL:
                return new PixelLiteral(lhs.getLiteral().getValue() + rhs.getLiteral().getValue());
            case PERCENTAGE:
                return new PercentageLiteral(lhs.getLiteral().getValue() + rhs.getLiteral().getValue());
            default:
                return null;
        }
    }
}
