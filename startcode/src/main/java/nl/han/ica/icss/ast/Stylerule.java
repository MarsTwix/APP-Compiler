package nl.han.ica.icss.ast;

import nl.han.ica.icss.checker.Checker;
import nl.han.ica.icss.transforms.Evaluator;

import java.util.ArrayList;
import java.util.Objects;

public class Stylerule extends ASTNode {
	
	public ArrayList<Selector> selectors = new ArrayList<>();
	public ArrayList<ASTNode> body = new ArrayList<>();

    public Stylerule() { }

    public Stylerule(Selector selector, ArrayList<ASTNode> body) {

    	this.selectors = new ArrayList<>();
    	this.selectors.add(selector);
    	this.body = body;
    }

    @Override
	public String getNodeLabel() {
		return "Stylerule";
	}
	@Override
	public ArrayList<ASTNode> getChildren() {
		ArrayList<ASTNode> children = new ArrayList<>();
		children.addAll(selectors);
		children.addAll(body);

		return children;
	}

    @Override
    public ASTNode addChild(ASTNode child) {
		if(child instanceof Selector)
			selectors.add((Selector) child);
		else
        	body.add(child);

		return this;
    }
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Stylerule stylerule = (Stylerule) o;
		return Objects.equals(selectors, stylerule.selectors) &&
				Objects.equals(body, stylerule.body);
	}

	@Override
	public int hashCode() {
		return Objects.hash(selectors, body);
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
		Evaluator.transformAndReplace(body);
		Evaluator.removeScope();
		return body;
	}

	@Override
	public String generate() {
		StringBuilder sb = new StringBuilder();
		sb.append(selectors.get(0).generate());
		sb.append(" {\n");
		body.forEach(node -> sb.append(node.generate()));
		sb.append("}\n\n");
		return sb.toString();
	}
}
