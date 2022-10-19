grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---
stylesheet: (variableAssignment | stylerule)+ EOF;

stylerule: selector OPEN_BRACE body CLOSE_BRACE;
selector: LOWER_IDENT | ID_IDENT | CLASS_IDENT;

declaration: property COLON (propertyValue | operation) SEMICOLON;
property: 'color' | 'background-color' | 'width' | 'height';


variableAssignment: variableReference ASSIGNMENT_OPERATOR variableValue SEMICOLON;
variableReference: CAPITAL_IDENT;
variableValue: value | operation;

operation: addOperation | subtractOperation | multiplyOperation;
addOperation: operationValue PLUS loopOperation;
subtractOperation: operationValue MIN loopOperation;
multiplyOperation: operationValue MUL loopOperation;
loopOperation: operation | operationValue ;

value : operationValue | booleanValue;
operationValue: propertyValue | scalarValue;
propertyValue: (pixelValue | percentageValue | colorValue | variableReference);
pixelValue: PIXELSIZE;
percentageValue: PERCENTAGE;
colorValue: COLOR;
booleanValue: TRUE | FALSE;
scalarValue: SCALAR;

ifClause: IF BOX_BRACKET_OPEN (variableReference | booleanValue) BOX_BRACKET_CLOSE OPEN_BRACE body CLOSE_BRACE elseClause?;
elseClause: ELSE OPEN_BRACE body CLOSE_BRACE;

body: (ifClause | declaration | variableAssignment)*;