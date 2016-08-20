package marf.Classification.Distance;

import marf.FeatureExtraction.IFeatureExtraction;
import marf.math.Matrix;
import marf.util.Debug;


/**
 * <p>Mahalanobis Distance Classification Module.</p>
 *
 * <p><b>NOTE</b>: Implemented as equivalent to Euclidean Distance in 0.2.0, i.e.
 * the Covariance matrix is always an Identity one.</p>
 *
 * @author Serguei Mokhov
 */
public class MahalanobisDistance
extends Distance
{
	/**
	 * Covariance Matrix.
	 */
	private Matrix oC = null;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -6720267386878796592L;

	/**
	 * MahalanobisDistance Constructor.
	 * @param poFeatureExtraction FeatureExtraction module reference
	 */
	public MahalanobisDistance(IFeatureExtraction poFeatureExtraction)
	{
		super(poFeatureExtraction);

		int iD = this.oFeatureExtraction.getFeaturesArray().length;

		this.oC = new Matrix(iD, iD);

		/*
		 * Make the default an identity matrix rendering it at least
		 * equivalent to the Euclidean distance. Will be fixed in 0.3.*.
		 */
		this.oC.makeIdentity();
	}

	/**
	 * Partial MahalanobisDistance implementation.
	 * @param paVector1 first vector to compare
	 * @param paVector2 second vector to compare
	 * @return Mahalanobis distance between two feature vectors
	 */
	public final double distance(final double[] paVector1, final double[] paVector2)
	{
		Debug.debug
		(
			"MahalanobisDistance.distance() - WARNING:  Mahalanobis distance is equivalent " +
			"to Euclidean as there is no learning of the co-variance matrix."
		);

		double dDistance = 0.0;

		Matrix oVector1 = new Matrix(paVector1);
		Matrix oVector2 = new Matrix(paVector2);

		Matrix oDifferenceVector = oVector1.minus(oVector2);

		Matrix oTransposedVector = (Matrix)oDifferenceVector.clone();
		oTransposedVector.transpose();

		this.oC.inverse();

		dDistance = Math.sqrt(oDifferenceVector.multiply(this.oC).multiply(oTransposedVector).getElement(0, 0));

		return dDistance;
	}
}

// EOF
