package marf.Stats.StatisticalEstimators.Smoothing;


/**
 * <p>Generic Smoothing Interface. All smoothing statistical
 * estimators should extend the <code>Smoothing</code> class.
 * If they can't, they must implement this interface.</p>
 *
 * @author Serguei Mokhov
 * @see Smoothing
 */
public interface ISmoothing
{
	/**
	 * General smoothing routine.
	 * @return <code>true</code> if any smoothing took place
	 * and underlying data was altered
	 */
	boolean smooth();
}

// EOF
