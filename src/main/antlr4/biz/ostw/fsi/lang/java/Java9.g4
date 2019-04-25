grammar Java9;

/*
 * Productions from §3 (Lexical Structure)
 */

literal
	:	IntegerLiteral
	|	FloatingPointLiteral
	|	BooleanLiteral
	|	CharacterLiteral
	|	StringLiteral
	|	NullLiteral
	;

/*
 * Productions from §4 (Types, Values, and Variables)
 */

primitiveType
	:	annotation* numericType
	|	annotation* 'boolean'
	;

numericType
	:	integralType
	|	floatingPointType
	;

integralType
	:	'byte'
	|	'short'
	|	'int'
	|	'long'
	|	'char'
	;

floatingPointType
	:	'float'
	|	'double'
	;

referenceType
	:	classOrInterfaceType
	|	typeVariable
	|	arrayType
	;

/*classOrInterfaceType
	:	classType
	|	interfaceType
	;
*/
classOrInterfaceType
	:	(	classType_lfno_classOrInterfaceType
		|	interfaceType_lfno_classOrInterfaceType
		)
		(	classType_lf_classOrInterfaceType
		|	interfaceType_lf_classOrInterfaceType
		)*
	;

classType
	:	(annotations ws+)? identifier (ws* typeArguments)?
	|	classOrInterfaceType ws* '.' ws* (annotations ws+)? identifier (ws* typeArguments)?
	;

classType_lf_classOrInterfaceType
	:	'.' ws* (annotations ws+)? identifier (ws* typeArguments)?
	;

classType_lfno_classOrInterfaceType
	:	(annotations ws+)? identifier (ws* typeArguments)?
	;

interfaceType
	:	classType
	;

interfaceType_lf_classOrInterfaceType
	:	classType_lf_classOrInterfaceType
	;

interfaceType_lfno_classOrInterfaceType
	:	classType_lfno_classOrInterfaceType
	;

typeVariable
	:	(annotations ws+)? identifier
	;

arrayType
	:	primitiveType dims
	|	classOrInterfaceType dims
	|	typeVariable dims
	;

dims
	:	(annotations ws*)? '[' ws* ']' (ws* (annotations ws*)? '[' ws* ']')*
	;

typeParameter
	:	(typeParameterModifiers ws+)? identifier (ws+ typeBound)?
	;

typeParameterModifiers
    :   typeParameterModifier (ws* typeParameterModifier)*
    ;

typeParameterModifier
	:	annotation
	;

typeBound
	:	'extends' ws+ typeVariable
	|	'extends' ws+ classOrInterfaceType (ws* additionalBound)*
	;

additionalBound
	:	'&' ws* interfaceType
	;

typeArguments
	:	'<' ws* typeArgumentList ws* '>'
	;

typeArgumentList
	:	typeArgument (ws* ',' ws* typeArgument)*
	;

typeArgument
	:	referenceType
	|	wildcard
	;

wildcard
	:	(annotations ws*)? '?' (ws * wildcardBounds)?
	;

wildcardBounds
	:	'extends' ws+ referenceType
	|	'super' ws+ referenceType
	;

/*
 * Productions from §6 (Names)
 */

moduleName
	:	identifier
	|	moduleName '.' identifier
	;

packageName
	:	identifier
	|	packageName ws* '.' ws* identifier
	;

typeName
	:	identifier
	|	packageOrTypeName ws* '.' ws* identifier
	;

packageOrTypeName
	:	identifier
	|	packageOrTypeName ws* '.' ws* identifier
	;

expressionName
	:	identifier
	|	ambiguousName ws* '.' ws* identifier
	;

methodName
	:	identifier
	;

ambiguousName
	:	identifier
	|	ambiguousName '.' identifier
	;

/*
 * Productions from §7 (Packages)
 */

compilationUnit
	:	ordinaryCompilation
	|	modularCompilation
	;

ordinaryCompilation
	:	ws* (packageDeclaration ws+)? (importDeclaration ws*)* (typeDeclaration ws*)* EOF
	;

modularCompilation
	:	importDeclaration* moduleDeclaration
	;

packageDeclaration
	:	(packageModifiers ws+)? 'package' ws+ packageName ws* ';' ws*
	;

packageModifiers
    :   packageModifier (ws* packageModifier)*
    ;

packageModifier
	:	annotation
	;

importDeclaration
	:	singleTypeImportDeclaration
	|	typeImportOnDemandDeclaration
	|	singleStaticImportDeclaration
	|	staticImportOnDemandDeclaration
	;

singleTypeImportDeclaration
	:	'import' ws+ typeName ws* ';' ws*
	;

typeImportOnDemandDeclaration
	:	'import' ws+ packageOrTypeName ws* '.' ws* '*' ws* ';' ws*
	;

singleStaticImportDeclaration
	:	'import' ws+ 'static' ws+ typeName ws* '.' identifier ws* ';' ws*
	;

staticImportOnDemandDeclaration
	:	'import' ws+ 'static' ws+ typeName ws* '.' ws* '*' ws* ';' ws*
	;

typeDeclaration
	:	classDeclaration
	|	interfaceDeclaration
	|	';' ws*
	;

moduleDeclaration
	:	annotation* 'open'? 'module' moduleName '{' moduleDirective* '}'
	;

moduleDirective
	:	'requires' requiresModifier* moduleName ';'
	|	'exports' packageName ('to' moduleName (',' moduleName)*)? ';'
	|	'opens' packageName ('to' moduleName (',' moduleName)*)? ';'
	|	'uses' typeName ';'
	|	'provides' typeName 'with' typeName (',' typeName)* ';'
	;

