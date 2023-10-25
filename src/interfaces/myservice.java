/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import models.Commande;
import models.Produit;

import java.util.List;

/**
 *
 * @author hama
 * @param <>
 */
public interface myservice <T> {
    void insert(int idc, int idp);

    public int add(T t);

    List<Produit> afficherproduit(int id);

    public List<T> afficher();
    public Boolean modifier(T t);

    Boolean DeleteCommandeproduit(int idc ,int idp);

    public Boolean supprimer(T t);
}


