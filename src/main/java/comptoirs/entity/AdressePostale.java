package comptoirs.entity;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Data;

@Embeddable // Ne donne pas lieu à une table relationnelle séparée
//@Emmbedded dans Client et Commande
@Data //Lombok, @Getter @Setter @NoArgsConstructor  @RequiredArgsConstructor @ToString
public class AdressePostale {
	@Size(max = 60)
	@Column(length = 60)
	private String adresse;
	
	@Size(max = 15)
	@Column(length = 15)
	private String ville;
	
	@Size(max = 15)
	@Column(length = 15)
	private String region;
	
	@Size(max = 10)
	@Column(length = 10)
	private String codePostal;
	
	@Size(max = 15)
	@Column(length = 15)
	private String pays;
    
}