requiresModifier
	:	'transitive'
	|	'static'
	;

/*
 * Productions from §8 (Classes)
 */

classDeclaration
	:	normalClassDeclaration
	|	enumDeclaration
	;

normalClassDeclaration
	:	(classModifiers ws+)? 'class' ws+ identifier (ws* typeParameters)? (ws+ superclass)? (ws+ superinterfaces)? ws* classBody
	;

classModifiers
    :   classModifier (ws+ classModifier)*
    ;

classModifier
	:	annotation
	|	'public'
	|	'protected'
	|	'private'
	|	'abstract'
	|	'static'
	|	'final'
	|	'strictfp'
	;

typeParameters
	:	'<' ws* typeParameterList ws* '>'
	;

typeParameterList
	:	typeParameter (ws* ',' ws* typeParameter)*
	;

superclass
	:	'extends' ws+ classType
	;

superinterfaces
	:	'implements' ws+ interfaceTypeList
	;

interfaceTypeList
	:	interfaceType (ws* ',' ws* interfaceType)*
	;

classBody
	:	'{' ws* (classBodyDeclaration ws*)* '}'
	;

classBodyDeclaration
	:	classMemberDeclaration
	|	instanceInitializer
	|	staticInitializer
	|	constructorDeclaration
	;

classMemberDeclaration
	:	fieldDeclaration
	|	methodDeclaration
	|	classDeclaration
	|	interfaceDeclaration
	|	';' ws*
	;

fieldDeclaration
	:	(fieldModifiers ws+)? unannType ws+ variableDeclaratorList ws* ';' ws*
	;

fieldModifiers
    :   fieldModifier (ws+ fieldModifier)*
    ;

fieldModifier
	:	annotation
	|	'public'
	|	'protected'
	|	'private'
	|	'static'
	|	'final'
	|	'transient'
	|	'volatile'
	;

variableDeclaratorList
	:	variableDeclarator (ws* ',' ws* variableDeclarator)*
	;

variableDeclarator
	:	variableDeclaratorId (ws* '=' ws* variableInitializer)?
	;

variableDeclaratorId
	:	identifier (ws* dims)?
	;

variableInitializer
	:	expression
	|	arrayInitializer
	;

unannType
	:	unannPrimitiveType
	|	unannReferenceType
	;

unannPrimitiveType
	:	numericType
	|	'boolean'
	;

unannReferenceType
	:	unannClassOrInterfaceType
	|	unannTypeVariable
	|	unannArrayType
	;

/*unannClassOrInterfaceType
	:	unannClassType
	|	unannInterfaceType
	;
*/

unannClassOrInterfaceType
	:	(	unannClassType_lfno_unannClassOrInterfaceType
		|	unannInterfaceType_lfno_unannClassOrInterfaceType
		)
		(	unannClassType_lf_unannClassOrInterfaceType
		|	unannInterfaceType_lf_unannClassOrInterfaceType
		)*
	;

unannClassType
	:	identifier typeArguments?
	|	unannClassOrInterfaceType ws* '.' ws* (annotations ws+)? identifier ws* typeArguments?
	;

unannClassType_lf_unannClassOrInterfaceType
	:	'.' ws* (annotations ws+)? identifier ws* typeArguments?
	;

unannClassType_lfno_unannClassOrInterfaceType
	:	identifier ws* typeArguments?
	;

unannInterfaceType
	:	unannClassType
	;

unannInterfaceType_lf_unannClassOrInterfaceType
	:	unannClassType_lf_unannClassOrInterfaceType
	;

unannInterfaceType_lfno_unannClassOrInterfaceType
	:	unannClassType_lfno_unannClassOrInterfaceType
	;

unannTypeVariable
	:	identifier
	;

unannArrayType
	:	unannPrimitiveType dims
	|	unannClassOrInterfaceType dims
	|	unannTypeVariable dims
	;

methodDeclaration
	:	(methodModifiers ws+)? methodHeader ws+ methodBody
	;

methodModifiers
    :   methodModifier (ws+ methodModifier)*
    ;

methodModifier
	:	annotation
	|	'public'
	|	'protected'
	|	'private'
	|	'abstract'
	|	'static'
	|	'final'
	|	'synchronized'
	|	'native'
	|	'strictfp'
	;

methodHeader
	:	result ws+ methodDeclarator (ws+ throws_)?
	|	typeParameters ws* (annotations ws+)? result ws+ methodDeclarator (ws+ throws_)?
	;

result
	:	unannType
	|	'void'
	;

methodDeclarator
	:	identifier ws* '(' ws* formalParameterList? ws* ')' (ws* dims)?
	;

formalParameterList
	:	formalParameters ws* ',' ws* lastFormalParameter
	|	lastFormalParameter
	|	receiverParameter
	;

formalParameters
	:	formalParameter (ws* ',' ws* formalParameter)*
	|	receiverParameter (ws* ',' ws* formalParameter)*
	;

formalParameter
	:	(variableModifiers ws+)? unannType ws+ variableDeclaratorId
	;

variableModifiers
    :   variableModifier (ws+ variableModifier)*
    ;

variableModifier
	:	annotation
	|	'final'
	;

lastFormalParameter
	:	(variableModifiers ws+)? unannType (ws* annotations ws)? ws* '...' ws* variableDeclaratorId
	|	formalParameter
	;

