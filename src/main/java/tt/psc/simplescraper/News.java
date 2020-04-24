package tt.psc.simplescraper;

import java.net.URL;

public class News {
    private Integer id;
    private Integer position;
    private String title;
    private URL link;
    private String author;
    private Integer points;

    public News(Integer id, Integer position, String title, URL link, String author, Integer points) {
        this.id = id;
        this.position = position;
        this.title = title;
        this.link = link;
        this.author = author;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
