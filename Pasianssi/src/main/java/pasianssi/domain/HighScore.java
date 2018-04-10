/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.domain;

/**
 *
 * @author juliagro
 */
public class HighScore {
    
    private String name;
    private String time;
    private Integer id;
    
    public HighScore(Integer id, String name, String time){
        this.name = name;
        this.time = time;
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getTime(){
        return this.time;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setTime(String time){
        this.time = time;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
}
