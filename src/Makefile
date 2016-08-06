# MARF Build Makefile
# Use with GNU make
#
# Serguei Mokhov
#
# Copyright (c) 2002 - 2006 The MARF Research and Development Group
#
# $Header: /cvsroot/marf/marf/src/Makefile,v 1.75 2006/02/23 16:44:06 mokhov Exp $

VERSION=0.3.0-devel-`date +%Y%m%d`

#
# Dirs of MARF modules
# If you add a directory, don't forget to list it here.
#

DISTRODIR=../distro
DEBUGDIR=debug

BASE_MARF_DIR=.

include $(BASE_MARF_DIR)/../Makefile.global

MARFDIRS := marf \
			marf/Preprocessing \
			marf/Preprocessing/Dummy \
			marf/Preprocessing/Endpoint \
			marf/Preprocessing/FFTFilter \
			marf/FeatureExtraction \
			marf/FeatureExtraction/FFT \
			marf/FeatureExtraction/LPC \
			marf/FeatureExtraction/F0 \
			marf/FeatureExtraction/Cepstral \
			marf/FeatureExtraction/Segmentation \
			marf/FeatureExtraction/RandomFeatureExtraction \
			marf/FeatureExtraction/MinMaxAmplitudes \
			marf/Classification \
			marf/Classification/Markov \
			marf/Classification/NeuralNetwork \
			marf/Classification/Stochastic \
			marf/Classification/Distance \
			marf/Classification/RandomClassification \
			marf/Stats \
			marf/Stats/StatisticalEstimators \
			marf/Stats/StatisticalEstimators/Smoothing \
			marf/Storage \
			marf/Storage/Loaders \
			marf/gui \
			marf/gui/util \
			marf/math \
			marf/nlp \
			marf/nlp/util \
			marf/nlp/Stemming \
			marf/nlp/Parsing \
			marf/nlp/Parsing/GrammarCompiler \
			marf/nlp/Collocations \
			marf/util \
			marf/util/comparators

#
# Java Extensions and CLASSPATH
# You might want to tweak these
#

EXTDIRS = "."
INSTALLDIR = /nettemp/marf
MARFDIR = /nettemp/marf:~/marf/marf

