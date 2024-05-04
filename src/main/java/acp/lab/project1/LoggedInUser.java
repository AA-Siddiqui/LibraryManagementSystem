/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acp.lab.project1;
import acp.lab.project1.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 *
 * @author addan
 */
public class LoggedInUser {
    public static int userID;
    public static int userType;
    public static String fullName;
    public static String phoneNo;
    public static String address;
    public static String username;
    public static String password;
    public static LinkedList<Book> booksBorrowed;
    public static LinkedList<Integer> cart;
    
    public static int borrowedBookCount;
    
    public static long ms3 = 259200000;
    public static long ms5 = 432000000;
    
    public static void reset() {
        userID = -1;
        userType = -1;
        fullName = new String("FullName");
        phoneNo = new String("0123456789123");
        address = new String("308 Negra Arrayo Lane, ABQ, NM, 87104");
        username = new String("user");
        password = new String("password");
        booksBorrowed = new LinkedList<>();
        cart = new LinkedList<>();
        
        borrowedBookCount = 0;
    }
    
    public static void update(int userID) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
            return;
        }
        
        try {
            ResultSet result = conn.createStatement().executeQuery("select UserId, UserType, FullName, PhoneNo, Address, Username, Password from UserDetails where UserId = " + Integer.valueOf(userID).toString());
            while (result.next()) {
                LoggedInUser.userType = result.getInt("UserType");
                LoggedInUser.userID = result.getInt("UserId");
                LoggedInUser.fullName = result.getString("FullName");
                LoggedInUser.phoneNo = result.getString("PhoneNo");
                LoggedInUser.address = result.getString("Address");
                LoggedInUser.username = result.getString("Username");
                LoggedInUser.password = result.getString("Password");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date refDate = null;
        try {
            refDate = formatter.parse("1999-12-31");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        
        LinkedList<Book> books = new LinkedList<>();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from Record where UserId = " + LoggedInUser.userID);
            while (result.next()) {
                Book temp = new Book();
                temp.ID = result.getInt("BookId");
                temp.borrowDate = result.getDate("BorrowDate");
                temp.returnDate = result.getDate("ReturnDate");
                temp.fine = result.getBigDecimal("Fine");
                books.add(temp);
                
                if (temp.returnDate.compareTo(refDate) == 0) LoggedInUser.borrowedBookCount++;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        try {
            for (int i = 0; i < books.size(); i++) {
                ResultSet result = conn.createStatement().executeQuery("select ISBN, Title, Description, YearPublished, Rating, Pages, ThumbnailURL from Book where BookId = " + books.get(i).ID);
                while (result.next()) {
                    Book temp = books.get(i);
                    temp.ISBN = result.getString("ISBN");
                    temp.title = result.getString("Title");
                    temp.description = result.getString("Description");
                    temp.yearPublished = result.getInt("YearPublished");
                    temp.rating = result.getFloat("Rating");
                    temp.thumbnailURL = result.getString("ThumbnailURL");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        LoggedInUser.booksBorrowed = books;
    }
}
