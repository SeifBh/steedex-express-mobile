package tn.seif.stedeex.Models;

public class Demande {

    public int id;
    public String titre;
    public String type;
    public String etat;
    public String nom_prenom_recept;
    public String addresse_recept;
    public String telephone_recept;
    public String montant;
    public String note;
    public String lieu;
    public String description_produit;
    public String id_client;

    public Demande() {

    }

    public Demande(int id, String titre, String type, String etat) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.etat = etat;
    }

    public Demande(int id, String titre, String type, String etat, String id_client) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.etat = etat;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNom_prenom_recept() {
        return nom_prenom_recept;
    }

    public void setNom_prenom_recept(String nom_prenom_recept) {
        this.nom_prenom_recept = nom_prenom_recept;
    }

    public String getAddresse_recept() {
        return addresse_recept;
    }

    public void setAddresse_recept(String addresse_recept) {
        this.addresse_recept = addresse_recept;
    }

    public String getTelephone_recept() {
        return telephone_recept;
    }

    public void setTelephone_recept(String telephone_recept) {
        this.telephone_recept = telephone_recept;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription_produit() {
        return description_produit;
    }

    public void setDescription_produit(String description_produit) {
        this.description_produit = description_produit;
    }

    @Override
    public String toString() {
        return "Demande{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                ", etat='" + etat + '\'' +
                ", nom_prenom_recept='" + nom_prenom_recept + '\'' +
                ", addresse_recept='" + addresse_recept + '\'' +
                ", telephone_recept='" + telephone_recept + '\'' +
                ", montant='" + montant + '\'' +
                ", note='" + note + '\'' +
                ", description_produit='" + description_produit + '\'' +
                ", id_client='" + id_client + '\'' +
                '}';
    }
}
