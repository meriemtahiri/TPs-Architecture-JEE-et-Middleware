package ma.enset.ormjpahibernatespringdata.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Doctor {
    @Id
    private String id;
    private String name;
    private String email;
    private String speciality;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Appointment> appointments;
}
