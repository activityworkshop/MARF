package marf.util;

import java.util.Hashtable;
import java.util.Vector;


/**
 * <p>Anything that provides an options set to option consumers
 * must conform to this SIMPLE interface.</p>
 * 
 * @author Serguei Mokhov
 * @since 0.3.0.6
 */
public interface IOptionProvider
{
	void addValidOption(final String pstrOptionString);
	void addValidOption(final int piOptionCode, final String pstrOptionString);
	void addValidOption(final int piOptionCode, final String pstrOptionString, boolean pbRequiresArgument);
	
	void addActiveOption(final String pstrOptionString);
	void addActiveOption(final String pstrOptionString, final String pstrOptionValue);
	void addActiveOption(final int piOptionCode, final String pstrOptionString);
	
	int size();
	String[] getArgumentVector();
	String getArgumentString();
	
	int getOption(final String pstrOption);
	int getOption(final String pstrOption, boolean pbReturnOnError);
	
	String getOption(int piOption);
	String getOption(int piOption, boolean pbReturnOnError);
	
	String getOptionArgument(final String pstrOption);
	String getOptionArgument(final String pstrOption, boolean pbReturnOnError);
	String getOptionArgument(final int piOption);
	String getOptionArgument(final int piOption, boolean pbReturnOnError);

	Vector<String> getInvalidOptions();
	Hashtable getActiveOptions();
	Hashtable getValidOptions();

	boolean isActiveOption(final String pstrOption);
	boolean isActiveOption(int piOption);
	
	boolean isValidOption(String pstrOption);
	boolean isValidOption(int piOption);
	
	boolean isInvalidOption(String pstrOption);
}

// EOF
