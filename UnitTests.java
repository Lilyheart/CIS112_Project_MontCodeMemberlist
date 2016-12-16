// import ch05.queues.*;
import java.util.Scanner;

class UnitTests {
  static int testNum = 0, fails = 0, skipped = 0;
  static boolean testError = false, isSkipping = false;
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    //testMontCodeMember();
    testMainMontCodeMembers();

    System.out.println("\nTotal test failed: " + fails);
    System.out.println("Total test skipped: " + skipped);
    System.exit(0);
  }

  static int test(String testName, Object test, Object goal){
    testNum++;
    if(test.equals(goal)){
      System.out.println(testNum + ") " + testName + ": " + "Passed");
      return 0;
    } else {
      System.out.println("\n" + testNum + ") " + testName + ": " + "----FAILED");
      System.out.println("Test:\n" + test + "\nGoal:\n" + goal + "\n");
    }
    return 1;
  }

  static void testMontCodeMember() {
    String[] testName1 = {"First1", "Last1"};
    String[] testName2 = {"First2", "Last2"};

    System.out.print("Do you want to SKIP the manual MontCodeMember tests? (y/n): ");
    if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
      isSkipping = true;
    }

    MontCodeMember testMember1 = null;
    try {
      if(testMember1 != null) {
        testError = true;
      }
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test create empty MontCodeMember", testError, false);
    testError = false;

    try {
      testMember1 = new MontCodeMember(testName1[0],testName1[1]);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test constructor - testMember1", testError, false);
    testError = false;

    MontCodeMember testMember2 = null;
    try {
      testMember2 = new MontCodeMember(testName2[0],testName2[1]);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test constructor - testMember2", testError, false);
    testError = false;

    try {
      fails += test("Test getFirstName 1",
        testMember1.getFirstName(), testName1[0]);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getFirstName Exception 1", testError, false);
    testError = false;

    try {
      testMember1.setFirstName("Anna");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setFirstName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test getFirstName 2",
        testMember1.getFirstName(), "Anna");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getFirstName Exception 2", testError, false);
    testError = false;

    try {
      fails += test("Test getLastName 1",
        testMember1.getLastName(), testName1[1]);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getLastName Exception 1", testError, false);
    testError = false;

    try {
      testMember1.setLastName("Smith");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setLastName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test getLastName 2",
        testMember1.getLastName(), "Smith");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getLastName Exception 2", testError, false);
    testError = false;

    try {
      testMember1.setEMail("test@gmail.com");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setEMail Exception 1", testError, false);
    testError = false;

    try {
      fails += test("Test getEMail 1",
        testMember1.getEMail(), "test@gmail.com");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getEMail Exception 2", testError, false);
    testError = false;

    try {
      testMember1.setGithubUserName("Lilyheart");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setGithubUserName Exception 1", testError, false);
    testError = false;

    try {
      fails += test("Test getGithubUserName 1",
        testMember1.getGithubUserName(), "Lilyheart");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getGithubUserName Exception 2", testError, false);
    testError = false;

    try {
      testMember1.setGitterUserName("MontBot");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setGitterUserName Exception 1", testError, false);
    testError = false;

    try {
      fails += test("Test getGitterUserName 1",
        testMember1.getGitterUserName(), "MontBot");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getGitterUserName Exception 2", testError, false);
    testError = false;

    try {
      testMember1.setFccUserName("Lilyheart");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setFccUserName Exception 1", testError, false);
    testError = false;

    try {
      fails += test("Test getFccUserName 1",
        testMember1.getFccUserName(), "Lilyheart");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getFccUserName Exception 2", testError, false);
    testError = false;

    try {
      fails += test("Test compareTo",
        testMember1.compareTo(testMember2), 7);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test compareTo Exception", testError, false);
    testError = false;

    try {
      if(isSkipping) {
        System.out.println(++testNum + ") openGitHub() test skipped.");
        skipped++;
      } else {
        System.out.print("Okay to attempt openGitHub()? (y/n): ");
        if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
          testMember1.openGitHub();
          System.out.print("Did the GitHub profile page open correctly? (y/n): ");
          fails += test("Test openGitHub", keyboard.nextLine().toUpperCase().charAt(0), 'Y');
        } else {
          System.out.println(++testNum + ") openGitHub() test skipped.");
          skipped++;
        }
      }
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test openGitHub Exception", testError, false);
    testError = false;

    try {
      if(isSkipping) {
        System.out.println(++testNum + ") sendGitter() test skipped.");
        skipped++;
      } else {
        System.out.print("Okay to attempt sendGitter()? (y/n): ");
        if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
          testMember1.sendGitter();
          System.out.print("Did the Gitter message page open correctly? (y/n): ");
          fails += test("Test sendGitter", keyboard.nextLine().toUpperCase().charAt(0), 'Y');
        } else {
          System.out.println(++testNum + ") sendGitter() test skipped.");
          skipped++;
        }
      }
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test sendGitter Exception", testError, false);
    testError = false;

    try {
      if(isSkipping) {
        System.out.println(++testNum + ") sendEMail() test skipped.");
        skipped++;
      } else {
        System.out.print("Okay to attempt sendEMail()? (y/n): ");
        if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
          testMember1.sendEMail();
          System.out.print("Did the sendEMail message page open correctly? (y/n): ");
          fails += test("Test sendEMail", keyboard.nextLine().toUpperCase().charAt(0), 'Y');
        } else {
          System.out.println(++testNum + ") sendEMail() test skipped.");
          skipped++;
        }
      }
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test sendEMail Exception", testError, false);
    testError = false;

    isSkipping = false;
  }

  static void testMainMontCodeMembers() {
    MainMontCodeMembers.init();
  }

}
