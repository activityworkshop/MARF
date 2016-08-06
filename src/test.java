import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import marf.MARF;
import marf.Version;
import marf.Classification.Classification;
import marf.Classification.ClassificationException;
import marf.Classification.ClassificationFactory;
import marf.Classification.IClassification;
import marf.Classification.Distance.ChebyshevDistance;
import marf.Classification.Distance.DiffDistance;
import marf.Classification.Distance.Distance;
import marf.Classification.Distance.EuclideanDistance;
import marf.Classification.Distance.MahalanobisDistance;
import marf.Classification.Distance.MinkowskiDistance;
import marf.Classification.Markov.Markov;
import marf.Classification.NeuralNetwork.Layer;
import marf.Classification.NeuralNetwork.NeuralNetwork;
import marf.Classification.NeuralNetwork.Neuron;
import marf.Classification.RandomClassification.RandomClassification;
import marf.Classification.Stochastic.MaxProbabilityClassifier;
import marf.Classification.Stochastic.Stochastic;
import marf.Classification.Stochastic.ZipfLaw;
import marf.FeatureExtraction.FeatureExtraction;
import marf.FeatureExtraction.FeatureExtractionAggregator;
import marf.FeatureExtraction.FeatureExtractionException;
import marf.FeatureExtraction.FeatureExtractionFactory;
import marf.FeatureExtraction.IFeatureExtraction;
import marf.FeatureExtraction.Cepstral.Cepstral;
import marf.FeatureExtraction.F0.F0;
import marf.FeatureExtraction.FFT.FFT;
import marf.FeatureExtraction.LPC.LPC;
import marf.FeatureExtraction.MinMaxAmplitudes.MinMaxAmplitudes;
import marf.FeatureExtraction.RandomFeatureExtraction.RandomFeatureExtraction;
import marf.FeatureExtraction.Segmentation.Segmentation;
import marf.Preprocessing.IFilter;
import marf.Preprocessing.IPreprocessing;
import marf.Preprocessing.Preprocessing;
import marf.Preprocessing.PreprocessingException;
import marf.Preprocessing.PreprocessingFactory;
import marf.Preprocessing.Dummy.Dummy;
import marf.Preprocessing.Dummy.Raw;
import marf.Preprocessing.Endpoint.Endpoint;
import marf.Preprocessing.FFTFilter.BandpassFilter;
import marf.Preprocessing.FFTFilter.FFTFilter;
import marf.Preprocessing.FFTFilter.HighFrequencyBoost;
import marf.Preprocessing.FFTFilter.HighPassFilter;
import marf.Preprocessing.FFTFilter.LowPassFilter;
import marf.Stats.Ngram;
import marf.Stats.Observation;
import marf.Stats.ProbabilityTable;
import marf.Stats.StatisticalObject;
import marf.Stats.StatsCollector;
import marf.Stats.WordStats;
import marf.Stats.StatisticalEstimators.GLI;
import marf.Stats.StatisticalEstimators.IStatisticalEstimator;
import marf.Stats.StatisticalEstimators.KatzBackoff;
import marf.Stats.StatisticalEstimators.MLE;
import marf.Stats.StatisticalEstimators.SLI;
import marf.Stats.StatisticalEstimators.StatisticalEstimator;
import marf.Stats.StatisticalEstimators.Smoothing.AddDelta;
import marf.Stats.StatisticalEstimators.Smoothing.AddOne;
import marf.Stats.StatisticalEstimators.Smoothing.GoodTuring;
import marf.Stats.StatisticalEstimators.Smoothing.ISmoothing;
import marf.Stats.StatisticalEstimators.Smoothing.Smoothing;
import marf.Stats.StatisticalEstimators.Smoothing.WittenBell;
import marf.Storage.Cluster;
import marf.Storage.Database;
import marf.Storage.FeatureSet;
import marf.Storage.IDatabase;
import marf.Storage.ISampleLoader;
import marf.Storage.IStorageManager;
import marf.Storage.MARFAudioFileFormat;
import marf.Storage.ModuleParams;
import marf.Storage.Result;
import marf.Storage.ResultSet;
import marf.Storage.Sample;
import marf.Storage.SampleLoader;
import marf.Storage.SampleLoaderFactory;
import marf.Storage.StorageException;
import marf.Storage.StorageManager;
import marf.Storage.TrainingSample;
import marf.Storage.TrainingSet;
import marf.Storage.Loaders.AIFFCLoader;
import marf.Storage.Loaders.AIFFLoader;
import marf.Storage.Loaders.AULoader;
import marf.Storage.Loaders.MIDILoader;
import marf.Storage.Loaders.MP3Loader;
import marf.Storage.Loaders.SNDLoader;
import marf.Storage.Loaders.SineLoader;
import marf.Storage.Loaders.ULAWLoader;
import marf.Storage.Loaders.WAVLoader;
import marf.gui.Spectrogram;
import marf.gui.SpectrogramPanel;
import marf.gui.WaveGrapher;
import marf.gui.WaveGrapherPanel;
import marf.gui.util.BorderPanel;
import marf.gui.util.ColoredStatusPanel;
import marf.gui.util.SmartSizablePanel;
import marf.math.Algorithms;
import marf.math.MathException;
import marf.math.Vector;
import marf.nlp.NLPException;
import marf.nlp.Collocations.ChiSquareTest;
import marf.nlp.Collocations.CollocationWindow;
import marf.nlp.Collocations.TTest;
import marf.nlp.Parsing.ClassSymTabEntry;
import marf.nlp.Parsing.CodeGenerator;
import marf.nlp.Parsing.Compiler;
import marf.nlp.Parsing.CompilerError;
import marf.nlp.Parsing.FuncSymTabEntry;
import marf.nlp.Parsing.GenericLexicalAnalyzer;
import marf.nlp.Parsing.LexicalAnalyzer;
import marf.nlp.Parsing.LexicalError;
import marf.nlp.Parsing.Parser;
import marf.nlp.Parsing.ProbabilisticParser;
import marf.nlp.Parsing.SemanticAnalyzer;
import marf.nlp.Parsing.SemanticError;
import marf.nlp.Parsing.SymDataType;
import marf.nlp.Parsing.SymTabEntry;
import marf.nlp.Parsing.SymbolTable;
import marf.nlp.Parsing.SyntaxError;
import marf.nlp.Parsing.Token;
import marf.nlp.Parsing.TokenSubType;
import marf.nlp.Parsing.TokenType;
import marf.nlp.Parsing.TransitionTable;
import marf.nlp.Parsing.VarSymTabEntry;
import marf.nlp.Parsing.GrammarCompiler.Grammar;
import marf.nlp.Parsing.GrammarCompiler.GrammarAnalyzer;
import marf.nlp.Parsing.GrammarCompiler.GrammarCompiler;
import marf.nlp.Parsing.GrammarCompiler.GrammarElement;
import marf.nlp.Parsing.GrammarCompiler.GrammarTokenType;
import marf.nlp.Parsing.GrammarCompiler.NonTerminal;
import marf.nlp.Parsing.GrammarCompiler.ProbabilisticGrammarAnalyzer;
import marf.nlp.Parsing.GrammarCompiler.ProbabilisticGrammarCompiler;
import marf.nlp.Parsing.GrammarCompiler.ProbabilisticGrammarTokenType;
import marf.nlp.Parsing.GrammarCompiler.ProbabilisticRule;
import marf.nlp.Parsing.GrammarCompiler.Rule;
import marf.nlp.Parsing.GrammarCompiler.SemanticToken;
import marf.nlp.Parsing.GrammarCompiler.Terminal;
import marf.nlp.Stemming.IStemming;
import marf.nlp.Stemming.Stemming;
import marf.nlp.Stemming.StemmingEN;
import marf.nlp.util.NLPStreamTokenizer;
import marf.util.Arrays;
import marf.util.BaseThread;
import marf.util.ByteUtils;
import marf.util.Debug;
import marf.util.ExpandedThreadGroup;
import marf.util.FreeVector;
import marf.util.InvalidSampleFormatException;
import marf.util.Logger;
import marf.util.MARFException;
import marf.util.NotImplementedException;
import marf.util.OptionProcessor;
import marf.util.SortComparator;
import marf.util.comparators.FrequencyComparator;
import marf.util.comparators.RankComparator;
import marf.util.comparators.ResultComparator;


