package vip.yeee.app.blog.client.constatnt;

import lombok.Getter;

/**
 * description......
 *
 * @author yeeee
 * @since 2023/7/14 9:32
 */
public enum BlogStatsEnum {

    ;

    @Getter
    public static enum Subject {

        INDEX("index", ""),
        ARTICLE("article", ""),
        ;
        private final String code;
        private final String name;

        Subject(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    @Getter
    public static enum Event {

        CLICK("click", ""),
        READ("read", ""),
        LIKE("like", ""),
        ;
        private final String code;
        private final String name;

        Event(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}
