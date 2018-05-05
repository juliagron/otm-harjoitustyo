/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

/**
 * Luokka, joka määrittää käyttäjän tekemän tuloksen
 * @author juliagro
 */
public class HighScore {
    
    private String name;
    private Integer time;
    
    public HighScore(String name, Integer time) {
        this.name = name;
        this.time = time;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Integer getTime() {
        return this.time;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTime(Integer time) {
        this.time = time;
    }
}
