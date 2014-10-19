import java.io.*;
import java.util.regex.*;
import java.util.*;

public class TelephoneNumbers{
    public static ArrayList<String> phonebook = new ArrayList<String>();
    
    public static void main(String[] args){
 File file;
  Scanner reader = null;
 String format = "(^\\(?0([0-9]{1,2})\\)?\\s[0-9]{3}(\\s|-)?[0-9]{4}$)|^(\\(?0([0-9]{3})\\)?\\s(((?=.*[A-Z])([A-Z0-9]{6,9}))|([0-9]{3}(-|\\s)?[0-9]{3}))$)";
 String first_part = "^\\(?0([0-9]{1,3})\\)?.*";
        Pattern pattern = Pattern.compile("\\t");
        Matcher matcher; 
       
       reader = new Scanner(System.in);
        String number = null;
        try{
     //   Scanner reader = new Scanner(new File("./input.txt"));
        while (reader.hasNextLine()) {
           number = reader.nextLine();
           matcher = pattern.matcher(number);
              if((number.matches("^\\(.*") && !number.matches("^\\(.*\\).*"))||(number.matches("^.*\\).*") && !number.matches("^\\(.*"))){
              System.out.println("Invalid Number: " +number + " does not have matching brackets");
           }else if(!number.matches("[0-9A-Z\\s\\(\\)-]*")){
              System.out.println("Invalid Number: " +number + " contains a(n) unknown character(s)");
           }else if(matcher.find()){
              System.out.println("Invalid Number: " +number + " contains a tab");                   
           }else if(number.matches(first_part)){
              if(number.matches("^\\(?0([0-9]{1,3})\\)?\\s.*")){
                 if(number.matches("^\\(?0(508|800)\\)?.*")){
                    if(number.matches(format))
                       System.out.println(normalise_initial(number));
                    else{
                       if(number.matches(first_part + "\\s[-\\s]{2,}")){
                          System.out.println("Invalid Number: " +number + 
                                             "contains too many or incorrectly placed spaces and/or hyphens");
                       }else{
                          System.out.println("Invalid Number: " +number + " contains incorrect many numbers ");
                       }
                    }     
                 }else if(number.matches("^\\(?((03)|(04)|(06)|(07)|(09)|(020)|(021)|(022)|(027)|(028)|(029))\\)?\\s.*")){
                    if(number.matches(format))
                       System.out.println(normalise(number));
                    else{
                       if(number.matches(first_part + "\\s[-\\s]{2,}")){
                          System.out.println("Invalid Number: " +number + 
                                             "contains too many or incorrectly placed spaces and/or hyphens");
                       }else{
                          System.out.println("Invalid Number: " +number + " contains incorrect amount numbers");
                       }
                    }
                 }else{
                    System.out.println("Invalid Number: " +number + " not a recognised code");
                 } 
              }else{
                 System.out.println("Invalid Number: " + number + " does not have a space after code");
              }
           }else{
              System.out.println("Invalid Number: " + number + " the code is incorrect; (either too long, contains incorrect characters or does not start with zero)");
           }
        }  
        }catch(Exception e){
         System.err.println("input.txt aint there");
         System.err.println(e);
      }
    }
    
    public static String normalise_initial(String number){
       int size = 0;
       String tmp ="";
       char compare;

 for(int i = 0; size < 12; i++){
           if(size == 4 || size == 8){
              tmp+= " ";
              size++;
              }
     compare = number.charAt(i);         
     switch (compare) {
     case'0': case'1': case'2': case'3': case'4': case'5': case'6': case'7': case'8': case'9':
  tmp += compare;
  size ++;
  break;
     case 'A': case 'B': case 'C':
  tmp += "2";
  size++;
  break;
     case 'D': case 'E': case 'F':
  tmp += "3";
  size++;
  break;
     case 'G': case 'H': case 'I':
  tmp += "4";
  size++;
  break;
     case 'J': case 'K': case 'L':
  tmp += "5";
  size++;
  break;
     case 'M': case 'N': case 'O':
  tmp += "6";
  size++;
  break;
     case 'P': case 'Q': case 'R': case 'S':
  tmp += "7";
  size++;
  break;
     case 'T': case 'U': case 'V':
  tmp += "8";
  size++;
  break;
     case 'W': case 'X': case 'Y': case 'Z':
  tmp += "9";
             size++;
             break;
             }
       }
       if(phonebook.contains(tmp)){
          tmp = tmp + " (Duplicate)";
       }else{
          phonebook.add(tmp);
       }
       return tmp;
    }

    public static String normalise(String number){
       String tmp = "";
       char current;     
       for(int i = 0; i<number.length(); i++){
          current = number.charAt(i);
          if(current!='(' && current != ')' && current !='-'){
                tmp+= current;
             }
          }
       if(tmp.charAt(tmp.length()-5)!=' '){
             tmp = tmp.substring(0,tmp.length() - 4) + " " + tmp.substring(tmp.length() -4, tmp.length());
       }
       if(phonebook.contains(tmp)){
          tmp = tmp + " (Duplicate)";
       }else{
          phonebook.add(number);
       }
       return tmp;
    }
}


