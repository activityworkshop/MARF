package marf.junit.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;
import marf.Storage.Sample;
import marf.util.Arrays;


/**
 * <p>A unit test case for the Sample class' methods.</p>
 * 
 * $Id: SampleTest.java,v 1.3 2007/01/28 16:00:31 mokhov Exp $
 * 
 * @author Serguei Mokhov
 * @since 0.3.0.6
 * @see Sample
 */
public class SampleTest
extends TestCase
{

	public static void main(String[] args) {
		junit.textui.TestRunner.run(SampleTest.class);
	}

	public SampleTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests whether a Sample class serialized to disk is the same
	 * after reloading it in terms of data items it contains.
	 * It creates a temporary file for the intermediate serialized
	 * sample class, and then the file is automatically removed.
	 *
	 * @throws Exception in case of any I/O error
	 */
	public final void testSampleSerialization()
	throws Exception
	{
		Sample oSample = new Sample(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		//Sample oSample = new Sample();
	
		File oTestFile = File.createTempFile("sample.test.", ".bin");
		
		ObjectOutputStream oOOS = new ObjectOutputStream(new FileOutputStream(oTestFile));
		oOOS.writeObject(oSample);
		oOOS.close();
		
		ObjectInputStream oOIS = new ObjectInputStream(new FileInputStream(oTestFile));
		Sample oLoadedSample = (Sample)oOIS.readObject(); 
		oOIS.close();

		assertEquals(oSample, oLoadedSample);
		//assertTrue(oTestFile.delete());
	}
	
	/*
	 * Test method for 'marf.Storage.Sample.Sample()'
	 */
	public final void testSample() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.Sample(double[])'
	 */
	public final void testSampleDoubleArray() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.Sample(int)'
	 */
	public final void testSampleInt() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.Sample(int, double[])'
	 */
	public final void testSampleIntDoubleArray() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.Sample(Sample)'
	 */
	public final void testSampleSample() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.getAudioFileFormatCode()'
	 */
	public final void testGetAudioFileFormatCode() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.getAudioFormat()'
	 */
	public final void testGetAudioFormat() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.setAudioFileFormatCode(int)'
	 */
	public final void testSetAudioFileFormatCode() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.setSampleArray(double[])'
	 */
	public final void testSetSampleArray() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.getSampleArray()'
	 */
	public final void testGetSampleArray() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.getNextChunk(double[])'
	 */
	public final void testGetNextChunk() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.resetArrayMark()'
	 */
	public final void testResetArrayMark() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.reset()'
	 */
	public final void testReset() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.getSampleSize()'
	 */
	public final void testGetSampleSize() {
		// TODO Auto-generated method stub

	}

	/**
	 * Test method for 'marf.Storage.Sample.setSampleSize(int)'.
	 * Specifically setting sample size, which is larger or smaller
	 * than the current sample size.
	 */
	public final void testSetSampleSize()
	{
		double[] adSampleData = {1, 2, 3, 4};
		double[] adSampleDataTruncated = {1, 2};

		Sample oSample = new Sample(adSampleData);
		
		// Enlarge. First 4 out of 6 should be as in the original
		oSample.setSampleSize(6);
		
		assertEquals(oSample.getSampleArray().length, 6);
		
		for(int i = 0; i < adSampleData.length; i++)
		{
			assertEquals(adSampleData[i], oSample.getSampleArray()[i], 0);
		}
		
		// Tuncate, and check.
		oSample.setSampleSize(2);
		assertTrue(Arrays.equals(adSampleDataTruncated, oSample.getSampleArray()));
	}

	/*
	 * Test method for 'marf.Storage.Sample.clone()'
	 */
	public final void testClone() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'marf.Storage.Sample.toString()'
	 */
	public final void testToString() {
		// TODO Auto-generated method stub

	}

}

// EOF
