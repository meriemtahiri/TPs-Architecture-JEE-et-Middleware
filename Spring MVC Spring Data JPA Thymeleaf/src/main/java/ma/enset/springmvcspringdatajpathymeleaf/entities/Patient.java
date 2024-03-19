package ma.enset.springmvcspringdatajpathymeleaf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Collection;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min=2, max=20)
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date birthday;
    private boolean sick;
    @DecimalMin("0")
    @DecimalMax("100")
    private int score;
    @OneToMany(mappedBy = "patient",fetch =  FetchType.LAZY)
    private Collection<Appointment> appointments;
}
