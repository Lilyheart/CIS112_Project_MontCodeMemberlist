import java.io.*;

class MontCodeMembersBST {
  private BinarySearchTreeReverseable<MontCodeMember> montCodeMembers;
  private MontCodeMember currentMember;

  static final int INORDER = 1;
  static final int PREORDER = 2;
  static final int POSTORDER = 3;
  static final int INORDER_REV = 4;
  static int sortType;
  static int addsSinceBalance;

  public MontCodeMembersBST() {
    addsSinceBalance = 0;
    montCodeMembers = new BinarySearchTreeReverseable<MontCodeMember>();
  }

  public MontCodeMembersBST(String fileName) throws ClassNotFoundException, FileNotFoundException, IOException {
    addsSinceBalance = 0;
    restoreFromDrive(fileName);
  }

  public void add(String firstName, String lastName) throws MCRecordDuplicateException {
    addsSinceBalance++;
    if(contains(firstName, lastName)) {
      throw new MCRecordDuplicateException("MontCoder List already contains " + firstName + " " + lastName);
    } else {
      currentMember = new MontCodeMember(firstName, lastName);
      montCodeMembers.add(currentMember);
    }
    if(addsSinceBalance >= 10) {
      montCodeMembers.balance();
      addsSinceBalance = 0;
    }
  }

  public void saveToDrive(String fileName) throws FileNotFoundException, IOException {
    ObjectOutputStream output;

    try {
      output = new ObjectOutputStream(new FileOutputStream(fileName));
      output.writeObject(montCodeMembers);
      output.close();
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Error: " + e);
    } catch (IOException e) {
      throw new IOException("Error: " + e, e);
    }
  }

  public void restoreFromDrive(String fileName) throws ClassNotFoundException, FileNotFoundException, IOException {
    ObjectInputStream input;

    try {
      input = new ObjectInputStream(new FileInputStream(fileName));
      montCodeMembers = (BinarySearchTreeReverseable<MontCodeMember>)input.readObject();
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException( "Error: " + e);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Error: " + e);
    } catch (IOException e) {
      throw new IOException("Error: " + e, e);
    }
  }

  public void update(String updateKey, String updateValue) throws NoSuchFieldException {
    switch (updateKey) {
      case "First Name": currentMember.setFirstName(updateValue);
        break;
      case "Last Name": currentMember.setLastName(updateValue);
        break;
      case "E-Mail": currentMember.setEMail(updateValue);
        break;
      case "GitHub Username": currentMember.setGithubUserName(updateValue);
        break;
      case "Gitter Username": currentMember.setGitterUserName(updateValue);
        break;
      case "FCC Username": currentMember.setFccUserName(updateValue);
        break;
      default:
        throw new NoSuchFieldException("The field " + updateKey +
          " does not exist in the MontCodeMember class");
    }
  }

  public boolean contains(String firstName, String lastName) {
    return montCodeMembers.contains(new MontCodeMember(firstName, lastName));
  }

  public MontCodeMember setCurrentMember(String firstName, String lastName) throws MCRecordNotFoundException {
    MontCodeMember origCurrentMember;

    origCurrentMember = currentMember;
    currentMember = montCodeMembers.get(new MontCodeMember(firstName, lastName));
    if(currentMember == null) {
      currentMember = origCurrentMember;
      throw new MCRecordNotFoundException("Member " + firstName + " " + lastName + " does not exist.  Unable to set member");
    }

    return currentMember;
  }

  public MontCodeMember getCurrentMember() {
    return currentMember;
  }

  public void remove(String firstName, String lastName) throws MCRecordNotFoundException {
    MontCodeMember memberToRemove;

    memberToRemove = montCodeMembers.get(new MontCodeMember(firstName, lastName));
      if(memberToRemove == null) {
        throw new MCRecordNotFoundException("No memberToRemove.  Need to run setCurrentMember()");
      } else {
        montCodeMembers.remove(memberToRemove);
      }
  }

  public void removeCurrentMember() throws MCRecordNotFoundException {
    if(currentMember == null) {
      throw new MCRecordNotFoundException("No currentMember.  Need to run setCurrentMember()");
    } else {
      montCodeMembers.remove(currentMember);
      currentMember = null;
    }
  }

  public int reset(String orderBy) {
    int numOfMembers;

    sortType = INORDER;
    numOfMembers = montCodeMembers.reset(sortType);

    return numOfMembers;
  }

  public MontCodeMember getNext() {
    return montCodeMembers.getNext(sortType);
  }
}
