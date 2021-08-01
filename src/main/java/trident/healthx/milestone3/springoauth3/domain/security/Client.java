package trident.healthx.milestone3.springoauth3.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String clientId;

    private String secret;

    private String scope;

    @Column(name="redirect_uri")
    private String redirectUri;

    @OneToMany(mappedBy = "client",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<ClientGrants> clientGrants;

}
