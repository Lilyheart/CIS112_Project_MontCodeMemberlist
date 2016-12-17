import ch08.trees.BinarySearchTree;
import java.io.File;

class MontCodeMembersBST {
  private BinarySearchTree<MontCodeMember> montCodeMembers;
  private MontCodeMember currentMember;

  public MontCodeMembersBST() {
    montCodeMembers = new BinarySearchTree<MontCodeMember>();
  }

  public MontCodeMembersBST(File fileName) {
    //TODO Constructor from file
  }

  public void add(String firstName, String lastName) throws DuplicateRecordException {
    if(contains(firstName, lastName)) {
      throw new DuplicateRecordException("MontCoder List already contains " + firstName + " " + lastName);
    } else {
      currentMember = new MontCodeMember(firstName, lastName);
      montCodeMembers.add(currentMember);
    }
  }

  public void update(String updateKey, String updateValue) throws NoSuchFieldException {
    switch (updateKey) {
      case "firstName": currentMember.setFirstName(updateValue);
        break;
      case "lastName": currentMember.setLastName(updateValue);
        break;
      case "eMail": currentMember.setEMail(updateValue);
        break;
      case "githubUserName": currentMember.setGithubUserName(updateValue);
        break;
      case "gitterUserName": currentMember.setGitterUserName(updateValue);
        break;
      case "fccUserName": currentMember.setFccUserName(updateValue);
        break;
      default:
        throw new NoSuchFieldException("The field " + updateKey +
          " does not exist in the MontCodeMember class");
    }
  }

  public boolean contains(String firstName, String lastName) {
    return montCodeMembers.contains(new MontCodeMember(firstName, lastName));
  }

  public MontCodeMember setCurrentMember(String firstName, String lastName) throws RecordNotFoundException {
    MontCodeMember origCurrentMember;

    origCurrentMember = currentMember;
    currentMember = montCodeMembers.get(new MontCodeMember(firstName, lastName));
    if(currentMember == null) {
      currentMember = origCurrentMember;
      throw new RecordNotFoundException("Member " + firstName + " " + lastName + " does not exist.  Unable to set member");
    }

    return currentMember;
  }

  public MontCodeMember getCurrentMember() {
    return currentMember;
  }

  public void remove(String firstName, String lastName) throws RecordNotFoundException {
    MontCodeMember memberToRemove;

    memberToRemove = montCodeMembers.get(new MontCodeMember(firstName, lastName));
      if(memberToRemove == null) {
        throw new RecordNotFoundException("No memberToRemove.  Need to run setCurrentMember()");
      } else {
        montCodeMembers.remove(memberToRemove);
      }
  }

  public void removeCurrentMember() throws RecordNotFoundException {
    if(currentMember == null) {
      throw new RecordNotFoundException("No currentMember.  Need to run setCurrentMember()");
    } else {
      montCodeMembers.remove(currentMember);
      currentMember = null;
    }
  }
}
