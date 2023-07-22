package danekerscode.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import danekerscode.server.model.audit.DateAudit;
import danekerscode.server.utils.Gender;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @JsonIgnore
    private String password;

    private String name;
    private String surname;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToMany(mappedBy = "user")
    private List<AmazonFile> files;

    @PrePersist
    private void onCreate(){
        this.files = new ArrayList<>();
    }
}