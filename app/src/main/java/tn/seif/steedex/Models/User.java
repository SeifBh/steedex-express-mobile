package tn.seif.steedex.Models;

public class User {
    public int id;
    public String nom;
    public String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public User(int id, String nom, String tel) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
    }
}
