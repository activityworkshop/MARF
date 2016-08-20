package marf.nlp;

import marf.util.MARFException;


/**
 * <p>NLPException is a root of exception hierarchy in the NLP
 * exception processing.</p>
 *
 * @author Serguei Mokhov
 */
public class NLPException
extends MARFException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -6738475142356489003L;

	/**
	 * Default NLP exception.
	 */
	public NLPException()
	{
		super();
	}

	/**
	 * Generic exception.
	 * @param pstrMessage Error message string
	 */
	public NLPException(String pstrMessage)
	{
		super(pstrMessage);
	}

	/**
	 * Exception wrapper constructor.
	 * @param poException Exception object to wrap
	 */
	public NLPException(Exception poException)
	{
		super(poException);
	}

	/**
	 * Accepts custom message and the exception object.
	 * @param pstrMessage custom error message
	 * @param poException exception happened
	 */
	public NLPException(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}
}

// EOF
