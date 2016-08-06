# MARF LaTeX Documentation
# Use with GNU make
#
# Serguei Mokhov
#
# Copyright (c) 2002 - 2006 The MARF Research and Development Group
#
# $Header: /cvsroot/marf/marf/doc/src/tex/Makefile,v 1.25 2006/02/26 21:36:34 mokhov Exp $

#
# Settings
#

#PDFLATEXFLAGS = --interaction=batchmode --file-line-error-style
PDFLATEXFLAGS = --interaction=batchmode

APPDIR = ../../../../apps/SpeakerIdentApp
APPFILES = SpeakerIdentApp.tex SpeakersIdentDb.tex testing-sh.tex

ARCHDIR = ../graphics/arch
ARCHIMAGES := \
	arch-general.png \
	classification.png \
	feature-extraction.png \
	gui.png \
	packages.png \
	pipeline-flow.png \
	pipeline.png \
	preprocessing.png \
	storage.png

ZIPFDIR = ../graphics/zipf
ZIPFIMAGES := \
	ZipfLaw_java_case_num_eos.png \
	ZipfLaw_java_default.png \
	grfst10_txt_case_num_eos.png \
	grfst10_txt_default.png \
	hwswc10_txt_case_num_eos.png \
	hwswc10_txt_default.png \
	mokhov_case_num_eos.png \
	mokhov_default.png \
	multiprocessor_txt_case_num_eos.png \
	multiprocessor_txt_default.png \
	ulysses_txt_case_num_eos.png \
	ulysses_txt_default.png

IMGFILES := \
	$(ARCHDIR/ARCHIMAGES:%=%.png) \
	$(ZIPFDIR/ZIPFIMAGES:%=%.png)

LANGS := \
	test.langs \
	test.latin.langs \
	test.non-latin.langs \
	test.pls.langs

LANGDIRS = ../../../../apps/LangIdentApp/test
LANGFILES := $(LANGDIRS/LANGS:%=%.langs)
LANGTEXFILES := $(LANGS:%=%.tex)

GRAMMARS := \
	grammar.asmt \
	grammar.extended \
	grammar.original

GRAMMARDIRS = ../../../../apps/ProbabilisticParsingApp/grammars
GRAMMARFILES := $(GRAMMARDIRS/GRAMMARS:%=%.txt)
GRAMMARTEXFILES := $(GRAMMARS:%=%.tex)

SENTENCES := \
	asmt-sentences \
	test-sentences

SENTENCEDIRS = ../../../../apps/ProbabilisticParsingApp/data
SENTENCEFILES := $(SENTENCEDIRS/GRAMMARS:%=%.txt)
SENTENCETEXFILES := $(SENTENCES:%=%.tex)


#
# Build
#

all: report

report: date.tex $(LANGTEXFILES) $(GRAMMARTEXFILES) $(SENTENCETEXFILES) images
	@echo "Compiling *.tex files..."
	pdflatex $(PDFLATEXFLAGS) report
	@echo "Compiling bibliography..."
	bibtex report
	@echo "Making index..."
	makeindex report
	@echo "Updating cross-references..."
	pdflatex $(PDFLATEXFLAGS) report
	pdflatex $(PDFLATEXFLAGS) report

date.tex: \
        report.tex \
        intro \
        methodology \
        gui \
        experiments \
        references.tex \
        appendix
	@echo "Time-stamping..."
	date > date.tex

intro: \
        toc.tex \
        intro.tex \
        architecture.tex \
        legal.tex

methodology: \
        methodology.tex \
        storage \
        preprocessing \
        feature-extraction \
        classification

storage: storage.tex sampleloading.tex

preprocessing: \
        preprocessing.tex \
        normalization.tex \
        fft-filter.tex \
        high-frequency-boost.tex \
        high-pass.tex \
        low-pass.tex \
        bandpass-filter.tex \
        noise-removal.tex

feature-extraction: \
        feature-extraction.tex \
        hamming.tex \
        fft.tex \
        lpc.tex \
        f0.tex \
        random-feature-extraction.tex

classification: \
        classification.tex \
        chebyshev-distance.tex \
        euclidean-distance.tex \
        minkowski-distance.tex \
        mahalanobis-distance.tex \
        random-classification.tex \
        nnet.tex

gui: gui.tex wave-grapher.tex spectrogram.tex

