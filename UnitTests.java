import java.util.Scanner;

class UnitTests {
  static int testNum = 0, fails = 0, skipped = 0;
  static boolean testError = false, isSkipping = false;
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    testMontCodeMember();
    testMontCodeMembersBST();

    System.out.println("\nTotal tests: " + testNum);
    System.out.println("Total test failed: " + fails);
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
      System.out.println("Test:\n" + test + "\nGoal:\n" + goal + "\n----");
    }
    return 1;
  }

  static void testMontCodeMember() {
    String[] testName1 = {"First1", "Last1"};
    String[] testName2 = {"First2", "Last2"};

    System.out.print("Do you want to SKIP the manual MontCodeMember tests? (y/n): ");
    isSkipping = (keyboard.nextLine().toUpperCase().charAt(0) == 'Y');

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
      fails += test("Test nameToString",
        testMember1.nameToString(), "Anna Smith");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test nameToString Exception", testError, false);
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
        System.out.println(++testNum + ") openFCC() test skipped.");
        skipped++;
      } else {
        System.out.print("Okay to attempt openFCC()? (y/n): ");
        if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') {
          testMember1.openFCC();
          System.out.print("Did the FCC page open correctly? (y/n): ");
          fails += test("Test openFCC", keyboard.nextLine().toUpperCase().charAt(0), 'Y');
        } else {
          System.out.println(++testNum + ") openFCC() test skipped.");
          skipped++;
        }
      }
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test openFCC Exception", testError, false);
    testError = false;

  }

  static void testMontCodeMembersBST() {
    String testRecordReturnText = "";

    MontCodeMembersBST testList1 = null;
    try {
      if(testList1 != null) {
        testError = true;
      }
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test create empty MontCodeMembersBST", testError, false);
    testError = false;

    try {
      testList1 = new MontCodeMembersBST();
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test constructor - testList1", testError, false);
    testError = false;

    MontCodeMembersBST testList2 = null;
    try {
      testList2 = new MontCodeMembersBST("unitTestOther.ser");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test constructor - testList2", testError, false);
    testError = false;

    try {
      testList1.add("Anna", "Smith");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test testList1.add 1 Exception", testError, false);
    testError = false;

    try {
      testList1.add("Bob", "Wilber");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test testList1.add 2 Exception", testError, false);
    testError = false;

    try {
      testList1.add("Kevin", "Johnson");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test testList1.add 3 Exception", testError, false);
    testError = false;

    try {
      testList1.add("Robert", "Simpson");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test testList1.add 4 Exception", testError, false);
    testError = false;

    try {
      testList1.add("Robert", "Simpson");
    } catch (MCRecordDuplicateException e) {
      fails += test("Test testList1.add MCRecordDuplicateException Okay", false, false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test testList1.add MCRecordDuplicateException Not Okay", testError, false);
    testError = false;

    try {
      testList1.saveToDrive("unitTest.ser");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test saveToDrive('unitTest.ser') Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText = "  Robert Simpson\n";
      fails += test("Test print current member",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test print current member Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText = "  NewFirst Simpson\n";
      testList1.update("First Name", "NewFirst");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update firstName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm update firstName",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update firstName confirm Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText = "  NewFirst NewLast\n";
      testList1.update("Last Name", "NewLast");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update lastName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm update lastName",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update lastName confirm Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText += "    E-Mail: NewEMail\n";
      testList1.update("E-Mail", "NewEMail");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update eMail Exception", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm update eMail",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update eMail confirm Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText += "    GitHub: NewGitHub\n";
      testList1.update("GitHub Username", "NewGitHub");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update githubUserName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm update githubUserName",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update githubUserName confirm Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText += "    Gitter: NewGitter\n";
      testList1.update("Gitter Username", "NewGitter");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update gitterUserName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm update gitterUserName",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update gitterUserName confirm Exception", testError, false);
    testError = false;

    try {
      testRecordReturnText += "    FCC: NewFCC\n";
      testList1.update("FCC Username", "NewFCC");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update fccUserName Exception", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm update fccUserName",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update fccUserName confirm Exception", testError, false);
    testError = false;

    try {
      testList1.update("iDoNotExist", "hahaha");
    } catch (NoSuchFieldException e) {
      fails += test("Test update 'iDoNotExist' NoSuchFieldException Okay", false, false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test update 'iDoNotExist' NoSuchFieldException Not Okay", testError, false);
    testError = false;

    try {
      fails += test("Test to confirm all updates",
        testList1.getCurrentMember().toString(), testRecordReturnText);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test all updates confirmed Exception", testError, false);
    testError = false;

    try {
      fails += test("Test contains yes",
        testList1.contains("Anna", "Smith"), true);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test contains yes Exception", testError, false);
    testError = false;

    try {
      fails += test("Test contains no",
        testList1.contains("Lily", "Romano"), false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test contains no Exception", testError, false);
    testError = false;

    try {
      testList1.setCurrentMember("Anna", "Smith");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setCurrentMember 1", testError, false);
    testError = false;

    try {
      fails += test("Test setCurrentMember 1 correct",
        testList1.getCurrentMember().toString(), "  Anna Smith\n");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setCurrentMember 1 correct Exception 1", testError, false);
    testError = false;

    try {
      testList1.setCurrentMember("iDoNotExist", "hahaha");
    } catch (MCRecordNotFoundException e) {
      fails += test("Test setCurrentMember 2 MCRecordNotFoundException Okay", false, false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test setCurrentMember 2 MCRecordNotFoundException Not Okay", testError, false);
    testError = false;

    try {
      testList1.removeCurrentMember();
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test removeCurrentMember Exception", testError, false);
    testError = false;

    try {
      fails += test("Test contains no after removeCurrentMember",
        testList1.contains("Anna", "Smith"), false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test contains no after removeCurrentMember Exception", testError, false);
    testError = false;

    try {
      testList1.removeCurrentMember();
    } catch (MCRecordNotFoundException e) {
      fails += test("Test removeCurrentMember MCRecordNotFoundException Okay", false, false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test removeCurrentMember MCRecordNotFoundException Not Okay", testError, false);
    testError = false;

    try {
      testList1.remove("Bob", "Wilber");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test remove Exception", testError, false);
    testError = false;

    try {
      fails += test("Test contains no after remove",
        testList1.contains("Bob", "Wilber"), false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test contains no after remove Exception", testError, false);
    testError = false;

    try {
      testList1.remove("Bob", "Wilber");
    } catch (MCRecordNotFoundException e) {
      fails += test("Test remove MCRecordNotFoundException Okay", false, false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test remove MCRecordNotFoundException Not Okay", testError, false);
    testError = false;

    try {
      fails += test("Test resetList byLastAsc",
        testList1.reset("byLastAsc"), 2);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test resetList byLastAsc Exception", testError, false);
    testError = false;

    try {
      fails += test("Test getNext 1",
        testList1.getNext().nameToString(), "Kevin Johnson");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getNext 1 Exception", testError, false);
    testError = false;

    try {
      fails += test("Test getNext 2",
        testList1.getNext().nameToString(), "NewFirst NewLast");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getNext 2 Exception", testError, false);
    testError = false;

    try {
      testList1.getNext();
    } catch (QueueUnderflowException e) {
      fails += test("Test getNext QueueUnderflowException Okay", false, false);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getNext QueueUnderflowException Not Okay", testError, false);
    testError = false;

    try {
      testList1.add("Bob", "Wilber");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test testList1.add 2 Exception", testError, false);
    testError = false;

    try {
      fails += test("Test resetList byLastDec",
        testList1.reset("byLastDec"), 3);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test resetList byLastDec Exception", testError, false);
    testError = false;

    try {
      fails += test("Test getNext 3",
        testList1.getNext().nameToString(), "Bob Wilber");
        testList1.getNext();
        testList1.getNext();
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getNext 3 Exception", testError, false);
    testError = false;

    // IDEA sort byFirstAsc unitTest
    // try {
    //   testList1.add("Anna", "Smith");
    // } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    // fails += test("Test testList1.add 2 Exception", testError, false);
    // testError = false;
    //
    // try {
    //   fails += test("Test resetList byFirstAsc",
    //     testList1.reset("byFirstAsc"), 4);
    // } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    // fails += test("Test resetList byFirstAsc Exception", testError, false);
    // testError = false;
    //
    // try {
    //   fails += test("Test getNext 4",
    //     testList1.getNext().nameToString(), "Anna Smith");
    //     testList1.getNext();
    //     testList1.getNext();
    //     testList1.getNext();
    // } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    // fails += test("Test getNext 4 Exception", testError, false);
    // testError = false;

    // IDEA sort byFirstDec unitTest
    // try {
    //   testList1.add("Mike", "Jones");
    // } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    // fails += test("Test testList1.add 2 Exception", testError, false);
    // testError = false;
    //
    // try {
    //   fails += test("Test resetList byFirstDec",
    //     testList1.reset("byFirstDec"), 5);
    // } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    // fails += test("Test resetList byFirstDec Exception", testError, false);
    // testError = false;
    //
    // try {
    //   fails += test("Test getNext 5",
    //     testList1.getNext().nameToString(), "Mike Jones");
    //     testList1.getNext();
    //     testList1.getNext();
    //     testList1.getNext();
    //     testList1.getNext();
    // } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    // fails += test("Test getNext 5 Exception", testError, false);
    // testError = false;

    try {
      testList1.restoreFromDrive("unitTestOther.ser");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test restoreFromDrive('unitTestOther.ser') Exception", testError, false);
    testError = false;

    try {
      fails += test("Test resetList OTHER byLastAsc",
        testList1.reset("byLastAsc"), 4);
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test resetList OTHER byLastAsc Exception", testError, false);
    testError = false;

    try {
      fails += test("Test getNext 6",
        testList1.getNext().nameToString(), "Susy Doughnut");
    } catch (Exception e) {System.out.println("\n" + e); testError = true;}
    fails += test("Test getNext 6 Exception", testError, false);
    testError = false;

  }

}
