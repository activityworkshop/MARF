package marf.Stats.StatisticalEstimators.Smoothing;

import marf.MARF;
import marf.Stats.Ngram;


/**
 * <p>Represents Witten-Bell Smoothing Algorithm.</p>
 *
 * @author Serguei Mokhov
 */
public class WittenBell
extends Smoothing
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 4056635859068758735L;

	/**
	 * Default constructor with a call to the parent.
	 */
	public WittenBell()
	{
		super();
	}

	/**
	 * Implements the Witten-Bell smoothing algorithm for
	 * uni-, bi, and tri-gram models.
	 * @see marf.Stats.StatisticalEstimators.Smoothing.ISmoothing#smooth()
	 */
	public boolean smooth()
	{
		try
		{
			System.out.println(getClass().getName() + " smoothing has begun.");

			int iV = this.oProbabilityTable.size();

			switch(MARF.NLP.getNgramModel())
			{
				case MARF.ENgramModels.UNIGRAM:
				{
					long lN = 0;
					long lZ = 0;
					long lT = 0;

					for(int j = 0; j < iV; j++)
					{
						long lOccurrence = (long)this.oProbabilityTable.getOccurrence(j);

						lN += lOccurrence;
						lZ += lOccurrence == 0 ? 1 : 0;
					}

					lT = iV - lZ;

					for(int j = 0; j < iV; j++)
					{
						double dNewOccurrence;

						long lOccurrence = (long)this.oProbabilityTable.getOccurrence(j);

						double dAdjustment = (double)lN / (double)(lN + lT);

						// Unseen
						if(lOccurrence == 0)
						{
							dNewOccurrence = ((double)lT / (double)lZ) * dAdjustment;
						}

						// Seen
						else
						{
							dNewOccurrence = lOccurrence * dAdjustment;
						}

						this.oProbabilityTable.setOccurrence(j, dNewOccurrence);
					}

					break;
				}

				case MARF.ENgramModels.BIGRAM:
				{
					long alN[] = new long[iV];
					long alZ[] = new long[iV];
					long alT[] = new long[iV];

//					Debug.debug("V=" + iV);

					for(int i = 0; i < iV; i++)
					{
						for(int j = 0; j < iV; j++)
						{
							long lOccurrence = (long)this.oProbabilityTable.getOccurrence(j, i);

							alN[i] += lOccurrence;
							alZ[i] += lOccurrence == 0 ? 1 : 0;
						}

						alT[i] = iV - alZ[i];
/*
						Debug.debug
						(
							"N["+i+"]=" + alN[i] + ", " +
							"Z["+i+"]=" + alZ[i] + ", " +
							"T["+i+"]=" + alT[i]
						);
*/
					}

					for(int i = 0; i < iV; i++)
					{
						for(int j = 0; j < iV; j++)
						{
							double dNewOccurrence;

							long lOccurrence = (long)this.oProbabilityTable.getOccurrence(j, i);

							double dAdjustment = (double)alN[i] / (double)(alN[i] + alT[i]);

							// Unseen
							if(lOccurrence == 0)
							{
								dNewOccurrence = ((double)alT[i] / (double)alZ[i]) * dAdjustment;
							}

							// Seen
							else
							{
								dNewOccurrence = lOccurrence * dAdjustment;
							}

/*
							Debug.debug
							(
								"Old Occurrence: " + lOccurence + "\n" +
								"New Occurrence: " + dNewOccurence
							);
*/
							this.oProbabilityTable.setOccurrence(j, i, dNewOccurrence);
						}
					}

					break;
				}

				case MARF.ENgramModels.TRIGRAM:
				{
					long alN[][] = new long[iV][iV];
					long alZ[][] = new long[iV][iV];
					long alT[][] = new long[iV][iV];

					for(int k = 0; k < iV; k++)
					{
						for(int i = 0; i < iV; i++)
						{
							for(int j = 0; j < iV; j++)
							{
								long lOccurrence = (long)this.oProbabilityTable.getOccurrence(j, i, k);

								alN[k][i] += lOccurrence;
								alZ[k][i] += lOccurrence == 0 ? 1 : 0;
							}

							alT[k][i] = iV - alZ[k][i];
						}
					}

					for(int k = 0; k < iV; k++)
					{
						for(int i = 0; i < iV; i++)
						{
							for(int j = 0; j < iV; j++)
							{
								double dNewOccurrence;

								long lOccurrence = (long)this.oProbabilityTable.getOccurrence(j, i, k);

								double dAdjustment = (double)alN[k][i] / (double)(alN[k][i] + alT[k][i]);

								// Unseen
								if(lOccurrence == 0)
								{
									dNewOccurrence = ((double)alT[k][i] / (double)alZ[k][i]) * dAdjustment;
								}

								// Seen
								else
								{
									dNewOccurrence = lOccurrence * dAdjustment;
								}

								this.oProbabilityTable.setOccurrence(j, i, k, dNewOccurrence);
							}
						}
					}

					break;
				}

				default:
				{
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
	 * Not implemented. 
	 * @param poNgram
	 * @return 0.0
	 */
	public double p(Ngram poNgram)
	{
		return 0.0;
	}
}

// EOF
