package marf.nlp.Parsing.GrammarCompiler;


/**
 * <p>Probabilistic Rule encapsulates a rule of a
 * probabilistic grammar, which is amended with probabilities.</p>
 *
 * @author Serguei Mokhov
 */
public class ProbabilisticRule
extends Rule
{
	/**
	 * Probability of the rule.
	 */
	protected double dProbability = 0.0;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 1346265103950170148L;

	/**
	 * Constructor with the LHS non-terminal set.
	 * @param poLHS
	 */
	public ProbabilisticRule(NonTerminal poLHS)
	{
		super(poLHS);
	}

	/**
	 * Sets probability of the rule.
	 * @param pdProbability the desired probability
	 */
	public final void setProbability(final double pdProbability)
	{
		this.dProbability = pdProbability;
	}

	/**
	 * Retrieves probability of the rule.
	 * @return current probability value
	 */
	public final double getProbability()
	{
		return this.dProbability;
	}
}

// EOF
