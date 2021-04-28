package algo;

public class LicenseKeyFormatting {

    public static void main(String[] args) {
        new LicenseKeyFormatting();
    }

    public LicenseKeyFormatting() {
        System.out.println(licenseKeyFormatting("2", 2));
    }

    public String licenseKeyFormatting(String S, int K) {
        int len = S.length();
        char[] c = S.toCharArray();

        int dashes = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (c[i] == '-') dashes++;
        }

        int numSymbols = len - dashes;
        numSymbols += (numSymbols - 1) / K;
        char[] result = new char[numSymbols];
        int upperDiff = 'a' - 'A';
        int inserted = 0;
        int pos = numSymbols - 1;

        for (int i = len - 1; i >= 0; i--) {
            if (c[i] == '-') {
                continue;
            }

            if (inserted > 0 && inserted % K == 0) {
                result[pos--] = '-';
            }

            if (c[i] >= 'a') {
                result[pos--] = (char)(c[i] - upperDiff);
                inserted++;
            }
            else {
                result[pos--] = c[i];
                inserted++;
            }
        }

        return new String(result);
    }
}
