package models;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String mdp;
    private Role role;
    private String email;
    private String img;
    private int age;
    private static User instance;
      public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    public User() {
    }

    public User(String nom, String prenom, String mdp, String email, String img, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.img = img;
        this.age = age;
    }

    public User(String nom, String prenom, String mdp, Role role, String email, String img, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.role = role;
        this.email = email;
        this.img = img;
        this.age = age;
    }

    public User(int id, String nom, String prenom, String mdp, Role role, String email, String img, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.role = role;
        this.email = email;
        this.img = img;
        this.age = age;
    }

    

    public  Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", role=" + role + ", email=" + email + ", img=" + img + ", age=" + age + '}';
    }

    
}
