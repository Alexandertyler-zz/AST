/*
Copyright (c) 2000 The Regents of the University of California.
All rights reserved.

Permission to use, copy, modify, and distribute this software for any
purpose, without fee, and without written agreement is hereby granted,
provided that the above copyright notice and the following two
paragraphs appear in all copies of this software.

IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
*/

// This is a project skeleton file

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ArrayList;

/** This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.  */
class ClassTable {
    private int semantErrors;
    private PrintStream errorStream;
    private boolean firstRecurse;

    /* Here I define variables for classes so that I can access them 
     * across other files.
     */
    private class_c Object_class;
    private class_c IO_class;
    private class_c Int_class;
    private class_c Bool_class;
    private class_c Str_class;

    /*Classwide variables for our hashtable. */
    public Hashtable<AbstractSymbol, ArrayList<class_c>> parentChildTable;
    public Hashtable<AbstractSymbol, class_c> class_cTable;
    



    /** Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     * */
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// The following demonstrates how to create dummy parse trees to
	// refer to basic Cool classes.  There's no need for method
	// bodies -- these are already built into the runtime system.

	// IMPORTANT: The results of the following expressions are
	// stored in local variables.  You will want to do something
	// with those variables at the end of this method to make this
	// code meaningful.

	// The Object class has no parent class. Its methods are
	//        cool_abort() : Object    aborts the program
	//        type_name() : Str        returns a string representation 
	//                                 of class name
	//        copy() : SELF_TYPE       returns a copy of the object

