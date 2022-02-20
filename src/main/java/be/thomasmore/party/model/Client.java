package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Locale;
@Entity
public class Client {
    @Id
    private Integer id;
    private String name;
    private String birthdate;
    private String gender;
    private String startdate;

    public Client(String name, String birthdate, String gender, String startdate) {
        this.name = name;
        this.birthdate = birthdate;
        if(this.gender.toUpperCase().equals("M") || this.gender.toUpperCase().equals("F")){
            this.gender = gender.toUpperCase();
        }else {
            this.gender = "U";
        }
        this.startdate = startdate;
    }
    public Client() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
}
