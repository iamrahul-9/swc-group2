# Part 1
Draw the architecture of ​at least 3 packages and 10 classes
Draw a call graph, starting from the most prominent entry point


# Part 2
Write the data flow of six methods; ​six methods: ​two methods containing ​for loops and ​four with ​if/else statements; Each method must have 15 or more lines of code

1. located in main\commons\src\main\java\org\cryptomator\common\SemVerComparator.java
```Java
private int compareNumericallyThenLexicographically(String version1, String version2) {
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
	}
``` 
This method takes in two versions as arguments. They are first split by a Version Separator, namely a dot, and the minimum of both lengths is stored.
Then, using a for loop, the method iterates over this length and for each iteration it declares a comparison result as 0.
Both version numbers are compared using the iterating variable i, and they are subtracted from eachother. If there should be an error during the subtraction, 
the comparison is then carried out lexicographically, for example if there are characters in the versions instead of only integers.
If this difference turns out to be not zero, the for loop is interrupted by the return statement of the method, which returns the integer result of the version difference at position i.
Should the for loop finish without finding a difference at any i, the method just returns the difference of both version-String array's length.
(not sure if this does actually do exactly that in the last return)

2.
3.
4.
5.
6.