	Object_class = 
	    new class_c(0, 
		       TreeConstants.Object_, 
		       TreeConstants.No_class,
		       new Features(0)
			   .appendElement(new method(0, 
					      TreeConstants.cool_abort, 
					      new Formals(0), 
					      TreeConstants.Object_, 
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.type_name,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.copy,
					      new Formals(0),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0))),
		       filename);
	
	// The IO class inherits from Object. Its methods are
	//        out_string(Str) : SELF_TYPE  writes a string to the output
	//        out_int(Int) : SELF_TYPE      "    an int    "  "     "
	//        in_string() : Str            reads a string from the input
	//        in_int() : Int                "   an int     "  "     "

	IO_class = 
	    new class_c(0,
		       TreeConstants.IO,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new method(0,
					      TreeConstants.out_string,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Str)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.out_int,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_string,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_int,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0))),
		       filename);

	// The Int class has no methods and only a single attribute, the
	// "val" for the integer.

	Int_class = 
	    new class_c(0,
		       TreeConstants.Int,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// Bool also has only the "val" slot.
	Bool_class = 
	    new class_c(0,
		       TreeConstants.Bool,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	// The class Str has a number of slots and operations:
	//       val                              the length of the string
	//       str_field                        the string itself
	//       length() : Int                   returns length of the string
	//       concat(arg: Str) : Str           performs string concatenation
	//       substr(arg: Int, arg2: Int): Str substring selection

	Str_class =
	    new class_c(0,
		       TreeConstants.Str,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.Int,
					    new no_expr(0)))
			   .appendElement(new attr(0,
					    TreeConstants.str_field,
					    TreeConstants.prim_slot,
					    new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.length,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.concat,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg, 
								     TreeConstants.Str)),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.substr,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int))
						  .appendElement(new formalc(0,
								     TreeConstants.arg2,
								     TreeConstants.Int)),
					      TreeConstants.Str,
					      new no_expr(0))),
		       filename);

	/* Do somethind with Object_class, IO_class, Int_class,
           Bool_class, and Str_class here */

	// NOT TO BE INCLUDED IN SKELETON
	
	/*This adds our basic class types to the class_c lookup table
	*/	

	class_cTable.put(Object_class.getName(), Object_class);
	class_cTable.put(IO_class.getName(), IO_class);
	class_cTable.put(Int_class.getName(), Int_class);
	class_cTable.put(Bool_class.getName(), Bool_class);
	class_cTable.put(Str_class.getName(), Str_class);

	//Object_class.dump_with_types(System.err, 0);
	//IO_class.dump_with_types(System.err, 0);
	//Int_class.dump_with_types(System.err, 0);
	//Bool_class.dump_with_types(System.err, 0);
	//Str_class.dump_with_types(System.err, 0);
    }
	


    public ClassTable(Classes cls) {

		semantErrors = 0;
		errorStream = System.err;

		parentChildTable = new Hashtable<AbstractSymbol, ArrayList<class_c>>();
		class_cTable = new Hashtable<AbstractSymbol, class_c>();

        /* Our Variables:
         *      curr_class 
         *      parent
         *      parentChildTable
         *      child_nodes
         */

    	installBasicClasses();
    	for(Enumeration e = cls.getElements(); e.hasMoreElements();) {

            class_c curr_class = (class_c)e.nextElement();
	    class_cTable.put(curr_class.getName(), curr_class);

            AbstractSymbol parent = curr_class.getParent();
		
            /*Add class parent to hashtable and child to list. */
            if (parent!=null) {
        	if (parentChildTable.containsKey(parent)) {
                    parentChildTable.get(parent).add(curr_class);	
        	} else {
        	    ArrayList<class_c> child_nodes = new ArrayList<class_c>();
		    child_nodes.add(curr_class);
        	    parentChildTable.put(parent, child_nodes);
        	}        		
            }
            /*check curr_class types*/
            if (check_uninheritable(curr_class)) {
            	semantError(curr_class).println("Class " + curr_class.getName().toString()
                	+ " cannot inherit class " + curr_class.getParent().getString());
            }

	}
	/*At this point, all of our classes and parents should be stored.
	 * We need to check for cycles and other misc things
	 * such as a main class.
	 */
	//checkForCycles(class_cTable, parentChildTable);
	for (Enumeration e = class_cTable.elements(); e.hasMoreElements(); ) {
	    class_c curr_class = (class_c) e.nextElement();
	    firstRecurse = true;
	    visitNode(curr_class);
	    
	}	
	
	if (!class_cTable.containsKey(TreeConstants.Main)) {
	    semantError().println("No main class found.");
	}	
	

    }

    public void visitNode(class_c curr_class) {
	//System.out.println(curr_class.getName().getString());
	if (firstRecurse) {
    	    curr_class.visit();
	    if (parentChildTable.containsKey(curr_class.getName())) {
	        for (class_c child : parentChildTable.get(curr_class.getName())) {
		    firstRecurse = false;
		    visitNode(child);
	        }
            }
	    curr_class.resetVisit();
	} else {
	    if (curr_class.checkVisit()) {
		semantError().println("Detected cyclic inheritance tree at class " + curr_class.getName().getString());
	    } else {
	            if (parentChildTable.containsKey(curr_class.getName())) {
			for (class_c child : parentChildTable.get(curr_class.getName())) {
		        visitNode(child);
		    }
 	        }
	    }
        }
    }


    /*This is used to check the basic uninheritable classes,
     * int, string, bool
     */
    public boolean check_uninheritable(class_c curr_class) {
        AbstractSymbol parent = curr_class.getParent();
        if (parent.equals(Int_class.getName()) || parent.equals(Str_class.getName())
                || parent.equals(Bool_class.getName())) {
            return true;
	}
	return false;
    }

    public Formals formalLookup(AbstractSymbol className, AbstractSymbol myMethod) {
        class_c curr_class = class_cTable.get(className);
        if (curr_class == null) {
            semantError().println("Class passed into formalLookup is not defined.");
            return null;
        }
        Features features = curr_class.features;
	    for (Enumeration e = features.getElements(); e.hasMoreElements(); ) {
	        Feature curr_feat = (Feature) e.nextElement();
            if ((!(curr_feat instanceof attr)) && (curr_feat != null)) {
                method curr_method = (method) curr_feat;
                if (curr_method.name.equals(myMethod)) {
                    return curr_method.formals;
                }
            }
        }
   
        AbstractSymbol parent = curr_class.getParent();
        if (parent == null || parent.equals(TreeConstants.No_class)) {
            return null;
        } else {
            return formalLookup(parent, myMethod);
        }
    }

    public AbstractSymbol attrLookup(AbstractSymbol className, AbstractSymbol attribute) {
	    class_c curr_class = class_cTable.get(className);
        if (curr_class == null) {
            semantError().println("Class " + className.toString() + " passed into attrLookup is not defined.");
	    return null;
        }

        Features features = curr_class.features;
	    for (Enumeration e = features.getElements(); e.hasMoreElements(); ) {
	        Feature curr_feat = (Feature) e.nextElement();
            if ((!(curr_feat instanceof method)) && (curr_feat != null)) {
                attr curr_attr = (attr) curr_feat;
                if (curr_attr.name.equals(attribute)) {
                    return curr_attr.type_decl;
                }
            }
        }
        AbstractSymbol parent = curr_class.getParent();
        if (parent == null || parent.equals(TreeConstants.No_class)) {
            return null;
        } else {
            return attrLookup(parent, attribute);
        }
    }

    public boolean typeCheck(AbstractSymbol type1, AbstractSymbol type2, class_c curr_class) {
        
	if (type1 == null || type2 == null || (class_cTable.get(type1) == null && !(type1.equals(TreeConstants.SELF_TYPE)))
		 || ((class_cTable.get(type2) == null) && !(type2.equals(TreeConstants.SELF_TYPE)))) {
            //System.out.println("Class_cTable type1 is " + class_cTable.get(type1) + " and type1 is " + type1);
	    return false;
        }

        if (type1.equals(type2)) {
            return true;
        }

        if (type1.equals(TreeConstants.SELF_TYPE)) {
            type1 = curr_class.getName();
            if (type1.equals(type2)) {
                return true;
            }
        }

	//System.out.println(type1);
	//System.out.println(class_cTable.get(type1));	
        while (true) {
            AbstractSymbol parent = (class_cTable.get(type1)).getParent();
            //System.out.println("HEY"+parent);
            if (parent == null || parent == TreeConstants.No_class) {
                return false;
            } else if (parent.equals(type2)) {
                return true;
            } else {
                type1 = parent;
            }
	    //return false;
        }
	
    } 

    public AbstractSymbol LeastUpperBound(AbstractSymbol type1, AbstractSymbol type2, class_c curr_class) {
        if (type1.equals(type2)) {
            return type1;
        }

        if (type1.equals(TreeConstants.SELF_TYPE)) {
            type1 = curr_class.getName();
        }
        if (type2.equals(TreeConstants.SELF_TYPE)) {
            type2 = curr_class.getName();
        }

        //add parents and current class to a list
        ArrayList<AbstractSymbol> type1Family = new ArrayList<AbstractSymbol>();
        ArrayList<AbstractSymbol> type2Family = new ArrayList<AbstractSymbol>();
        AbstractSymbol type1Parent = class_cTable.get(type1).getParent();
        AbstractSymbol type2Parent = class_cTable.get(type2).getParent();
        
        type1Family.add(type1);
        type2Family.add(type2);
        
        while (type1Parent != null && class_cTable.get(type1Parent) != null) {
            type1Family.add(type1Parent);
            type1Parent = class_cTable.get(type1Parent).getParent();
        }
        while (type2Parent != null && class_cTable.get(type2Parent) != null) {
            type2Family.add(type2Parent);
            type2Parent = class_cTable.get(type2Parent).getParent();
        }
        
        int type1Index = type1Family.size() -1;
        int type2Index = type2Family.size() -1;
        AbstractSymbol finalType = null;
        while (type1Index >= 0 && type2Index >= 0) {
            if (type1Family.get(type1Index).equals(type2Family.get(type2Index))) {
                finalType = type1Family.get(type1Index);
            } else {
                return finalType;
            }
            type1Index--;
            type2Index--;
        }
        return null;
    }

    public AbstractSymbol toReturn(AbstractSymbol specifiedClassName, class_c curr_class, AbstractSymbol methodName, ArrayList<AbstractSymbol> argTypes) {
	if (specifiedClassName.equals(TreeConstants.SELF_TYPE)) {
	    specifiedClassName = curr_class.getName();
	}
	
        class_c specifiedClass = class_cTable.get(specifiedClassName);
        if (specifiedClass == null) {
            semantError().println("Specified class in toReturn is not in class_cTable.");
	}
	for (Enumeration e = (specifiedClass.features).getElements(); e.hasMoreElements(); ) {
	    Feature curr_feat = (Feature) e.nextElement();
	    if (!(curr_feat instanceof attr) && curr_feat != null) {
		method curr_method = (method) curr_feat;
		if (curr_method.name.equals(methodName)) {
		    if (!(argTypes.size() == curr_method.formals.getLength())) {
		        semantError().println("Dispatch arg length doesn't match definition arg length.");
			return curr_method.return_type;
		    }
		    int iter = 0;
		    for (Enumeration f = (curr_method.formals).getElements(); f.hasMoreElements(); ) {
			formalc curr_formal = (formalc) f.nextElement();
			if (!typeCheck(argTypes.get(iter), curr_formal.type_decl, curr_class)) {
			    semantError().println("Dispatch types don't match.");
			    break;
			}
			iter++;
		    }
		    return curr_method.return_type;
		}
	    }
	}
        AbstractSymbol parent = specifiedClass.getParent();
        if (parent == null || parent.equals(TreeConstants.No_class)) {
            return null;
        } else {
            //System.out.println(parent);
            return toReturn(parent, curr_class, methodName, argTypes);
        }
    }


    /** Prints line number and file name of the given class.
     *
     * Also increments semantic error count.
     *
     * @param c the class
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(class_c c) {
	return semantError(c.getFilename(), c);
    }

    /** Prints the file name and the line number of the given tree node.
     *
     * Also increments semantic error count.
     *
     * @param filename the file name
     * @param t the tree node
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
	errorStream.print(filename + ":" + t.getLineNumber() + ": ");
	return semantError();
    }

    /** Increments semantic error count and returns the print stream for
     * error messages.
     *
     * @return a print stream to which the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError() {
	semantErrors++;
	return errorStream;
    }

    /** Returns true if there are any static semantic errors. */
    public boolean errors() {
	return semantErrors != 0;
    }

    // NOT TO BE INCLUDED IN SKELETON
    public static void main(String[] args) {
	new ClassTable(null).installBasicClasses();
    }

}
			  


