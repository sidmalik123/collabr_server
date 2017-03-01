package collabr.core;

import com.codahale.metrics.annotation.Timed;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {

    @NotBlank
    private String name;

    public Skill(){

    }

    public Skill(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
