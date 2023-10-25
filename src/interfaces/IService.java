/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author HP
 */
public interface IService<T> {
    
    public void addReservation(T t);
    public List<T> afficher();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);
}
