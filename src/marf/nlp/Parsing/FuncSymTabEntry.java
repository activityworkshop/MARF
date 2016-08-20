package marf.nlp.Parsing;

import java.util.Vector;


/**
 * <p>FuncSymTabEntry represents a symbol table entry
 * for function definition.</p>
 *
 * @author Serguei Mokhov
 */
public class FuncSymTabEntry
extends SymTabEntry
{
	/**
	 * Return Value Type.
	 */
	protected SymDataType oRetValType = null;

	/**
	 * Parameter List.
	 * Parameters are VarSymTabEntries.
	 */
	protected Vector<VarSymTabEntry> oParams = null;

	/**
	 * Constructor.
	 */
	public FuncSymTabEntry()
	{
		this.oParams = new Vector<VarSymTabEntry>();
	}

	/**
	 * Add a new parameter.
	 * @param poParam a parameter to add
	 */
	public void addParam(VarSymTabEntry poParam)
	{
		this.oParams.addElement(poParam);
	}

	/**
	 * Allows retrieval of parameter list as Vector.
	 * @return a parameter list
	 */
	public Vector<VarSymTabEntry> getParams()
	{
		return this.oParams;
	}

	/**
	 * Cannot be anything other than FUNCTION.
	 * @return SymDataType corresponding to the FUNCTION identifier.
	 * @see #FUNCTION
	 */
	public SymDataType getDataType()
	{
		return new SymDataType(FUNCTION);
	}

	/**
	 * Allows setting the return data type.
	 * @param poSymDataType type of the return value
	 */
	public void setRetValDataType(SymDataType poSymDataType)
	{
		this.oRetValType = poSymDataType;
	}

	/**
	 * Allows getting the data type of the function's return value.
	 * @return SymDataType corresponding to the return value
	 * @see SymDataType
	 */
	public SymDataType getRetValDataType()
	{
		return this.oRetValType;
	}
}

// EOF
