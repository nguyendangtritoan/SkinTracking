package sample;

public class Explicit {

    /*
     * G Gomez, M Sanchez, LE Sucar. 2002. On selecting an appropriate colour space
     * for skin detection. MICAI 2002.
     *
     * Color space: YES
     */
    public static int[][] ExplicitYES(int[][][] rgb) {
        int[][] mask = new int[rgb.length][rgb[0].length];

        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                if (0.5 * (rgb[i][j][0] - rgb[i][j][1]) > 13.4224   // Y = (red - green) / 2
                        && (double) rgb[i][j][0] / (double) rgb[i][j][1] < 1.7602) {
                    mask[i][j] = 1;
                } else {
                    mask[i][j] = 0;
                }
            }
        }

        return mask;
    }
}