package marf.Stats.StatisticalEstimators.Smoothing;


/**
 * <p>Add-One Smoothing Estimator.</p>
 *
 * @author Serguei Mokhov
 */
public class AddOne
extends AddDelta
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 3966754485295753364L;

	/**
	 * Merely calls <code>super(1)</code>.
	 * @see AddDelta
	 */
	public AddOne()
	{
		super(1);
	}
}

// EOF
