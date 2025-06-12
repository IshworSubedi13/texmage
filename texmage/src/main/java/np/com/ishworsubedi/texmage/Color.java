package np.com.ishworsubedi.texmage;

import java.io.Serializable;
import java.util.Objects;

public class Color implements Serializable {
    private final String name;
    private final String colorCode; // e.g. "#e57373"
    private final int argb;         // e.g. 0xffe57373

    public Color(String name, String colorCode) {
        if (!colorCode.matches("^#?[0-9a-fA-F]{6}$")) {
            throw new IllegalArgumentException("Invalid hex color code: " + colorCode);
        }

        this.name = name;
        this.colorCode = colorCode.startsWith("#") ? colorCode : "#" + colorCode;
        this.argb = android.graphics.Color.parseColor(this.colorCode); // Android ARGB int
    }

    public String getName() {
        return name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getArgb() {
        return argb;
    }

    @Override
    public String toString() {
        return "Color{name='" + name + "', colorCode='" + colorCode + "', argb=" + argb + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color)) return false;
        Color color = (Color) o;
        return argb == color.argb &&
                name.equals(color.name) &&
                colorCode.equals(color.colorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, colorCode, argb);
    }
}