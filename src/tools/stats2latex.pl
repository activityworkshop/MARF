#!/usr/bin/perl -w

#
# Converts CVS stats to LaTeX
# Serguei Mokhov
#
# $Header: /cvsroot/marf/marf/src/tools/stats2latex.pl,v 1.3 2005/06/06 19:30:45 mokhov Exp $
#

use strict;

my $i = 1;
my $part = 1;
my $bFirstLine = 1;

&printHead();

while(<STDIN>)
{
	# Skip first line
	if($bFirstLine)
	{
		$bFirstLine = 0;
		next;
	}

	if($i % 30 == 0)
	{
		&printTail($part);
		&printHead();
		$part++;
	}

	chomp;
	s/,/ \& /g;

	print "$_\\\\ \\hline\n";
	$i++;
}

&printTail($part);

exit 0;

sub printHead()
{
	print <<HEAD;
\\begin{table}
\\begin{minipage}[b]{\\textwidth}
\\centering
\\begin{tabular}{|c|c|l|c|c|r|} \\hline
Guess & Run \\# & Configuration & GOOD & BAD & Recognition Rate,\\\%\\\\ \\hline\\hline
HEAD
}

sub printTail()
{
	my($iTableStripe) = @_;

	print <<TAIL;
\\end{tabular}
\\end{minipage}
\\caption{Consolidated results, Part $iTableStripe.}
\\label{tab:results$iTableStripe}
\\end{table}

TAIL
}

# EOF
