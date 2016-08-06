#!/bin/bash

#
# Finds .java files with public classes and interfaces
# that are missing either getMARFSourceCodeRevision()
# or MARF_INTERFACE_CODE_REVISION. If there are such cases,
# they have to be "inevntoried". Note, this is different
# from the notion of serialVersionUID.
#
# Synopsis:
#
#    revision-check.sh [--verbose]
#
# Author:
#
#    Serguei Mokhov
#
# Copyright (c) 2005 - 2006 The MARF Research and Development Group
#
# $Id: revision-check.sh,v 1.9 2006/01/03 08:16:08 mokhov Exp $
#

if [ "$1" == "--verbose" ]; then

	#
	# Classes
	#

	echo "List of inventoried Java files with public classes:"
	echo "---------------------------------------------------"
	grep -e 'g getMARFSourceCodeRevision()' -R . \
		| grep -v '\.sh' \
		| perl -e 'while(<STDIN>){s/:.*//g; print}' \
		| sort -u \
		| grep '\.java' \
		| tee inventoried.java.txt
	echo "---------------------------------------------------"
	echo ""

	echo "List of all Java files with public classes:"
	echo "-------------------------------------------"
	grep -e 'public ' -R . \
		| grep 'class' \
		| grep -v '\.sh' \
		| grep -v '\.txt' \
		| grep -v '#' \
		| grep -v '~' \
		| grep java \
		| perl -e 'while(<STDIN>){s/:.*//g; print}' \
		| sort -u \
		| tee all.java.txt
	echo "-------------------------------------------"
	echo ""

	echo "Presumably uninventoried files:"
	echo "-------------------------------"
	diff -c all.java.txt inventoried.java.txt
	echo "-------------------------------"

	cat all.java.txt \
		| perl -e 'while(<STDIN>){s/.*\///g; print}' \
		| sort \
		> class.java.log

	grep -e 'getMARFSourceCodeRevision()' test.java \
		| grep '+' \
		| tr -d ' \t' \
		| perl -e 'while(<STDIN>){chomp; s/\..*//g; print; print ".java\n";}' \
		| grep -v 'marf' \
		| sort \
		> test.java.log

	echo "Missing printouts in test.java:"
	echo "-------------------------------"
	diff -c class.java.log test.java.log
	echo "-------------------------------"

	#
	# Interfaces
	#

	echo "List of inventoried Java files with intefaces:"
	echo "----------------------------------------------"
	grep -e 'g MARF_INTERFACE_CODE_REVISION' -R . \
		| grep -v '\.sh' \
		| perl -e 'while(<STDIN>){s/:.*//g; print}' \
		| sort -u \
		| grep '\.java' \
		| tee inventoried-i.java.txt
	echo "----------------------------------------------"
	echo ""

	echo "List of all Java files with interfaces:"
	echo "---------------------------------------"
	grep -e 'public ' -R . \
		| grep 'interface' \
		| grep -v '\.sh' \
		| grep -v '\.txt' \
		| grep -v '#' \
		| grep -v '~' \
		| grep -v 'MARF' \
		| grep java \
		| perl -e 'while(<STDIN>){s/:.*//g; print}' \
		| sort -u \
		| tee all-i.java.txt
	echo "---------------------------------------"
	echo ""

	echo "Presumably uninventoried interface files:"
	echo "-----------------------------------------"
	diff -c all-i.java.txt inventoried-i.java.txt
	echo "-----------------------------------------"

	cat all-i.java.txt \
		| perl -e 'while(<STDIN>){s/.*\///g; print}' \
		| sort \
		> class-i.java.log

	grep -e 'MARF_INTERFACE_CODE_REVISION' test.java \
		| grep '+' \
		| tr -d ' \t' \
		| perl -e 'while(<STDIN>){chomp; s/\..*//g; print; print ".java\n";}' \
		| grep -v 'marf' \
		| sort \
		> test-i.java.log

	echo "Missing printouts in test.java for interfaces:"
	echo "----------------------------------------------"
	diff -c class-i.java.log test-i.java.log
	echo "----------------------------------------------"
fi

#
# Classes
#

echo -n "Total number of inventoried files with getMARFSourceCodeRevision() : "
grep -e 'g getMARFSourceCodeRevision()' -R . | grep -v '\.txt' | grep -c '\.java'

echo -n "Total number of printouts in test.java                             : "
grep -e 'getMARFSourceCodeRevision()' test.java | grep -c '+'

echo -n "Number of .java files with public classes                          : "
grep -e 'public ' -R . \
	| grep 'class' \
	| grep -v '\.txt' \
	| grep -v '\.sh' \
	| grep -v '#' \
	| grep -v '~' \
	| perl -e 'while(<STDIN>){s/:.*//g; print}' \
	| sort -u \
	| grep -c '\.java'
echo ""

#
# Interfaces
#

echo -n "Total number of inventoried files with MARF_INTERFACE_CODE_REVISION: "
grep -e 'g MARF_INTERFACE_CODE_REVISION' -R . | grep -v '\.txt' | grep -c '\.java'

echo -n "Total number of printouts in test.java for interfaces              : "
grep -e 'MARF_INTERFACE_CODE_REVISION' test.java | grep -c '+'

echo -n "Number of .java files with true (non-enum) interfaces in them      : "
grep -e 'public ' -R . \
	| grep 'interface' \
	| grep -v '\.txt' \
	| grep -v '\.sh' \
	| grep -v '#' \
	| grep -v '~' \
	| grep -v 'MARF' \
	| perl -e 'while(<STDIN>){s/:.*//g; print}' \
	| sort -u \
	| grep -c '\.java'
echo ""

echo "If these counts do not match, please fix the Java code."

# EOF
