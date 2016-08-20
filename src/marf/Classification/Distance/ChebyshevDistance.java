package marf.Classification.Distance;

import marf.FeatureExtraction.IFeatureExtraction;


/**
 * <p>Chebyshev Distance Classifier.</p>
 * <p>Also known as Manhattan or City Block distance.</>
 *
 * @author Serguei Mokhov
 */
public class ChebyshevDistance
extends Distance
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -9173510703171507530L;

	/**
	 * ChebyshevDistance Constructor.
	 * @param poFeatureExtraction FeatureExtraction module reference
	 */
	public ChebyshevDistance(IFeatureExtraction poFeatureExtraction)
	{
		super(poFeatureExtraction);
	}

	/**
	 * Chebyshev Distance implementation.
	 * @param padVector1 first vector to compare
	 * @param padVector2 second vector to compare
	 * @return Chebyshev (a.k.a city-block/Manhattan) distance between two feature vectors
	 */
	public final double distance(final double[] padVector1, final double[] padVector2)
	{
		double dDistance = 0;

		for(int f = 0; f < padVector1.length; f++)
		{
			dDistance += Math.abs(padVector1[f] - padVector2[f]);
		}

		return dDistance;
	}
}

// EOF