receiverParameter
	:	annotation* unannType (identifier '.')? 'this'
	;

throws_
	:	'throws' ws+ exceptionTypeList
	;

exceptionTypeList
	:	exceptionType (ws* ',' ws* exceptionType)*
	;

exceptionType
	:	classType
	|	typeVariable
	;

methodBody
	:	block
	|	';' ws*
	;

instanceInitializer
	:	block
	;

staticInitializer
	:	'static' ws+ block
	;

constructorDeclaration
	:	(constructorModifiers ws+)? constructorDeclarator (ws+ throws_)? ws* constructorBody
	;

constructorModifiers
    :   constructorModifier (ws+ constructorModifier)*
    ;

constructorModifier
	:	annotation
	|	'public'
	|	'protected'
	|	'private'
	;

constructorDeclarator
	:	(typeParameters ws*)? simpleTypeName ws* '(' ws* (formalParameterList ws*)? ')' ws*
	;

simpleTypeName
	:	identifier
	;

constructorBody
	:	'{' ws* (explicitConstructorInvocation)? (blockStatements)? ws* '}'
	;

explicitConstructorInvocation
	:	(typeArguments ws*)? 'this' ws* '(' ws* (argumentList ws*)? ')' ';' ws*
	|	typeArguments? 'super' '(' ws* (argumentList ws*)? ')' ';' ws*
	|	expressionName ws* '.' ws* (typeArguments ws*)? 'super' ws* '(' ws* (argumentList ws*)? ')' ws* ';' ws*
	|	primary ws* '.' ws* (typeArguments ws*)? 'super' ws* '(' ws* (argumentList ws*)? ')' ws* ';' ws*
	;

enumDeclaration
	:	(classModifiers ws+)? 'enum' ws+ identifier (ws+ superinterfaces)? ws* enumBody
	;

enumBody
	:	'{' ws* (enumConstantList ws*)? ','? ws* (enumBodyDeclarations ws*)? '}'
	;

enumConstantList
	:	enumConstant (ws* ',' ws* enumConstant)*
	;

enumConstant
	:	(enumConstantModifiers ws+)? identifier (ws* '(' ws* (argumentList ws*)? ')')? (ws* classBody)?
	;

enumConstantModifiers
    :   enumConstantModifier (ws* enumConstantModifier)*
    ;

enumConstantModifier
	:	annotation
	;

enumBodyDeclarations
	:	';' (ws* classBodyDeclaration)*
	;

/*
 * Productions from §9 (Interfaces)
 */

interfaceDeclaration
	:	normalInterfaceDeclaration
	|	annotationTypeDeclaration
	;

normalInterfaceDeclaration
	:	(interfaceModifiers ws+)? 'interface' ws+ identifier (ws* typeParameters)? (ws* extendsInterfaces)? ws* interfaceBody
	;

interfaceModifiers
    :   interfaceModifier (ws+ interfaceModifier)*
    ;

interfaceModifier
	:	annotation
	|	'public'
	|	'protected'
	|	'private'
	|	'abstract'
	|	'static'
	|	'strictfp'
	;

extendsInterfaces
	:	'extends' ws+ interfaceTypeList
	;

interfaceBody
	:	'{' ws* (interfaceMemberDeclaration ws*)* '}'
	;

interfaceMemberDeclaration
	:	constantDeclaration
	|	interfaceMethodDeclaration
	|	classDeclaration
	|	interfaceDeclaration
	|	';' ws*
	;

constantDeclaration
	:	(constantModifiers ws+)? unannType ws+ variableDeclaratorList ws* ';' ws*
	;

constantModifiers
    :   constantModifier (ws+ constantModifier)*
    ;

constantModifier
	:	annotation
	|	'public'
	|	'static'
	|	'final'
	;

interfaceMethodDeclaration
	:	(interfaceMethodModifiers ws+)? methodHeader ws* methodBody
	;

interfaceMethodModifiers
    :   interfaceMethodModifier (ws+ interfaceMethodModifier)*
    ;

interfaceMethodModifier
	:	annotation
	|	'public'
	|	'private'//Introduced in Java 9
	|	'abstract'
	|	'default'
	|	'static'
	|	'strictfp'
	;

annotationTypeDeclaration
	:	interfaceModifier* '@' 'interface' identifier annotationTypeBody
	;

annotationTypeBody
	:	'{' annotationTypeMemberDeclaration* '}'
	;

annotationTypeMemberDeclaration
	:	annotationTypeElementDeclaration
	|	constantDeclaration
	|	classDeclaration
	|	interfaceDeclaration
	|	';' ws*
	;

annotationTypeElementDeclaration
	:	annotationTypeElementModifier* unannType identifier '(' ')' dims? defaultValue? ';'
	;

annotationTypeElementModifier
	:	annotation
	|	'public'
	|	'abstract'
	;

defaultValue
	:	'default' elementValue
	;

annotations
    :   annotation (ws* annotation)*
    ;

annotation
	:	normalAnnotation
	|	markerAnnotation
	|	singleElementAnnotation
	;

normalAnnotation
	:	'@' typeName ws* '(' ws* (elementValuePairList ws*)? ')'
	;

elementValuePairList
	:	elementValuePair (ws* ',' ws* elementValuePair)*
	;

elementValuePair
	:	identifier ws* '=' ws* elementValue
	;

elementValue
	:	conditionalExpression
	|	elementValueArrayInitializer
	|	annotation
	;

