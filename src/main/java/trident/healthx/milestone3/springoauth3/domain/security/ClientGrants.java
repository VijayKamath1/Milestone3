package trident.healthx.milestone3.springoauth3.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class ClientGrants {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;

private String grantType;

@ManyToOne
@JsonIgnore
@JoinColumn(name="clientId")
private Client client;



}
