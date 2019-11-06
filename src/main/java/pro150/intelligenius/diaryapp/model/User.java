package pro150.intelligenius.diaryapp.model;

public class User {
    private String username;
    private String password;
    private int id;

    public User(String username, String password, int id) {
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + " Password: " + getPassword();
    }

}
