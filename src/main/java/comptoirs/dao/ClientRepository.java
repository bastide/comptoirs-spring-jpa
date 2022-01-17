package comptoirs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import comptoirs.entity.Client;

// This will be AUTO IMPLEMENTED by Spring 

public interface ClientRepository extends JpaRepository<Client, String> {
    Client findBySociete(String societe);
}
