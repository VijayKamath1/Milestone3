package trident.healthx.milestone3.springoauth3.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder



@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;


    private String username;
    private String password;

    @Singular
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="user.authority",joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "ID")},
    inverseJoinColumns={@JoinColumn(name="AUTHORITY_ID",referencedColumnName = "ID")})
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNotExpired = true;

    @Builder.Default
    private Boolean enabled = true;


}