/**
 * <p>Startup utility class allowing querying MARF's version
 * class revisions, and copyright.</p>
 *
 * <p>$Id: test.java,v 1.48 2006/01/14 19:06:19 mokhov Exp $</p>
 *
 * @author Serguei Mokhov
 * @version $Revision: 1.48 $
 * @since 0.0.1
 */
public class test
{
	/**
	 * Option number for --help.
	 * @since 0.3.0
	 */
	public static final int OPT_HELP_LONG = 0;

	/**
	 * Option number for -h.
	 * @since 0.3.0.2
	 */
	public static final int OPT_HELP_SHORT = 1;

	/**
	 * Option number for --version.
	 * @since 0.3.0.2
	 */
	public static final int OPT_VERSION = 2;

	/**
	 * Option number for --verbose.
	 * @since 0.3.0.2
	 */
	public static final int OPT_REVISION = 3;

	/**
	 * Option number for --copyright.
	 * @since 0.3.0.2
	 */
	public static final int OPT_COPYRIGHT = 4;

	/**
	 * Main program expecting and processing one or more options.
	 * @param argv command-line options. Accepted values are "--help",
	 * "-h", "--version", "--verbose", and "--copyright".
	 */
	public static void main(String[] argv)
	{
		try
		{
			Debug.enableDebug(false);

			OptionProcessor oGetOpt = new OptionProcessor();

			oGetOpt.addValidOption(OPT_HELP_LONG,  "--help");
			oGetOpt.addValidOption(OPT_HELP_SHORT, "-h");
			oGetOpt.addValidOption(OPT_VERSION,    "--version");
			oGetOpt.addValidOption(OPT_REVISION,   "--verbose");
			oGetOpt.addValidOption(OPT_COPYRIGHT,  "--copyright");

			int iValidOptions = oGetOpt.parse(argv);

			if(oGetOpt.getInvalidOptions().size() > 0)
			{
				System.err.println
				(
					"WARNING: unrecognized option(s) supplied:\n" +
					oGetOpt.getInvalidOptions() + "\n" +
					"Ignored. Supply --help or -h for the list of valid options."
				);
			}
			else if(iValidOptions == 0)
			{
				usage();
				System.exit(0);
			}

			for(int i = 0; i < argv.length; i++)
			{
				Debug.debug("Processing " + argv[i] + "...");
				Debug.debug("Is active? " + oGetOpt.isActiveOption(argv[i]));

				if(oGetOpt.isActiveOption(argv[i]))
				{
					int iOption = oGetOpt.getOption(argv[i]);

					switch(iOption)
					{
						// --help or -h
						case OPT_HELP_LONG:
						case OPT_HELP_SHORT:
						{
							usage();
							break;
						}

						// Query for version; used when generating a distro
						case OPT_VERSION:
						{
							System.out.print(MARF.getVersion());
							break;
						}

						// Verbose class revision information
						case OPT_REVISION:
						{
							revision();
							break;
						}

						case OPT_COPYRIGHT:
						{
							copyright();
							break;
						}

						// Sanity check.
						default:
						{
							throw new MARFException("Unhandled active option: " + iOption);
						}
					}
				}
			}
		}

		// Either a MARFException or some subclass of RuntimeException
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	/**
	 * Returns source code revision information.
	 * @return revision string
	 * @since 0.3.0.2
	 */
	public static String getMARFSourceCodeRevision()
	{
		return "$Revision: 1.48 $";
	}

	/**
	 * Display usage information for all of MARF.
	 * @since 0.3.0.2
	 */
	private static void usage()
	{
		System.out.println
		(
			"\nUsage:\n\n" +
			"\tjava -jar marf.jar <option>\n\n" +
			"Where option is one or more of the following:\n\n" +
			"\t--help | -h    shows this help\n" +
			"\t--version      displays MARF's version\n" +
			"\t--verbose      displays revisions of all MARF classes\n" +
			"\t--copyright    displays copyright information\n"
		);
	}

	/**
	 * Display class revision information for all of MARF.
	 * @since 0.3.0.2
	 */
	private static void revision()
	{
		System.out.println
		(
			"MARF Classes' Revision Information:\n\n" +

			MARF.class.getName() + ": " + MARF.getMARFSourceCodeRevision() + "\n" +
			Version.class.getName() + ": " + Version.getMARFSourceCodeRevision() + "\n" +

			// marf.Preprocessing
			Preprocessing.class.getName() + ": " + Preprocessing.getMARFSourceCodeRevision() + "\n" +
			PreprocessingException.class.getName() + ": " + PreprocessingException.getMARFSourceCodeRevision() + "\n" +
			PreprocessingFactory.class.getName() + ": " + PreprocessingFactory.getMARFSourceCodeRevision() + "\n" +
			FFTFilter.class.getName() + ": " + FFTFilter.getMARFSourceCodeRevision() + "\n" +
			BandpassFilter.class.getName() + ": " + BandpassFilter.getMARFSourceCodeRevision() + "\n" +
			HighFrequencyBoost.class.getName() + ": " + HighFrequencyBoost.getMARFSourceCodeRevision() + "\n" +
			HighPassFilter.class.getName() + ": " + HighPassFilter.getMARFSourceCodeRevision() + "\n" +
			LowPassFilter.class.getName() + ": " + LowPassFilter.getMARFSourceCodeRevision() + "\n" +
			Dummy.class.getName() + ": " + Dummy.getMARFSourceCodeRevision() + "\n" +
			Raw.class.getName() + ": " + Raw.getMARFSourceCodeRevision() + "\n" +
			Endpoint.class.getName() + ": " + Endpoint.getMARFSourceCodeRevision() + "\n" +

			// marf.FeatureExtraction
			FeatureExtraction.class.getName() + ": " + FeatureExtraction.getMARFSourceCodeRevision() + "\n" +
			FeatureExtractionException.class.getName() + ": " + FeatureExtractionException.getMARFSourceCodeRevision() + "\n" +
			FeatureExtractionFactory.class.getName() + ": " + FeatureExtractionFactory.getMARFSourceCodeRevision() + "\n" +
			FFT.class.getName() + ": " + FFT.getMARFSourceCodeRevision() + "\n" +
			LPC.class.getName() + ": " + LPC.getMARFSourceCodeRevision() + "\n" +
			MinMaxAmplitudes.class.getName() + ": " + MinMaxAmplitudes.getMARFSourceCodeRevision() + "\n" +
			F0.class.getName() + ": " + F0.getMARFSourceCodeRevision() + "\n" +
			Cepstral.class.getName() + ": " + Cepstral.getMARFSourceCodeRevision() + "\n" +
			Segmentation.class.getName() + ": " + Segmentation.getMARFSourceCodeRevision() + "\n" +
			RandomFeatureExtraction.class.getName() + ": " + RandomFeatureExtraction.getMARFSourceCodeRevision() + "\n" +
			FeatureExtractionAggregator.class.getName() + ": " + FeatureExtractionAggregator.getMARFSourceCodeRevision() + "\n" +

			// marf.Classification
			Classification.class.getName() + ": " + Classification.getMARFSourceCodeRevision() + "\n" +
			ClassificationException.class.getName() + ": " + ClassificationException.getMARFSourceCodeRevision() + "\n" +
			ClassificationFactory.class.getName() + ": " + ClassificationFactory.getMARFSourceCodeRevision() + "\n" +

			NeuralNetwork.class.getName() + ": " + NeuralNetwork.getMARFSourceCodeRevision() + "\n" +
			Neuron.class.getName() + ": " + Neuron.getMARFSourceCodeRevision() + "\n" +
			Layer.class.getName() + ": " + Layer.getMARFSourceCodeRevision() + "\n" +

			// marf.Classification.Distance
			Distance.class.getName() + ": " + Distance.getMARFSourceCodeRevision() + "\n" +
			ChebyshevDistance.class.getName( ) + ": " + ChebyshevDistance.getMARFSourceCodeRevision() + "\n" +
			EuclideanDistance.class.getName() + ": " + EuclideanDistance.getMARFSourceCodeRevision() + "\n" +
			MinkowskiDistance.class.getName() + ": " + MinkowskiDistance.getMARFSourceCodeRevision() + "\n" +
			MahalanobisDistance.class.getName() + ": " + MahalanobisDistance.getMARFSourceCodeRevision() + "\n" +
			DiffDistance.class.getName() + ": " + DiffDistance.getMARFSourceCodeRevision() + "\n" +

			Stochastic.class.getName() + ": " + Stochastic.getMARFSourceCodeRevision() + "\n" +
			MaxProbabilityClassifier.class.getName() + ": " + MaxProbabilityClassifier.getMARFSourceCodeRevision() + "\n" +
			ZipfLaw.class.getName() + ": " + ZipfLaw.getMARFSourceCodeRevision() + "\n" +
			Markov.class.getName() + ": " + Markov.getMARFSourceCodeRevision() + "\n" +

			RandomClassification.class.getName() + ": " + RandomClassification.getMARFSourceCodeRevision() + "\n" +

			// marf.Storage
			Database.class.getName() + ": " + Database.getMARFSourceCodeRevision() + "\n" +
			Sample.class.getName() + ": " + Sample.getMARFSourceCodeRevision() + "\n" +
			Cluster.class.getName() + ": " + Cluster.getMARFSourceCodeRevision() + "\n" +
			Result.class.getName() + ": " + Result.getMARFSourceCodeRevision() + "\n" +
			ResultSet.class.getName() + ": " + ResultSet.getMARFSourceCodeRevision() + "\n" +
			FeatureSet.class.getName() + ": " + FeatureSet.getMARFSourceCodeRevision() + "\n" +
			MARFAudioFileFormat.class.getName() + ": " + MARFAudioFileFormat.getMARFSourceCodeRevision() + "\n" +
			SampleLoader.class.getName() + ": " + SampleLoader.getMARFSourceCodeRevision() + "\n" +
			SampleLoaderFactory.class.getName() + ": " + SampleLoaderFactory.getMARFSourceCodeRevision() + "\n" +
			StorageException.class.getName() + ": " + StorageException.getMARFSourceCodeRevision() + "\n" +
			StorageManager.class.getName() + ": " + StorageManager.getMARFSourceCodeRevision() + "\n" +
			ModuleParams.class.getName() + ": " + ModuleParams.getMARFSourceCodeRevision() + "\n" +
			TrainingSample.class.getName() + ": " + TrainingSample.getMARFSourceCodeRevision() + "\n" +
			TrainingSet.class.getName() + ": " + TrainingSet.getMARFSourceCodeRevision() + "\n" +

			// marf.Storage.Loaders
			MP3Loader.class.getName() + ": " + MP3Loader.getMARFSourceCodeRevision() + "\n" +
			SineLoader.class.getName() + ": " + SineLoader.getMARFSourceCodeRevision() + "\n" +
			ULAWLoader.class.getName() + ": " + ULAWLoader.getMARFSourceCodeRevision() + "\n" +
			WAVLoader.class.getName() + ": " + WAVLoader.getMARFSourceCodeRevision() + "\n" +
			AIFFCLoader.class.getName() + ": " + AIFFCLoader.getMARFSourceCodeRevision() + "\n" +
			AIFFLoader.class.getName() + ": " + WAVLoader.getMARFSourceCodeRevision() + "\n" +
			AULoader.class.getName() + ": " + AULoader.getMARFSourceCodeRevision() + "\n" +
			MIDILoader.class.getName() + ": " + MIDILoader.getMARFSourceCodeRevision() + "\n" +
			SNDLoader.class.getName() + ": " + SNDLoader.getMARFSourceCodeRevision() + "\n" +

			// marf.Stats
			StatsCollector.class.getName() + ": " + StatsCollector.getMARFSourceCodeRevision() + "\n" +
			Ngram.class.getName() + ": " + Ngram.getMARFSourceCodeRevision() + "\n" +
			Observation.class.getName() + ": " + Observation.getMARFSourceCodeRevision() + "\n" +
			ProbabilityTable.class.getName() + ": " + ProbabilityTable.getMARFSourceCodeRevision() + "\n" +
			StatisticalObject.class.getName() + ": " + StatisticalObject.getMARFSourceCodeRevision() + "\n" +
			WordStats.class.getName() + ": " + WordStats.getMARFSourceCodeRevision() + "\n" +

			GLI.class.getName() + ": " + GLI.getMARFSourceCodeRevision() + "\n" +
			KatzBackoff.class.getName() + ": " + KatzBackoff.getMARFSourceCodeRevision() + "\n" +
			MLE.class.getName() + ": " + MLE.getMARFSourceCodeRevision() + "\n" +
			SLI.class.getName() + ": " + SLI.getMARFSourceCodeRevision() + "\n" +
			StatisticalEstimator.class.getName() + ": " + StatisticalEstimator.getMARFSourceCodeRevision() + "\n" +

			Smoothing.class.getName() + ": " + Smoothing.getMARFSourceCodeRevision() + "\n" +
			AddDelta.class.getName() + ": " + AddDelta.getMARFSourceCodeRevision() + "\n" +
			AddOne.class.getName() + ": " + AddOne.getMARFSourceCodeRevision() + "\n" +
			GoodTuring.class.getName() + ": " + GoodTuring.getMARFSourceCodeRevision() + "\n" +
			WittenBell.class.getName() + ": " + WittenBell.getMARFSourceCodeRevision() + "\n" +

			// marf.math
			Vector.class.getName() + ": " + Vector.getMARFSourceCodeRevision() + "\n" +
			marf.math.Matrix.class.getName() + ": " + marf.math.Matrix.getMARFSourceCodeRevision() + "\n" +
			MathException.class.getName() + ": " + MathException.getMARFSourceCodeRevision() + "\n" +
			Algorithms.class.getName() + ": " + Algorithms.getMARFSourceCodeRevision() + "\n" +

			// marf.util
			BaseThread.class.getName() + ": " + BaseThread.getMARFSourceCodeRevision() + "\n" +
			ExpandedThreadGroup.class.getName() + ": " + ExpandedThreadGroup.getMARFSourceCodeRevision() + "\n" +
			FreeVector.class.getName() + ": " + FreeVector.getMARFSourceCodeRevision() + "\n" +
			marf.util.Matrix.class.getName() + ": " + marf.util.Matrix.getMARFSourceCodeRevision() + "\n" +
			Debug.class.getName() + ": " + Debug.getMARFSourceCodeRevision() + "\n" +
			Logger.class.getName() + ": " + Logger.getMARFSourceCodeRevision() + "\n" +
			Arrays.class.getName() + ": " + Arrays.getMARFSourceCodeRevision() + "\n" +
			ByteUtils.class.getName() + ": " + ByteUtils.getMARFSourceCodeRevision() + "\n" +
			InvalidSampleFormatException.class.getName() + ": " + InvalidSampleFormatException.getMARFSourceCodeRevision() + "\n" +
			MARFException.class.getName() + ": " + MARFException.getMARFSourceCodeRevision() + "\n" +
			NotImplementedException.class.getName() + ": " + NotImplementedException.getMARFSourceCodeRevision() + "\n" +
			OptionProcessor.class.getName() + ": " + OptionProcessor.getMARFSourceCodeRevision() + "\n" +
			SortComparator.class.getName() + ": " + SortComparator.getMARFSourceCodeRevision() + "\n" +

			// marf.util.comparators
			RankComparator.class.getName() + ": " + RankComparator.getMARFSourceCodeRevision() + "\n" +
			FrequencyComparator.class.getName() + ": " + FrequencyComparator.getMARFSourceCodeRevision() + "\n" +
			ResultComparator.class.getName() + ": " + ResultComparator.getMARFSourceCodeRevision() + "\n" +

			// marf.nlp
			NLPException.class.getName() + ": " + NLPException.getMARFSourceCodeRevision() + "\n" +
			Compiler.class.getName() + ": " + Compiler.getMARFSourceCodeRevision() + "\n" +
			CompilerError.class.getName() + ": " + CompilerError.getMARFSourceCodeRevision() + "\n" +
			GenericLexicalAnalyzer.class.getName() + ": " + GenericLexicalAnalyzer.getMARFSourceCodeRevision() + "\n" +
			LexicalAnalyzer.class.getName() + ": " + LexicalAnalyzer.getMARFSourceCodeRevision() + "\n" +
			LexicalError.class.getName() + ": " + LexicalError.getMARFSourceCodeRevision() + "\n" +
			SymbolTable.class.getName() + ": " + SymbolTable.getMARFSourceCodeRevision() + "\n" +
			SymDataType.class.getName() + ": " + SymDataType.getMARFSourceCodeRevision() + "\n" +
			SymTabEntry.class.getName() + ": " + SymTabEntry.getMARFSourceCodeRevision() + "\n" +
			SyntaxError.class.getName() + ": " + SyntaxError.getMARFSourceCodeRevision() + "\n" +
			Token.class.getName() + ": " + Token.getMARFSourceCodeRevision() + "\n" +
			TokenSubType.class.getName() + ": " + TokenSubType.getMARFSourceCodeRevision() + "\n" +
			TokenType.class.getName() + ": " + TokenType.getMARFSourceCodeRevision() + "\n" +
			TransitionTable.class.getName() + ": " + TransitionTable.getMARFSourceCodeRevision() + "\n" +

			Parser.class.getName() + ": " + Parser.getMARFSourceCodeRevision() + "\n" +
			ProbabilisticParser.class.getName() + ": " + ProbabilisticParser.getMARFSourceCodeRevision() + "\n" +
			SemanticAnalyzer.class.getName() + ": " + SemanticAnalyzer.getMARFSourceCodeRevision() + "\n" +
			SemanticError.class.getName() + ": " + SemanticError.getMARFSourceCodeRevision() + "\n" +
			CodeGenerator.class.getName() + ": " + CodeGenerator.getMARFSourceCodeRevision() + "\n" +
			ClassSymTabEntry.class.getName() + ": " + ClassSymTabEntry.getMARFSourceCodeRevision() + "\n" +
			FuncSymTabEntry.class.getName() + ": " + FuncSymTabEntry.getMARFSourceCodeRevision() + "\n" +
			VarSymTabEntry.class.getName() + ": " + VarSymTabEntry.getMARFSourceCodeRevision() + "\n" +

			ProbabilisticGrammarCompiler.class.getName() + ": " + ProbabilisticGrammarCompiler.getMARFSourceCodeRevision() + "\n" +
			Grammar.class.getName() + ": " + Grammar.getMARFSourceCodeRevision() + "\n" +
			GrammarAnalyzer.class.getName() + ": " + GrammarAnalyzer.getMARFSourceCodeRevision() + "\n" +
			GrammarCompiler.class.getName() + ": " + GrammarCompiler.getMARFSourceCodeRevision() + "\n" +
			GrammarElement.class.getName() + ": " + GrammarElement.getMARFSourceCodeRevision() + "\n" +
			GrammarTokenType.class.getName() + ": " + GrammarTokenType.getMARFSourceCodeRevision() + "\n" +
			NonTerminal.class.getName() + ": " + NonTerminal.getMARFSourceCodeRevision() + "\n" +
			ProbabilisticGrammarAnalyzer.class.getName() + ": " + ProbabilisticGrammarAnalyzer.getMARFSourceCodeRevision() + "\n" +
			ProbabilisticGrammarTokenType.class.getName() + ": " + ProbabilisticGrammarTokenType.getMARFSourceCodeRevision() + "\n" +
			ProbabilisticRule.class.getName() + ": " + ProbabilisticRule.getMARFSourceCodeRevision() + "\n" +
			Rule.class.getName() + ": " + Rule.getMARFSourceCodeRevision() + "\n" +
			SemanticToken.class.getName() + ": " + SemanticToken.getMARFSourceCodeRevision() + "\n" +
			Terminal.class.getName() + ": " + Terminal.getMARFSourceCodeRevision() + "\n" +


			CollocationWindow.class.getName() + ": " + CollocationWindow.getMARFSourceCodeRevision() + "\n" +
			TTest.class.getName() + ": " + TTest.getMARFSourceCodeRevision() + "\n" +
			ChiSquareTest.class.getName() + ": " + ChiSquareTest.getMARFSourceCodeRevision() + "\n" +

			Stemming.class.getName() + ": " + Stemming.getMARFSourceCodeRevision() + "\n" +
			StemmingEN.class.getName() + ": " + StemmingEN.getMARFSourceCodeRevision() + "\n" +
			NLPStreamTokenizer.class.getName() + ": " + NLPStreamTokenizer.getMARFSourceCodeRevision() + "\n" +

			// marf.gui
			Spectrogram.class.getName() + ": " + Spectrogram.getMARFSourceCodeRevision() + "\n" +
			SpectrogramPanel.class.getName() + ": " + SpectrogramPanel.getMARFSourceCodeRevision() + "\n" +
			WaveGrapher.class.getName() + ": " + WaveGrapher.getMARFSourceCodeRevision() + "\n" +
			WaveGrapherPanel.class.getName() + ": " + WaveGrapherPanel.getMARFSourceCodeRevision() + "\n" +

			// marf.gui.util
			BorderPanel.class.getName() + ": " + BorderPanel.getMARFSourceCodeRevision() + "\n" +
			SmartSizablePanel.class.getName() + ": " + SmartSizablePanel.getMARFSourceCodeRevision() + "\n" +
			ColoredStatusPanel.class.getName() + ": " + ColoredStatusPanel.getMARFSourceCodeRevision() + "\n" +

			test.class.getName() + ": " + getMARFSourceCodeRevision() + "\n\n" +

			"MARF Interfaces' Revision Information:\n\n" +

			IPreprocessing.class.getName() + ": " + IPreprocessing.MARF_INTERFACE_CODE_REVISION + "\n" +
			IFilter.class.getName() + ": " + IFilter.MARF_INTERFACE_CODE_REVISION + "\n" +
			IFeatureExtraction.class.getName() + ": " + IFeatureExtraction.MARF_INTERFACE_CODE_REVISION + "\n" +
			IClassification.class.getName() + ": " + IClassification.MARF_INTERFACE_CODE_REVISION + "\n" +
			IStorageManager.class.getName() + ": " + IStorageManager.MARF_INTERFACE_CODE_REVISION + "\n" +
			ISampleLoader.class.getName() + ": " + ISampleLoader.MARF_INTERFACE_CODE_REVISION + "\n" +
			IDatabase.class.getName() + ": " + IDatabase.MARF_INTERFACE_CODE_REVISION + "\n" +
			IStatisticalEstimator.class.getName() + ": " + IStatisticalEstimator.MARF_INTERFACE_CODE_REVISION + "\n" +
			ISmoothing.class.getName() + ": " + ISmoothing.MARF_INTERFACE_CODE_REVISION + "\n" +
			IStemming.class.getName() + ": " + IStemming.MARF_INTERFACE_CODE_REVISION + "\n" +

			""
		);
	}

	/**
	 * Display copyright information. First attempts
	 * opening and reading the COPYRIGHT file. If this
	 * does not succeed in anyway, print a short embedded
	 * copyright notice and the URL of MARF.
	 *
	 * @since 0.3.0.2
	 */
	private static void copyright()
	{
		String strGenericCopyright =
			"Copyright (c) 2002 - 2006 The MARF Research and Development Group.\n" +
			"Visit http://marf.sourceforge.net for details.";

		try
		{
			// Attempt to read the COPYRIGHT file from the marf.jar file first
			// and then in case of any error fall back to the default generic message.
			JarInputStream oMarfJar = new JarInputStream(new FileInputStream("marf.jar"));

			JarEntry oJarEntry;

			while((oJarEntry = oMarfJar.getNextJarEntry()) != null)
			{
				if(oJarEntry.getName().equals("COPYRIGHT"))
				{
					int iChar;

					while((iChar = oMarfJar.read()) != -1)
					{
						System.out.print((char)iChar);
					}

					return;
				}
			}

			throw new RuntimeException("The .jar file did not contain COPYRIGHT.");
		}
		catch(Throwable e)
		{
			System.out.println(strGenericCopyright);
		}
	}
}

// EOF