# Lists of all *.java and *.class files
JAVAFILES := $(MARFDIRS:%=%/*.java) test.java
CLASSES := $(MARFDIRS:%=%/*.class)

# Devel
DIFFS := $(MARFDIRS:%=%/*.diff)

#
# Java Setup
#

JAVAC = javac
ALTJAVAC = /pkg/j2sdk/bin/javac
WARNINGS = -deprecation #-Xlint:unchecked
JFLAGS = -source 1.4 -verbose $(WARNINGS) -sourcepath . -classpath $(EXTDIRS)
JVM = java

#
# Java Doc for API doc generation
#

JAVADOC = javadoc
JAVADOCDIR = ../..
JAVADOCFLAGS = -J-enableassertions -source 1.4 -verbose -use -version -author \
               -stylesheetfile ../doc/src/sgml/stylesheet.css

# For the web site only; distros don't have that
# Needed to improve stats.
JAVADOCFLAGSSF = -bottom '<a href="http://sourceforge.net"><img \
							src="http://sourceforge.net/sflogo.php?group_id=63118&amp;type=5" \
							width="210" \
							height="62" \
							border="0" \
							alt="SourceForge Logo" /></a>'

#
# Build
#

all: test.class copyright
	make -C marf $@
	jar cvmf ../manifest.mf marf-$(VERSION)-fat.jar marf test.class COPYRIGHT
	ln -s -f marf-$(VERSION)-fat.jar marf.jar
	@echo "(-: MARF build has been successful :-)"

debug: clean copyright
	rm -rf $(DEBUGDIR) && mkdir -p $(DEBUGDIR)
	$(JAVAC) -g $(JFLAGS):../lib/junit.jar -d $(DEBUGDIR) test.java marf/junit/CloneabilityTest.java
	( \
		cp COPYRIGHT $(DEBUGDIR) ; \
		cd $(DEBUGDIR) ; \
		jar cvmf ../../manifest.mf marf-debug-$(VERSION).jar marf test.class COPYRIGHT ; \
	)
	mv $(DEBUGDIR)/marf-debug-$(VERSION).jar .
	ln -s -f marf-debug-$(VERSION).jar marf.jar
	@echo "(-: MARF debug build has been successful :-)"

altall: $(JAVAFILES) copyright
	mkdir -p release
	$(ALTJAVAC) $(JFLAGS) -d release test.java
	( \
		cd release; \
		jar cvmf ../manifest.mf marf-$(VERSION)-fat.jar marf test.class COPYRIGHT ; \
	)
	@echo "(-: Alternative MARF build has been successful :-)"

test.class: test.java
	$(JAVAC) -g $(JFLAGS) test.java

# Quick hack for optimized build.
# Faster but class files may be larger; no debug info included.
optimize: $(JAVAFILES) copyright
	$(JAVAC) -g:none -O $(JFLAGS) test.java
	jar cvmf ../manifest.mf marf-$(VERSION)-opti.jar COPYRIGHT marf test.class
	ln -s -f marf-$(VERSION)-opti.jar marf.jar

copyright: ../COPYRIGHT
	ln -f -s ../COPYRIGHT COPYRIGHT


#
# Install
#

install: all
	cp marf-$(VERSION).jar $(INSTALLDIR)
	chmod go+r $(INSTALLDIR)/marf-$(VERSION).jar
	ln -s $(INSTALLDIR)/marf-$(VERSION).jar marf.jar


#
# For packaging and distribution
#

distro: $(JAVAFILES) distro-dir marf-debug marf-util marf-math marf-storage marf-utilimathstor api
	@echo "MARF: Making binary marf distro..."
	$(JAVAC) -g:none -O $(JFLAGS) -d $(DISTRODIR) test.java
	( \
		cd $(DISTRODIR) ; \
		cp ../COPYRIGHT . ; \
		jar cvmf ../manifest.mf marf-$(VERSION).jar COPYRIGHT marf test.class ; \
		rm -rf marf test.class \
	)

marf-util: $(JAVAFILES) distro-dir
	@echo "MARF: Making binary marf-util distro..."
	$(JAVAC) -g:none -O $(JFLAGS) -d $(DISTRODIR) test.java
	( \
		cd $(DISTRODIR) ; \
		cp ../COPYRIGHT . ; \
		rm -rf \
			test.class \
			marf/Stats \
			marf/Storage \
			marf/math \
			marf/gui \
			marf/Preprocessing \
			marf/FeatureExtraction \
			marf/Classification \
			marf/speech \
			marf/nlp \
			marf/junit \
			marf/util/comparators \
			marf/MARF*; \
		jar cvmf ../manifest.mf marf-util-$(VERSION).jar COPYRIGHT marf ; \
		rm -rf marf \
	)

marf-math: $(JAVAFILES) distro-dir
	@echo "MARF: Making binary marf-math distro..."
	$(JAVAC) -g:none -O $(JFLAGS) -d $(DISTRODIR) test.java
	( \
		cd $(DISTRODIR) ; \
		cp ../COPYRIGHT . ; \
		rm -rf \
			test.class \
			marf/Storage \
			marf/gui \
			marf/Preprocessing \
			marf/FeatureExtraction \
			marf/Classification \
			marf/speech \
			marf/nlp \
			marf/junit \
			marf/MARF*; \
		jar cvmf ../manifest.mf marf-math-$(VERSION).jar COPYRIGHT marf ; \
		rm -rf marf \
	)

marf-storage: $(JAVAFILES) distro-dir
	@echo "MARF: Making binary marf-storage distro..."
	$(JAVAC) -g:none -O $(JFLAGS) -d $(DISTRODIR) test.java
	( \
		cd $(DISTRODIR) ; \
		cp ../COPYRIGHT . ; \
		rm -rf \
			test.class \
			marf/Stats \
			marf/math \
			marf/gui \
			marf/Preprocessing \
			marf/FeatureExtraction \
			marf/Classification \
			marf/speech \
			marf/nlp \
			marf/junit \
			marf/util/comparators \
			marf/MARF*; \
		jar cvmf ../manifest.mf marf-storage-$(VERSION).jar COPYRIGHT marf ; \
		rm -rf marf \
	)

marf-utilimathstor: $(JAVAFILES) distro-dir
	@echo "MARF: Making binary marf-utilimathstor distro..."
	$(JAVAC) -g:none -O $(JFLAGS) -d $(DISTRODIR) test.java
	( \
		cd $(DISTRODIR) ; \
		cp ../COPYRIGHT . ; \
		rm -rf \
			test.class \
			marf/gui \
			marf/Preprocessing \
			marf/FeatureExtraction \
			marf/Classification \
			marf/speech \
			marf/nlp \
			marf/junit \
			marf/MARF*; \
		jar cvmf ../manifest.mf marf-utilimathstor-$(VERSION).jar COPYRIGHT marf ; \
		rm -rf marf \
	)

marf-debug: debug distro-dir
	@echo "MARF: Making binary marf-debug distro..."
	mv marf-debug-$(VERSION).jar $(DISTRODIR)

distro-dir:
	mkdir -p $(DISTRODIR)


#
# API docs in javadoc
#

# API Documentation for MARF users
apidoc: $(JAVAFILES)
	mkdir -p $(JAVADOCDIR)/api
	$(JAVADOC) $(JAVADOCFLAGS) -package -d $(JAVADOCDIR)/api $(JAVAFILES)

# API Documentation for MARF developers
apidoc-dev: $(JAVAFILES)
	mkdir -p $(JAVADOCDIR)/api-dev
	$(JAVADOC) $(JAVADOCFLAGS) -private -d $(JAVADOCDIR)/api-dev $(JAVAFILES)

# Both APIs
api: apidoc apidoc-dev

# API Documentation for MARF the web site if the SF.net logo
apidoc-sf: $(JAVAFILES)
	mkdir -p $(JAVADOCDIR)/api
	$(JAVADOC) $(JAVADOCFLAGS) \
		$(JAVADOCFLAGSSF) \
		-package -d $(JAVADOCDIR)/api $(JAVAFILES)

# API Documentation for MARF developers for the web site
apidoc-dev-sf: $(JAVAFILES)
	mkdir -p $(JAVADOCDIR)/api-dev
	$(JAVADOC) $(JAVADOCFLAGS) \
		$(JAVADOCFLAGSSF) \
		-private -d $(JAVADOCDIR)/api-dev $(JAVAFILES)

# Both APIs for the web site
api-sf: apidoc-sf apidoc-dev-sf

#
# Clean up
#

clean:
	rm -f $(CLASSES) test.class

maintainer-clean: clean
	rm -f *.diff *.jar *log $(DIFFS)

# EOF
