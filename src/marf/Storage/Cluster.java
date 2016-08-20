package marf.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import marf.util.Arrays;
import marf.util.Debug;
import marf.util.NotImplementedException;


/**
 * <p>Cluster contains a cluster information per subject.</p>
 *
 * @author Serguei Mokhov
 */
public class Cluster
extends TrainingSample
{
	/**
	 * How many times the mean was computed.
	 * Used in recomputation of it when new data is coming in.
	 */
	private int iMeanCount = 0;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -8773915807751754560L;

	/**
	 * Default cluster constructor.
	 */
	public Cluster()
	{
	}

	/**
	 * Copy-constructor.
	 * @param poCluster the Cluster object to copy properties of
	 */
	public Cluster(final Cluster poCluster)
	{
		super(poCluster);
		this.iMeanCount = poCluster.iMeanCount;
	}

	/**
	 * Adds a filename to the training set.
	 * @param pstrFilename filename to add
	 * @return <code>false</code> if the filename is already there; <code>true</code> otherwise
	 * @see #existsFilename(String)
	 */
	public /*final*/ boolean addFilename(String pstrFilename)
	{
		return super.addFilename(pstrFilename);
	}

	/**
	 * Checks existence of the file in the training set.
	 * Serves as an indication that we already trained on the given file.
	 * @param pstrFilename filename to check
	 * @return <code>true</code> if the filename is there; <code>false</code> if not
	 */
	public boolean existsFilename(String pstrFilename)
	{
		return this.oFilenames.contains(pstrFilename);
	}

	/**
	 * Retrieves current mean count.
	 * @return mean count
	 */
	public int getMeanCount()
	{
		return this.iMeanCount;
	}

	/**
	 * Increases mean count by one.
	 * @return new mean count
	 */
	private final int incMeanCount()
	{
		return (++this.iMeanCount);
	}

	/**
	 * Sets new mean vector.
	 * @param padMeanVector double array representing the mean vector
	 */
	public /*final*/ void setMeanVector(double[] padMeanVector)
	{
		setDataVector(padMeanVector);
	}

	/**
	 * Adds new feature vector to the mean and recomputes the mean.
	 * @param padFeatureVector vector to add
	 * @param pstrFilename filename to add
	 * @param piSubjectID for which subject that vector is
	 * @return <code>true</code> if the vector was added; <code>false</code> otherwise
	 */
	public boolean addFeatureVector
	(
		double[] padFeatureVector,
		String pstrFilename,
		int piSubjectID
	)
	{
		boolean bNewSample = this.adDataVector == null ? true : false;

		if(bNewSample == true)
		{
			setSubjectID(piSubjectID);
			setMeanVector((double[])padFeatureVector.clone());
			addFilename(pstrFilename);
		}
		else
		{
			/*
			 * If this file has been trained on already, no retraining is
			 * required. Return false to indicate that no changes are made.
			 */
			if(addFilename(pstrFilename) == false)
			{
				return false;
			}

			// What if piSubjectID is different from the one in this.iSubjectID?
			assert this.iSubjectID == piSubjectID;

			// Recompute the mean
			// XXX: what if the length of the parameter is less
			// or more than that of
			for(int f = 0; f < this.adDataVector.length; f++)
			{
				this.adDataVector[f] =
					(this.adDataVector[f] * this.iMeanCount + padFeatureVector[f]) /
					(this.iMeanCount + 1);
			}
		}

		incMeanCount();

		if(bNewSample == true)
		{
			Debug.debug("Cluster.addFeatureVector() -- Added feature vector for subject: " + piSubjectID);
		}
		else
		{
			Debug.debug("Cluster.addFeatureVector() -- Updated mean vector for subject: " + piSubjectID);
		}

		return true;
	}

	/**
	 * Write one training cluster as a CSV text.
	 * @param poWriter BufferedWriter to write to
	 * @throws StorageException in case of any error while dumping
	 */
	public void dumpCSV(BufferedWriter poWriter)
	throws StorageException
	{
		try
		{
			StringBuffer oDump = new StringBuffer();

			oDump
				.append(this.iSubjectID).append(",")
				.append(this.adDataVector.length).append(",")
				.append(this.iMeanCount).append(",")
				.append(Arrays.arrayToCSV(this.adDataVector));

			poWriter.write(oDump.toString());
			poWriter.newLine();
		}
		catch(Exception e)
		{
			throw new StorageException(e);
		}
	}

	/**
	 * Retrieve one training cluster from the specified reader as a CSV text.
	 * Not implemented.
	 * @param poReader BufferedReader to read from
	 * @throws StorageException never thrown
	 * @throws NotImplementedException
	 */
	public void restoreCSV(BufferedReader poReader)
	throws StorageException
	{
		throw new NotImplementedException();
	}

	/**
	 * Implements Cloneable interface for the Cluster object.
	 * @see java.lang.Object#clone()
	 */
	public Object clone()
	{
		return new Cluster(this);
	}

	/**
	 * Provides string representation of the training set data in addition
	 * to that of the parent TrainingSample.
	 * @see marf.Storage.TrainingSample#toString()
	 * @since 0.3.0.6
	 */
	public synchronized String toString()
	{
		StringBuffer oBuffer = new StringBuffer(super.toString());

		oBuffer
			.append("Mean Count: ").append(this.iMeanCount).append("\n");

		return oBuffer.toString();
	}
} // class Cluster

// EOF
