package gapowork.constants;

public enum Icon {
    BANMARK("ic24-fill-banmark"),
    FOLDER("ic24-fill-folder"),
    FLAG("ic24-fill-flag"),
    STAR("ic24-fill-star"),
    CLOCK("ic24-fill-clock"),
    PENCIL("ic24-fill-pencil"),
    WARNINGMARK("ic24-fill-warningmark"),
    CALENDAR("ic24-fill-calendar"),
    EYE("ic24-fill-eye"),
    HOUSE("ic24-fill-house"),
    THREE_PEOPLE("ic24-fill-3people"),
    TRASH("ic24-fill-trash"),
    CHECK_BOARD("ic24-fill-check-board"),
    STORE("ic24-fill-store"),
    HEART("ic24-fill-heart"),
    EARTH("ic24-fill-earth"),
    WALLET("ic24-fill-wallet"),
    PIN_LOCATION("ic24-fill-pinlocation"),
    FEED("ic24-fill-feed"),
    STAR_SHIELD("ic24-fill-star-shield"),
    XMARK_CIRCLE("ic24-fill-xmark-circle"),
    STAR_CIRCLE("ic24-fill-star-circle"),
    GEAR("ic24-fill-gear"),
    LOCK("ic24-fill-lock"),
    FACE_SMILE("ic24-fill-face-smile"),
    MEDAL("ic24-fill-medal"),
    GIFT("ic24-fill-gift"),
    CAKE_BIRTHDAY("ic24-fill-cake-birthday"),
    CLAPPERBOARD("ic24-fill-clapperboard"),
    LIKE_THUMB_UP("ic24-line15-like-thumb-up"),
    EDUCATION_CAP("ic24-fill-educationcap"),
    QUESTIONMARK_CIRCLE("ic24-line20-questionmark-circle"),
    ARCHIVE_BOX("ic24-fill-archive-box"),
    CONTACT_BOOK("ic24-fill-contact-book"),
    SIDE_CAMERA("ic24-fill-sidecamera"),
    BRIEFCASE("ic24-fill-briefcase"),
    PARAGRAPH("ic24-fill-paragraph"),
    CLOUD_ARROW_UP("ic24-fill-cloud-arrow-up"),
    TAG("ic24-fill-tag"),
    BADGE_PERCENT("ic24-fill-badge-percent"),
    CAMERA("ic24-fill-camera"),
    SPEAKER_2WAVE("ic24-fill-speaker-2wave"),
    STICKER("ic24-fill-sticker"),
    TALL_BUILDING("ic24-fill-tall-building"),
    BUBBLE_ELLIPSE_3DOT("ic24-fill-bubble-ellipse-3dot"),
    BUBBLE_RECTANGLE_WITH_TEXT("ic-24-fill-bubble-rectangle-with-text"),
    INFORMATIONMARK_CIRCLE("ic24-line20-informationmark-circle"),
    QUESTIONMARK_BUBBLE_RECTANGLE("ic24-line20-questionmark-bubble-rectangle"),
    PHOTO("ic24-fill-photo"),
    PAPERPLANE("ic24-fill-paperplane"),
    POLL("ic24-fill-poll");

    private final String icon_name;

    Icon(String icon_name) {
        this.icon_name = icon_name;
    }

    public String getIconName() {
        return icon_name;
    }
}