elementValueArrayInitializer
	:	'{' ws* (elementValueList ws*)? ','? ws* '}'
	;

elementValueList
	:	elementValue (ws* ',' ws* elementValue)*
	;

markerAnnotation
	:	'@' typeName
	;

singleElementAnnotation
	:	'@' typeName ws* '(' ws* elementValue ws* ')'
	;

/*
 * Productions from §10 (Arrays)
 */

arrayInitializer
	:	'{' ws* (variableInitializerList ws*)? ','? ws* '}'
	;

variableInitializerList
	:	variableInitializer (ws* ',' ws* variableInitializer)*
	;

/*
 * Productions from §14 (Blocks and Statements)
 */

block
	:	'{' ws* (blockStatements ws*)? '}'
	;

blockStatements
	:	(blockStatement ws*)+
	;

blockStatement
	:	localVariableDeclarationStatement
	|	classDeclaration
	|	statement
	;

localVariableDeclarationStatement
	:	localVariableDeclaration ws* ';' ws*
	;

localVariableDeclaration
	:	(variableModifiers ws+)? unannType ws+ variableDeclaratorList
	;

statement
	:	statementWithoutTrailingSubstatement
	|	labeledStatement
	|	ifThenStatement
	|	ifThenElseStatement
	|	whileStatement
	|	forStatement
	;

statementNoShortIf
	:	statementWithoutTrailingSubstatement
	|	labeledStatementNoShortIf
	|	ifThenElseStatementNoShortIf
	|	whileStatementNoShortIf
	|	forStatementNoShortIf
	;

statementWithoutTrailingSubstatement
	:	block
	|	emptyStatement
	|	expressionStatement
	|	assertStatement
	|	switchStatement
	|	doStatement
	|	breakStatement
	|	continueStatement
	|	returnStatement
	|	synchronizedStatement
	|	throwStatement
	|	tryStatement
	;

emptyStatement
	:	';' ws*
	;

labeledStatement
	:	identifier ws* ':' ws* statement
	;

labeledStatementNoShortIf
	:	identifier ':' statementNoShortIf
	;

expressionStatement
	:	statementExpression ws* ';' ws*
	;

statementExpression
	:	assignment
	|	preIncrementExpression
	|	preDecrementExpression
	|	postIncrementExpression
	|	postDecrementExpression
	|	methodInvocation
	|	classInstanceCreationExpression
	;

ifThenStatement
	:	e='if' ws* '(' ws* expression ws* ')' ws* statement {System.out.println( "expr: " + $expression.text + " " + $e.line);}
	;

ifThenElseStatement
	:	'if' ws* '(' ws* expression ws* ')' ws* statementNoShortIf ws* 'else' ws* statement
	;

ifThenElseStatementNoShortIf
	:	'if' ws* '('  ws* expression ws* ')' ws* statementNoShortIf ws* 'else' ws* statementNoShortIf
	;

assertStatement
	:	'assert' ws+ expression ws* ';' ws*
	|	'assert' ws+ expression ws* ':' ws* expression ws* ';' ws*
	;

switchStatement
	:	'switch' ws* '(' ws* expression ws* ')' ws* switchBlock
	;

switchBlock
	:	'{' ws* (switchBlockStatementGroup ws*)* switchLabel* '}'
	;

switchBlockStatementGroup
	:	switchLabels blockStatements
	;

switchLabels
	:	switchLabel+
	;

switchLabel
	:	'case' constantExpression ':'
	|	'case' enumConstantName ':'
	|	'default' ':'
	;

enumConstantName
	:	identifier
	;

whileStatement
	:	'while' ws* '(' ws* expression ws* ')' ws* statement
	;

whileStatementNoShortIf
	:	'while' ws* '(' ws* expression ws* ')' ws* statementNoShortIf
	;

doStatement
	:	'do' ws* statement ws* 'while' ws* '(' ws* expression ws* ')' ws* ';' ws*
	;

forStatement
	:	basicForStatement
	|	enhancedForStatement
	;

forStatementNoShortIf
	:	basicForStatementNoShortIf
	|	enhancedForStatementNoShortIf
	;

basicForStatement
	:	'for' ws* '(' ws* forInit? ws* ';' ws* expression? ws* ';' ws* forUpdate? ws* ')' ws* statement
	;

basicForStatementNoShortIf
	:	'for' ws* '(' ws* forInit? ws* ';' ws* expression? ws* ';' ws* forUpdate? ws* ')' ws* statementNoShortIf
	;

forInit
	:	statementExpressionList
	|	localVariableDeclaration
	;

forUpdate
	:	statementExpressionList
	;

statementExpressionList
	:	statementExpression (ws* ',' ws* statementExpression)*
	;

enhancedForStatement
	:	'for' ws* '(' ws* (variableModifiers ws+)? unannType ws+ variableDeclaratorId ws* ':' ws* expression ws* ')' ws* statement
	;

enhancedForStatementNoShortIf
	:	'for' ws* '(' ws* (variableModifiers ws+)? unannType ws+ variableDeclaratorId ws* ':' ws* expression ws* ')' ws* statementNoShortIf
	;

breakStatement
	:	'break' (ws+ identifier)? ws* ';' ws*
	;

continueStatement
	:	'continue' (ws+ identifier)? ws* ';' ws*
	;

returnStatement
	:	'return' (ws+ expression)? ws* ';' ws*
	;

throwStatement
	:	'throw' ws+ expression ws* ';' ws*
	;

