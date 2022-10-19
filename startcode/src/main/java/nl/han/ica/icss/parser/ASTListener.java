package nl.han.ica.icss.parser;


import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {


	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }


	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		currentContainer.push(ast.root);
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule styleRule = new Stylerule();
		currentContainer.push(styleRule);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		ASTNode stylerule = currentContainer.pop();
		currentContainer.peek().addChild(stylerule);
	}

	@Override
	public void enterSelector(ICSSParser.SelectorContext ctx) {
		ASTNode stylerule = currentContainer.peek();
		switch (ctx.getText().charAt(0)) {
			case '.':
				stylerule.addChild(new ClassSelector(ctx.getText()));
				break;
			case '#':
				stylerule.addChild(new IdSelector(ctx.getText()));
				break;
			default:
				stylerule.addChild(new TagSelector(ctx.getText()));
				break;
		}
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration declaration = new Declaration();
		currentContainer.peek().addChild(declaration);
		currentContainer.push(declaration);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterProperty(ICSSParser.PropertyContext ctx) {
		currentContainer.peek().addChild(new PropertyName(ctx.getText()));
	}

	@Override
	public void enterPixelValue(ICSSParser.PixelValueContext ctx) {
		currentContainer.peek().addChild(new PixelLiteral(ctx.getText()));
	}

	@Override
	public void enterPercentageValue(ICSSParser.PercentageValueContext ctx) {
		currentContainer.peek().addChild(new PercentageLiteral(ctx.getText()));
	}

	@Override
	public void enterBooleanValue(ICSSParser.BooleanValueContext ctx) {
		currentContainer.peek().addChild(new BoolLiteral(ctx.getText()));
	}

	@Override
	public void enterColorValue(ICSSParser.ColorValueContext ctx) {
		currentContainer.peek().addChild(new ColorLiteral(ctx.getText()));
	}

	@Override
	public void enterScalarValue(ICSSParser.ScalarValueContext ctx) {
		currentContainer.peek().addChild(new ScalarLiteral(ctx.getText()));
	}

	@Override
	public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		ASTNode assignmentNode = new VariableAssignment();
		currentContainer.peek().addChild(assignmentNode);
		currentContainer.push(assignmentNode);
	}

	@Override
	public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
		currentContainer.peek().addChild(new VariableReference(ctx.getText()));
	}

	@Override
	public void enterAddOperation(ICSSParser.AddOperationContext ctx) {
		currentContainer.push(new AddOperation());
	}

	@Override
	public void enterSubtractOperation(ICSSParser.SubtractOperationContext ctx) {
		currentContainer.push(new SubtractOperation());
	}

	@Override
	public void enterMultiplyOperation(ICSSParser.MultiplyOperationContext ctx) {
		currentContainer.push(new MultiplyOperation());
	}

	@Override
	public void exitOperation(ICSSParser.OperationContext ctx) {
		ASTNode operation = currentContainer.pop();
		currentContainer.peek().addChild(operation);
	}

	@Override
	public void enterIfClause(ICSSParser.IfClauseContext ctx) {
		ASTNode ifClause = new IfClause();
		currentContainer.peek().addChild(ifClause);
		currentContainer.push(ifClause);
	}

	@Override
	public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
		ASTNode elseClause = new ElseClause();
		currentContainer.peek().addChild(elseClause);
		currentContainer.push(elseClause);
	}

	@Override
	public void exitIfClause(ICSSParser.IfClauseContext ctx) {
		currentContainer.pop();
	}

	@Override
	public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
		currentContainer.pop();
	}
}