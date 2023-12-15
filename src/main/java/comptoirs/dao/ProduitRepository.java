package comptoirs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comptoirs.projection.UnitesParProduit;
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
     // La requête JPQL est exprimée en termes du modèle conceptuel de données
     // Avec cette requête JPQL, JPA va générer une jointure entre Ligne et Produit
    @Query(
    // Chaîne de caractères multilignes
    """
     SELECT l.produit.nom as nom, SUM(l.quantite) AS unites 
     FROM Ligne l
     WHERE l.produit.categorie.code = :codeCategorie
     GROUP BY nom
    """)
    List<UnitesParProduit> produitsVendusJPQL(Integer codeCategorie);

    /**
     * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
     * La requête est écrite en SQL natif
     * @param codeCategorie la catégorie à traiter
     * @return le nombre d'unités vendus pour chaque produit, 
     *		sous la forme d'une liste de DTO UnitesParProduit
     */	
    // La requête SQL native est exprimée en termes du modèle logique (relationnel) de données, 
    //            on doit expliciter les jointures
    @Query( value = """
            SELECT nom, SUM(quantite) AS unites FROM Produit
            INNER JOIN Ligne ON Produit_Reference = Reference
            WHERE Categorie_Code = :codeCategorie
            GROUP BY nom  
    """, 
    nativeQuery = true )
    List<UnitesParProduit> produitsVendusSQL(Integer codeCategorie);

}
