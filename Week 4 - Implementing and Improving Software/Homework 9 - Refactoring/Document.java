import java.util.Date;

public abstract class Document {
    private String title;
    private String author;
    private Date date;
    private PublishingLocation location;

    public Document(String title, String author, Date date, PublishingLocation location) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public String getCity() {
        return location.getCity();
    }

    public String getState() {
        return location.getState();
    }

    public String getPostCode() {
        return location.getPostCode();
    }

    public int compareWithGeneralDate(Date date){
        return this.getDate().compareTo(date);
    }

    public int compareDates(Document document) {
        return this.getDate().compareTo(document.getDate());
    }

    public boolean sameAuthor(Document document){
        return this.getAuthor().equals(document.getAuthor());
    }
}
