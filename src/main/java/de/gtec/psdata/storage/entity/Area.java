package de.gtec.psdata.storage.entity;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @name Area
 * @author jml04
 * @version 1.0
 *
 * @description
 * The {@link Area} class represents an area with lat and lon.
 */
public final class Area {

    // value separator string
    public static final String SEPARATOR = "%2C";
    private static final Pattern AREA_PATTERN = Pattern.compile("(-?[0-9]*\\.-?[0-9]+(%2C-?[0-9]*\\.-?[0-9]+)+)");

    //
    private final float latTopLeft, latBottomRight, lonTopLeft, lonBottomRight;

    public Area(final float latTopLeft, final float latBottomRight,
                final float lonTopLeft, final float lonBottomRight) {
        this.latTopLeft     = latTopLeft;
        this.latBottomRight = latBottomRight;
        this.lonTopLeft     = lonTopLeft;
        this.lonBottomRight = lonBottomRight;
    }


    /**
     * private Area constructor for Area.fromString()
     *
     * @param coords is the coordinate array with a length of 4
     */
    private Area(final double[] coords) {
        this((float) coords[0], (float) coords[1], (float) coords[2], (float) coords[3]);
    }


    public static Area fromString(String area) throws MalformedAreaException {
        checkAreaString(area);
        double[] coords = Arrays.stream(area.split(SEPARATOR))
                .mapToDouble(Double::parseDouble)
                .toArray();

        return new Area(coords);

    }

    private static void checkAreaString(String area) throws MalformedAreaException {
        // TODO: 22.11.2022 check area string with regex
        if (!AREA_PATTERN.matcher(area).matches()) {
            throw new MalformedAreaException();
        }
    }

    public float getLatTopLeft() {
        return latTopLeft;
    }

    public float getLatBottomRight() {
        return latBottomRight;
    }

    public float getLonTopLeft() {
        return lonTopLeft;
    }

    public float getLonBottomRight() {
        return lonBottomRight;
    }

    @Override
    public String toString() {
        return  latTopLeft     + SEPARATOR +
                latBottomRight + SEPARATOR +
                lonTopLeft     + SEPARATOR +
                lonBottomRight;
    }

    public String[] toStringArray() {
        return new String[] {toString()};
    }
}
