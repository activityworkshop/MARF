package marf.junit.math;

import static org.junit.Assert.fail;
import marf.math.MathException;
import marf.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Serguei Mokhov
 * @since June 3, 2012
 */
public class Algorithms
{
	public static final double[] sdInitialSampleSignal =
	{
		  1.1,    3.0,   4.0,   5.0,   6.0,   7.0,   8.0,
		  9.0,   10.0, -30.0,  31.0, -32.0,  33.0, -34.0,
		  35.0, -36.0,  37.0, -38.0,  39.0, -40.0, 20.0,
		  21.0,  22.0,  23.0,  24.0,  25.0,  26.0,  27.0,
		  28.0,  29.0,  50.0,  51.0
	};

	/**
	 * By 4 (-4).
	 */
	public static final double[] sdLeftShiftedSampleSignal =
	{
		                               6.0,   7.0,   8.0,
		  9.0,   10.0, -30.0,  31.0, -32.0,  33.0, -34.0,
		  35.0, -36.0,  37.0, -38.0,  39.0, -40.0, 20.0,
		  21.0,  22.0,  23.0,  24.0,  25.0,  26.0,  27.0,
		  28.0,  29.0,  50.0,  51.0,

		  1.1,    3.0,   4.0,   5.0,
	};

	/**
	 * By 4.
	 */
	public static final double[] sdRightShiftedSampleSignal =
	{
		  28.0,  29.0,  50.0,  51.0,
		  
		  1.1,    3.0,   4.0,   5.0,   6.0,   7.0,   8.0,
		  9.0,   10.0, -30.0,  31.0, -32.0,  33.0, -34.0,
		  35.0, -36.0,  37.0, -38.0,  39.0, -40.0, 20.0,
		  21.0,  22.0,  23.0,  24.0,  25.0,  26.0,  27.0
	};
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp()
	throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown()
	throws Exception
	{
	}

