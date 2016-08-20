package marf.Stats;

import java.io.Serializable;


/**
 * <p>Generic Statistical Object.</p>
 *
 * @author Serguei Mokhov
 */
public class StatisticalObject
implements Serializable, Cloneable
{
	/**
	 * Number of occurrences of this object basic data in a given document
	 * (for example a corpus or a WAVE file).
	 * Default <code>0</code>.
	 */
	protected int iFrequency = 0;

	/**
	 * Rank of the object in the document.
	 * The rank of 1 indicates the most frequent.
	 * Default is <code>-1</code>, i.e. unset.
	 */
	protected int iRank = -1;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 3277836605237662721L;

	/**
	 * Constructs a default statistical object.
	 */
	public StatisticalObject()
	{
	}

	/**
	 * Constructs a statistical object with a frequency parameter.
	 * @param piFrequency the desired frequency of this object
	 */
	public StatisticalObject(final int piFrequency)
	{
		this.iFrequency = piFrequency;
	}

	/**
	 * Copy-constructor.
	 * @param poStatisticalObject statistical object to copy properties of
	 * @since 0.3.0.5
	 */
	public StatisticalObject(final StatisticalObject poStatisticalObject)
	{
		this.iFrequency = poStatisticalObject.iFrequency;
		this.iRank = poStatisticalObject.iRank;
	}

	/**
	 * Allows setting rank of this object.
	 * @param piRank the new rank
	 */
	public final void setRank(final int piRank)
	{
		this.iRank = piRank;
	}

	/**
	 * Increments rank of the object by one.
	 * @return the new rank
	 */
	public final int incRank()
	{
		return (++this.iRank);
	}

	/**
	 * Retrieves the object's rank.
	 * @return current rank
	 */
	public final int getRank()
	{
		return this.iRank;
	}

	/**
	 * Retrieves the object's frequency.
	 * @return current frequency
	 */
	public final int getFrequency()
	{
		return this.iFrequency;
	}

	/**
	 * Increments the object frequency by one.
	 * @return the new frequency
	 */
	public final int incFrequency()
	{
		return (++this.iFrequency);
	}

	/**
	 * Implements Cloneable interface for the StatisticalObject.
	 * @see java.lang.Object#clone()
	 */
	public Object clone()
	{
		return new StatisticalObject(this);
	}

	/**
	 * Checks equality of two StatisticalObjects whether
	 * the parameter is not null and its <code>toString()</code>'s output
	 * is equal to this one.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public synchronized boolean equals(Object poStatisticalObject)
	{
		if(poStatisticalObject instanceof StatisticalObject)
		{
			return poStatisticalObject.toString().equals(toString());
		}

		return false;
	}

	/**
	 * Overrides <code>hashCode()</code> since <code>equals()</code> is
	 * overridden by returning the hash code of the <code>toString()</code>.
	 * @see java.lang.Object#hashCode()
	 * @see #equals(Object)
	 */
	public synchronized int hashCode()
	{
		return toString().hashCode();
	}

	/**
	 * Reports frequency and rank of an occurrence of a statistical object.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return new StringBuffer()
			.append("Frequency: ").append(this.iFrequency).append(", ")
			.append("Rank: ").append(this.iRank)
			.toString();
	}
}

// EOF
