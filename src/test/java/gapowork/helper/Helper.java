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

    public static long getTimestamp () {
        return System.currentTimeMillis();
    }
}
