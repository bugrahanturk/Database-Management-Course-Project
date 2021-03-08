

class Kisi {

    public static short KisiID;
    private String İsim;
    private String Soyisim;
    private int Uyruk;
    private String PasaportNumber;
    private long TCNumber;

    Kisi() {

    }

    Kisi(String İsim, String Soyisim) {
        this.İsim = İsim;
        this.Soyisim = Soyisim;
    }

    Kisi(String İsim, String Soyisim, int Uyruk, long TCNumber) {
        this.KisiID += 1;
        this.İsim = İsim;
        this.Soyisim = Soyisim;
        this.Uyruk = Uyruk;
        this.TCNumber = TCNumber;

    }

    Kisi(String İsim, String Soyisim, int Uyruk, String PasaportNumber) {
        this.KisiID += 1;
        this.İsim = İsim;
        this.Soyisim = Soyisim;
        this.Uyruk = Uyruk;
        this.PasaportNumber = PasaportNumber;

    }

    public String getPasaportNumber() {
        return PasaportNumber;
    }

    public long getTCNumber() {
        return TCNumber;
    }

    public short getKisiID() {
        return KisiID;
    }

    public void setKisiID(short departmentID) {
        this.KisiID = departmentID;
    }

    public String getName() {
        return İsim;
    }

    public void setName(String isim) {
        this.İsim = isim;
    }

    public String getGroupName() {
        return Soyisim;
    }

    public void setGroupName(String groupName) {
        this.Soyisim = groupName;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "KisiID":
                return KisiID;
            case "İsim":
                return İsim;
            case "Soyisim":
                return Soyisim;
            case "Uyruk":
                return Uyruk;
            case "TCNumber":
                return TCNumber;
            case "PasaportNumber":
                return PasaportNumber;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return KisiID + ", " + İsim + ", " + Soyisim + ", " + Uyruk + ", " + TCNumber + ", " + PasaportNumber + ", ";
    }

}
