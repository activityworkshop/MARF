package marf.Stats.StatisticalEstimators;

import marf.Stats.Ngram;


/**
 * <p>GLI Statistical Estimator.
 * TODO: complete.
 * </p>
 *
 * @author Serguei Mokhov
 */
public class GLI
extends StatisticalEstimator
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -2834387217504589395L;

	/**
	 * Default constructor as in parent.
	 */
	public GLI()
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
