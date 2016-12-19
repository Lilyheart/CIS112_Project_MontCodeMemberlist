class MontCodePreferences implements java.io.Serializable {
  private String defaultFile;
	private String defaultListOption;

  public MontCodePreferences() {
    defaultFile = "members.ser";
		defaultListOption = "byLastAsc";
  }

	public String getDefaultFile() {
		return this.defaultFile;
	}

	public void setDefaultFile(String defaultFile) {
		this.defaultFile = defaultFile;
	}

	public String getDefaultListOption() {
		return this.defaultListOption;
	}

	public void setDefaultListOption(String defaultListOption) {
		this.defaultListOption = defaultListOption;
	}

}
