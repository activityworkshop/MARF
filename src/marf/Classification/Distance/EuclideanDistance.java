package marf.Classification.Distance;

import marf.FeatureExtraction.IFeatureExtraction;


/**
 * <p>Class EuclideanDistance.</p>
 *
 * @author Stephen Sinclair
 * @author Serguei Mokhov
 */
public class EuclideanDistance
extends Distance
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 6805036936942102456L;

	/**
	 * EuclideanDistance Constructor.
	 * @param poFeatureExtraction FeatureExtraction module reference
	 */
	public EuclideanDistance(IFeatureExtraction poFeatureExtraction)
	{
		super(poFeatureExtraction);
	}

	/**
	 * EuclideanDistance implementation.
	 * @param paVector1 first vector to compare
	 * @param paVector2 second vector to compare
	 * @return Euclidean distance between two feature vectors
	 */
	public final double distance(final double[] paVector1, final double[] paVector2)
	{
		double dDistance = 0;

		for(int f = 0; f < paVector1.length; f++)
		{
			dDistance += (paVector1[f] - paVector2[f]) * (paVector1[f] - paVector2[f]);
		}

		return dDistance;
	}
}

// EOF