synchronizedStatement
	:	'synchronized' ws* '(' ws* expression ws* ')' ws* block
	;

tryStatement
	:	'try' ws* block ws* catches
	|	'try' ws* block (ws+ catches)? (ws+ finally_)
	|	tryWithResourcesStatement
	;

catches
	:	(catchClause ws*)+
	;

catchClause
	:	'catch' ws* '(' ws* catchFormalParameter ws* ')' ws* block
	;

catchFormalParameter
	:	(variableModifiers ws+)? catchType ws+ variableDeclaratorId
	;

catchType
	:	unannClassType (ws* '|' ws* classType)*
	;

finally_
	:	'finally' ws+ block
	;

tryWithResourcesStatement
	:	'try' ws* resourceSpecification ws* block (ws+ catches)? (ws+ finally_)?
	;

resourceSpecification
	:	'(' ws* resourceList ws* ';'? ws* ')'
	;

resourceList
	:	resource (ws* ';' ws* resource)*
	;

resource
	:	variableModifier* unannType variableDeclaratorId '=' expression
	|	variableAccess//Introduced in Java 9
	;

variableAccess
	:	expressionName
	|	fieldAccess
	;

/*
 * Productions from §15 (Expressions)
 */

/*primary
	:	primaryNoNewArray
	|	arrayCreationExpression
	;
*/

primary
	:	(	primaryNoNewArray_lfno_primary
		|	arrayCreationExpression
		)
		(	primaryNoNewArray_lf_primary
		)*
	;

primaryNoNewArray
	:	literal
	|	classLiteral
	|	'this'
	|	typeName '.' 'this'
	|	'(' expression ')'
	|	classInstanceCreationExpression
	|	fieldAccess
	|	arrayAccess
	|	methodInvocation
	|	methodReference
	;

primaryNoNewArray_lf_arrayAccess
	:
	;

primaryNoNewArray_lfno_arrayAccess
	:	literal
	|	typeName (ws* '[' ws* ']')* ws* '.' ws* 'class'
	|	'void' ws* '.' ws* 'class'
	|	'this'
	|	typeName ws* '.' ws* 'this'
	|	'(' ws* expression ws* ')'
	|	classInstanceCreationExpression
	|	fieldAccess
	|	methodInvocation
	|	methodReference
	;

primaryNoNewArray_lf_primary
	:	classInstanceCreationExpression_lf_primary
	|	fieldAccess_lf_primary
	|	arrayAccess_lf_primary
	|	methodInvocation_lf_primary
	|	methodReference_lf_primary
	;

primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary
	:
	;

primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary
	:	classInstanceCreationExpression_lf_primary
	|	fieldAccess_lf_primary
	|	methodInvocation_lf_primary
	|	methodReference_lf_primary
	;

primaryNoNewArray_lfno_primary
	:	literal
	|	typeName ('[' ']')* '.' 'class'
	|	unannPrimitiveType ('[' ']')* '.' 'class'
	|	'void' '.' 'class'
	|	'this'
	|	typeName '.' 'this'
	|	'(' expression ')'
	|	classInstanceCreationExpression_lfno_primary
	|	fieldAccess_lfno_primary
	|	arrayAccess_lfno_primary
	|	methodInvocation_lfno_primary
	|	methodReference_lfno_primary
	;

primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary
	:
	;

primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary
	:	literal
	|	typeName ('[' ']')* '.' 'class'
	|	unannPrimitiveType ('[' ']')* '.' 'class'
	|	'void' '.' 'class'
	|	'this'
	|	typeName '.' 'this'
	|	'(' expression ')'
	|	classInstanceCreationExpression_lfno_primary
	|	fieldAccess_lfno_primary
	|	methodInvocation_lfno_primary
	|	methodReference_lfno_primary
	;

classLiteral
	:	(typeName|numericType|'boolean') ('[' ']')* '.' 'class'
	|	'void' '.' 'class'
	;


classInstanceCreationExpression
	:	'new' (typeArguments? (annotations ws+)? | ws+) identifier (ws* '.' ws* (annotations ws+)? identifier)* (ws* typeArgumentsOrDiamond)? ws* '(' ws* (argumentList ws*)? ')' (ws* classBody)?
	|	expressionName ws* '.' ws* 'new' (typeArguments? (annotations ws+)? | ws+) identifier (ws* typeArgumentsOrDiamond)? ws* '(' ws* (argumentList ws*)? ')' (ws* classBody)?
	|	primary ws* '.' ws* 'new' (typeArguments? (annotations ws+) | ws+) identifier (ws* typeArgumentsOrDiamond)? ws* '(' ws* (argumentList ws*)? ')' (ws* classBody)?
	;

classInstanceCreationExpression_lf_primary
	:	'.' ws* 'new' (typeArguments? (annotations ws+)? | ws+) identifier (ws* typeArgumentsOrDiamond)? ws* '(' ws* (argumentList ws*)? ')' (ws* classBody)?
	;

classInstanceCreationExpression_lfno_primary
	:	'new' (typeArguments? (annotations ws+)? | ws+) identifier (ws* '.' ws* (annotations ws+)? identifier)* (ws* typeArgumentsOrDiamond)? ws* '(' ws* (argumentList ws*)? ')' (ws* classBody)?
	|	expressionName ws* '.' ws* 'new' (typeArguments? (annotations ws+)? | ws+) identifier (ws* typeArgumentsOrDiamond)? ws* '(' ws* (argumentList ws*)? ')' (ws* classBody)?
	;

