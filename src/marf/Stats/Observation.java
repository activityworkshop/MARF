package marf.Stats;


/**
 * <p>Generic Observation of an occurrence of something (like a word or an n-gram).</p>
 *
 * @author Serguei Mokhov
 */
public class Observation
extends WordStats
{
	/**
	 * Probability of the observation of happening prior
	 * it actually being observed. 
	 */
	protected double dPriorProbability = 0.0;

	/**
	 * Probability of the observation after
	 * it was observed. 
	 */
	protected double dPosteriorProbability = 0.0;

	/**
	 * Indicates a fact that this observation has been "seen".
	 */
	protected boolean bSeen = false;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 608350003332106275L;

	/**
	 * Constructs default unseen observation with zero probabilities.
	 */
	public Observation()
	{
		super();
	}

	/**
	 * Copy-constructor.
	 * @param poObservation the Observation object to copy properties of
	 */
	public Observation(final Observation poObservation)
	{
		// This will copy StatisticalObject and WordStats members.
		super(poObservation);

		// Copy ours
		this.dPriorProbability = poObservation.getPriorProbability(); 
		this.dPosteriorProbability = poObservation.getPosteriorProbability();
		this.bSeen = poObservation.wasSeen();
	}

	/**
	 * Indicates whether this observation was actually seen.
	 * @return <code>true</code> if it was seen
	 */
	public boolean wasSeen()
	{
		return this.bSeen;
	}

	/**
	 * Marks this observation as seen.
	 */
	public void markAsSeen()
	{
		this.bSeen = true;
	}

	/**
	 * Allows querying for the current value of the posterior probability.
	 * @return the current posterior probability of this observation
	 */
	public double getPosteriorProbability()
	{
		return this.dPosteriorProbability;
	}

	/**
	 * Allows setting the new value of the posterior probability.
	 * @param pdPosteriorProbability the posterior probability to set
	 */
	public void setPosteriorProbability(double pdPosteriorProbability)
	{
		this.dPosteriorProbability = pdPosteriorProbability;
	}

	/**
	 * Allows querying for the current value of the prior probability.
	 * @return the current prior probability of this observation
	 */
	public double getPriorProbability()
	{
		return this.dPriorProbability;
	}

	/**
	 * Allows setting the new value of the prior probability.
	 * @param pdPriorProbability the prior probability to set
	 */
	public void setPriorProbability(double pdPriorProbability)
	{
		this.dPriorProbability = pdPriorProbability;
	}

	/**
	 * Implements Cloneable interface for the Observation object.
	 * @see java.lang.Object#clone()
	 */
	public Object clone()
	{
		return new Observation(this);
	}
}

// EOF
