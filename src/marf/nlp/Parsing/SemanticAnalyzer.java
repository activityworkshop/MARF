package marf.nlp.Parsing;

import java.util.Stack;
import java.util.Vector;


/**
 * <p>Semantic Analyzer.
 * TODO: complete implementation.
 * </p>
 *
 * @author Serguei Mokhov
 */
public class SemanticAnalyzer
{
	/**
	 * A collection of semantic errors found.
	 */
	protected Vector oSemanticErrors = new Vector();
	
	/**
	 * Semantic stack for semantic checks.
	 */
	protected Stack oSemanticStack = new Stack();
	
	/**
	 * Internal reference to the symbol table for
	 * type and definition checks.
	 */
	public SymbolTable oSymTab = null;
	
	/**
	 * Not implemented. Checks whether a given identifier was defined.
	 * @param pstrScopeSymbol
	 * @param pstrSymbolToCheck
	 * @return <code>false</code>
	 */
	public boolean isDefinedID(String pstrScopeSymbol, String pstrSymbolToCheck)
	{
		return false;
	}
}

// EOF
