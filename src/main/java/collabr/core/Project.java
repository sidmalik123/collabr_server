package collabr.core;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.annotation.Nonnegative;
import javax.persistence.*;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Nonnegative
    @Column(name = "people_required")
    private int peopleRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToMany(fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_skill_junction",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    Set<Skill> skills = new HashSet<Skill>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Match> matches = new ArrayList<Match>();

    public Project(){

    }

    public Project(String name, String description, int peopleRequired, User user){
        this.name = name;
        this.description = description;
        this.peopleRequired = peopleRequired;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeopleRequired() {
        return peopleRequired;
    }

    public void setPeopleRequired(int peopleRequired) {
        this.peopleRequired = peopleRequired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
