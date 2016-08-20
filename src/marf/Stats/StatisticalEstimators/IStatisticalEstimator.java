package marf.Stats.StatisticalEstimators;

import marf.Stats.ProbabilityTable;
import marf.nlp.util.NLPStreamTokenizer;


/**
 * <p>Represents Statistical Estimator interface.
 * If concrete statistical estimators cannot inherit
 * from the generic <code>StatisticalEstimator</code> class
 * (which provides most generic implementation), they must
 * implement this interface.</p>
 *
 * @author Serguei Mokhov
 */
public interface IStatisticalEstimator
{
	/**
	 * Classification via calculation of a probability <i>p</i>.
	 * @return calculated probability value
	 */
	double p();

	/**
	 * Every estimator needs to implement its specific training method.
	 * @return <code>true</code> if training was successful
	 */
	boolean train();

	/**
	 * Sets desired stream tokenizer.
	 * @param poStreamTokenizer NLPStreamTokenizer or a derivative to use for tokens
	 * @see marf.nlp.util.NLPStreamTokenizer
	 */
	void setStreamTokenizer(NLPStreamTokenizer poStreamTokenizer);

	/**
	 * Retrieves current stream tokenizer.
	 * @return the stream tokenizer being used
	 */
	NLPStreamTokenizer getStreamTokenizer();

	/**
	 * Retrieves current probabilities table.
	 * @return probabilities table being used
	 * @see ProbabilityTable
	 */
	ProbabilityTable getProbabilityTable();

	/**
	 * Allows alteration of the current language being processed.
	 * @param pstrLang desired language
	 */
	void setLanguage(String pstrLang);

	/**
	 * Retrieves current language.
	 * @return language name of language being processed
	 */
	String getLanguage();
}

// EOF
