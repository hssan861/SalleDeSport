
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pi.entities.Commande;
import pi.entities.Produit;
import pi.services.serviceCommande;
import pi.services.serviceProduit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Pi {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2023-09-30");

        // Créer une instance de la classe Produit avec les valeurs appropriées
        Produit p = new Produit(1, "tenue", "img", date, "des", "Cfitness");

        // Créer une instance de serviceProduit (assurez-vous d'avoir correctement instancié votre service)
        serviceProduit sp = new serviceProduit();

        // Appeler la méthode addProduit() pour ajouter le produit
        int newProductId = sp.add(p);
        if(newProductId == -1){
            System.out.println("On peut pas continuer , id invalide");
            return;
        }
        p.setId(newProductId);        
// Afficher les produits
        System.out.println(sp.afficher());
        
        
        
        
       
        
        
       //ajout commande
        Commande commande = new Commande(1, p.getId(), date, "adresse", "type", 1);
        serviceCommande sc = new serviceCommande();
        int newCommandId = sc.add(commande);
        if(newCommandId == -1){
            System.out.println("On peut pas continuer , id invalide");
            return;
        }
        System.out.println(newCommandId);
        commande.setId(newCommandId);        
// Afficher les commandes
        System.out.println(sc.afficher());
        
        // Modifier la commande
        serviceCommande serviceCommande = new serviceCommande();

        // Format de date
        Date nouvelleDate = dateFormat.parse("2023-10-15"); // Nouvelle date pour la commande

        // Remplissez les attributs de la commande à modifier
         // Remplacez par l'ID de la commande que vous souhaitez modifier
         // Remplacez par le nouvel ID du produit lié
        commande.setDate(nouvelleDate);  // Nouvelle date de commande
        commande.setAdresse("Nouvelle adresse");  // Nouvelle adresse
        commande.setType("Nouveau type");  // Nouveau type
        commande.setIdUser(3);  // Nouvel ID de l'utilisateur

        // Appelez la méthode modifier() pour mettre à jour la commande
        Boolean modificationCommandeReussie = serviceCommande.modifier(commande);

        // Vérifiez si la modification de la commande a réussi
        if (modificationCommandeReussie) {
            System.out.println("La commande a été modifiée avec succès.");
        } else {
            System.out.println("Échec de la modification de la commande.");
        }
    
        
        
        
        




// Remplissez les attributs du produit à modifier
p.setIdAdmin(3); // Remplacez par le nouvel ID de l'administrateur lié au produit
p.setTitre("New titre"); // Nouveau titre
p.setImage("New image"); // Nouvelle image
p.setDate(nouvelleDate); // Nouvelle date du produit
p.setDescription("New desc"); // Nouvelle description
p.setCategorie("New catég"); // Nouvelle catégorie

// Appelez la méthode modifier() pour mettre à jour le produit
Boolean modificationProduitReussie = sp.modifier(p);

// Vérifiez si la modification du produit a réussi
if (modificationProduitReussie) {
    System.out.println("Le produit a été modifié avec succès.");
} else {
    System.out.println("Échec de la modification du produit.");
}}}
    /*
            // Appelez la méthode supprimerProduit() pour supprimer le produit
        Boolean suppressionCommandeReussie = serviceCommande.supprimer(commande);

        // Vérifiez si la suppression a réussi
        if (suppressionCommandeReussie) {
            System.out.println("La commande a été supprimée avec succès.");
        } else {
            System.out.println("Échec de la suppression de la commande.");
        }
   
// Remplacez par l'ID du produit que vous souhaitez supprimer

        // Appelez la méthode supprimerProduit() pour supprimer le produit
        Boolean suppressionProduitReussie = sp.supprimer(p);

        // Vérifiez si la suppression a réussi
        if (suppressionProduitReussie) {
            System.out.println("Le produit a été supprimé avec succès.");
        } else {
            System.out.println("Échec de la suppression du produit.");
        }
} 
}


   //supprimer commande 
/*
serviceCommande serviceCommandeInstance = new serviceCommande();
Commande commandeASupprimer = new Commande();
commandeASupprimer.setId(1); // Remplacez par l'ID de la commande que vous souhaitez supprimer

// Appelez la méthode supprimerCommande() pour supprimer la commande
Boolean suppressionCommandeReussie = serviceCommande.supprimer(commandeASupprimer);

// Vérifiez si la suppression a réussi
if (suppressionCommandeReussie) {
    System.out.println("La commande a été supprimée avec succès.");
} else {
    System.out.println("Échec de la suppression de la commande.");
}
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   







    
    
    
    
    
    
    
    
    
    

       // Modifier un produit (remarque : vous devez remplacer les valeurs par celles que vous souhaitez)
//Produit produitAModifier = new Produit();
/*produitAModifier.setId(1); // Remplacez par l'ID du produit à modifier
produitAModifier.setTitre("Nouveau titre"); // Remplacez par le nouveau titre
produitAModifier.setImage("Nouvelle image"); // Remplacez par la nouvelle image
produitAModifier.setDate(new Date()); // Remplacez par la nouvelle date
produitAModifier.setDescription("Nouvelle description"); // Remplacez par la nouvelle description
produitAModifier.setCategorie("Nouvelle catégorie"); // Remplacez par la nouvelle catégorie
produitAModifier.setIdAdmin(2); // Remplacez par le nouvel ID Admin*/


        // Appelez la méthode modifier() pour mettre à jour le produit
     /*   Boolean modificationReussieProduit = serviceProduit.modifier(produitAModifier);

        // Vérifiez si la modification a réussi
        if (modificationReussieProduit) {
            System.out.println("Le produit a été modifié avec succès.");
        } else {
            System.out.println("Échec de la modification du produit.");
        }

        // Supprimer un produit (remarque : vous devez remplacer l'ID par celui du produit à supprimer)
        int idProduitASupprimer = 1; // Remplacez par l'ID du produit à supprimer
        Produit produitASupprimer = new Produit();
        produitASupprimer.setId(idProduitASupprimer);

        // Appelez la méthode supprimer() pour supprimer le produit
        Boolean suppressionReussieProduit = serviceProduit.supprimer(produitASupprimer);

        // Vérifiez si la suppression a réussi
        if (suppressionReussieProduit) {
            System.out.println("Le produit a été supprimé avec succès.");
        } else {
            System.out.println("Échec de la suppression du produit.");
        }

        // Vous pouvez répéter des étapes similaires pour tester la classe Commande
    }*/

