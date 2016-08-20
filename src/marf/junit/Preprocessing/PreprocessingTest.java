package marf.junit.Preprocessing;

import marf.Preprocessing.Preprocessing;
import marf.util.Arrays;
import junit.framework.TestCase;


/**
 * <p>Unit-tests some methods of the marf.Preprocessing.Preprocessing class.</p>
 * 
 * @author Serguei Mokhov
 * @see marf.Preprocessing.Preprocessing
 * @since 0.3.0.6
 */
public class PreprocessingTest
extends TestCase
{

	double[] adEmpty = {};
	double[] adOne = {1};
	double[] adTwoSame = {1, 1};
	double[] adTwoDifferent = {1, 2};
	
	// Assume the silence threshold is 2
	// Values of 2 and below should be removed
	double dSilenceThreshold = 2;

	double[] adSample = {1, 2, 3, 1, 2, 3, 4, 1, 4, 1};
	double[] adSampleCompressedNone = {1, 2, 3, 1, 2, 3, 4, 1, 4, 1};
	double[] adSampleNoSilence = {3, 3, 4, 4};
	double[] adSampleCompressed = {3, 4};
	

	public static void main(String[] args) {
		junit.textui.TestRunner.run(PreprocessingTest.class);
	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.backSynchronizeObject()'
	 */
	public final void testBackSynchronizeObject() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.clone()'
	 */
	public final void testClone() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.Preprocessing()'
	 */
	public final void testPreprocessing() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.Preprocessing(Sample)'
	 */
	public final void testPreprocessingSample() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.Preprocessing(IPreprocessing)'
	 */
	public final void testPreprocessingIPreprocessing() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.preprocess()'
	 */
	public final void testPreprocess() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.removeNoise()'
	 */
	public final void testRemoveNoise() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.removeSilence()'
	 */
	public final void testRemoveSilence() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.normalize()'
	 */
	public final void testNormalize() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.normalize(int)'
	 */
	public final void testNormalizeInt() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.normalize(int, int)'
	 */
	public final void testNormalizeIntInt() {

	}

	/**
	 * Test method for 'marf.Preprocessing.Preprocessing.compress(double[])'.
	 */
	public final void testCompressDoubleArray()
	{
		assertEquals(Preprocessing.compress(adEmpty).length, 0);
		assertEquals(Preprocessing.compress(adOne).length, 1);
		assertEquals(Preprocessing.compress(adTwoSame).length, 1);
		assertEquals(Preprocessing.compress(adSample).length, adSample.length);
		assertEquals(Preprocessing.compress(adSampleNoSilence).length, 2);

		assertTrue(Arrays.equals(adEmpty, Preprocessing.compress(adEmpty)));
		assertTrue(Arrays.equals(adOne, Preprocessing.compress(adOne)));
		assertTrue(Arrays.equals(adOne, Preprocessing.compress(adTwoSame)));
		assertTrue(Arrays.equals(adSampleCompressedNone, Preprocessing.compress(adSample)));
		assertTrue(Arrays.equals(adSampleCompressed, Preprocessing.compress(adSampleNoSilence)));
	}

	/**
	 * Test method for 'marf.Preprocessing.Preprocessing.removeSilence(double[], double)'.
	 */
	public final void testRemoveSilenceDoubleArrayDouble()
	{
		assertTrue(Arrays.equals(adEmpty, Preprocessing.removeSilence(adEmpty, dSilenceThreshold)));
		assertTrue(Arrays.equals(adEmpty, Preprocessing.removeSilence(adOne, dSilenceThreshold)));
		assertTrue(Arrays.equals(adEmpty, Preprocessing.removeSilence(adTwoSame, dSilenceThreshold)));
		assertTrue(Arrays.equals(adSampleNoSilence, Preprocessing.removeSilence(adSample, dSilenceThreshold)));
	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.compress()'
	 */
	public final void testCompress() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.cropAudio(double, double)'
	 */
	public final void testCropAudio() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.getSample()'
	 */
	public final void testGetSample() {

	}

	/*
	 * Test method for 'marf.Preprocessing.Preprocessing.setSample(Sample)'
	 */
	public final void testSetSample() {

	}

}
