/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;


/**
 *
 * @author Jacob
 */
public class User {

    private String username;
    private String password;
    private int points;
    
    public User() {

    }

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.points = 0;
    }

    public User(String user, String pass, int pnt) {
        this.username = user;
        this.password = pass;
        this.points = pnt;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int pnt) {
        this.points = pnt;
    }
}