experiments: \
        experimentation.tex \
        data.tex \
        results.tex \
        stats.tex \
        stats-date.tex \
        best-score.tex \
        conclusions.tex

appendix: appendix.tex cvs.tex app todo

#
# Support Files
#

images:  $(ARCHIMAGES) $(ZIPFIMAGES)

$(ARCHIMAGES):
	ln -s $(ARCHDIR)/$@ $@

$(ZIPFIMAGES):
	ln -s $(ZIPFDIR)/$@ $@

$(LANGTEXFILES):
	@echo "Preparing $@ ..."
	@echo "\begin{verbatim}" > $@
	@cat $< | perl -e "while(<STDIN>){s/\t/    /g; print}" >> $@
	@echo "\end{verbatim}" >> $@

test.langs.tex: $(LANGDIRS)/test.langs
test.latin.langs.tex: $(LANGDIRS)/test.latin.langs
test.non-latin.langs.tex: $(LANGDIRS)/test.non-latin.langs
test.pls.langs.tex: $(LANGDIRS)/test.pls.langs

$(GRAMMARTEXFILES):
	@echo "Preparing grammar file $@ for LaTeX..."
	@echo "\begin{verbatim}" > $@
	@cat $< | grep -v '#' | grep -E '[:space:].*' | perl -e "while(<STDIN>){s/\t/    /g; print}" >> $@
	@echo "\end{verbatim}" >> $@

grammar.asmt.tex: $(GRAMMARDIRS)/grammar.asmt.txt
grammar.extended.tex: $(GRAMMARDIRS)/grammar.extended.txt
grammar.original.tex: $(GRAMMARDIRS)/grammar.original.txt

$(SENTENCETEXFILES):
	@echo "Preparing sentence file $@ for LaTeX..."
	@echo "\scriptsize" > $@
	@cat $< | perl -e 'while(<STDIN>){print; print "\n\\par\n";}' >> $@
	@echo "\normalsize" >> $@

asmt-sentences.tex: $(SENTENCEDIRS)/asmt-sentences.txt
test-sentences.tex: $(SENTENCEDIRS)/test-sentences.txt

#
# Make up app's tex files from the source
#
# For the time being there's a hack to replace tabs with 4 spaces because
# LaTeX's verbatim mode seems to ignore them.
#

# NOTE: this requires presence of the application sources

app: SpeakerIdentApp.tex SpeakersIdentDb.tex testing-sh.tex

SpeakerIdentApp.tex: $(APPDIR)/SpeakerIdentApp.java
	@echo "Preparing application files for insertion..."
	@echo "\begin{verbatim}" > SpeakerIdentApp.tex
	@cat $(APPDIR)/SpeakerIdentApp.java | perl -e "while(<STDIN>){s/\t/    /g; print}" >> SpeakerIdentApp.tex
	@echo "\end{verbatim}" >> SpeakerIdentApp.tex

SpeakersIdentDb.tex: $(APPDIR)/SpeakersIdentDb.java
	@echo "\begin{verbatim}" > SpeakersIdentDb.tex
	@cat $(APPDIR)/SpeakersIdentDb.java | perl -e "while(<STDIN>){s/\t/    /g; print}" >> SpeakersIdentDb.tex
	@echo "\end{verbatim}" >> SpeakersIdentDb.tex

testing-sh.tex: $(APPDIR)/testing.sh
	@echo "\begin{verbatim}" > testing-sh.tex
	@cat $(APPDIR)/testing.sh | perl -e "while(<STDIN>){s/\t/    /g; print}" >> testing-sh.tex
	@echo "\end{verbatim}" >> testing-sh.tex

todo: TODO.tex

TODO.tex: ../../../TODO
	@echo "Preparing TODO list for insertion..."
	@echo "\begin{verbatim}" > TODO.tex
	@cat ../../../TODO | perl -e "while(<STDIN>){s/\t/    /g; print}" >> TODO.tex
	@echo "\end{verbatim}" >> TODO.tex


#
# Distro
#

distro: report
	cp report.pdf ../../../distro/doc
	# XXX: latex2html

#
# Clean up
#

clean:
	rm -f *.log *.pdf

maintainer-clean: clean
	rm -f *.diff \
	$(APPFILES) $(LANGTEXFILES) $(GRAMMARTEXFILES) \
	TODO.tex date.tex \
	*.dvi *.aux *.toc *.lot *.lof *.idx *.ind *.ilg *.bbl *.blg

# EOF
