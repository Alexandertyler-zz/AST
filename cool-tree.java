// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////



import java.util.Enumeration;
import java.io.PrintStream;
import java.util.Vector;
import java.util.ArrayList;

/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();
    //a placeholder for semantCheck maybe
}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getParent();
    public abstract AbstractSymbol getFilename();
    public abstract Features getFeatures();
    //a placeholder for semantCheck maybe

}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    //placeholder for semantCheck
    public abstract void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class);

}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    //placeholder for semantCheck()
    public abstract void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class);
}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    //placeholder for semantCheck
    public abstract void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class);

    //assertType?

}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    //placeholder for semantCheck

}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Defines AST constructor 'programc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class programc extends Program {
    protected Classes classes;
    /** Creates "programc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new programc(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "programc\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */

	//Call a class table constructor that checks that
	//it is well formed
	ClassTable cTable = new ClassTable(classes);

	/* some semantic analysis code may go here */

	if (cTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
        }
    
    	//error check once now and once after semantCheck
    
    	//make a sym table for use in semantCheck
    	SymbolTable sTable = new SymbolTable();
    	//this is where we actually walk through all of our classes and
    	//check them semantically
    	for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
        	class_c curr_class = (class_c) e.nextElement();
		//System.out.println("Performing initial semantCheck on class_c " + curr_class.getName());
	    	curr_class.semantCheck(sTable, cTable, curr_class);
    	}
    
    	if (cTable.errors()) {
        	System.err.println("Compilation halted due to static semantic errors.");
        	System.exit(1);
    	}

    	/*Here we need to do the actual semant error check.
     	* Not sure if I want to put it in ClassTable or not.
     	* I could have it automatically check when class table is called.
     	*
     	* variables: 
     	*      classTable (Table of all classes made)
     	*	            
     	*	    
     	*/
    }
}


