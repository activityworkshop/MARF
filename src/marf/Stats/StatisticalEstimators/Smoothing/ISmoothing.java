package marf.Stats.StatisticalEstimators.Smoothing;


/**
 * <p>Generic Smoothing Interface. All smoothing statistical
 * estimators should extend the <code>Smoothing</code> class.
 * If they can't, they must implement this interface.
 * </p>
 *
 * $Id: ISmoothing.java,v 1.3 2007/12/18 21:57:15 mokhov Exp $
 *
 * @author Serguei Mokhov
 * @version $Revision: 1.3 $
 * @since 0.3.0.3
 * @see Smoothing
 */
public interface ISmoothing
{
	/**
	 * Interface source code revision.
	 */
	String MARF_INTERFACE_CODE_REVISION = "$Revision: 1.3 $";

	/**
	 * General smoothing routine.
	 * @return <code>true</code> if any smoothing took place
	 * and underlying data was altered
	 */
	boolean smooth();
}

// EOF
