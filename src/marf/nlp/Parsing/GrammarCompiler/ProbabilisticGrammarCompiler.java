package marf.nlp.Parsing.GrammarCompiler;

import marf.nlp.Parsing.CompilerError;
import marf.nlp.Parsing.SyntaxError;


/**
 * <p>Probabilistic Grammar Compiler processes
 * a grammar enhanced with probability tokens
 * assigned to each production.</p>
 *
 * @author Serguei Mokhov
 */
public class ProbabilisticGrammarCompiler
extends GrammarCompiler
{
	/**
	 * For serialization versioning.
	 * When adding new members or make other structural
	 * changes regenerate this number with the
	 * <code>serialver</code> tool that comes with JDK.
	 */
	private static final long serialVersionUID = -5611774474809711992L;

	/**
	 * Constructor with the grammar file filename.
	 * @param pstrGrammarFilename the filename of the grammar file to use
	 * @throws CompilerError if initialization fails
	 */
	public ProbabilisticGrammarCompiler(String pstrGrammarFilename)
	throws CompilerError
	{
		super(pstrGrammarFilename);
	}

	/**
	 * Instantiates <code>ProbabilisticGrammarAnalyzer</code> with the
	 * current grammar.
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarCompiler#createGrammarAnalyzer()
	 * @see ProbabilisticGrammarAnalyzer
	 */
	protected void createGrammarAnalyzer()
	{
		System.out.println("Instantiating Probabilistic Grammar Analyzer.");
		this.oGrammarAnalyzer = new ProbabilisticGrammarAnalyzer(this.strGrammarFileName);
	}

	/**
	 * Compiles probabilistic grammar.
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarCompiler#compileGrammar()
	 * @throws CompilerError in case of parsing or lexical mistakes
	 */
	public void compileGrammar()
	throws CompilerError
	{
		//try
		{
			parseGrammar();

			// Dump Vectors of rules, terminals and non-terminals
			// Dump oGrammar
			//dump();
		}
/*		catch(StorageException e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace(System.err);
			throw new CompilerError(e);
		}*/
	}

	/**
	 * Overridden not to create epsilon tokens for
	 * probabilistic grammars. Doesn't do anything.
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarCompiler#createEpsilonToken()
	 */
	protected void createEpsilonToken()
	{
		// Override to don't do anything
	}

	/**
	 * Overridden to take into account the probability
	 * of grammar productions.
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarCompiler#createRule()
	 * @throws CompilerError if a rule operator ::= or a probability are not found, or there
	 * was syntax error while scanning a token in
	 * @throws NumberFormatException if the probability cannot be parsed as valid double
	 */
	protected void createRule()
	throws CompilerError
	{
		// Create a new grammar rule with the first
		// non-terminal as its LHS, and empty RHS.
		this.oRule = new ProbabilisticRule((NonTerminal)this.oGrammarElement);

		// Pick up next grammar token from the token stream
		this.oToken = this.oGrammarAnalyzer.getNextToken();

		// Must be ::=
		if(((GrammarTokenType)this.oToken.getTokenType()).getSubtype() != ProbabilisticGrammarTokenType.RULE_OP)
		{
			throw new SyntaxError
			(
				"ProbabilisticGrammarCompiler::createRule() - ERROR: Expected rule operator (::=), but got: " +
				this.oToken.getLexeme() +
				", subtype: " + ((GrammarTokenType)this.oToken.getTokenType()).getSubtype() +
				", required: " + GrammarTokenType.RULE_OP
			);
		}

		// Pick up probability
		this.oToken = this.oGrammarAnalyzer.getNextToken();

		if(((GrammarTokenType)this.oToken.getTokenType()).getSubtype() != ProbabilisticGrammarTokenType.NUM)
		{
			throw new SyntaxError
			(
				"ProbabilisticGrammarCompiler::createRule() - ERROR: Expected probability (NUM), but got: " +
				this.oToken.getLexeme()
			);
		}

		((ProbabilisticRule)this.oRule).setProbability(Double.parseDouble(this.oToken.getLexeme()));
	}

	/**
	 * Overridden to possibly take into consideration
	 * the probability tokens. Now off.
	 * @throws SyntaxError if unrecognized token type found
	 * @see marf.nlp.Parsing.GrammarCompiler.GrammarCompiler#addNextRHSElement()
	 */
	protected void addNextRHSElement()
	throws SyntaxError
	{
		switch(this.oToken.getTokenType().getType())
		{
			case ProbabilisticGrammarTokenType.ID:
			{
				if(addIDToken() == true)
				{
					break;
				}
				
				// else let it slide down...
			}

			// Keywords of the MARF as well as Grammar
			// languages (i.e., terminals)
			case ProbabilisticGrammarTokenType.KEYWORD:
			case ProbabilisticGrammarTokenType.PUNCT:
			case ProbabilisticGrammarTokenType.OPERATOR:
			case ProbabilisticGrammarTokenType.BRACKET:
			case ProbabilisticGrammarTokenType.NUM:
			//case ProbabilisticGrammarTokenType.PROBABILITY:
			case ProbabilisticGrammarTokenType.DICT_WORD:
			{
				addTerminalToken();
				break;
			}

			// We got busted right here...
			default:
			{
				getBusted();
			}
		} // switch
	}
}

// EOF
