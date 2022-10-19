package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Evaluator implements Transform {

    public static IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        ast.root.transform();
    }

    public static void addScope() {
        variableValues.addFirst(new HashMap<>());
    }

    public static void removeScope() {
        variableValues.removeFirst();
    }

    public static Literal getVariableValue(String name) {
        for(int i = 0; i < variableValues.getSize(); i++) {
            if(variableValues.get(i).containsKey(name)) {
                return variableValues.get(i).get(name);
            }
        }
        return null;
    }

    public static void transformAndReplace(ArrayList<ASTNode> body){
        for (int i = 0; i < body.size(); i++) {
            ASTNode child = body.get(i);
            if (child instanceof IfClause || child instanceof VariableAssignment) {
                ArrayList<ASTNode> transformed = child.transform();
                body.remove(i);
                body.addAll(i, transformed);
            }
        }
    }
    
}
