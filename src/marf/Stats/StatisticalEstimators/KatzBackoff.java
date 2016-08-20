package marf.Stats.StatisticalEstimators;

import marf.Stats.Ngram;

/**
 * <p>Katz Backoff Statistical Estimator.
 * TODO: complete.</p>
 *
 * @author Serguei Mokhov
 */
public class KatzBackoff
extends StatisticalEstimator
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 7089278009894659434L;

	/**
	 * Default constructor as in parent.
	 */
	public KatzBackoff()
	{
		super();
	}

	/**
	 * Not implemented.
	 * @param poNgram
	 * @return 0.0
	 */
	public double p(Ngram poNgram)
	{
		return 0.0;
	}

	/**
	 * Not implemented.
	 * @see marf.Stats.StatisticalEstimators.IStatisticalEstimator#train()
	 */
	public boolean train()
	{
		return false;
	}
}

// EOF
