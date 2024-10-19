package data;

import lombok.Getter;

@Getter
public class LessonCard {
    private String href;
    private String name;
    private String date;

    public LessonCard(String href, String name, String date) {
        this.href = href;
        this.name = name;
        this.date = date;
    }
}
