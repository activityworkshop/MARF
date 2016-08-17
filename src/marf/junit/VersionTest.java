package marf.junit;

import marf.Version;
import marf.util.MARFException;
import marf.util.NotImplementedException;
import junit.framework.TestCase;


/**
 * <p>Tests version operations and validation.</p>
 * 
 * TODO: document; finish.
 *
 *
 * $Id: VersionTest.java,v 1.2 2006/06/25 16:35:51 mokhov Exp $
 *
 * @author Serguei Mokhov
 * @since 0.3.0.6
 * @version $Revision: 1.2 $
 */
public class VersionTest
extends TestCase
{

	public static void main(String[] args)
	{
		junit.textui.TestRunner.run(VersionTest.class);
	}

	/**
	 * Test method for 'marf.Version.getStringVersion()'
	 */
	public void testGetStringVersion()
	{
		assertEquals
		(
			Version.getStringVersion(),
			Version.MAJOR_VERSION + "." + Version.MINOR_VERSION + "." + Version.REVISION + "." + Version.MINOR_REVISION
		);
		
		System.out.println(Version.getStringVersion() + " - ok");
	}

	/**
	 * Test method for 'marf.Version.getStringVersion(double)'
	 */
	public void testGetStringVersionDouble()
	{
		double dDoubleVersion = 30.6;
		assertEquals(Version.getStringVersion(dDoubleVersion), "0.3.0.6");
		System.out.println(dDoubleVersion + " - ok");
		
		dDoubleVersion = 0.1;
		assertEquals(Version.getStringVersion(dDoubleVersion), "0.0.0.1");
		System.out.println(dDoubleVersion + " - ok");

		dDoubleVersion = 100.6;
		System.out.println(Version.getStringVersion(dDoubleVersion) + " - ?");
		assertEquals(Version.getStringVersion(dDoubleVersion), "1.0.0.6");
		System.out.println(dDoubleVersion + " - ok");
	}

	/**
	 * Test method for 'marf.Version.getStringVersion(int)'
	 */
	public void testGetStringVersionInt()
	{
		int iIntVersion = 306;
		assertEquals(Version.getStringVersion(iIntVersion), "0.3.0.6");
		System.out.println(iIntVersion + " - ok");
		
		iIntVersion = 1;
		assertEquals(Version.getStringVersion(iIntVersion), "0.0.0.1");
		System.out.println(iIntVersion + " - ok");

		iIntVersion = 1006;
		System.out.println(Version.getStringVersion(iIntVersion) + " - ?");
		assertEquals(Version.getStringVersion(iIntVersion), "1.0.0.6");
		System.out.println(iIntVersion + " - ok");
	}

	/**
	 * Test method for 'marf.Version.getIntVersion()'
	 */
	public void testGetIntVersion()
	{
		assertEquals(Version.getIntVersion(), 306);
	}

	/**
	 * Test method for 'marf.Version.getDoubleVersion()'
	 */
	public void testGetDoubleVersion()
	{
		assertEquals(Version.getDoubleVersion(), 30.6, 0);
	}

	/**
	 * Test method for 'marf.Version.validateVersions(double)'.
	 * XXX: verify equality of MARFException objects instead of their toString()'s.
	 */
	public void testValidateVersionsDouble()
	throws MARFException
	{
		double dVersion = 30.6;
		
		// No exceptions should be thrown
		Version.validateVersions(dVersion);
		Version.validateVersions(dVersion, false);
		Version.validateVersions(dVersion, true);
		
		dVersion = 40.6;
		
		// An expected exception
		MARFException oExpectedException =
			new MARFException
			(
				"Your MARF version (" + Version.getStringVersion()
				+ ") is too old. This application requires "
				+ Version.getStringVersion(dVersion) + " or above."
			);

		try
		{
			Version.validateVersions(dVersion);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}

		try
		{
			Version.validateVersions(dVersion, false);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}

		oExpectedException = 
			new MARFException
			(
				"Your MARF version (" + Version.getStringVersion()
				+ ") is too old. This application requires "
				+ Version.getStringVersion(dVersion) + " precisely."
			);

		try
		{
			Version.validateVersions(dVersion, true);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}
	}

	/*
	 * Test method for 'marf.Version.validateVersions(double, boolean)'
	 */
	public void testValidateVersionsDoubleBoolean()
	{
		throw new NotImplementedException();
	}

	/**
	 * Test method for 'marf.Version.validateVersions(int)'.
	 * XXX: same as double for MARFException
	 */
	public void testValidateVersionsInt()
	throws MARFException
	{
		int iVersion = 306;
		
		// No exceptions should be thrown
		Version.validateVersions(iVersion);
		Version.validateVersions(iVersion, false);
		Version.validateVersions(iVersion, true);
		
		iVersion = 406;
		
		// An expected exception
		MARFException oExpectedException =
			new MARFException
			(
				"Your MARF version (" + Version.getStringVersion()
				+ ") is too old. This application requires "
				+ Version.getStringVersion(iVersion) + " or above."
			);

		try
		{
			Version.validateVersions(iVersion);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}

		try
		{
			Version.validateVersions(iVersion, false);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}

		oExpectedException = 
			new MARFException
			(
				"Your MARF version (" + Version.getStringVersion()
				+ ") is too old. This application requires "
				+ Version.getStringVersion(iVersion) + " precisely."
			);

		try
		{
			Version.validateVersions(iVersion, true);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}
	}

	/*
	 * Test method for 'marf.Version.validateVersions(int, boolean)'
	 */
	public void testValidateVersionsIntBoolean()
	{
		throw new NotImplementedException();
	}

	/*
	 * Test method for 'marf.Version.validateVersions(String)'
	 */
	public void testValidateVersionsString()
	throws MARFException
	{
		String strVersion = "0.3.0.6";
		
		// No exceptions should be thrown
		Version.validateVersions(strVersion);
		Version.validateVersions(strVersion, false);
		Version.validateVersions(strVersion, true);
		
		strVersion = "0.4.0.6";
		
		// An expected exception
		MARFException oExpectedException =
			new MARFException
			(
				"Your MARF version (" + Version.getStringVersion()
				+ ") is too old. This application requires "
				+ strVersion + " or above."
			);

		try
		{
			Version.validateVersions(strVersion);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}

		try
		{
			Version.validateVersions(strVersion, false);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}

		oExpectedException = 
			new MARFException
			(
				"Your MARF version (" + Version.getStringVersion()
				+ ") is too old. This application requires "
				+ strVersion + " precisely."
			);

		try
		{
			Version.validateVersions(strVersion, true);
		}
		catch(MARFException e)
		{
			assertEquals(oExpectedException.toString(), e.toString());
		}
	}

	/*
	 * Test method for 'marf.Version.validateVersions(String, boolean)'
	 */
	public void testValidateVersionsStringBoolean()
	{
		throw new NotImplementedException();
	}
}

// EOF
