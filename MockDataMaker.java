import java.io.*;
import java.util.*;

class MockDataMaker {
  private List<String> firstNames, lastNames, eMailDomains;
  private int firstNamesLength, lastNamesLength, eMailsLength;
  private Random randomNumber;

  public MockDataMaker() throws IOException {
    String tempString;
    File file = new File("MOCK_DATA.dat");
    Scanner inputFile = new Scanner(file);
    randomNumber = new Random();

    tempString = inputFile.nextLine();
    firstNames = Arrays.asList(tempString.split(","));
    firstNamesLength = firstNames.size();
    tempString = inputFile.nextLine();
    lastNames = Arrays.asList(tempString.split(","));
    lastNamesLength = lastNames.size();
    tempString = inputFile.nextLine();
    eMailDomains = Arrays.asList(tempString.split(","));
    eMailsLength = eMailDomains.size();

    System.out.println(firstNamesLength+"*"+lastNamesLength+"="+firstNamesLength*lastNamesLength);
  }

  public int add(MontCodeMembersBST memberList, int recordsToAdd) throws NoMoreDemoDataException {
    String firstName, lastName;
    String eMail = null, githubUserName = null;
    String gitterUserName = null, fccUserName = null;
    int userNamePercent;
    int percentMatchingUsernames = 80;
    int percentMissingUsername = 5;
    int countAlreadyAdded = 0;
    int newMembers = 0;

    for (int i = 0; i < recordsToAdd; i++) {
      String[] updateKeys = {"E-Mail", "GitHub Username", "Gitter Username", "FCC Username"};
      firstName = firstNames.get(randomNumber.nextInt(firstNamesLength));
      lastName = lastNames.get(randomNumber.nextInt(lastNamesLength));
      githubUserName = firstName.toLowerCase().charAt(0) + lastName.toLowerCase() + randomNumber.nextInt(100);
      eMail = githubUserName + eMailDomains.get(randomNumber.nextInt(eMailsLength));

      userNamePercent = randomNumber.nextInt(100);
      if(userNamePercent < percentMatchingUsernames) {
        gitterUserName = githubUserName;
        fccUserName = githubUserName;
      } else {
        userNamePercent = randomNumber.nextInt(100 - percentMatchingUsernames);
        if(userNamePercent > percentMissingUsername) {
          gitterUserName = lastName.toLowerCase().charAt(0) + firstName.toLowerCase() + randomNumber.nextInt(100);;
        }
        userNamePercent = randomNumber.nextInt(100 - percentMatchingUsernames);
        if(userNamePercent > percentMissingUsername) {
          fccUserName = lastName.toLowerCase().charAt(0) + firstName.toLowerCase() + randomNumber.nextInt(100);;
        }
      }

      try {
        memberList.add(firstName, lastName);
        memberList.update("E-Mail", eMail);
        memberList.update("GitHub Username", githubUserName);
        memberList.update("Gitter Username", gitterUserName);
        memberList.update("FCC Username", fccUserName);
        countAlreadyAdded = 0;
        newMembers++;
      } catch (MCRecordDuplicateException e) {
        countAlreadyAdded++;
        if(countAlreadyAdded == 10) {
          throw new NoMoreDemoDataException("No remaining unique names in demo file. Please load new demo information.  " + newMembers + " records were added.");
        }
        i--;
      } catch (NoSuchFieldException e) {
        System.out.println(e);
      }
    }
    return newMembers;
  }

}
