package collabr.core;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @NotBlank
    @Column(unique = true)
    private String email;

    @Column(name = "hash_password")
    private int hashPassword;

    @Column(name = "created_at")
    private DateTime createdAt;

    @Column(name = "last_login")
    private DateTime lastLogin;

    @Column(name = "is_validated")
    private boolean isValidated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Project> projects = new ArrayList<Project>();

    @ManyToMany(fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_skill_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    Set<Skill> skills = new HashSet<Skill>();

    public User() {
    }

    public User(String fname, String lname, String email, int hashPassword) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public User(User user){
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.hashPassword = user.getHashPassword();
        this.email = user.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(int hashPassword) {
        this.hashPassword = hashPassword;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int hashPassword(String password){
        return password.hashCode();
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }
}
