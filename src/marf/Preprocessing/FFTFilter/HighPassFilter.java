package marf.Preprocessing.FFTFilter;

import marf.Preprocessing.IPreprocessing;
import marf.Preprocessing.PreprocessingException;
import marf.Storage.Sample;
import marf.util.Debug;


/**
 * <p>HighPassFilter class implements high-pass filtering the FFT Filter.</p>
 *
 * @author Serguei Mokhov
 */
public class HighPassFilter
extends FFTFilter
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 6784418884789745008L;

	/**
	 * Default constructor for reflective creation of Preprocessing
	 * clones. Typically should not be used unless really necessary
	 * for the frameworked modules.
	 * @since 0.3.0.5
	 */
	public HighPassFilter()
	{
		super();
	}

	/**
	 * Preprocessing pipeline constructor.
	 * @param poPreprocessing follow up preprocessing module
	 * @throws PreprocessingException
	 */
	public HighPassFilter(IPreprocessing poPreprocessing)
	throws PreprocessingException
	{
		super(poPreprocessing);
	}

	/**
	 * HighPassFilter Constructor.
	 * @param poSample incoming sample
	 * @throws PreprocessingException
	 */
	public HighPassFilter(Sample poSample)
	throws PreprocessingException
	{
		super(poSample);
	}

	/**
	 * Stub implementation of <code>cropAudio()</code>.
	 * @param pdStartingFrequency unused
	 * @param pdEndFrequency unused
	 * @return <code>false</code>
	 * @throws PreprocessingException never thrown
	 */
	public final boolean cropAudio(double pdStartingFrequency, double pdEndFrequency)
	throws PreprocessingException
	{
		Debug.debug("HighPassFilter.cropAudio()");
		return false;
	}

	/**
	 * Creates high-pass frequency response coefficients and applies
	 * them to the frequency response vector.
	 */
	public void generateResponseCoefficients()
	{
		double[] adResponse = new double[DEFAULT_FREQUENCY_RESPONSE_SIZE];

		/*
		 * Create a response that drops all frequencies below 2853 Hz
		 * XXX -- Note: 2853Hz = 70 * 128 every 8000Hz
		 */
		for(int i = 0; i < adResponse.length; i++)
		{
			if(i > 70)
			{
				adResponse[i] = 1;
			}
			else
			{
				adResponse[i] = 0;
			}
		}

		setFrequencyResponse(adResponse);
	}
}

// EOF
