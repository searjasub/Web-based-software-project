package pro150.intelligenius.diaryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int profileId;

    private String name;

    private String dateOfBirth;

    @ManyToMany(mappedBy = "profileOwner")
    @JsonIgnore
    private List<Entry> entries = new ArrayList<>();

    public Profile() {
    }

    public Profile(int profileId, String name, String dateOfBirth) {
        this.setProfileId(profileId);
        this.setName(name);
        this.setDateOfBirth(dateOfBirth);
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
