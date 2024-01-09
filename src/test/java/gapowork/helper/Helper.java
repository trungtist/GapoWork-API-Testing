package gapowork.helper;

import gapowork.constants.Color;
import gapowork.constants.Icon;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Helper {

    public static String[] splitStringToList(String value) {
        return value.split(",");
    }

    @SneakyThrows
    public static void shortWait() {
        TimeUnit.SECONDS.sleep(2);
    }

    @SneakyThrows
    public static void longWait() {
        TimeUnit.SECONDS.sleep(5);
    }

    public static long getTimestamp () {
        return System.currentTimeMillis();
    }

    public static String getRandomColor () {
        Color[] colors = Color.values();
        Random random = new Random();
        int index = random.nextInt(colors.length);
        System.out.println("Hex code: " + colors[index].getHexCode());
        return colors[index].getHexCode();
    }

    public static String getRandomIcon () {
        Icon[] icons = Icon.values();
        Random random = new Random();
        int index = random.nextInt(icons.length);

        return icons[index].getIconName();
    }
}