/** Defines AST constructor 'class_c'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_c extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;
    private boolean visited;
    /** Creates "class_c" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_c(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
        visited = false;
    }
    public TreeNode copy() {
        return new class_c(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_c\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }
    public AbstractSymbol getFilename() { return filename; }
    public Features getFeatures()       { return features; }

    //This is where I defined the functions for checking booleans for cycles
    public boolean checkVisit()		{ return visited; }
    public void visit()			{ visited = true; }
    public void resetVisit()		{ visited = false; }    


    /* For this check, we initially add the self_type to the symbol
     * table. Then we walk through each of the features of the 
     * class and type check them.
     */
    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	sTable.enterScope();
	//System.out.println("Curr_class in class_c is " + curr_class.getName());
        sTable.addId(TreeConstants.self, TreeConstants.SELF_TYPE);
	for (Enumeration e = features.getElements(); e.hasMoreElements(); ) {
	    Feature curr_feat = (Feature) e.nextElement();
	    //System.out.println("Performing semantCheck in class_c on Feature " + curr_feat);
	    curr_feat.semantCheck(sTable, cTable, curr_class);
	}
        sTable.exitScope();
	
    }
}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        Formals parentFormals = cTable.formalLookup(curr_class.getParent(), name);
        
        if (parentFormals != null) {
            if (parentFormals.getLength() == formals.getLength()) { 
                int iter = 0;
                for (Enumeration e = parentFormals.getElements(); e.hasMoreElements(); ) {
                    formalc parentFormal = (formalc) e.nextElement();
                    formalc currFormal = (formalc) formals.getNth(iter);
                    iter++;
                    if (!(parentFormal.type_decl.equals(currFormal.type_decl))) {
                        cTable.semantError(curr_class.getFilename(), this).println("Formal "+currFormal.type_decl+" does not have the same type as parent formal "+parentFormal.type_decl+".");
                    }
                }
            } else {
                cTable.semantError(curr_class.getFilename(), this).println("Error when overriding parent formals. Parents length: "+parentFormals.getLength()+
			", Formals length "+formals.getLength()+".");
            }
        }
        AbstractSymbol parentReturnType = cTable.toReturn( 
        sTable.enterScope();
        for (Enumeration e = formals.getElements(); e.hasMoreElements(); ) {
            Formal currFormal = (Formal) e.nextElement();
            currFormal.semantCheck(sTable, cTable, curr_class);
        }

        AbstractSymbol returnType = return_type;
        if(!returnType.equals(TreeConstants.SELF_TYPE) && !(cTable.class_cTable.containsKey(returnType))) {
            cTable.semantError(curr_class.getFilename(), this).println("Method "+name+" return type is not found.");
        }

        if (returnType.equals(TreeConstants.SELF_TYPE)) {
            returnType = curr_class.getName();
        }

        expr.semantCheck(sTable, cTable, curr_class);
        //System.out.println("Expression type is " + expr.get_type());
	//System.out.println(returnType);
	if (!cTable.typeCheck(expr.get_type(), returnType, curr_class)) { //assert expr.get_type == returnType
                //System.out.println("Expression is " + expr.get_type() + " and return type is " + returnType);
		cTable.semantError(curr_class.getFilename, this).println("Method return type "+expr.get_type()+
			" is not equal to expression return type "+returnType+".");
        }
        sTable.exitScope();


    }

}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        //check for inherited attributes that are being overwritten
	
	//System.out.println("Curr_class in attr is " + curr_class.getName());
	AbstractSymbol parentName = curr_class.getParent();
	//System.out.println(parentName);
	if (!(parentName.equals(TreeConstants.No_class))) {
            AbstractSymbol parentAttr = cTable.attrLookup(curr_class.getParent(), name);
            if (parentAttr != null) {
                cTable.semantError(curr_class.getFilename(), this).println("Redefining parent attribute "+parentAttr+".");
            }
	}
	//System.out.println("Outside");
        sTable.enterScope();
	//System.out.println("init: " + init);
	init.semantCheck(sTable, cTable, curr_class);
	//System.out.println("After init.");

        if (name.equals(TreeConstants.self)) {
            cTable.semantError(curr_class.getFilename(), this).println("Attribute has name self.");
        }
        if ((!cTable.typeCheck(init.get_type(), type_decl, curr_class)) &&  (!init.get_type().equals(TreeConstants.No_type))) {
            cTable.semantError(curr_class.getFilename(), this).println("Expr does not conform to declared type "+type_decl+" in attr.");
        }
        sTable.exitScope();

    }

}


