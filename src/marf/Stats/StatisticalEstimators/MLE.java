package marf.Stats.StatisticalEstimators;

import marf.Stats.Observation;
import marf.Stats.StatisticalEstimators.Smoothing.AddDelta;

import java.util.Vector;

/**
 * <p>Maximum-Likelihood Statistical Estimator (MLE).</p>
 *
 * @author Serguei Mokhov
 */
public class MLE
extends AddDelta
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -2490823052719390594L;

	/**
	 * Default construct with the call <code>super(0)</code>.
	 * @see AddDelta
	 */
	public MLE()
	{
		// delta = 0 for MLE
		super(0);
	}

	/**
	 * Not implemented. TODO: implement.
	 * @param poEvent
	 * @return 0
	 */
	public double p(Observation poEvent)
	{
		return 0;
	}

	/**
	 * Not implemented. TODO: implement.
	 * @param poEventList
	 * @return 0
	 */
	public double p(Vector poEventList)
	{
		return 0;
	}
}

// EOF
