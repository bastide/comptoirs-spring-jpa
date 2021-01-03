package comptoirs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comptoirs.dto.UnitesParProduit;
import comptoirs.entity.Produit;

// This will be AUTO IMPLEMENTED by Spring 

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
     * La requête est écrite en JPQL
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit, 
	 *		sous la forme d'une liste de DTO UnitesParProduit
	 */	
	@Query("SELECT p.nom as nom, SUM(li.quantite) AS unites "
		+ "FROM Categorie c "
		+ "JOIN c.produits p "
		+ "JOIN p.lignes li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
    List<UnitesParProduit> produitsVendusJPQL(Integer codeCategorie);
    
	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
     * La requête est écrite en SQL natif
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit, 
	 *		sous la forme d'une liste de DTO UnitesParProduit
	 */	
	@Query(
        value = 
        "SELECT nom, SUM(quantite) AS unites FROM Produit " +
        "INNER JOIN Ligne ON Produit_Reference = Reference "+
        "WHERE Categorie_Code = :codeCategorie " +
        "GROUP BY nom", 
        nativeQuery = true
    )
    List<UnitesParProduit> produitsVendusSQL(Integer codeCategorie);

}