/** Defines AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    /** Creates "formalc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formalc(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formalc\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    if (name.equals(TreeConstants.self)) {
            cTable.semantError(curr_class.getFilename(), this).println("Self is used as a formal_c.");
        }
        AbstractSymbol sType = (AbstractSymbol) sTable.probe(name);
        if (sType != null) {
            cTable.semantError(curr_class.getFilename(), this).println("Name "+sType+" of formalc is in current scope of symbol table.");
        }
        if (type_decl.equals(TreeConstants.SELF_TYPE)) {
            cTable.semantError(curr_class.getFilename(), this).println("Type is of type SELF_TYPE in formalc.");
        }

        sTable.addId(name, type_decl);
    }
}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

    //branch doesn't need a type because it is a holder for an ID and an expression
    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        sTable.enterScope();
        sTable.addId(name, type_decl);
        expr.semantCheck(sTable, cTable, curr_class);
        sTable.exitScope();
    }
}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    protected AbstractSymbol name;
    protected Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        if (name.equals(TreeConstants.self)) {
            cTable.semantError(curr_class.getFilename(), this).println("Cannot make assignment to self.");
        }

        expr.semantCheck(sTable, cTable, curr_class);

        AbstractSymbol symType = (AbstractSymbol) sTable.lookup(name);
        AbstractSymbol classType = (AbstractSymbol) cTable.attrLookup(curr_class.getName(), name);
        if (symType == null && classType == null) {
            cTable.semantError(curr_class.getFilename(), this).println("Can't find symbol "+name+" in assign.");
        }
        AbstractSymbol setType = null;
        if (symType != null) {
            setType = symType;
        } else if (classType != null) {
            setType = classType;
        } else {
            setType = TreeConstants.Object_;
        }
        if(!cTable.typeCheck(expr.get_type(), setType, curr_class)) {
                cTable.semantError(curr_class.getFilename(), this).println("Expression type: " + expr.get_type() + " does not match variable type: " + setType);
            }
        //assert expr.get_type is setType

        this.set_type(expr.get_type());
       
    }
}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        expr.semantCheck(sTable, cTable, curr_class);
        
        if (!cTable.typeCheck(expr.get_type(), type_name, curr_class)) {   //assert expr.get_type <= type_name
            cTable.semantError(curr_class.getFilename(), this).println("Static dispatch typeCheck failed.");
        }

        ArrayList<AbstractSymbol> argTypes = new ArrayList<AbstractSymbol>();
        for (Enumeration e = actual.getElements(); e.hasMoreElements(); ) {
            Expression expr = (Expression) e.nextElement();
            expr.semantCheck(sTable, cTable, curr_class);
            argTypes.add(expr.get_type());
        }
        //make sure class of expr has the method name, same number of args, and all types
        //must match
        AbstractSymbol returnType = cTable.toReturn(type_name, curr_class, name, argTypes);
	if (returnType == null) {
	    cTable.semantError(curr_class.getFilename(), this).println("Can't find method "+name+" in class in static dispatch.");
	    returnType = TreeConstants.Object_;
	}
        //if the method name has self_type as a return type, 
        //we set the return type = to expr.get_type
        if (returnType.equals(TreeConstants.SELF_TYPE)) {
            returnType = expr.get_type();
        }
        this.set_type(returnType);
    }
}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        expr.semantCheck(sTable, cTable, curr_class);
        
        ArrayList<AbstractSymbol> argTypes = new ArrayList<AbstractSymbol>();
        for (Enumeration e = actual.getElements(); e.hasMoreElements(); ) {
            Expression expr = (Expression) e.nextElement();
            expr.semantCheck(sTable, cTable, curr_class);
            argTypes.add(expr.get_type());
        }
        //make sure class of expr has the method name, same number of args, and all types
        //must match
        AbstractSymbol returnType = cTable.toReturn(expr.get_type(), curr_class, name, argTypes);
	if (returnType == null) {
	    cTable.semantError(curr_class.getFilename(), this).println("Can't find method "+name+" in class in dispatch.");
	    returnType = TreeConstants.Object_;
	}
        //if the method name has self_type as a return type, 
        //we set the return type = to expr.get_type
        if (returnType.equals(TreeConstants.SELF_TYPE)) {
            returnType = expr.get_type();
        }
        
        this.set_type(returnType);
    }
}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        pred.semantCheck(sTable, cTable, curr_class);
        if (!cTable.typeCheck(pred.get_type(), TreeConstants.Bool, curr_class)) {  //assert pred is a bool
            cTable.semantError(curr_class.getFilename(), this).println("Predicate in Conditional is not a bool but type "+pred.get_type()+".");
        }
        then_exp.semantCheck(sTable, cTable, curr_class);
        else_exp.semantCheck(sTable, cTable, curr_class);
        this.set_type(cTable.LeastUpperBound(then_exp.get_type(), else_exp.get_type(), curr_class));
    }

}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        pred.semantCheck(sTable, cTable, curr_class);       
        if (!cTable.typeCheck(pred.get_type(), TreeConstants.Bool, curr_class)) {  //assert pred is a bool
            cTable.semantError(curr_class.getFilename(), this).println("Predicate in Conditional is not a bool but type "+pred.get_type()+".");
        }
        body.semantCheck(sTable, cTable, curr_class);
        this.set_type(TreeConstants.Object_);
    }
}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        expr.semantCheck(sTable, cTable, curr_class);
        branch curr_branch = null;
        ArrayList<AbstractSymbol> typeList = new ArrayList<AbstractSymbol>();
        ArrayList<AbstractSymbol> exprTypes = new ArrayList<AbstractSymbol>();
        for (Enumeration e = cases.getElements(); e.hasMoreElements(); ) {
            curr_branch = (branch) e.nextElement();
            curr_branch.semantCheck(sTable, cTable, curr_class);
            if (typeList.contains(curr_branch.type_decl)) {
                cTable.semantError(curr_class.getFilename(), this).println("More than one of the same type in case: " + curr_branch.type_decl.toString());
            } else {
                typeList.add(curr_branch.type_decl);
            }
            exprTypes.add(curr_branch.expr.get_type());
        }
        if (curr_branch == null) {
            cTable.semantError(curr_class.getFilename(), this).println("No cases for typcase.");
            this.set_type(TreeConstants.No_type);
        } else {
            AbstractSymbol curr_type = typeList.get(0);
            for (int i = 1; i < typeList.size(); i++) {
                curr_type = cTable.LeastUpperBound(curr_type, typeList.get(i), curr_class);
            }
            this.set_type(curr_type);
            //lublublublublub
        }
    }

}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	    dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        sTable.enterScope();
        Expression expr = null; //possible error if not set to null
        for (Enumeration e = body.getElements(); e.hasMoreElements(); ){
            expr = (Expression) e.nextElement();
            expr.semantCheck(sTable, cTable, curr_class);
        }

        if (expr == null) {
            this.set_type(TreeConstants.No_type);
            cTable.semantError(curr_class.getFilename(), this).println("Block has no expressions.");
        } else {
            this.set_type(expr.get_type());
        }

        sTable.exitScope();
    }
}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        sTable.enterScope();
        sTable.addId(identifier, type_decl);
        
        if (identifier.equals(TreeConstants.self)) {
            cTable.semantError(curr_class.getFilename(), this).println("Cannot use self as an identifier in a let statement.");
        }

        AbstractSymbol type = type_decl;
        if (type.equals(TreeConstants.SELF_TYPE)) {
            type = curr_class.getName();
        }
        
        init.semantCheck(sTable, cTable, curr_class);
        body.semantCheck(sTable, cTable, curr_class);

        //checks if init exists, then make sure its type conforms with declared type, "type"
        if (!init.get_type().equals(TreeConstants.No_type)) {
            if (!cTable.typeCheck(init.get_type(), type_decl, curr_class)) {  //assert with init with "type"
                cTable.semantError(curr_class.getFilename(), this).println("Type declaration "+type_decl+" in let does not match init type "+init.get_type()".");
            }
        }
        
        this.set_type(body.get_type());

        sTable.exitScope();
    }
}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	    e1.dump_with_types(out, n + 2);
	    e2.dump_with_types(out, n + 2);
	    dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        e1.semantCheck(sTable, cTable, curr_class);
        e2.semantCheck(sTable, cTable, curr_class);

        //assert int
        if (!(cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) || 
                    !(cTable.typeCheck(e2.get_type(), TreeConstants.Int, curr_class))) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 "+e1.get_type()+" or E2 "+e2.get_type()+" is not of type int in plus.");
        }
        this.set_type(TreeConstants.Int);
    }
}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        e1.semantCheck(sTable, cTable, curr_class);
        e2.semantCheck(sTable, cTable, curr_class);

        //assert int
        if (!(cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) || 
                    !(cTable.typeCheck(e2.get_type(), TreeConstants.Int, curr_class))) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 or E2 is not of type int in sub.");
        }
        this.set_type(TreeConstants.Int);
    }
}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	    e1.dump_with_types(out, n + 2);
	    e2.dump_with_types(out, n + 2);
	    dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        e1.semantCheck(sTable, cTable, curr_class);
        e2.semantCheck(sTable, cTable, curr_class);

        //assert int
        if (!(cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) || 
                    !(cTable.typeCheck(e2.get_type(), TreeConstants.Int, curr_class))) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 or E2 is not of type int in mul.");
        }
        this.set_type(TreeConstants.Int);
    }

}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	    e1.dump_with_types(out, n + 2);
	    e2.dump_with_types(out, n + 2);
	    dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    e1.semantCheck(sTable, cTable, curr_class);
	    e2.semantCheck(sTable, cTable, curr_class);

	    //assert Int
        if (!(cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) || 
                    !(cTable.typeCheck(e2.get_type(), TreeConstants.Int, curr_class))) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 or E2 is not of type int in divide.");
        }
        this.set_type(TreeConstants.Int);
    }
}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    e1.semantCheck(sTable, cTable, curr_class);

	    //assert int
        if (!cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 is not of type int in Neg.");
        }
        this.set_type(TreeConstants.Int);
    }

}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	    e1.dump_with_types(out, n + 2);
	    e2.dump_with_types(out, n + 2);
	    dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    e1.semantCheck(sTable, cTable, curr_class);
	    e2.semantCheck(sTable, cTable, curr_class);

	    //assert?
        if (!(cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) || 
                    !(cTable.typeCheck(e2.get_type(), TreeConstants.Int, curr_class))) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 or E2 is not of type int in lt.");
        }
        
        this.set_type(TreeConstants.Bool);
    }
}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    e1.semantCheck(sTable, cTable, curr_class);
	    e2.semantCheck(sTable, cTable, curr_class);

	    //check equality types
        AbstractSymbol type1 = e1.get_type();
        AbstractSymbol type2 = e2.get_type();

        if ((type1.equals(TreeConstants.Int) || type1.equals(TreeConstants.Str) || type1.equals(TreeConstants.Bool)) ||
            (type2.equals(TreeConstants.Int) || type1.equals(TreeConstants.Str) || type2.equals(TreeConstants.Bool))) {
            if (!type1.equals(type2)) {
                cTable.semantError(curr_class.getFilename(), this).println("Equivalence error on different types");
            }
        }

        this.set_type(TreeConstants.Bool);
    }

}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    e1.semantCheck(sTable, cTable, curr_class);
	    e2.semantCheck(sTable, cTable, curr_class);

	    //check e1 and e2 inheritance are ints
        if (!(cTable.typeCheck(e1.get_type(), TreeConstants.Int, curr_class)) || 
                    !(cTable.typeCheck(e2.get_type(), TreeConstants.Int, curr_class))) {
            cTable.semantError(curr_class.getFilename(), this).println("E1 or E2 is not of type int in leq.");
        }
        this.set_type(TreeConstants.Bool);
    }
}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	    e1.semantCheck(sTable, cTable, curr_class);
        this.set_type(TreeConstants.Bool);
    }
}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        //do nothing
        this.set_type(TreeConstants.Int);
    }

}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	//do nothing
        this.set_type(TreeConstants.Bool);
    }

}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	//do nothing b/c its a constant
        this.set_type(TreeConstants.Str);
    }

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }
	
    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
	//do nothing
        this.set_type(type_name);
    }
}


/* Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        e1.semantCheck(sTable, cTable, curr_class);
	    this.set_type(TreeConstants.Bool);
    }

}


/* Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /* Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        //do nothing?
	//System.out.println("Inside of no_expr semant check.");
	this.set_type(TreeConstants.No_type);
    }
}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }
 
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }

    public void semantCheck(SymbolTable sTable, ClassTable cTable, class_c curr_class) {
        AbstractSymbol symType = (AbstractSymbol) sTable.lookup(name);
        AbstractSymbol attrType = cTable.attrLookup(curr_class.getName(), name);
	if (symType == null && attrType == null) {
            System.out.println(lineNumber+" "+name);
            System.out.println(curr_class.getName());
	    cTable.semantError(curr_class.getFilename(), this).println("Object undefined");
        }   
    	if (symType != null) {
	        this.set_type(symType);
	} else if (attrType != null) {
	        this.set_type(attrType);
	}
    }
}
