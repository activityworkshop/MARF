package marf.gui;

import marf.gui.util.BorderPanel;
import marf.gui.util.ColoredStatusPanel;


/**
 * <p>GUI component to draw spectrograms to be used by apps.
 * TODO: complete implementation.</p>
 *
 * @author Serguei Mokhov
 */
public class SpectrogramPanel
extends BorderPanel
{
	/**
	 * Reference to the spectrogram object to draw.
	 */
	protected Spectrogram oSpectrogram;

	/**
	 * Status panel reference.
	 */
	protected ColoredStatusPanel oStatusPanel;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 6943369072027609738L;

	/**
	 * Default constructor.
	 */
	public SpectrogramPanel()
	{
	}
}

// EOF
