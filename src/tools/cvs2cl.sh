#!/bin/bash

# Wrapper script with MARF-preferred options of cvs2cl.pl
# Serguei Mokhov
#
# $Header: /cvsroot/marf/marf/src/tools/cvs2cl.sh,v 1.2 2005/06/06 21:42:01 mokhov Exp $
#
# We always accumulate, --accum
# Don't show emty log messages, --prune
# --follow should contain the lastes previous branch

#cvs2cl.pl --prune --accum --follow MARF_0_2_1_RELEASE
./cvs2cl.pl --prune --accum --follow MAIN

# EOF
