package comptoirs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import comptoirs.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    
}
