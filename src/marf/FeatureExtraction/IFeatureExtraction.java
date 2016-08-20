package marf.FeatureExtraction;

import marf.Preprocessing.IPreprocessing;


/**
 * <p>Feature Extraction Interface.</p>
 *
 * @author Serguei Mokhov
 */
public interface IFeatureExtraction
{
	/**
	 * Abstract feature extraction routine.
	 * Requires an IPreprocessing to provide the sample data.
	 * @return boolean true if there were features extracted, false otherwise
	 * @throws FeatureExtractionException if there was an error while extracting features
	 */
	boolean extractFeatures()
	throws FeatureExtractionException;

	/**
	 * Abstract feature extraction routine.
	 * @param padSampleData the sample to extract features from
	 * @return boolean true if there were features extracted, false otherwise
	 * @throws FeatureExtractionException if there was an error while extracting features
	 * @since 0.3.0.6
	 */
	boolean extractFeatures(double[] padSampleData)
	throws FeatureExtractionException;

	/**
	 * Allows retrieval of the features.
	 * @return array of features (<code>double</code> values)
	 */
	double[] getFeaturesArray();

	/**
	 * Retrieves inner preprocessing reference.
	 * @return the preprocessing reference
	 * @since 0.3.0.4
	 */
	IPreprocessing getPreprocessing();

	/**
	 * Allows setting the source preprocessing module.
	 * @param poPreprocessing the preprocessing object to set
	 * @since 0.3.0.4
	 */
	void setPreprocessing(IPreprocessing poPreprocessing);
}

// EOF
