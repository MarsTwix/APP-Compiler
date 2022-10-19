package nl.han.ica.icss.ast.literals;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.Objects;

public class ColorLiteral extends Literal {
    public String value;

    public ColorLiteral(String value) {
        this.value = value;
    }
    @Override
    public String getNodeLabel() {
        return "Color literal (" + value + ")";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorLiteral that = (ColorLiteral) o;
        return Objects.equals(value, that.value);
    }
    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.COLOR;
    }

    @Override
    public Literal getLiteral() {
        return this;
    }

    @Override
    public String getValue() {
        return value;
    }
}
