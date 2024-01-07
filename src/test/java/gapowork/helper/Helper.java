package gapowork.helper;

import lombok.SneakyThrows;

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
}
