package marf.nlp.Parsing;

import marf.nlp.NLPException;


/**
 * <p>Generic Compiler Error.
 * Normally subclassed to differentiate
 * between various error types like
 * lexical, syntax, semantic and such.</p>
 *
 * @author Serguei Mokhov
 */
public class CompilerError
extends NLPException
{
	/**
	 * Error code signifying "no error".
	 */
	public static final int OK = 0;

	/**
	 * Error code of the last error occurred.
	 */
	protected int iCurrentErrorCode = OK;

	/**
	 * Line number where the given error occurred.
	 * The default value of (-1) means it was not yet initialized.
	 */
	protected int iLineNo = -1;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -6425511198047596019L;

	/**
	 * Default Constructor.
	 */
	public CompilerError()
	{
		super("CompilerError");
	}

	/**
	 * Wraps the parameter exception into CompilerError.
	 * @param poException the exception object to wrap
	 */
	public CompilerError(Exception poException)
	{
		super(poException);
	}

	/**
	 * Creates a CompilerError with a custom error message string. 
	 * @param pstrMessage the custom error message
	 */
	public CompilerError(String pstrMessage)
	{
		super(pstrMessage);
	}

	/**
	 * Wraps the parameter exception into CompilerError
	 * with a custom error message string.
	 * @param pstrMessage the custom error message
	 * @param poException the exception object to wrap
	 */
	public CompilerError(String pstrMessage, Exception poException)
	{
		super(pstrMessage, poException);
	}

	/**
	 * @return the current value of the iCurrentErrorCode property
	 */
	public int getCurrentErrorCode()
	{
		return this.iCurrentErrorCode;
	}

	/**
	 * @return the current value of the iLineNo property
	 */
	public int getLineNo()
	{
		return this.iLineNo;
	}
}
