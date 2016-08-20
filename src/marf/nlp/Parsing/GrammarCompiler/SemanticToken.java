package marf.nlp.Parsing.GrammarCompiler;

import marf.nlp.Parsing.Token;


/**
 * <p>Class SemanticToken, for Semantic Analysis.</p>
 *
 * @author Serguei Mokhov
 */
public class SemanticToken
extends GrammarElement
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 2945717735922665260L;

	/**
	 * Token-based constructor.
	 * @param poToken the token
	 */
	public SemanticToken(Token poToken)
	{
		super(poToken, 0);
	}

	/**
	 * Token- and ID-based constructor.
	 * @param poToken the token
	 * @param piID token ID
	 */
	public SemanticToken(Token poToken, int piID)
	{
		super(poToken, piID);
	}

	/**
	 * Name- and ID-based constructor.
	 * @param pstrName the name
	 * @param piID token ID
	 */
	public SemanticToken(String pstrName, int piID)
	{
		super(pstrName, piID);
	}

	/**
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarElement#isNonTerminal()
	 * @return <code>false</code>
	 */
	public boolean isNonTerminal()
	{
		return false;
	}

	/**
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarElement#isTerminal()
	 * @return <code>false</code>
	 */
	public boolean isTerminal()
	{
		return false;
	}
}

// EOF
