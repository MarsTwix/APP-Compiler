package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;



public class Checker {

    public static IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
         variableTypes = new HANLinkedList<>();
        ast.root.check();
    }

    public static void addScope() {
        variableTypes.addFirst(new HashMap<>());
    }

    public static void removeScope() {
        variableTypes.removeFirst();
    }

    public static void addVariable(String name, ExpressionType type) {
        variableTypes.getFirst().put(name, type);
    }

    public static ExpressionType getVariableType(String name) {
        for(int i = 0; i < variableTypes.getSize(); i++) {
            if(variableTypes.get(i).containsKey(name)) {
                return variableTypes.get(i).get(name);
            }
        }
        return null;
    }

}
