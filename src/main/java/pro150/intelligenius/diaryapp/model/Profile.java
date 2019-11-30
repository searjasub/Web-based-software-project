package pro150.intelligenius.diaryapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile extends User {

    private String name;

    private String dateOfBirth;

    @OneToMany(mappedBy = "profileOwner")
    private List<Entry> entries = new ArrayList<>();

    public Profile() {
    }

    public Profile(String name, String dateOfBirth) {
        this.setName(name);
        this.setDateOfBirth(dateOfBirth);
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
