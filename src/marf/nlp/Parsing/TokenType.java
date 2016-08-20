package marf.nlp.Parsing;

import java.io.Serializable;
import java.util.Hashtable;


/**
 * <p>Generic Token Type.
 * Can be subclassed for more specific token types.</p>
 *
 * @author Serguei Mokhov
 */
public class TokenType
implements Serializable
{
	/**
	 * Identifier token type.
	 */
	public static final int ID = -1;

	/**
	 * Numerical token type.
	 */
	public static final int NUM = -2;

	/**
	 * Operator token type.
	 */
	public static final int OPERATOR = -3;

	/**
	 * Punctuation token type.
	 */
	public static final int PUNCT = -4;

	/**
	 * Bracket or parenthesis token type.
	 */
	public static final int BRACKET = -5;

	/**
	 * Identified keyword token type. A more precise set of IDs.
	 */
	public static final int KEYWORD = -6;

	/**
	 * Unknown token type.
	 */
	public static final int UNKNOWN = -7;

	/**
	 * End of File.
	 * Not really a token type, but an indicator
	 * that the end of the source file has been reached
	 */
	public static final int EOF = -8;

	/**
	 * This indicates an error in the token.
	 */
	public static final int ERROR = -9;

	/**
	 * This indicates we have to backtrack one token
	 * and don't do any extra processing.
	 */
	public static final int BACKTRACK = -10;

	/**
	 * Current token type.
	 */
	protected int iType = UNKNOWN;

	/**
	 * Hash representing literal values of the
	 * above tokens (for output, for example).
	 */
	public static Hashtable<Integer, String> soTokenTypes = new Hashtable<Integer, String>();

	static
	{
		// Init token types hashtable
		soTokenTypes.put(new Integer(ID),       new String("ID"));
		soTokenTypes.put(new Integer(NUM),      new String("NUMBER"));
		soTokenTypes.put(new Integer(OPERATOR), new String("OPERATOR"));
		soTokenTypes.put(new Integer(PUNCT),    new String("PUNCTUATION"));
		soTokenTypes.put(new Integer(BRACKET),  new String("BRACKET"));
		soTokenTypes.put(new Integer(KEYWORD),  new String("KEYWORD"));
		soTokenTypes.put(new Integer(UNKNOWN),  new String("UNKNOWN"));
		soTokenTypes.put(new Integer(EOF),      new String("EOF"));
		soTokenTypes.put(new Integer(ERROR),    new String("ERROR"));
	}

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 8903691871078901508L;

	/**
	 * Default Constructor.
	 */
	public TokenType()
	{
	}

	/**
	 * Allows querying for the current token type.
	 * @return the current token type
	 * @since 0.3.0.5
	 */
	public int getType()
	{
		return this.iType;
	}

	/**
	 * Allows setting the current token type.
	 * @param piType the token type
	 * @since 0.3.0.5
	 */
	public void setType(int piType)
	{
		this.iType = piType;
	}
}

// EOF
