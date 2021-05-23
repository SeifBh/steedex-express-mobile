package tn.seif.steedex.Models;

import java.util.Date;

public class Demande {

    public int id;
    public String titre;
    public String quoi;
    public String type;
    public String typeDC;
    public String etat;
    public String nom_prenom_recept;
    public String addresse_recept;
    public String telephone_recept;
    public String montant;
    public String note;
    public String lieu;
    public String description_produit;
    public String id_client;

    public String nomLivreur;
    public String prenomLivreur;
    public String telLivreur;

    public String dateCreation ;
    public String dateModification ;

    public Demande() {

    }


    public Demande(int id, String titre, String quoi, String type, String typeDC, String etat, String nom_prenom_recept, String addresse_recept, String telephone_recept, String montant, String note, String lieu, String description_produit, String id_client, String dateCreation, String dateModification
    ,String nomLivreur,String prenomLivreur,String telLivreur

    )

    {
        this.id = id;
        this.titre = titre;
        this.quoi = quoi;
        this.type = type;
        this.typeDC = typeDC;
        this.etat = etat;
        this.nom_prenom_recept = nom_prenom_recept;
        this.addresse_recept = addresse_recept;
        this.telephone_recept = telephone_recept;
        this.montant = montant;
        this.note = note;
        this.lieu = lieu;
        this.description_produit = description_produit;
        this.id_client = id_client;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.nomLivreur = nomLivreur;
        this.prenomLivreur = prenomLivreur;
        this.telLivreur = telLivreur;
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

    public String getQuoi() {
        return quoi;
    }

    public void setQuoi(String quoi) {
        this.quoi = quoi;
    }

    public String getTypeDC() {
        return typeDC;
    }

    public void setTypeDC(String typeDC) {
        this.typeDC = typeDC;
    }


    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateModification() {
        return dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public String getNomLivreur() {
        return nomLivreur;
    }

    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }

    public String getPrenomLivreur() {
        return prenomLivreur;
    }

    public void setPrenomLivreur(String prenomLivreur) {
        this.prenomLivreur = prenomLivreur;
    }

    public String getTelLivreur() {
        return telLivreur;
    }

    public void setTelLivreur(String telLivreur) {
        this.telLivreur = telLivreur;
    }

    @Override
    public String toString() {
        return "Demande{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", quoi='" + quoi + '\'' +
                ", type='" + type + '\'' +
                ", typeDC='" + typeDC + '\'' +
                ", etat='" + etat + '\'' +
                ", nom_prenom_recept='" + nom_prenom_recept + '\'' +
                ", addresse_recept='" + addresse_recept + '\'' +
                ", telephone_recept='" + telephone_recept + '\'' +
                ", montant='" + montant + '\'' +
                ", note='" + note + '\'' +
                ", lieu='" + lieu + '\'' +
                ", description_produit='" + description_produit + '\'' +
                ", id_client='" + id_client + '\'' +
                ", nomLivreur='" + nomLivreur + '\'' +
                ", prenomLivreur='" + prenomLivreur + '\'' +
                ", telLivreur='" + telLivreur + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", dateModification='" + dateModification + '\'' +
                '}';
    }
}
