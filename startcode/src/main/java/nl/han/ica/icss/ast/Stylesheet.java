package nl.han.ica.icss.ast;

import nl.han.ica.icss.checker.Checker;
import nl.han.ica.icss.checker.SemanticError;
import nl.han.ica.icss.transforms.Evaluator;

import static nl.han.ica.icss.checker.Checker.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A stylesheet is the root node of the AST, it consists of one or more statements
 *
 */
public class Stylesheet extends ASTNode {


	public ArrayList<ASTNode> body;
	
	public Stylesheet() {
		this.body = new ArrayList<>();
	}
	public Stylesheet(ArrayList<ASTNode> body) {
		this.body = body;
	}
	@Override
	public String getNodeLabel() {
		return "Stylesheet";
	}
	@Override
	public ArrayList<ASTNode> getChildren() {
		return this.body;
	}
	@Override
	public ASTNode addChild(ASTNode child) {
	    	body.add(child);
	    	return this;
	}
	@Override
	public ASTNode removeChild(ASTNode child) {
		body.remove(child);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Stylesheet that = (Stylesheet) o;
		return Objects.equals(body, that.body);
	}

	@Override
	public int hashCode() {

		return Objects.hash(body);
	}

	@Override
	public void check() {
		Checker.addScope();

		getChildren().forEach(ASTNode::check);

		Checker.removeScope();
	}

	@Override
	public ArrayList<ASTNode> transform() {
		Evaluator.addScope();
		getChildren().forEach(ASTNode::transform);
		Evaluator.removeScope();
		return body;
	}

	@Override
	public String generate() {
		StringBuilder sb = new StringBuilder();
		for (ASTNode node : body) {
			sb.append(node.generate());
		}
		return sb.toString();
	}
}
