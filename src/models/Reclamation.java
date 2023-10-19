package models;

public class Reclamation {
    private int id;
    private String description;
    private User user;

    public Reclamation() {
    }

    public Reclamation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Reclamation(int id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Reclamation(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", user=" + user + '}';
    }
}