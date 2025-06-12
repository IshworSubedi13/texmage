package np.com.ishworsubedi.texmage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import java.util.*;

public class ColorGenerator {
    public static final ColorGenerator DEFAULT;
    public static final ColorGenerator MATERIAL;

    private final List<Color> mColors;
    private final Random mRandom;

    static {
        DEFAULT = create(Arrays.asList(
                new Color("light_red", "#f16364"),
                new Color("light_orange", "#f58559"),
                new Color("light_yellow", "#f9a43e"),
                new Color("light_gold", "#e4c62e"),
                new Color("light_green", "#67bf74"),
                new Color("light_teal", "#59a2be"),
                new Color("light_blue", "#2093cd"),
                new Color("light_purple", "#ad62a7"),
                new Color("light_violet", "#805781")
        ));

        MATERIAL = create(Arrays.asList(
                new Color("red", "#e57373"),
                new Color("pink", "#f06292"),
                new Color("purple", "#ba68c8"),
                new Color("deep_purple", "#9575cd"),
                new Color("indigo", "#7986cb"),
                new Color("blue", "#64b5f6"),
                new Color("light_blue", "#4fc3f7"),
                new Color("cyan", "#4dd0e1"),
                new Color("teal", "#4db6ac"),
                new Color("green", "#81c784"),
                new Color("light_green", "#aed581"),
                new Color("orange", "#ff8a65"),
                new Color("lime", "#d4e157"),
                new Color("yellow", "#ffd54f"),
                new Color("amber", "#ffb74d"),
                new Color("brown", "#a1887f"),
                new Color("blue_grey", "#90a4ae")
        ));
    }

    public static ColorGenerator create(List<Color> colors) {
        return new ColorGenerator(colors);
    }

    private ColorGenerator(List<Color> colors) {
        this.mColors = new ArrayList<>(colors);
        this.mRandom = new Random(System.currentTimeMillis());
    }

    public Color getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public Color getColor(Object key) {
        int index = Math.abs(key.hashCode()) % mColors.size();
        return mColors.get(index);
    }

    public List<Color> getColors() {
        return Collections.unmodifiableList(mColors);
    }
}