typeArgumentsOrDiamond
	:	typeArguments
	|	'<' ws* '>'
	;

fieldAccess
	:	primary '.' identifier
	|	'super' '.' identifier
	|	typeName '.' 'super' '.' identifier
	;

fieldAccess_lf_primary
	:	'.' identifier
	;

fieldAccess_lfno_primary
	:	'super' '.' identifier
	|	typeName '.' 'super' '.' identifier
	;

/*arrayAccess
	:	expressionName '[' expression ']'
	|	primaryNoNewArray '[' expression ']'
	;
*/

arrayAccess
	:	(	expressionName '[' expression ']'
		|	primaryNoNewArray_lfno_arrayAccess '[' expression ']'
		)
		(	primaryNoNewArray_lf_arrayAccess '[' expression ']'
		)*
	;

arrayAccess_lf_primary
	:	(	primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary '[' expression ']'
		)
		(	primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary '[' expression ']'
		)*
	;

arrayAccess_lfno_primary
	:	(	expressionName '[' expression ']'
		|	primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary '[' expression ']'
		)
		(	primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary '[' expression ']'
		)*
	;


methodInvocation
	:	methodName '(' argumentList? ')'
	|	typeName '.' typeArguments? identifier '(' argumentList? ')'
	|	expressionName '.' typeArguments? identifier '(' argumentList? ')'
	|	primary '.' typeArguments? identifier '(' argumentList? ')'
	|	'super' '.' typeArguments? identifier '(' argumentList? ')'
	|	typeName '.' 'super' '.' typeArguments? identifier '(' argumentList? ')'
	;

methodInvocation_lf_primary
	:	'.' typeArguments? identifier '(' argumentList? ')'
	;

methodInvocation_lfno_primary
	:	methodName '(' argumentList? ')'
	|	typeName '.' typeArguments? identifier '(' argumentList? ')'
	|	expressionName '.' typeArguments? identifier '(' argumentList? ')'
	|	'super' '.' typeArguments? identifier '(' argumentList? ')'
	|	typeName '.' 'super' '.' typeArguments? identifier '(' argumentList? ')'
	;

argumentList
	:	expression (ws* ',' ws* expression)*
	;

methodReference
	:	expressionName '::' typeArguments? identifier
	|	referenceType '::' typeArguments? identifier
	|	primary '::' typeArguments? identifier
	|	'super' '::' typeArguments? identifier
	|	typeName '.' 'super' '::' typeArguments? identifier
	|	classType '::' typeArguments? 'new'
	|	arrayType '::' 'new'
	;

methodReference_lf_primary
	:	'::' typeArguments? identifier
	;

methodReference_lfno_primary
	:	expressionName '::' typeArguments? identifier
	|	referenceType '::' typeArguments? identifier
	|	'super' '::' typeArguments? identifier
	|	typeName '.' 'super' '::' typeArguments? identifier
	|	classType '::' typeArguments? 'new'
	|	arrayType '::' 'new'
	;

arrayCreationExpression
	:	'new' ws+ primitiveType dimExprs (ws* dims)?
	|	'new' ws+ classOrInterfaceType dimExprs (ws* dims)?
	|	'new' ws+ primitiveType ws* dims ws* arrayInitializer
	|	'new' ws+ classOrInterfaceType ws* dims ws* arrayInitializer
	;

dimExprs
	:	(ws* dimExpr)+
	;

dimExpr
	:	(annotations ws*)? '[' ws* expression ws* ']'
	;

constantExpression
	:	expression
	;

expression
	:	lambdaExpression
	|	assignmentExpression
	;

lambdaExpression
	:	lambdaParameters ws* '->' ws* lambdaBody
	;

lambdaParameters
	:	identifier
	|	'(' ws* (formalParameterList ws*)? ')'
	|	'(' ws* inferredFormalParameterList ws* ')'
	;

inferredFormalParameterList
	:	identifier (ws* ',' ws* identifier)*
	;

lambdaBody
	:	expression
	|	block
	;

assignmentExpression
	:	conditionalExpression
	|	assignment
	;

assignment
	:	leftHandSide ws* assignmentOperator ws* expression
	;

leftHandSide
	:	expressionName
	|	fieldAccess
	|	arrayAccess
	;

assignmentOperator
	:	'='
	|	'*='
	|	'/='
	|	'%='
	|	'+='
	|	'-='
	|	'<<='
	|	'>>='
	|	'>>>='
	|	'&='
	|	'^='
	|	'|='
	;

conditionalExpression
	:	conditionalOrExpression
	|	conditionalOrExpression ws* '?' ws* expression ws* ':' ws* (conditionalExpression|lambdaExpression)
	;

conditionalOrExpression
	:	conditionalAndExpression
	|	conditionalOrExpression ws* '||' ws* conditionalAndExpression
	;

conditionalAndExpression
	:	inclusiveOrExpression
	|	conditionalAndExpression ws* '&&' ws* inclusiveOrExpression
	;

inclusiveOrExpression
	:	exclusiveOrExpression
	|	inclusiveOrExpression ws* '|' ws* exclusiveOrExpression
	;

exclusiveOrExpression
	:	andExpression
	|	exclusiveOrExpression ws* '^' ws* andExpression
	;

andExpression
	:	equalityExpression
	|	andExpression ws* '&' ws* equalityExpression
	;

equalityExpression
	:	relationalExpression
	|	equalityExpression ws* '==' ws* relationalExpression
	|	equalityExpression ws* '!=' ws* relationalExpression
	;

