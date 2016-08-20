package marf.FeatureExtraction;

import marf.util.MARFException;


/**
 * <p>Class FeatureExtractionException.</p>
 *
 * @author Serguei Mokhov
 */
public class FeatureExtractionException
extends MARFException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -2325585432639761782L;

	/**
	 * Encapsulation of another Exception object.
	 * @param poException Exception to wrap around
	 */
	public FeatureExtractionException(Exception poException)
	{
		super(poException);
	}

	/**
	 * Encapsulation of another Exception object and a new message.
	 * @param pstrMessage additional information to add
	 * @param poException Exception to wrap around
	 */
	public FeatureExtractionException(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}

	/**
	 * Generic exception.
	 * @param pstrMessage Error message string
	 */
	public FeatureExtractionException(String pstrMessage)
	{
		super(pstrMessage);
	}
}

// EOF
