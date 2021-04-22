package sample;

import javafx.fxml.FXML;

import java.util.ArrayList;

public class Subway
{
    // Class members
    private String size;
    private String bread;
    private String meat;
    private String cheese;
    private String sauce;
    private ArrayList<String> vegetables;
    private String salt;
    private String pepper;

    // Constructor
    public Subway(String sz, String br, String mt, String ch, String sa, ArrayList<String> ve, String sal, String pep)
    {
        this.size = sz;
        this.bread = br;
        this.meat = mt;
        this.cheese = ch;
        this.sauce = sa;
        this.vegetables = ve;
        this.salt = sal;
        this.pepper = pep;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public ArrayList<String> getVegetables() {
        return vegetables;
    }

    public void setVegetables(ArrayList<String> vegetables) {
        this.vegetables = vegetables;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
    }
}
