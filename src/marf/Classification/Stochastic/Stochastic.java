package marf.Classification.Stochastic;

import marf.Classification.Classification;
import marf.Classification.ClassificationException;
import marf.FeatureExtraction.IFeatureExtraction;
import marf.Storage.Result;
import marf.util.NotImplementedException;


/**
 * <p>Generic Stochastic Classification Module.</p>
 * <p>TODO: partially implemented.</p>
 *
 * @author Serguei Mokhov
 */
public class Stochastic
extends Classification
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -415255678695729045L;

	/**
	 * Stochastic Constructor.
	 * @param poFeatureExtraction FeatureExtraction module reference
	 */
	public Stochastic(IFeatureExtraction poFeatureExtraction)
	{
		super(poFeatureExtraction);
	}

	/**
	 * Not Implemented.
	 * @throws NotImplementedException
	 * @throws ClassificationException never thrown
	 * @return nothing
	 * @since 0.3.0.6
	 */
	public boolean classify(double[] padFeatureVector)
	throws ClassificationException
	{
		throw new NotImplementedException(this, "classify()");
	}

	/**
	 * Not Implemented.
	 * @throws NotImplementedException
	 * @throws ClassificationException never thrown
	 * @return nothing
	 * @since 0.3.0.6
	 */
	public boolean train(double[] padFeatureVector)
	throws ClassificationException
	{
		throw new NotImplementedException(this, "train()");
	}

	/**
	 * Retrieves the maximum-probability classification result.
	 * @return Result object
	 */
	public Result getResult()
	{
		return this.oResultSet.getMaximumResult();
	}
}

// EOF
