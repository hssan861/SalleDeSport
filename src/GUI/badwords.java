/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author lenovo
 */
public class badwords {
     private Set<String> badWords;

    public badwords() {
        badWords = new HashSet<>();
        // Add your list of bad words to the 'badWords' set here
        // For example:
        badWords.add("fuck");
        badWords.add("stupid");
    }

    public boolean containsBadWord(String text) {
        for (String word : text.split("\\s+")) {
            if (badWords.contains(word)) {
                return true;
            }
        }
        return false;
    }





    
}
