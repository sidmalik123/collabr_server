package collabr.core;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "com.mates.core.Users.findAll",
                query = "select e from User e"),
        @NamedQuery(name = "com.mates.core.Users.findByEmail",
                query = "select e from User e "
                        + "where e.email like :email ")
})
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

    @NotBlank
    @Column(name = "hash_password")
    private int hashPassword;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_login")
    private Date lastLogin;

    private boolean validated;

    public User() {
    }

    public User(String fname, String lname, String email, int hashPassword) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.hashPassword = hashPassword;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int hashPassword(String password){
        return password.hashCode();
    }
}
