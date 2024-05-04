/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acp.lab.project1.utils;
import java.util.*;
/**
 *
 * @author addan
 */
public class StringSplit {
    public static String breakText(String text, int maxLength) {
        List<String> chunks = new ArrayList<>();
        StringBuilder currentChunk = new StringBuilder();
        String[] words = text.split("\\s"); // Split by whitespace

        for (String word : words) {
          if (currentChunk.length() + word.length() <= maxLength) {
            currentChunk.append(" ").append(word);
          } else {
            chunks.add(currentChunk.toString().trim());
            currentChunk = new StringBuilder(word);
          }
        }

        chunks.add(currentChunk.toString().trim());
        String n = "";
        for (int i = 0; i < chunks.size(); i++) {
            n += chunks.get(i) + "<br>";
        }
        return n.substring(0, n.length() - 4);
      }
}
