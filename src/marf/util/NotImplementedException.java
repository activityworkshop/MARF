package marf.util;


/**
 * <p>This class extends RuntimeException for MARF unimplemented parts.</p>
 *
 * @author Serguei A. Mokhov
 */
public class NotImplementedException
extends MARFRuntimeException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -52979270946708931L;

	/**
     * Generic exception.
	 */
	public NotImplementedException()
	{
		super("not implemented");
	}

	/**
	 * Generic exception.
	 * @param pstrMessage Error message string
	 */
	public NotImplementedException(String pstrMessage)
	{
		super("Not implemented: " + pstrMessage);
	}

	/**
	 * Generates Class.Method exception message.
	 * @param poObject object to query for class name that has something unimplemented
	 * @param pstrMethod method name that is not implemented
	 */
	public NotImplementedException(final Object poObject, String pstrMethod)
	{
		this(poObject.getClass().getName() + "." + pstrMethod);
	}
}

// EOF
