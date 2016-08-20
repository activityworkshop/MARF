package marf.nlp.Parsing;

/**
 * <p>User-defined symbol's data type.</p>
 *
 * @author Serguei Mokhov
 */
public class SymDataType
{
	/**
	 * Unknown data type of the symbol.
	 */
	public static final int UNKNOWN  = -1;

	/**
	 * Integer data type of the symbol.
	 */
	public static final int INTEGER  = 0;

	/**
	 * Real data type of the symbol.
	 */
	public static final int REAL     = 1;

	/**
	 * Class data type of the symbol.
	 */
	public static final int CLASS    = 3;

	/**
	 * Function data type of the symbol.
	 */
	public static final int FUNCTION = 4;

	/**
	 * Current type. Default is UNKNOWN.
	 * @see #UNKNOWN
	 */
	private int iType = UNKNOWN;

	/**
	 * Default Constructor.
	 */
	public SymDataType()
	{
	}

	/**
	 * Constructor with the type parameter.
	 * @param piType the desired data type to set
	 * @throws IllegalArgumentException if the parameter is out of range
	 */
	public SymDataType(int piType)
	{
		setType(piType);
	}

	/**
	 * Allows querying for data type.
	 * @return the current data type
	 * @since 0.3.0.5
	 */
	public int getType()
	{
		return this.iType;
	}

	/**
	 * Allows setting the date type.
	 * @param piType the desired type
	 * @since 0.3.0.5
	 * @throws IllegalArgumentException if the parameter is out of range
	 */
	public void setType(int piType)
	{
		if(piType < UNKNOWN || piType > FUNCTION)
		{
			throw new IllegalArgumentException
			(
				"The type parameter (" + piType + ") is out of range "
				+ "[" + UNKNOWN + ", " + FUNCTION + "]"
			);
		}

		this.iType = piType;
	}
}

// EOF