relationalExpression
	:	shiftExpression
	|	relationalExpression ws* '<' ws* shiftExpression
	|	relationalExpression ws* '>' ws* shiftExpression
	|	relationalExpression ws* '<=' ws* shiftExpression
	|	relationalExpression ws* '>=' ws* shiftExpression
	|	relationalExpression ws+ 'instanceof' ws+ referenceType
	;

shiftExpression
	:	additiveExpression
	|	shiftExpression ws* '<' '<' ws*additiveExpression
	|	shiftExpression ws* '>' '>' ws* additiveExpression
	|	shiftExpression ws* '>' '>' '>' ws* additiveExpression
	;

additiveExpression
	:	multiplicativeExpression
	|	additiveExpression ws* '+' ws* multiplicativeExpression
	|	additiveExpression ws* '-' ws* multiplicativeExpression
	;

multiplicativeExpression
	:	unaryExpression
	|	multiplicativeExpression ws* '*' ws* unaryExpression
	|	multiplicativeExpression ws* '/' ws* unaryExpression
	|	multiplicativeExpression ws* '%' ws* unaryExpression
	;

unaryExpression
	:	preIncrementExpression
	|	preDecrementExpression
	|	'+' ws* unaryExpression
	|	'-' ws* unaryExpression
	|	unaryExpressionNotPlusMinus
	;

preIncrementExpression
	:	'++' ws* unaryExpression
	;

preDecrementExpression
	:	'--' ws* unaryExpression
	;

unaryExpressionNotPlusMinus
	:	postfixExpression
	|	'~' ws* unaryExpression
	|	'!' ws* unaryExpression
	|	castExpression
	;

/*postfixExpression
	:	primary
	|	expressionName
	|	postIncrementExpression
	|	postDecrementExpression
	;
*/

postfixExpression
	:	(	primary
		|	expressionName
		)
		(	postIncrementExpression_lf_postfixExpression
		|	postDecrementExpression_lf_postfixExpression
		)*
	;

postIncrementExpression
	:	postfixExpression '++'
	;

postIncrementExpression_lf_postfixExpression
	:	'++'
	;

postDecrementExpression
	:	postfixExpression '--'
	;

postDecrementExpression_lf_postfixExpression
	:	'--'
	;

castExpression
	:	'(' ws* primitiveType ws* ')' ws* unaryExpression
	|	'(' ws* referenceType ws* additionalBound* ')' ws* unaryExpressionNotPlusMinus
	|	'(' ws* referenceType ws* additionalBound* ')' ws* lambdaExpression
	;

// LEXER

identifier : Identifier | 'to' | 'module' | 'open' | 'with' | 'provides' | 'uses' | 'opens' | 'requires' | 'exports';

// §3.9 Keywords

ABSTRACT : 'abstract';
ASSERT : 'assert';
BOOLEAN : 'boolean';
BREAK : 'break';
BYTE : 'byte';
CASE : 'case';
CATCH : 'catch';
CHAR : 'char';
CLASS : 'class';
CONST : 'const';
CONTINUE : 'continue';
DEFAULT : 'default';
DO : 'do';
DOUBLE : 'double';
ELSE : 'else';
ENUM : 'enum';
EXTENDS : 'extends';
FINAL : 'final';
FINALLY : 'finally';
FLOAT : 'float';
FOR : 'for';
IF : 'if';
GOTO : 'goto';
IMPLEMENTS : 'implements';
IMPORT : 'import';
INSTANCEOF : 'instanceof';
INT : 'int';
INTERFACE : 'interface';
LONG : 'long';
NATIVE : 'native';
NEW : 'new';
PACKAGE : 'package';
PRIVATE : 'private';
PROTECTED : 'protected';
PUBLIC : 'public';
RETURN : 'return';
SHORT : 'short';
STATIC : 'static';
STRICTFP : 'strictfp';
SUPER : 'super';
SWITCH : 'switch';
SYNCHRONIZED : 'synchronized';
THIS : 'this';
THROW : 'throw';
THROWS : 'throws';
TRANSIENT : 'transient';
TRY : 'try';
VOID : 'void';
VOLATILE : 'volatile';
WHILE : 'while';
UNDER_SCORE : '_';//Introduced in Java 9

// §3.10.1 Integer Literals

IntegerLiteral
	:	DecimalIntegerLiteral
	|	HexIntegerLiteral
	|	OctalIntegerLiteral
	|	BinaryIntegerLiteral
	;

fragment
DecimalIntegerLiteral
	:	DecimalNumeral IntegerTypeSuffix?
	;

fragment
HexIntegerLiteral
	:	HexNumeral IntegerTypeSuffix?
	;

fragment
OctalIntegerLiteral
	:	OctalNumeral IntegerTypeSuffix?
	;

fragment
BinaryIntegerLiteral
	:	BinaryNumeral IntegerTypeSuffix?
	;

fragment
IntegerTypeSuffix
	:	[lL]
	;

fragment
DecimalNumeral
	:	'0'
	|	NonZeroDigit (Digits? | Underscores Digits)
	;

fragment
Digits
	:	Digit (DigitsAndUnderscores? Digit)?
	;

fragment
Digit
	:	'0'
	|	NonZeroDigit
	;

fragment
NonZeroDigit
	:	[1-9]
	;

fragment
DigitsAndUnderscores
	:	DigitOrUnderscore+
	;

