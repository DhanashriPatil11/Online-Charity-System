package charityPackage;

public class SetGet {
      static int ngoId;

	public static int getNgoId() {
		System.out.println("Get="+ngoId);
		return ngoId;
	}

	public static void setNgoId(int ngoId) {
		System.out.println("Get="+ngoId);
		ngoId=SetGet.ngoId=ngoId;
	}

	
}
