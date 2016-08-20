package marf.Stats.StatisticalEstimators;

import marf.Stats.Ngram;

/**
 * <p>SLI Statistical Estimator.
 * TODO: complete.</p>
 *
 * @author Serguei Mokhov
 */
public class SLI
extends StatisticalEstimator
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -4349244415620171096L;

	/**
	 * Default constructor as in parent.
	 */
	public SLI()
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
