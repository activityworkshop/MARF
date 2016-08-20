package marf.nlp.Parsing;


/**
 * <p>Represents Variable SymTab Entry.</p>
 *
 * @author Serguei Mokhov
 */
public class VarSymTabEntry
extends SymTabEntry
{
	/**
	 * Variable kind.
	 */
	protected int iKind = SCOPE_LOCAL;
	
	/**
	 * Normal (local) variable kind.
	 */
	public static final int SCOPE_LOCAL = 0;
	
	/**
	 * Parameter variable.
	 */
	public static final int SCOPE_PARAM = 1;
	
	/**
	 * Class member.
	 */
	public static final int SCOPE_MEMBER = 2;

	/**
	 * Structure.
	 */
	protected int iStructure = TYPE_SIMPLE;
	
	/**
	 * Simple (scalar, primitive type) variable.
	 */
	public static final int TYPE_SIMPLE = 0;
	
	/**
	 * Array.
	 */
	public static final int TYPE_ARRAY = 1;
	
	/**
	 * Object.
	 */
	public static final int TYPE_CLASS = 2;
	
	/**
	 * Dimension of an array.
	 * -1 for other structures
	 */
	protected int iDimension = -1;
	
	/**
	 * Default Constructor.
	 */
	public VarSymTabEntry()
	{
		super();
	}

	/**
	 * Allows getting the dimensionality of the array.
	 * @return the dimensionality of current array
	 */
	public int getArrayDimension()
	{
		return this.iDimension;
	}

	/**
	 * Allows setting the dimensionality of the array.
	 * @param piDimension the new dimensionality value
	 */
	public void setArrayDimension(int piDimension)
	{
		this.iDimension = piDimension;
	}
	
	/**
	 * Allows to set data type of the variable.
	 * @param piType the new type
	 */
	public void setDataType(int piType)
	{
		this.oDataType = new SymDataType(piType);
	}
}

// EOF
