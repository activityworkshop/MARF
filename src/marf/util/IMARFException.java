package marf.util;

/**
 * TODO: document.
 *
 * @author serguei
 */
public interface IMARFException
{
	IMARFException create();
	IMARFException create(String pstrMessage);
	IMARFException create(Exception poException);
	IMARFException create(String pstrMessage, Exception poException);
}

// EOF
