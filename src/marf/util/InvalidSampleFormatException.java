package marf.util;


/**
 * <p>Class InvalidSampleFormatException typically signals
 * a mismatch of a loader and file being loader or sample type
 * and its data.</p>
 * 
 * @author Serguei Mokhov
 */
public class InvalidSampleFormatException
extends MARFException
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -4262507209933509360L;

	/**
	 * Exception for specific sample format.
	 * @param piFormat Format number, caused the exception to be thrown
	 */
	public InvalidSampleFormatException(int piFormat)
	{
		super("Invalid sample file format: " + piFormat);
	}

	/**
	 * Generic exception.
	 * @param pstrMsg error message only
	 */
	public InvalidSampleFormatException(String pstrMsg)
	{
		super(pstrMsg);
	}

	/**
	 * Default InvalidSampleFormat exception with empty message. 
	 * @since 0.3.0.5
	 */
	public InvalidSampleFormatException()
	{
		super();
	}

	/**
	 * InvalidSampleFormat exception with wrapped exception of another type.
	 * @param poException the exception to wrap
	 * @since 0.3.0.5
	 */
	public InvalidSampleFormatException(Exception poException)
	{
		super(poException);
	}

	/**
	 * InvalidSampleFormat exception with wrapped exception of another type
	 * and a customized error message.
	 *
	 * @param pstrMessage the customized message
	 * @param poException the exception to wrap
	 * @since 0.3.0.5
	 */
	public InvalidSampleFormatException(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}
}

// EOF
