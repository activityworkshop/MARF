package marf.nlp.Parsing.GrammarCompiler;

/**
 * <p>Probabilistic Grammar Token Type.</p>
 *
 * @author Serguei Mokhov
 */
public class ProbabilisticGrammarTokenType
extends GrammarTokenType
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 2537398679757478875L;

    /**
     * Probability token.
     */
    public static final int PROBABILITY  = 56;

	/**
	 * Dictionary word (smth in between an identifier and a keyword).
	 */
	public static final int DICT_WORD  = 57;

    /**
     * Constructor.
     */
    public ProbabilisticGrammarTokenType()
    {
        super();

        soTokenTypes.put(new Integer(PROBABILITY), new String("PROBABILITY"));
		soTokenSubTypes.put(new Integer(DICT_WORD),  new String("DICT_WORD"));
	}
}

// EOF
