import java.io.*;

/*
  Input handler class by *Author name hidden* adapted from SRPN-Calc project. This is my own work.
*/
public class InputHandler {
  public int maxAllowedInt = 10;
  public InputHandler(){
    
  }
  public void setMax(int maxInt){
    this.maxAllowedInt = maxInt;
  }
  public int ReadLine() {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String userin;
    try { // Note : Handle exception
      Boolean goodInput = false;
      int userInput = 0;
        while(!goodInput){
        userin = input.readLine();
        if(cleanString(userin) != "***"){
          userInput = Integer.parseInt(cleanString(userin));
          if(userInput > 0 && userInput <= maxAllowedInt){
            goodInput=true;
          }else{
            System.out.println("Invalid Input");
          }
        }
      }
      return userInput;
    } catch (IOException e) {
      System.out.println("Error in reading line : " + e);
      return 999999;
    }
  }
  /*
   * It's very hard to consider everything the user might try to input. This
   * method will attempt to strip the string from anything that the user might
   * enter but is not actually usefull/accepted
   */
  private static String cleanString(String string) {
    if (string == null || string.length() == 0) { // NullorEmpty string
      return "***";
    } else {
      String removedCharacters = removeNotAllowed(string);
      if (removedCharacters == null || removedCharacters.length() == 0) {
        return "***";
      } else {
        return removedCharacters.trim();
      }
    }
  }

  private static String removeNotAllowed(String stringToClean){
    StringBuilder cleanString = new StringBuilder();
    for (String character : stringToClean.split("")){
      if (character.matches("[0-9]*")){
        cleanString.append(character);
      }
    }
    return cleanString.toString();
  }
}