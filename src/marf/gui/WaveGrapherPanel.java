package marf.gui;

import marf.gui.util.BorderPanel;
import marf.gui.util.ColoredStatusPanel;

/**
 * <p>GUI component to draw wave forms to be used by apps.
 * TODO: complete implementation.</p>
 *
 * @author Serguei Mokhov
 */
public class WaveGrapherPanel
extends BorderPanel
{
	/**
	 * Local reference to the wave grapher object for data.
	 */
	protected WaveGrapher oWaveGrapher;

	/**
	 * Local reference to the status panel.
	 */
	protected ColoredStatusPanel oStatusPanel;

	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = 3804010606988001885L;

	/**
	 * Default constructor.
	 */
	public WaveGrapherPanel()
	{
	}
}

// EOF
