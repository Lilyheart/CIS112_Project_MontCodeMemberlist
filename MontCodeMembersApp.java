import java.util.Scanner;
import java.io.File;

public class MontCodeMembersApp  {
  static boolean isEndProgram = false;
  static Scanner keyboard = new Scanner(System.in);
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
    File fileName;
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
        System.out.print("Please enter the stored file name (Homework test file=members.dat)>> ");
        fileName = new File(keyboard.nextLine());
        if(!fileName.exists()) {
          System.out.println("--==>>> File does not exist <<<==--");
        } else {
          memberList = new MontCodeMembersBST(fileName);
          isOkay = true;
        }
      }
    } else {
      memberList = new MontCodeMembersBST();
    }
  }

  static void mainMenu() {
    int response = -1, maxMenu = 7;

    System.out.println("*** Commands ***");
    while (response < 1 || response > maxMenu) {
      System.out.println("  1: Add a member         2: Update a Member");
      System.out.println("  3: Delete a Member      4: Display a Member's full details");
      System.out.println("  5: List all Members     6: Fill with Demo Data");
      System.out.println("  7: Exit Program");
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
      case 6: //TODO Fill with Demo Data
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
    } catch (RecordNotFoundException e) {
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
    } catch (DuplicateRecordException e) {
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
        System.out.print("What would you like to update this to?>> ");
        responseString = keyboard.nextLine();
      }
      try {
        switch (responseInt) {
          case 1: memberList.update("firstName", responseString);
            break;
          case 2: memberList.update("lastName", responseString);
            break;
          case 3: memberList.update("eMail", responseString);
            break;
          case 4: memberList.update("githubUserName", responseString);
            System.out.print("Also set Gitter to " + responseString + "  (y/n)?>> ");
            if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
              memberList.update("gitterUserName", responseString);
            }
            System.out.print("Also set Free Code Camp to " + responseString + "  (y/n)?>> ");
            if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
              memberList.update("fccUserName", responseString);
            }
            break;
          case 5: memberList.update("gitterUserName", responseString);
            break;
          case 6: memberList.update("fccUserName", responseString);
            break;
          case 7: System.out.println(memberList.getCurrentMember());
            break;
          default:
        }
      } catch (NoSuchFieldException e) {

      }
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

  static void listAllMembers() {
    int numOfMembers;

    numOfMembers = memberList.resetListAllMembers();
    for (int i = 0; i < numOfMembers; i++) {
      System.out.println(memberList.getNext().nameToString());
    }
  }

}
