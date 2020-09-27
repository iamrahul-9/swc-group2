# Part 1
Draw the architecture of ​at least 3 packages and 10 classes
Draw a call graph, starting from the most prominent entry point


# Part 2
Write the data flow of six methods; ​six methods: ​two methods containing ​for loops and ​four with ​if/else statements; Each method must have 15 or more lines of code

1.
```private int compareNumericallyThenLexicographically(String version1, String version2) {
		final String[] vComps1 = StringUtils.split(version1, VERSION_SEP);
		final String[] vComps2 = StringUtils.split(version2, VERSION_SEP);
		final int commonCompCount = Math.min(vComps1.length, vComps2.length);

		for (int i = 0; i < commonCompCount; i++) {
			int subversionComparisionResult = 0;
			try {
				final int v1 = Integer.parseInt(vComps1[i]);
				final int v2 = Integer.parseInt(vComps2[i]);
				subversionComparisionResult = v1 - v2;
			} catch (NumberFormatException ex) {
				// ok, lets compare this fragment lexicographically
				subversionComparisionResult = vComps1[i].compareTo(vComps2[i]);
			}
			if (subversionComparisionResult != 0) {
				return subversionComparisionResult;
			}
		}

		// all in common so far? longest version string is considered the higher version:
		return vComps1.length - vComps2.length;
	}``` located in main\commons\src\main\java\org\cryptomator\common


2.
3.
4.
5.
6.
