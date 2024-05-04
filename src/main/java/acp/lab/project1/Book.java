/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acp.lab.project1;
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @author addan
 */
public class Book {
    public int ID;
    public String ISBN;
    public String title;
    public String description;
    public int yearPublished;
    public float rating;
    public int pages;
    public String thumbnailURL;
    public Date borrowDate;
    public Date returnDate;
    public BigDecimal fine;
}
