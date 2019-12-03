package pro150.intelligenius.diaryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonIgnore
    private Profile profileOwner;

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

    public Profile getProfileOwner() {
        return profileOwner;
    }

    public void setProfileOwner(Profile profileOwner) {
        this.profileOwner = profileOwner;
    }
}
