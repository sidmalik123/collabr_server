package collabr.core;

import javax.persistence.*;


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
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "e_mail")
    private String email;

//    private int hashPassword;

    public User() {
    }

    public User(String firstName, String lastName, String email, int hashPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.hashPassword = hashPassword;
    }
    // Auto-generated equald, hashCode, getters and setters.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public int getPassword() {
//        return hashPassword;
//    }
//
//    public void setPassword(int hashPassword) {
//        this.hashPassword = hashPassword;
//    }

    public int hashPassword(String password){
        return password.hashCode();
    }
}
