import java.util.Scanner;
import java.io.*;

public class MontCodeMembersApp  {
  static Scanner keyboard = new Scanner(System.in);
  static boolean isEndProgram = false;
  static MontCodeMembersBST memberList;

  public static void main(String[] args) {
    System.out.println("Welcome to the MontCode Member Contact Manager");
    init();

    while(!isEndProgram) {
      mainMenu();
    }
  }

  static void init() {
    int response = -1;
    String fileName;
    File file;
    boolean isOkay = false;

    System.out.println("How would you like to initialize the database?");
    while (response < 1 || response > 2) {
      System.out.println("  1: Load Saved List   2: Make New List");
      System.out.print("What now?>> ");
      if(keyboard.hasNextInt()){
        response = keyboard.nextInt();
        if(response < 1 || response > 2) {
          System.out.println(">> Input a valid option.");
        }
      } else {
        System.out.println(">> You didn't input a number.  " +
          "Please input a number");
      }
      keyboard.nextLine();
    }
    if(response == 1) {
      while (!isOkay) {
        System.out.print("Please enter the stored file name (Homework test file=members.ser)>> ");
        fileName = keyboard.nextLine();
        file = new File(fileName);
        if(!file.exists()) {
          System.out.println("--==>>> File does not exist <<<==--");
        } else {
          try {
            memberList = new MontCodeMembersBST(fileName);
          } catch (Exception e) {
            System.out.println("Unable to load file.  Starting new List");
            memberList = new MontCodeMembersBST();
          }
          isOkay = true;
        }
      }
    } else {
      memberList = new MontCodeMembersBST();
    }
  }

  static void saveToFile() {
    //TODO add option to save to existing file in preferences
    System.out.print("Please enter the name of the file you want to store it in (Homework test file=members.ser)>> ");
    //TODO fileName = new File(keyboard.nextLine());
    //TODO memberList.saveToDrive(fileName);
  }

  static void mainMenu() {
    int response = -1, maxMenu = 9;

    System.out.println("\n\n*** Commands ***");
    while (response < 1 || response > maxMenu) {
      System.out.println("  1: Add a member         2: Update a Member");
      System.out.println("  3: Delete a Member      4: Display a Member's full details");
      System.out.println("  5: List all members     6: Change List Options");
      System.out.println("  7: Fill with Demo Data  8: Save to file");
      System.out.println("  9: Exit Program");
      System.out.print("What now?>> ");
      if(keyboard.hasNextInt()){
        response = keyboard.nextInt();
        if(response < 1 || response > maxMenu) {
          System.out.println(">> Input a valid option.");
        }
      } else {
        System.out.println(">> You didn't input a number.  " +
          "Please input a number");
      }
      keyboard.nextLine();
    }
    switch (response) {
      case 1: addMember();
        break;
      case 2: updateMember();
        break;
      case 3: deleteMember();
        break;
      case 4: displayMember();
        break;
      case 5: listAllMembers();
        break;
      case 6: listOptions();
        break;
      case 7: //TODO Fill with Demo Data
        break;
      case 8: saveToFile();
        break;
      default: isEndProgram = true;
    }
  }

  private static boolean findMember() {
    String firstName, lastName;
    boolean isFound = true;

    System.out.print("Please enter the first name>> ");
    firstName = keyboard.nextLine();
    System.out.print("Please enter the last name>> ");
    lastName = keyboard.nextLine();
    try {
      memberList.setCurrentMember(firstName, lastName);
    } catch (MCRecordNotFoundException e) {
      return false;
    }

    return isFound;
  }

