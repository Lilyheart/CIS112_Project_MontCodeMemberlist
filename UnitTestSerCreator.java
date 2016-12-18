import java.io.*;
import java.util.*;

class UnitTestSerCreator {

  public static void main(String[] args) {
    ObjectOutputStream output;
    ObjectInputStream input;

    BinarySearchTreeReverseable<MontCodeMember> montCodeMembers = new BinarySearchTreeReverseable<MontCodeMember>();

    montCodeMembers.add(new MontCodeMember("Susy", "Doughnut"));
    montCodeMembers.add(new MontCodeMember("Banana", "Foster"));
    montCodeMembers.add(new MontCodeMember("Omar", "Gurd"));
    montCodeMembers.add(new MontCodeMember("Frank", "Grimes"));

    // try {
    //   output = new ObjectOutputStream(new FileOutputStream("unitTestOther.ser"));
    //   output.writeObject(montCodeMembers);
    //   output.close();
    // } catch (Exception e) {
    //   System.out.print("AGHTHTH");
    // }
    //
    // montCodeMembers = null;
    //
    // try {
    //   input = new ObjectInputStream(new FileInputStream("unitTestOther.ser"));
    //   montCodeMembers = (BinarySearchTreeReverseable<MontCodeMember>)input.readObject();
    // } catch (Exception e) {
    //   System.out.println(e);
    //   System.out.print("AGHTHTH\n\n");
    // }
    //
    // System.out.print(montCodeMembers);

    montCodeMembers.add(new MontCodeMember("Toni", "Evans"));
    montCodeMembers.add(new MontCodeMember("Olga", "Robinson"));
    montCodeMembers.add(new MontCodeMember("Elaine", "Romero"));
    montCodeMembers.add(new MontCodeMember("Mercedes", "Ramsey"));
    montCodeMembers.add(new MontCodeMember("Casey", "Arnold"));
    montCodeMembers.add(new MontCodeMember("Madeline", "Butler"));
    montCodeMembers.add(new MontCodeMember("Joy", "Gordon"));
    montCodeMembers.add(new MontCodeMember("Bethany", "Stokes"));

    montCodeMembers.balance();

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Okay?");
    String response = keyboard.nextLine();

  }


}
