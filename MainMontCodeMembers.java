import ch08.trees.BinarySearchTree;
import java.util.Scanner;

public class MainMontCodeMembers  {
  static boolean isEndProgram = false;
  static Scanner keyboard = new Scanner(System.in);
  static BinarySearchTree<MontCodeMember> montCodeMembers;
  static MontCodeMember currentMember;

  public static void main(String[] args) {
    System.out.println("Welcome to the MontCode Member Contact Manager");
    init();

    while(!isEndProgram) {
      mainMenu();
    }
  }

  static void init() {
    int response = -1;

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
        //TODO Load List
    } else {
      montCodeMembers = new BinarySearchTree<MontCodeMember>();
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
      case 5: //TODO List all Members
        break;
      case 6: //TODO Fill with Demo Data
        break;
      default: isEndProgram = true;
    }
  }

  static void addMember() {
    String firstName, lastName, response;

    System.out.print("Please enter the first name>> ");
    firstName = keyboard.nextLine();
    System.out.print("Please enter the last name>> ");
    lastName = keyboard.nextLine();
    currentMember = new MontCodeMember(firstName, lastName);
    //TODO check to see if exists before adding
    updateSecondaryInformation();
    System.out.println("Added: \n" + currentMember);

    montCodeMembers.add(currentMember);
    currentMember = null;
  }

  static void displayMember() {
    //TODO displayMember()
    currentMember = null;
  }

  static void updateMember() {
    //TODO updateMember()
    findMember();
    updateSecondaryInformation();
    System.out.println("Updated Member: \n" + currentMember);
    currentMember = null;
  }

  private static void updateSecondaryInformation() {
    int response = -1, maxMenu = 7;

    while(response != maxMenu) {
      response = -1;
      System.out.println("*** What would you like to update? ***");
      while (response < 1 || response > maxMenu) {
        System.out.println("  1: First Name       2: Last Name");
        System.out.println("  3: eMail            4: GitHub userID");
        System.out.println("  5: Gitter userID    6: Free Code Camp user ID");
        System.out.println("  7: None - End updating information");
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
        case 1: currentMember.setFirstName(getUpdateValue());
          break;
        case 2: currentMember.setLastName(getUpdateValue());
          break;
        case 3: currentMember.setEMail(getUpdateValue());
          break;
        case 4: currentMember.setGithubUserName(getUpdateValue());
          System.out.print("Also set Gitter to " + currentMember.getGithubUserName() + "?>> ");
          if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
            currentMember.setGitterUserName(currentMember.getGithubUserName());
            System.out.print("Also set Free Code Camp to " + currentMember.getGithubUserName() + "?>> ");
            if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
              currentMember.setFccUserName(currentMember.getGithubUserName());
            }
          }
          break;
        case 5: currentMember.setGitterUserName(getUpdateValue());
          break;
        case 6: currentMember.setFccUserName(getUpdateValue());
          break;
        default:
      }
    }
  }

  private static String getUpdateValue() {
    String response;

    System.out.print("What would you like to update this to?>> ");
    response = keyboard.nextLine();

    return response;
  }

  static void deleteMember() {
    findMember();
    if(currentMember != null) {
      System.out.println("User Found: \n" + currentMember);
      System.out.print("Continue to delete?>> ");
      if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
        montCodeMembers.remove(currentMember);
      }
    } else {
      System.out.println("****************************");
      System.out.println("* This user was not Found! *");
      System.out.println("*    No one was deleted    *");
      System.out.println("****************************");
      System.out.println();
    }
    currentMember = null;
  }

  private static void findMember() {
    MontCodeMember searchMember;
    String firstName, lastName;

    System.out.print("Please enter the first name>> ");
    firstName = keyboard.nextLine();
    System.out.print("Please enter the last name>> ");
    lastName = keyboard.nextLine();
    searchMember = new MontCodeMember(firstName, lastName);

    currentMember = montCodeMembers.get(searchMember);
  }
}