	/*
	 * ---------------------------------------
	 * Tests of the Algorithms.Wavelet methods 
	 * ---------------------------------------
	 */
	
	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#applyWaveletTransform(double[], double[], double[], double[], int)}.
	 */
	@Test
	public void testApplyWaveletTransform()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#afb(double[], double[][])}.
	 */
	@Test
	public void testAfb()
	throws Exception
	{
		///*
		double[][] adLoHi = marf.math.Algorithms.Wavelet.afb
		(
			sdInitialSampleSignal,
			marf.math.Algorithms.Wavelet.af
		);
		
		double[][] adExpectedLoHi = 
		{
			{
				3.493035107734180,   7.476375421596027,   9.092469705267412,
				15.554923196531979,  -8.114797233204499,   1.881460864063819,
				-0.213309050898237,   0.000000000000019,   0.000000000000019,
				-7.424621202458860,  21.448784946375856,  31.303372714377456,
				34.558417940920137,  37.386845065666293,  40.215272190412463,
				70.799349363635130
			},
			{
				-9.465126754962579,   0.079549512883508,   0.460298478254160,
				-2.905407555818540,  26.996404377910419,  46.316920157290419,
				47.465968676718497,  50.294395801464660,  52.179772385397456,
				58.675602942762893,   3.358757210636180,   0.000000000000019,
				0.000000000000019,   0.000000000000019,   3.525429792995532,
				-5.382850371782672
			}
		};

		System.err.println("afb(0): " + Arrays.arrayToCSV(adLoHi[0]));
		System.err.println("expt.0: " + Arrays.arrayToCSV(adExpectedLoHi[0]));
		System.err.println("afb(1): " + Arrays.arrayToCSV(adLoHi[1]));
		System.err.println("expt.1: " + Arrays.arrayToCSV(adExpectedLoHi[1]));
		
		Assert.assertTrue(Arrays.equals(adLoHi[0], adExpectedLoHi[0]));
		Assert.assertTrue(Arrays.equals(adLoHi[1], adExpectedLoHi[1]));
		//*/
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#sfb(double[], double[], double[][])}.
	 */
	@Test
	public void testSfb()
	throws Exception
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#farras()}.
	 */
	@Test
	public void testFarras()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#add(double[], double[])}.
	 */
	@Test
	public void testAdd()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#cshift(double[], int)}.
	 */
	@Test
	public void testCshift()
	{
		double[] adShiftedSignal = marf.math.Algorithms.Wavelet.cshift(sdInitialSampleSignal, -4);

		//System.err.println(Arrays.arrayToCSV(sdInitialSampleSignal));
		//System.err.println(Arrays.arrayToCSV(sdLeftShiftedSampleSignal));
		//System.err.println(Arrays.arrayToCSV(adShiftedSignal));

		Assert.assertTrue(Arrays.equals(sdLeftShiftedSampleSignal, adShiftedSignal));
		Assert.assertEquals(Arrays.arrayToCSV(sdLeftShiftedSampleSignal), Arrays.arrayToCSV(adShiftedSignal));
		
		adShiftedSignal = marf.math.Algorithms.Wavelet.cshift(sdInitialSampleSignal, 4);
		//System.err.println(Arrays.arrayToCSV(sdRightShiftedSampleSignal));
		//System.err.println(Arrays.arrayToCSV(adShiftedSignal));
		Assert.assertTrue(Arrays.equals(sdRightShiftedSampleSignal, adShiftedSignal));
		
		adShiftedSignal = marf.math.Algorithms.Wavelet.cshift(sdInitialSampleSignal, -5);

		double[] adExpectedShiftedMinusFive =
		{
			  7.0000,    8.0000,    9.0000,   10.0000,  -30.0000,   31.0000,  -32.0000,
			 33.0000,  -34.0000,   35.0000,  -36.0000,   37.0000,  -38.0000,   39.0000,
			-40.0000,   20.0000,   21.0000,   22.0000,   23.0000,   24.0000,   25.0000,
			 26.0000,   27.0000,   28.0000,   29.0000,   50.0000,   51.0000,    1.1000,
			  3.0000,    4.0000,    5.0000,    6.0000,
		};
		
		//System.err.println("cshift(-5): " + Arrays.arrayToCSV(adShiftedSignal));
		//System.err.println("expect(-5): " + Arrays.arrayToCSV(adExpectedShiftedMinusFive));
		Assert.assertTrue(Arrays.equals(adShiftedSignal, adExpectedShiftedMinusFive));
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#soft(double[], int)}.
	 */
	@Test
	public void testSoft()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#dwt(double[], int, double[][])}.
	 */
	@Test
	public void testDwt()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#idwt(double[][], int, double[][])}.
	 */
	@Test
	public void testIdwt()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#upfirdn(int, int, double[], double[])}.
	 * @throws Exception 
	 */
	@Test
	public void testUpfirdnIntIntDoubleArrayDoubleArray()
	throws Exception
	{
		/*
		double[] adExpectedLo = 
		{
			 0,      -0.0972,  0.6771,  4.8800,  7.6670, 10.5168,  8.0419,
			-4.7926,  0.2021, -0.0000, -0.0000,  1.8562, -7.1889, 35.1490,
			32.4818, 35.9726, 37.0333, 60.1433, 37.9635, -3.6209,  0.5726
		};
		*/
		
		double[] adExpectedLo = 
		{
			 0.000000000000000,  -0.09722718241315201,   0.6770796402890802,
			 4.879998758002832,   7.66702935074179200,  10.5167873805777780,
			 8.041913646424740,  -4.792572378170382,   0.2020822587456994,
			-0.000000000000021,  -0.000000000000021,   1.8561553006147014,
			-7.188858567255139,  35.148978831865421,  32.481823641547201,
			35.972631503293222,  37.033291675072974,  60.143279611178173,
			37.963508309738422,  -3.620889150013660,   0.572566399779540
		};

		double[] adLo = marf.math.Algorithms.Wavelet.upfirdn
		(
			sdInitialSampleSignal, 
			marf.math.Algorithms.Wavelet.af[0],
			1,
			2
		);
		
		//System.err.println("upfirdn(): " + Arrays.arrayToCSV(adLo));
		//System.err.println("expected : " + Arrays.arrayToCSV(adExpectedLo));
		
		double PRECISION_THRESHOLD = 0.0000000001;
		
		//System.err.println("adLo.length: " + adLo.length + ", adExpectedLo.length: " + adExpectedLo.length);
		Assert.assertEquals(adLo.length, adExpectedLo.length);
		
		for(int i = 0; i < adLo.length; i++)
		{
			Assert.assertTrue(Math.abs(adLo[i] - adExpectedLo[i]) < PRECISION_THRESHOLD);
		}
	}

	/**
	 * Test method for {@link marf.math.Algorithms.Wavelet#upfirdn(double[], double[], int, int)}.
	 * @throws Exception 
	 */
	@Test
	public void testUpfirdnDoubleArrayDoubleArrayIntInt()
	throws Exception
	{
		testUpfirdnIntIntDoubleArrayDoubleArray();
	}

}

// EOF