fragment
DigitOrUnderscore
	:	Digit
	|	'_'
	;

fragment
Underscores
	:	'_'+
	;

fragment
HexNumeral
	:	'0' [xX] HexDigits
	;

fragment
HexDigits
	:	HexDigit (HexDigitsAndUnderscores? HexDigit)?
	;

fragment
HexDigit
	:	[0-9a-fA-F]
	;

fragment
HexDigitsAndUnderscores
	:	HexDigitOrUnderscore+
	;

fragment
HexDigitOrUnderscore
	:	HexDigit
	|	'_'
	;

fragment
OctalNumeral
	:	'0' Underscores? OctalDigits
	;

fragment
OctalDigits
	:	OctalDigit (OctalDigitsAndUnderscores? OctalDigit)?
	;

fragment
OctalDigit
	:	[0-7]
	;

fragment
OctalDigitsAndUnderscores
	:	OctalDigitOrUnderscore+
	;

fragment
OctalDigitOrUnderscore
	:	OctalDigit
	|	'_'
	;

fragment
BinaryNumeral
	:	'0' [bB] BinaryDigits
	;

fragment
BinaryDigits
	:	BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)?
	;

fragment
BinaryDigit
	:	[01]
	;

fragment
BinaryDigitsAndUnderscores
	:	BinaryDigitOrUnderscore+
	;

fragment
BinaryDigitOrUnderscore
	:	BinaryDigit
	|	'_'
	;

// §3.10.2 Floating-Point Literals

FloatingPointLiteral
	:	DecimalFloatingPointLiteral
	|	HexadecimalFloatingPointLiteral
	;

fragment
DecimalFloatingPointLiteral
	:	Digits '.' Digits? ExponentPart? FloatTypeSuffix?
	|	'.' Digits ExponentPart? FloatTypeSuffix?
	|	Digits ExponentPart FloatTypeSuffix?
	|	Digits FloatTypeSuffix
	;

fragment
ExponentPart
	:	ExponentIndicator SignedInteger
	;

fragment
ExponentIndicator
	:	[eE]
	;

fragment
SignedInteger
	:	Sign? Digits
	;

fragment
Sign
	:	[+-]
	;

fragment
FloatTypeSuffix
	:	[fFdD]
	;

fragment
HexadecimalFloatingPointLiteral
	:	HexSignificand BinaryExponent FloatTypeSuffix?
	;

fragment
HexSignificand
	:	HexNumeral '.'?
	|	'0' [xX] HexDigits? '.' HexDigits
	;

fragment
BinaryExponent
	:	BinaryExponentIndicator SignedInteger
	;

fragment
BinaryExponentIndicator
	:	[pP]
	;

// §3.10.3 Boolean Literals

BooleanLiteral
	:	'true'
	|	'false'
	;

// §3.10.4 Character Literals

CharacterLiteral
	:	'\'' SingleCharacter '\''
	|	'\'' EscapeSequence '\''
	;

fragment
SingleCharacter
	:	~['\\\r\n]
	;

// §3.10.5 String Literals

StringLiteral
	:	'"' StringCharacters? '"'
	;

fragment
StringCharacters
	:	StringCharacter+
	;

fragment
StringCharacter
	:	~["\\\r\n]
	|	EscapeSequence
	;

// §3.10.6 Escape Sequences for Character and String Literals

fragment
EscapeSequence
	:	'\\' [btnfr"'\\]
	|	OctalEscape
    |   UnicodeEscape // This is not in the spec but prevents having to preprocess the input
	;

fragment
OctalEscape
	:	'\\' OctalDigit
	|	'\\' OctalDigit OctalDigit
	|	'\\' ZeroToThree OctalDigit OctalDigit
	;

fragment
ZeroToThree
	:	[0-3]
	;

// This is not in the spec but prevents having to preprocess the input
fragment
UnicodeEscape
    :   '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

// §3.10.7 The Null Literal

NullLiteral
	:	'null'
	;

// §3.11 Separators

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
SEMI : ';';
COMMA : ',';
DOT : '.';
ELLIPSIS : '...';
AT : '@';
COLONCOLON : '::';


// §3.12 Operators

ASSIGN : '=';
GT : '>';
LT : '<';
BANG : '!';
TILDE : '~';
QUESTION : '?';
COLON : ':';
ARROW : '->';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';
AND : '&&';
OR : '||';
INC : '++';
DEC : '--';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
BITAND : '&';
BITOR : '|';
CARET : '^';
MOD : '%';
//LSHIFT : '<<';
//RSHIFT : '>>';
//URSHIFT : '>>>';

ADD_ASSIGN : '+=';
SUB_ASSIGN : '-=';
MUL_ASSIGN : '*=';
DIV_ASSIGN : '/=';
AND_ASSIGN : '&=';
OR_ASSIGN : '|=';
XOR_ASSIGN : '^=';
MOD_ASSIGN : '%=';
LSHIFT_ASSIGN : '<<=';
RSHIFT_ASSIGN : '>>=';
URSHIFT_ASSIGN : '>>>=';

// §3.8 Identifiers (must appear after all keywords in the grammar)

Identifier
	:	JavaLetter JavaLetterOrDigit*
	;

fragment
JavaLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierStart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierPart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;


//
// Whitespace and comments
//

ws
    :   WS
    |   LINE_COMMENT
    ;

WS  :  [ \t\r\n\u000C]+
    ;

COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;

LINE_COMMENT
    :   '//' ~[\r\n]*
    ;
