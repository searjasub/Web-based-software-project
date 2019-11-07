package pro150.intelligenius.diaryapp.model;

public class Entry {

    private String title;
    private String content;
    private String timeInMilliSeconds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeInMilliSeconds() {
        return timeInMilliSeconds;
    }

    public void setTimeInMilliSeconds(String timeInMilliSeconds) {
        this.timeInMilliSeconds = timeInMilliSeconds;
    }
}
