package marf.nlp.Stemming;

/**
 * <p>General Stemming Interface.</p>
 * 
 * @author Serguei Mokhov
 */
public interface IStemming
{
	/**
	 * General stem method the derivatives must implement.
	 * @return number of stems produced
	 */
	int stem();
}

// EOF
