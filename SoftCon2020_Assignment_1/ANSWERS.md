# Part 1
Draw the architecture of ​at least 3 packages and 10 classes
Draw a call graph, starting from the most prominent entry point


# Part 2
Write the data flow of six methods; ​six methods: ​two methods containing ​for loops and ​four with ​if/else statements; Each method must have 15 or more lines of code

1. located in main\commons\src\main\java\org\cryptomator\common\SemVerComparator.java
```Java
private int compare(String v1MajorMinorPatch, String v1PreReleaseVersion, String v2MajorMinorPatch, String v2PreReleaseVersion) {
	int comparisonResult = compareNumericallyThenLexicographically(v1MajorMinorPatch, v2MajorMinorPatch);
	if (comparisonResult == 0) {
		if (v1PreReleaseVersion.isEmpty()) {
			return 1; // 1.0.0 > 1.0.0-BETA
		} else if (v2PreReleaseVersion.isEmpty()) {
			return -1; // 1.0.0-BETA < 1.0.0
		} else {
			return compareNumericallyThenLexicographically(v1PreReleaseVersion, v2PreReleaseVersion);
		}
	} else {
		return comparisonResult;
	}
}
```

2. located in main\commons\src\main\java\org\cryptomator\common\SemVerComparator.java
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

3. located in main\commons\src\main\java\org\cryptomator\common\LicenseChecker.java
```Java
private static ECPublicKey decodePublicKey(String pemEncodedPublicKey) {
	try {
		byte[] keyBytes = BaseEncoding.base64().decode(pemEncodedPublicKey);
		PublicKey key = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(keyBytes));
		if (key instanceof ECPublicKey) {
			return (ECPublicKey) key;
		} else {
			throw new IllegalStateException("Key not an EC public key.");
		}
	} catch (InvalidKeySpecException e) {
		throw new IllegalArgumentException("Invalid license public key", e);
	} catch (NoSuchAlgorithmException e) {
		throw new IllegalStateException(e);
	}
}
```
(only 14 lines lol)	

4. located in main\ui\src\main\java\org\cryptomator\ui\addvaultwizard\ChooseExistingVaultController.java
```Java
public void chooseFileAndNext() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle(resourceBundle.getString("addvaultwizard.existing.filePickerTitle"));
	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Cryptomator Masterkey", "*.cryptomator"));
	File masterkeyFile = fileChooser.showOpenDialog(window);
	if (masterkeyFile != null) {
		vaultPath.setValue(masterkeyFile.toPath().toAbsolutePath().getParent());
		try {
			Vault newVault = vaultListManager.add(vaultPath.get());
			vault.set(newVault);
			window.setScene(successScene.get());
		} catch (NoSuchFileException e) {
			LOG.error("Failed to open existing vault.", e);
			errorComponent.cause(e).window(window).returnToScene(window.getScene()).build().showErrorScene();
		}
	}
}
```
5.
BINARY SEARCH
located in main\ui\src\main\java\org\cryptomator\ui\recoverykey\AutoCompleter.java
```Java
private int findIndexOfLexicographicallyPreceeding(int begin, int end, String prefix) {
		if (begin >= end) {
			return begin; // this is usually where a binary search ends "unsuccessful"
		}

		int mid = (begin + end) / 2;
		String word = dictionary.get(mid);
		if (prefix.compareTo(word) <= 0) { // prefix preceeds or matches word
			// proceed in left half
			assert mid < end;
			return findIndexOfLexicographicallyPreceeding(0, mid, prefix);
		} else {
			// proceed in right half
			assert mid >= begin;
			return findIndexOfLexicographicallyPreceeding(mid + 1, end, prefix);
		}
	}
```
6.
