import java.io.*;

class UnitTestSerCreator {

  public static void main(String[] args) {
    ObjectOutputStream output;
    ObjectInputStream input;

    BinarySearchTreeReverseable<MontCodeMember> montCodeMembers = new BinarySearchTreeReverseable<MontCodeMember>();

    montCodeMembers.add(new MontCodeMember("Susy", "Doughnut"));
    montCodeMembers.add(new MontCodeMember("Banana", "Foster"));
    montCodeMembers.add(new MontCodeMember("Omar", "Gurd"));
    montCodeMembers.add(new MontCodeMember("Frank", "Grimes"));

    try {
      output = new ObjectOutputStream(new FileOutputStream("unitTestOther.ser"));
      output.writeObject(montCodeMembers);
      output.close();
    } catch (Exception e) {
      System.out.print("AGHTHTH");
    }

    montCodeMembers = null;

    try {
      input = new ObjectInputStream(new FileInputStream("unitTestOther.ser"));
      montCodeMembers = (BinarySearchTreeReverseable<MontCodeMember>)input.readObject();
    } catch (Exception e) {
      System.out.println(e);
      System.out.print("AGHTHTH\n\n");
    }

    System.out.print(montCodeMembers);

  }


}
