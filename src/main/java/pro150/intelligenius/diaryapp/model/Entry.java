package pro150.intelligenius.diaryapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entry")
public class Entry {

    @Id
    private String timeInMilliSeconds = System.currentTimeMillis() + "";

    private String title;

    private String content;

    @ManyToMany
    private List<Profile> profileOwner = new ArrayList<>();

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

    public List<Profile> getProfileOwner() {
        return profileOwner;
    }

    public void setProfileOwner(List<Profile> profileOwner) {
        this.profileOwner = profileOwner;
    }
}
