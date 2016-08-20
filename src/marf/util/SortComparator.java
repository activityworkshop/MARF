package marf.util;

import java.io.Serializable;
import java.util.Comparator;


/**
 * <p>Sort Comparator is the base for all sorting operations in MARF.</p>
 *
 * @author Serguei Mokhov
 */
public abstract class SortComparator
implements Comparator<Object>, Serializable
{
	/*
	 * Constants to use for indication of the sorting order
	 */

	// TODO: Better to use an enum for ASCENDING and DESCENDING?

	/**
	 * Constant indicating to sort in the ascending order.
	 */
	public static final int ASCENDING  = 0;

	/**
	 * Constant indicating to sort in the descending order.
	 */
	public static final int DESCENDING = 1;

	/**
	 * Current sorting mode: either <code>ASCENDING</code>
	 * or <code>DESCENDING</code>.
	 */
	protected int iSortMode = ASCENDING;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -3395493010875051653L;

	/**
	 * When constructed, default mode is ASCENDING.
	 */
	public SortComparator()
	{
		this(ASCENDING);
	}

	/**
	 * When constructed, the mode is the one specified.
	 * @param piSortMode desired sorting mode of ASCENDING or DESCENDING.
	 * @throws IllegalArgumentException is not ASCENDING or DESCENDING
	 */
	public SortComparator(final int piSortMode)
	{
		if(piSortMode < ASCENDING || piSortMode > DESCENDING)
		{
			throw new IllegalArgumentException
			(
				"Sort mode (" + piSortMode + ") is out of range.\n" +
				"HINT: use SortComparator.ASCENDING or SortComparator.DESCENDING"
			);
		}

		this.iSortMode = piSortMode;
	}

	/**
	 * Retrieves the current sorting mode.
	 * @return the mode
	 * @see #DESCENDING
	 * @see #ASCENDING
	 */
	public int getSortMode()
	{
		return this.iSortMode;
	}
}

// EOF
