package marf.Stats.StatisticalEstimators.Smoothing;

import marf.MARF;

/**
 * <p>Add-Delta Smoothing Estimator. The delta in fact determines
 * the type of an estimator, e.g. 0.5 is for ELE, 0.0 for MLE,
 * 1.0 for Add-One are the special cases. 
 * </p>
 *
 * @author Serguei Mokhov
 * @version $Revision: 1.35 $
 * @since $Id: AddDelta.java,v 1.35 2010/08/21 21:56:55 mokhov Exp $
 */
public class AddDelta
extends Smoothing
{
	/**
	 * Default is 0.5: Expected Likelihood Estimation (ELE).
	 */
	protected double dDelta = 0.5;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 * @since 0.3.0.4
	 */
	private static final long serialVersionUID = -3936382438064284172L;

	/**
	 * Constructs the estimator with the specified delta.
	 * @param pdDelta the delta
	 */
	public AddDelta(final double pdDelta)
	{
		super();
		this.dDelta = pdDelta;
	}

	/**
	 * Allows querying for the current value of delta.
	 * @return the current value of delta
	 */
	public final double getDelta()
	{
		return this.dDelta;
	}

	/**
	 * Implements add-delta smoothing for uni-, bi-, and tri-grams.
	 * @see marf.Stats.StatisticalEstimators.Smoothing.ISmoothing#smooth()
	 */
	public boolean smooth()
	{
		try
		{
			System.out.println(getClass().getName() + " smoothing has begun.");

			// Vocab. size
			int iV = this.oProbabilityTable.size();

			switch(MARF.NLP.getNgramModel())
			{
				case MARF.ENgramModels.UNIGRAM:
				{
					//long lN = 0;
					double lN = 0;

					for(int i = 0; i < iV; i++)
					{
						//lN += (long)this.oProbabilityTable.getOccurrence(i);
						lN += this.oProbabilityTable.getOccurrence(i);

						//Debug.debug("N["+i+"]=" + N[i]);
					}

					for(int i = 0; i < iV; i++)
					{
						double dNewOccurrence =
							(this.oProbabilityTable.getOccurrence(i) + this.dDelta) / (lN + this.dDelta * iV);
/*
						Debug.debug
						(
							"Old Occurrence: " + this.oProbabilityTable.getOccurence(j, i) + "\n" +
							"New Occurrence: " + dNewOccurence
						);
*/
						this.oProbabilityTable.setOccurrence(i, dNewOccurrence);
					}

					break;
				}

				case MARF.ENgramModels.BIGRAM:
				{
					long alN[] = new long[iV];

//					Debug.debug("V=" + iV);

					for(int i = 0; i < iV; i++)
					{
						for(int j = 0; j < iV; j++)
						{
							alN[i] += (long)this.oProbabilityTable.getOccurrence(j, i);
						}

						//Debug.debug("N["+i+"]=" + N[i]);
					}

					for(int i = 0; i < iV; i++)
					{
						for(int j = 0; j < iV; j++)
						{
							double dNewOccurrence =
								(this.oProbabilityTable.getOccurrence(j, i) + this.dDelta) / (alN[i] + this.dDelta * iV);

							this.oProbabilityTable.setOccurrence(j, i, dNewOccurrence);
						}
					}

					break;
				}

				case MARF.ENgramModels.TRIGRAM:
				{
					long alN[][] = new long[iV][iV];

					for(int k = 0; k < iV; k++)
					{
						for(int i = 0; i < iV; i++)
						{
							for(int j = 0; j < iV; j++)
							{
								alN[k][i] += (long)this.oProbabilityTable.getOccurrence(j, i, k);

								if(alN[k][i] == 0)
								{
									System.out.println("WARNING: N["+k+"]["+i+"]=" + alN[k][i]);
									alN[k][i] = 1;
								}
							}

							//System.out.println("N["+i+"]=" + N[i]);
						}
					}

					for(int k = 0; k < iV; k++)
					{
						for(int i = 0; i < iV; i++)
						{
							for(int j = 0; j < iV; j++)
							{
								double dNewOccurence =
									(this.oProbabilityTable.getOccurrence(j, i, k) + this.dDelta) / (alN[k][i] + this.dDelta * iV);

								this.oProbabilityTable.setOccurrence(j, i, k, dNewOccurence);
							}
						}
					}

					break;
				}

				default:
				{
					// choke
					return false;
				}
			}

			System.out.println(getClass().getName() + " smoothing has completed.");
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			return false;
		}

		return true;
	}

	/**
	 * Returns source code revision information.
	 * @return revision string
	 */
	public static String getMARFSourceCodeRevision()
	{
		return "$Revision: 1.35 $";
	}
}

// EOF
