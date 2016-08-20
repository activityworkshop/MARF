package marf.nlp.Parsing;


/**
 * <p>ClassSymTabEntry represents an entry in the symbol
 * table corresponding to a class definition.</p>
 * 
 * @author Serguei Mokhov
 */
public class ClassSymTabEntry
extends SymTabEntry
{
	/**
	 * Default Constructor.
	 */
	public ClassSymTabEntry()
	{
	}

	/**
	 * Can return only CLASS.
	 * @return SymDataType of CLASS
	 */
	public SymDataType getDataType()
	{
		return new SymDataType(CLASS);
	}
}

// EOF
