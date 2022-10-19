package nl.han.ica.icss.ast;

import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.checker.Checker;

import java.util.Objects;

public class VariableReference extends Expression {

	public String name;
	
	public VariableReference(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getNodeLabel() {
		return "VariableReference (" + name + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		VariableReference that = (VariableReference) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name);
	}

	@Override
	public void check() {
			ExpressionType type = Checker.getVariableType(name);
			if (type == null) {
				setError("Variable " + name + " is not defined");
			} else {
				expressionType = type;
			}
	}

	@Override
	public ExpressionType getExpressionType() {
		check();
		return expressionType;
	}
}
