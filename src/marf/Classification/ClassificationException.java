package marf.Classification;

import marf.util.MARFException;


/**
 * <p>Class ClassificationException indicates an error
 * during classification process.</p>
 *
 * @author Serguei Mokhov
 */
public class ClassificationException
extends MARFException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -1088263414931478219L;

	/**
	 * Constructs a default classification exception with the message
	 * the same as the class name.
	 * @since 0.3.0.6
	 */
	public ClassificationException()
	{
		super(ClassificationException.class.getName());
	}

	/**
	 * Generic exception.
	 * @param pstrMessage Error message string
	 */
	public ClassificationException(String pstrMessage)
	{
		super(pstrMessage);
	}

	/**
	 * Replicates parent's constructor.
	 * @param poException Exception object to encapsulate
	 */
	public ClassificationException(Exception poException)
	{
		super(poException);
	}

	/**
	 * Replicates parent's constructor.
	 * @param pstrMessage Error message string
	 * @param poException Exception object to encapsulate
	 */
	public ClassificationException(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}
}

// EOF