  static void addMember() {
    String firstName, lastName;

    System.out.print("Please enter the first name>> ");
    firstName = keyboard.nextLine();
    System.out.print("Please enter the last name>> ");
    lastName = keyboard.nextLine();
    try {
      memberList.add(firstName, lastName);
      updateSecondaryInformation();
      System.out.println("Added: \n" + memberList.getCurrentMember());
    } catch (MCRecordDuplicateException e) {
      System.out.println("--==>>> Member already exists in database <<<==--");
      System.out.println("Would you rather update this member (y/n)?");
      if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
        memberList.setCurrentMember(firstName, lastName);
        updateSecondaryInformation();
        System.out.println("Updated: \n" + memberList.getCurrentMember());
      }
    }
  }

  static void displayMember() {
    findMember();
    System.out.println(memberList.getCurrentMember());
  }

  static void updateMember() {
    findMember();
    updateSecondaryInformation();
    System.out.println("Updated Member: \n" + memberList.getCurrentMember());
  }

  private static void updateSecondaryInformation() {
    int responseInt = -1, maxMenu = 8;
    String responseString = "";
    String[] updateKeys = {"First Name", "Last Name", "E-Mail", "GitHub Username", "Gitter Username", "FCC Username"};

    while(responseInt != maxMenu) {
      responseInt = -1;
      System.out.println("*** What would you like to update? ***");
      while (responseInt < 1 || responseInt > maxMenu) {
        System.out.println("  1: First Name       2: Last Name");
        System.out.println("  3: eMail            4: GitHub userID");
        System.out.println("  5: Gitter userID    6: Free Code Camp user ID");
        System.out.println("  7: Display current contact card");
        System.out.println("  8: None - End updating information");
        System.out.print("What now?>> ");
        if(keyboard.hasNextInt()){
          responseInt = keyboard.nextInt();
          if(responseInt < 1 || responseInt > maxMenu) {
            System.out.println(">> Input a valid option.");
          }
        } else {
          System.out.println(">> You didn't input a number.  " +
            "Please input a number");
        }
        keyboard.nextLine();
      }
      if(responseInt <= 6) {
        System.out.print("What would you like to update the" + updateKeys[responseInt-1] + " to?>> ");
        responseString = keyboard.nextLine();
      }
      try {
        if(responseInt <= 6) {
          memberList.update(updateKeys[responseInt-1], responseString);
        }
        if(responseInt == 4) {
          System.out.print("Also set Gitter to " + responseString + "  (y/n)?>> ");
          if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
            memberList.update("gitterUserName", responseString);
          }
          System.out.print("Also set Free Code Camp to " + responseString + "  (y/n)?>> ");
          if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
            memberList.update("fccUserName", responseString);
          }
        }
        if(responseInt == 7) {
          System.out.println(memberList.getCurrentMember());
        }
      } catch (NoSuchFieldException e) {System.out.println("Not updated. Please try again");}
    }
  }

  static void deleteMember() {
    boolean isFound;

    isFound = findMember();
    if(isFound) {
      System.out.println("User Found: \n" + memberList.getCurrentMember());
      System.out.print("Continue to delete?  (y/n)>> ");
      if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
        memberList.removeCurrentMember();
      }
    } else {
      System.out.println("****************************");
      System.out.println("* This user was not Found! *");
      System.out.println("*    No one was deleted    *");
      System.out.println("****************************\n");
    }
  }

  //TODO listOptions()
  static void listOptions() {
    int responseInt;
    String responseString;

    responseInt = -1;
    System.out.println("*** What would you like to sort by? ***");
    while (responseInt < 1 || responseInt > 4) {
      System.out.println("  1: Last Name Asc    2: Last Name Dec");
      System.out.println("  3: First Name Asc   4: First Name Dec");
      System.out.print("What now?>> ");
      if(keyboard.hasNextInt()){
        responseInt = keyboard.nextInt();
        if(responseInt < 1 || responseInt > 4) {
          System.out.println(">> Input a valid option.");
        }
      } else {
        System.out.println(">> You didn't input a number.  " +
          "Please input a number");
      }
      keyboard.nextLine();
    }
    switch (responseInt) {
      case 1:
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;

    }

    //TODO deal with selection
    // TODO Change other y/n places
    // while (responseString != 'Y' || responseString != 'N') {
    //   System.out.println("Would you like to store this as a preference (y/n)?");
    //   responseString = keyboard.nextLine().toUpperCase().charAt(0);
    //   if(responseString == 'Y') {
    //     //TODO prefs.put("SEARCH", "byLastAsc");
    //   } else if(responseString != 'N') {
    //     //TODO Error handleing
    //   }
    // }
  }

  static void listAllMembers() {
    //TODO listOption = prefs.get("SEARCH", "byLastAsc");
    String listOption = "byLastAsc";

    //TODO List Options
    switch (listOption) {
      case "byLastAsc": listAllMembersByLastAsc();
        break;
      case "byLastDec": listAllMembersByLastDec();
        break;
      case "byFirstAsc": listAllMembersByFirstAsc();
        break;
      case "byFirstDec":listAllMembersByFirstDec();
        break;
      default: listAllMembersByLastAsc();
    }
  }

  static void listAllMembersByLastAsc() {
    int numOfMembers;

    numOfMembers = memberList.reset("byLastAsc");
    if(numOfMembers == 0) {
      System.out.println("There are no members in the list.");
      return;
    }
    for (int i = 0; i < numOfMembers; i++) {
      System.out.println(memberList.getNext().nameToString());
    }
  }

  static void listAllMembersByLastDec() {
    int numOfMembers;

    numOfMembers = memberList.reset("byLastDec");
    if(numOfMembers == 0) {
      System.out.println("There are no members in the list.");
      return;
    }
    for (int i = 0; i < numOfMembers; i++) {
      System.out.println(memberList.getNext().nameToString());
    }
  }

  static void listAllMembersByFirstAsc() {
    int numOfMembers;

    numOfMembers = memberList.reset("byFirstAsc");
    if(numOfMembers == 0) {
      System.out.println("There are no members in the list.");
      return;
    }
    for (int i = 0; i < numOfMembers; i++) {
      System.out.println(memberList.getNext().nameToString());
    }
  }

  static void listAllMembersByFirstDec() {
    int numOfMembers;

    numOfMembers = memberList.reset("byFirstDec");
    if(numOfMembers == 0) {
      System.out.println("There are no members in the list.");
      return;
    }
    for (int i = 0; i < numOfMembers; i++) {
      System.out.println(memberList.getNext().nameToString());
    }
  }

}
