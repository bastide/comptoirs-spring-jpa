package comptoirs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

import comptoirs.entity.*;
import comptoird.dto.*;
import comptoirs.dao.*;

@Component
@Log4j2 // Génère le 'logger' pour afficher les messages de trace
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitDAO;

    @Autowired
    private CategorieRepository categorieDAO;

    @Override
    /**
     * Equivalent de la méthode 'main' pour une application Spring Boot
     **/
    public void run(String... args) throws Exception {

        tapezEnterPourContinuer();

        log.info("Recherche par clé");
        Optional<Produit> op = produitDAO.findById(1);
        op.ifPresent(p -> {
            log.info("On a trouvé le produit : {}", p);
        });

        tapezEnterPourContinuer();

        log.info("Insertion d'une catégorie avec plusieurs produits");
        Categorie nouvelleCat = new Categorie("Ma Nouvelle Catégorie");
        Produit np1 = new Produit("Un nouveau produit", nouvelleCat);
        Produit np2 = new Produit("Autre produit", nouvelleCat);
        nouvelleCat.getProduits().add(np1);
        nouvelleCat.getProduits().add(np2);
        categorieDAO.save(nouvelleCat);

        tapezEnterPourContinuer();

        log.info("Exécution d'une requête 'custom' JPQL");
        int codeCategorie = 1;
        List<UnitesParProduit> resultat = produitDAO.produitsVendusJPQL(codeCategorie);
        resultat.forEach( // Une autre syntaxe pour itérer sur une liste !
            ligne -> log.info("Pour {} on a vendu {} unités", ligne.getNom(), ligne.getUnites())
        );        
         
        tapezEnterPourContinuer();

        log.info("Même requête en SQL natif");
        resultat = produitDAO.produitsVendusSQL(codeCategorie);
        resultat.forEach( 
            ligne -> log.info("Pour {} on a vendu {} unités", ligne.getNom(), ligne.getUnites())
        );        
      
        tapezEnterPourContinuer();    

    }

    public static void tapezEnterPourContinuer() throws Exception {
        System.out.println("Tapez \"ENTER\" pour continuer...");
        System.in.read();
    }
}
