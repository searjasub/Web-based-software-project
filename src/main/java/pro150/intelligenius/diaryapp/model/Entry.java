package pro150.intelligenius.diaryapp.model;

import javax.persistence.*;

@Entity
@Table(name="entries")
public class Entry {

    private Profile profile;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String content;

    @Id
    private String timeInMilliSeconds = System.currentTimeMillis() + "";

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
