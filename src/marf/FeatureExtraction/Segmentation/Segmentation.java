package marf.FeatureExtraction.Segmentation;

import marf.FeatureExtraction.FeatureExtraction;
import marf.FeatureExtraction.FeatureExtractionException;
import marf.Preprocessing.IPreprocessing;
import marf.util.NotImplementedException;


/**
 * <p>Class Segmentation.</p>
 *
 * @author Serguei Mokhov
 */
public class Segmentation
extends FeatureExtraction
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -8113388768153118543L;

	/**
	 * Segmentation Constructor.
	 * @param poPreprocessing Preprocessing object reference
	 */
	public Segmentation(IPreprocessing poPreprocessing)
	{
		super(poPreprocessing);
	}

	/**
	 * Not Implemented.
	 * @return nothing
	 * @throws NotImplementedException
	 * @throws FeatureExtractionException never thrown
	 * @see marf.FeatureExtraction.IFeatureExtraction#extractFeatures(double[])
	 * @since 0.3.0.6
	 */
	public final boolean extractFeatures(double[] padSampleData)
	throws FeatureExtractionException
	{
		throw new NotImplementedException("Segmentation.extractFeatures()");
	}
}

// EOF
