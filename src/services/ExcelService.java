/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.File;
import java.io.PrintWriter;
import models.Offer;
import util.MyConnection;

/**
 *
 * @author chayma2
 */
public class ExcelService {
    public void ExcelCreator(){
        Offer offer=new Offer();
         MyConnection Mycnx = MyConnection.getInstance();
    java.sql.Connection cnx = Mycnx.getCnx();
        Offer_Service sm = new Offer_Service();
    try {
   PrintWriter pw= new PrintWriter(new File("../SalleDeSport_Offer_Reservation/src/documents/Offer.csv"));
   
   StringBuilder sb=new StringBuilder();
   sb.append("ID_Offer");
   sb.append(",");
   sb.append("Title_Offer");
   sb.append(",");
   sb.append("Description");
   sb.append(",");
   sb.append("Prix");
   sb.append(",");
   sb.append("Date_Debut");
   sb.append(",");
   sb.append("Date_fin");
   sb.append(",");
   sb.append("Nombre Max reservation");
   sb.append(",");
   sb.append("Nombre reservation");
   sb.append("\n");
        for (Offer m : sm.afficherOffer()) {
   sb.append(m.getIdOffer());
   sb.append(",");
   sb.append(m.getTitleOffer());
   sb.append(",");
   sb.append(m.getDescriptionOffer());
   sb.append(",");
   sb.append(m.getPrix());
   sb.append(",");
   sb.append(m.getDateDebOffer());
   sb.append(",");
   sb.append(m.getDateFinOffer());
    sb.append(",");
   sb.append(m.getNb_max_reservation());
    sb.append(",");
   sb.append(m.getNb_reservation());
   sb.append("\n");  
        }
   
   
   pw.write(sb.toString());
   pw.close();
   System.out.println("finished");
   } catch (Exception e) {
      // TODO: handle exception
   }
    }
    
}
