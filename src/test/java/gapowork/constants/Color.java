package gapowork.constants;

public enum Color {
    F5F7F9("#f5f7f9"),
    C8CACF("c8cacf"),
    F5222D("f5222d"),
    FFA940("#ffa940"),
    FADB14("fadb14"),
    A0D911("a0d911"),
    _20C950("#20c950"),
    _13C2C2("#13c2c2"),
    _1A99F4("#1a99f4"),
    _2F54EB("#2f54eb"),
    _722ED1("#722ed1"),
    FF4D82("#ff4d82");


    private final String hex_code;

    Color(String hex_code) {
        this.hex_code = hex_code;
    }

    public String getHexCode() {
        return hex_code;
    }
}
