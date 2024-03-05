package ma.enset.ormjpahibernatespringdata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthday;
    private boolean sick;
    private int score;
    @OneToMany(mappedBy = "patient",fetch =  FetchType.LAZY)
    private Collection<Appointment> appointments;
}
