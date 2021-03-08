



public class UcretTarifesi {

    public static int BasvuruNumarasi;
    private String UcretTalepTarihi;
    private double Ucreti;

     public UcretTarifesi() {
    }
    
    
    public UcretTarifesi(int BasvuruNumarasi, String UcretTalepTarihi, double Ucreti) {
        this.BasvuruNumarasi = BasvuruNumarasi;
        this.UcretTalepTarihi = UcretTalepTarihi;
        this.Ucreti = Ucreti;
    }

    public int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public String getUcretTalepTarihi() {
        return UcretTalepTarihi;
    }

    public double getUcreti() {
        return Ucreti;
    }

 
   
    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BasvuruNumarasi": return BasvuruNumarasi;
		case "UcretTalepTarihi": return UcretTalepTarihi;
		case "Ucreti": return Ucreti;
 		
		default: return null;
		}
	}

    @Override
    public String toString() {
        return "UcretTarifesi{" + "UcretTalepTarihi=" + UcretTalepTarihi + ", Ucreti=" + Ucreti + '}';
    }

    
    
    
}
