import java.util.Scanner;
import java.io.*;

public class MontCodeMembersApp  {
  static Scanner keyboard = new Scanner(System.in);
  static boolean isEndProgram = false;
  static MontCodeMembersBST memberList;
  static MockDataMaker mockData = null;

  public static void main(String[] args) {
    System.out.println("Welcome to the MontCode Member Contact Manager");
    init();

    while(!isEndProgram) {
      mainMenu();
    }
    //IDEA prompt to saveToFile if unsaved changes
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
        if(fileName == null || fileName.isEmpty()) {
          fileName = "members.ser";
        }
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

  static void displayConfirmationMessage(String message) {
    System.out.println(">>>>> " + message + " <<<<<");
    System.out.println("Press any key to continue");
    keyboard.nextLine();
  }

  static boolean findMember() {
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
      case 7: demoData();
        break;
      case 8: saveToFile();
        break;
      default: isEndProgram = true;
    }
  }

  static void addMember() {
    String firstName, lastName;

    System.out.print("Please enter the first name>> ");
    firstName = keyboard.nextLine();
    System.out.print("Please enter the last name>> ");
    lastName = keyboard.nextLine();
    try {
      memberList.add(firstName, lastName);
      displayConfirmationMessage(firstName + " " + lastName + " added.  Please update secondary information.");
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

  static void updateMember() {
    findMember();
    updateSecondaryInformation();
    System.out.println("Updated Member: \n" + memberList.getCurrentMember());
  }

  private static void updateSecondaryInformation() {
    int responseInt = -1, maxMenu = 8;
    String responseString = "", moreText;
    String[] updateKeys = {"First Name", "Last Name", "E-Mail", "GitHub Username", "Gitter Username", "FCC Username"};
    int changesMade = 0;

    while(responseInt != maxMenu) {
      responseInt = -1;
      moreText = null;
      System.out.println("*** What would you like to update? ***");
      while (responseInt < 1 || responseInt > maxMenu) {
        System.out.println("  1: First Name        2: Last Name");
        System.out.println("  3: E-Mail            4: GitHub Username");
        System.out.println("  5: Gitter Username   6: FCC Username");
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
        System.out.print("What would you like to update the " + updateKeys[responseInt-1] + " to?>> ");
        responseString = keyboard.nextLine();
      }
      try {
        if(responseInt <= 6) {
          memberList.update(updateKeys[responseInt-1], responseString);
          changesMade++;
          if(responseInt != 4) {
            displayConfirmationMessage("The " + updateKeys[responseInt-1] + " was updated.");
          }
        }
        if(responseInt == 4) {
          System.out.print("Also set Gitter to " + responseString + "  (y/n)?>> ");
          if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
            memberList.update("Gitter Username", responseString);
            changesMade++;
            moreText = " and Gitter Username";
          }
          System.out.print("Also set Free Code Camp to " + responseString + "  (y/n)?>> ");
          if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
            memberList.update("FCC Username", responseString);
            changesMade++;
            if(moreText == null) {
              moreText = " and FCC Username";
            } else {
              moreText = ", Gitter Username and FCC Username";
            }
          }
          if(moreText == null) {
            displayConfirmationMessage("The " + updateKeys[responseInt-1] + " was updated.");
          } else {
            displayConfirmationMessage("The " + updateKeys[responseInt-1] + moreText + " were updated.");
          }
        }
        if(responseInt == 7) {
          System.out.println(memberList.getCurrentMember());
        }
      } catch (NoSuchFieldException e) {System.out.println("Not updated. Please try again");}
    }
    if(changesMade == 0) {
      displayConfirmationMessage("No information was updated");
    } else if (changesMade == 1) {
      displayConfirmationMessage(changesMade + " update was processed.");
    } else  {
      displayConfirmationMessage(changesMade + " updates were processed.");
    }
  }

  static void deleteMember() {
    boolean isFound;
    String confirmationMessage = "Sucessfully deleted";

    if(memberList.size()== 0) {
      displayConfirmationMessage("There are currently no members in the database to delete");
      return;
    }
    isFound = findMember();
    if(isFound) {
      System.out.println("User Found: \n" + memberList.getCurrentMember());
      System.out.print("Continue to delete?  (y/n)>> ");
      if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
        memberList.removeCurrentMember();
      }
    } else {
      confirmationMessage = "**************************** <<<<<\n"
                          + ">>>>> * This user was not Found! * <<<<<\n"
                          + ">>>>> *    No one was deleted    * <<<<<\n"
                          + ">>>>> ****************************";
    }
    displayConfirmationMessage(confirmationMessage);
  }

  static void displayMember() {
    findMember();
    displayConfirmationMessage("\n" + memberList.getCurrentMember() + "\n");
  }

  static void listOptions() {
    int responseInt;
    String responseString;

    responseInt = -1;
    System.out.println("*** What would you like to sort by? ***");
    while (responseInt < 1 || responseInt > 4) {
      System.out.println("  1: Last Name Asc    2: Last Name Dec");
      //IDEA System.out.println("  3: First Name Asc   4: First Name Dec");
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
      // case 3:
      //   break;
      // case 4:
      //   break;

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
      // IDEA Sorts by First Name
      // case "byFirstAsc": listAllMembersByFirstAsc();
      //   break;
      // case "byFirstDec":listAllMembersByFirstDec();
      //   break;
      default: listAllMembersByLastAsc();
    }

    displayConfirmationMessage("Displayed all " + memberList.size() + " members.");
  }

  static void listAllMembersByLastAsc() {
    int numOfMembers;

    numOfMembers = memberList.reset("byLastAsc");
    if(numOfMembers == 0) {
      System.out.println("There are no members in the list.");
      return;
    }
    for (int i = 0; i < numOfMembers; i++) {
      if(i % 10 == 0 && i != 0) {
        System.out.println(">>>>> Enter X to cancel or any key other for the next page");
        String responseString = keyboard.nextLine();
        if(responseString.toUpperCase().equals("X")) {
          return;
        }
      }
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

  //IDEA static void listAllMembersByFirstAsc() {}

  //IDEA static void listAllMembersByFirstDec() {}

  static void demoData() {
    int response = -1;
    if(mockData == null) {
      try {
        mockData = new MockDataMaker();
      } catch (IOException e) {
        System.out.println("Error opening MockData file.  Add MOCK_DATA.dat and try again");
      }
    }

    while (response < 1) {
      System.out.print("How many records would you like to add?>> ");
      if(keyboard.hasNextInt()){
        response = keyboard.nextInt();
        if(response < 1 ) {
          System.out.println(">> Input a valid option.");
        }
      } else {
        System.out.println(">> You didn't input a number.  " +
          "Please input a number");
      }
      keyboard.nextLine();
    }

    try {
      System.out.println("Please wait.  Adding demo users...");
      response = mockData.add(memberList, response);
      displayConfirmationMessage(response + " users added");
    } catch (NoMoreDemoDataException e) {
      displayConfirmationMessage(e.getMessage());
    }

  }

  static void saveToFile() {
    String fileName = null;
    //TODO add option to save to existing file in preferences
    System.out.print("Please enter the name of the file you want to store it in (Homework test file=members.ser)>> ");
    fileName = keyboard.nextLine();
    if(fileName == null || fileName.isEmpty()) {
      fileName = "members.ser";
    }
    try {
      memberList.saveToDrive(fileName);
    } catch (Exception e) {
      displayConfirmationMessage("File could not be saved.  Please try again with a new file name. " + e);
      return;
    }

    displayConfirmationMessage("File saved.");
  }

  //IDEA static void loadFromFile() {}

}
