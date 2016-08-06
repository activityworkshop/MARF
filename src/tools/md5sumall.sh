#!/bin/bash

#
# md5sum wrapper to create an MD5 digest of all
# files in the specified directory or the file
# specified on the command line.
#
# Serguei Mokhov
# $Id: md5sumall.sh,v 1.3 2006/01/03 07:22:00 mokhov Exp $
#

if [ ! "$1" ]; then
	cat <<-USAGE
	Usage:

	    $0 <dir> | <filename>
	USAGE
fi

MD5SUM="md5sum -b"

if [ -d "$1" ]; then

	cd "$1"

	# "hidden" files ignored
	for file in * ; 
	do
		if [ -f "$file" ]; then
			$MD5SUM "$file" > "$file.md5"
		fi
	done

	cd ..

else
	if [ -f "$1" ]; then
		$MD5SUM "$1" > "$1.md5"
	fi
fi

echo "Done:"
ls -al "$1"

# EOF
