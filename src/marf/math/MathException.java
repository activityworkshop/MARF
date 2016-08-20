package marf.math;

import marf.util.MARFException;

/**
 * <p>Indicates exceptional situations in MARF's math.</p>
 *
 * @author Serguei Mokhov
 */
public class MathException
extends MARFException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -5874565130017306067L;

	/**
	 * Encapsulation of another Exception object.
	 * @param poException Exception to wrap around
	 */
	public MathException(Exception poException)
	{
		super(poException);
	}

	/**
	 * Encapsulation of another Exception object and a new message.
	 * @param pstrMessage additional information to add
	 * @param poException Exception to wrap around
	 */
	public MathException(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}

	/**
	 * Generic exception.
	 * @param pstrMessage Error message string
	 */
	public MathException(String pstrMessage)
	{
		super(pstrMessage);
	}
}

// EOF
