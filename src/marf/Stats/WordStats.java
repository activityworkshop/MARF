package marf.Stats;

/**
 * <p>A pure container for statistics of a word --- no business logic.</p>
 *
 * @author Serguei Mokhov
 */
public class WordStats
extends StatisticalObject
{
	/**
	 * Spelling of the word.
	 */
	private String strLexeme = "";

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 1553592340644601516L;

	/**
	 * Constructs the default object with an empty lexeme.
	 */
	public WordStats()
	{
		super();
	}

	/**
	 * Constructs object with the desired frequency and the lexeme.
	 * @param piFrequency the frequency
	 * @param pstrLexeme the lexeme
	 */
	public WordStats(int piFrequency, String pstrLexeme)
	{
		super(piFrequency);
		this.strLexeme = pstrLexeme;
	}

	/**
	 * Constructs object with the desired frequency.
	 * @param piFrequency the frequency
	 */
	public WordStats(int piFrequency)
	{
		super(piFrequency);
	}

	/**
	 * Copy-constructor.
	 * @param poWordStats WordStats object to copy properties from
	 */
	public WordStats(final WordStats poWordStats)
	{
		super(poWordStats);
		this.strLexeme = new String(poWordStats.strLexeme);
	}

	/**
	 * Retrieves the lexeme of the object.
	 * @return the current lexeme
	 */
	public final String getLexeme()
	{
		return this.strLexeme;
	}

	/**
	 * Reports lexeme, frequency, and rank of an occurrence of a word.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		 return new StringBuffer()
			.append("Lexeme: ").append(this.strLexeme).append(", ")
			.append(super.toString())
			.toString();
	}

	/**
	 * Implements Cloneable interface for the WordStats object.
	 * @see java.lang.Object#clone()
	 */
	public Object clone()
	{
		return new WordStats(this);
	}
}

// EOF
