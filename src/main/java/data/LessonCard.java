package data;

public class LessonCard {
    private String href;
    private String name;
    private String date;

    public LessonCard(String href, String name, String date) {
        this.href = href;
        this.name = name;
        this.date = date;
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "LessonCard{"
                + "href='"
                + href
                + '\''
                + ", name='"
                + name
                + '\''
                + ", date='"
                + date
                + '\''
                + '}';
    }
}
