import java.net.*;

public class MontCodeMember implements Comparable<MontCodeMember>, java.io.Serializable {

  private String firstName, lastName;
  private String eMail, githubUserName, gitterUserName, fccUserName;
	//IDEA Languages protected String[] languages; //known and wanting to know

	public MontCodeMember(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getGithubUserName() {
		return githubUserName;
	}

	public void setGithubUserName(String githubUserName) {
		this.githubUserName = githubUserName;
	}

	public String getGitterUserName() {
		return gitterUserName;
	}

	public void setGitterUserName(String gitterUserName) {
		this.gitterUserName = gitterUserName;
	}

	public String getFccUserName() {
		return fccUserName;
	}

	public void setFccUserName(String fccUserName) {
		this.fccUserName = fccUserName;
	}

	public int compareTo(MontCodeMember other) {
		String thisName = this.getLastName().toLowerCase() +
			this.getFirstName().toLowerCase();

		String otherName = other.getLastName().toLowerCase() +
			other.getFirstName().toLowerCase();

		return thisName.compareTo(otherName);
  }

	public void sendEMail() {
  	try {
			URL emailURL = new URL("mailto:" + eMail + "?Subject=MontCode: ");
			DesktopUtil.browse(emailURL);
		} catch (NullPointerException e) {
			System.out.println(firstName + " " + lastName + " doesn't have an email address");
		} catch (Exception e) {
			System.out.println("sendEMail error: " + e);
		}
	}

	public void openGitHub() {
  	try {
      URL githubURL = new URL("https", "github.com/", githubUserName);
			DesktopUtil.browse(githubURL);
		} catch (NullPointerException e) {
			System.out.println(firstName + " " + lastName + " doesn't have a GitHub userID");
		} catch (Exception e) {
			System.out.println("openGitHub error: " + e);
		}
	}

	public void sendGitter() {
  	try {
      URL githubURL = new URL("https", "gitter.im/", gitterUserName);
			DesktopUtil.browse(githubURL);
		} catch (NullPointerException e) {
			System.out.println(firstName + " " + lastName + " doesn't have a Gitter userID");
		} catch (Exception e) {
			System.out.println("sendGitter error: " + e);
		}
	}

	public void openFCC() {
  	try {
      URL fccURL = new URL("https", "www.freecodecamp.com/", fccUserName);
			DesktopUtil.browse(fccURL);
		} catch (NullPointerException e) {
			System.out.println(firstName + " " + lastName + " doesn't have a Free Code Camp userID");
		} catch (Exception e) {
			System.out.println("openFCC error: " + e);
		}
	}

	public String toString() {
		String result;

		result = "  " + firstName;
		result += " " + lastName + "\n";
		if (eMail != null && !eMail.isEmpty()) {
		  result += "    E-Mail: " + eMail + "\n";
		}
		if (githubUserName != null && !githubUserName.isEmpty()) {
		  result += "    GitHub: " + githubUserName + "\n";
		}
		if (gitterUserName != null && !gitterUserName.isEmpty()) {
		  result += "    Gitter: " + gitterUserName + "\n";
		}
		if (fccUserName != null && !fccUserName.isEmpty()) {
		  result += "    FCC: " + fccUserName + "\n";
		}

		return result;
	}

	public String nameToString() {
		return firstName +" " + lastName;
	}

}
