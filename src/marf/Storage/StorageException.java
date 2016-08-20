package marf.Storage;

import marf.util.MARFException;

/**
 * <p>Class StorageException indicates
 * a serialization/storage-related error.</p>
 *
 * @author Serguei Mokhov
 */
public class StorageException
extends MARFException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 8148028971974635121L;


	/**
	 * Generic exception.
	 * @param pstrMessage Error message string
	 */
	public StorageException(String pstrMessage)
	{
		super(pstrMessage);
	}

	/**
	 * Exception wrapper constructor.
	 * @param poException Exception object to wrap
	 */
	public StorageException(Exception poException)
	{
		super(poException);
	}

	/**
	 * Default parameterless storage error. 
	 */
	public StorageException()
	{
		super();
	}

	/**
	 * An storage exception with a message and wrapped exception object.
	 * @param pstrMessage the desired custom message
	 * @param poException the wrapped exception object
	 */
	public StorageException(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}
}

// EOF
