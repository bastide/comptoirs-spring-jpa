package comptoirs.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor  @RequiredArgsConstructor @ToString
public class Client {

	@Id
	@Basic(optional = false)
	@NonNull
	@Size(min = 1, max = 5)
	@Column(nullable = false, length = 5)
	private String code;

	@Basic(optional = false)
	@NonNull
	@Size(min = 1, max = 40)
	@Column(nullable = false, length = 40)
	private String societe;

	@Size(max = 30)
	@Column(length = 30)
	private String contact;
	
	@Size(max = 30)
	@Column(length = 30)
	private String fonction;
	
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
	
	@Size(max = 24)
	@Column(length = 24)
	private String telephone;
	
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	// message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field
	// contains phone or fax number consider using this annotation to enforce field
	// validation
	@Size(max = 24)
	@Column(length = 24)
	private String fax;
	
	@OneToMany(mappedBy = "client")
	private List<Commande> commandes = new ArrayList<>();


}
