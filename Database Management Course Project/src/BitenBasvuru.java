/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class BitenBasvuru {

    private int BasvuruNumarasi;
    private int BasvuruSureciTypeID;
    private String Cevap;

    public BitenBasvuru() {
    }

    public BitenBasvuru(int BasvuruNumarasi, int BasvuruSureciTypeID, String Cevap) {
        this.BasvuruNumarasi = BasvuruNumarasi;
        this.BasvuruSureciTypeID = BasvuruSureciTypeID;
        this.Cevap = Cevap;
    }

    public int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public int getBasvuruSureciTypeID() {
        return BasvuruSureciTypeID;
    }

    public String getAciklamasi() {
        return Cevap;
    }

    @Override
    public String toString() {
        return "DevamEdenBasvuru{" + "BasvuruNumarasi=" + BasvuruNumarasi + ", BasvuruSureciTypeID=" + BasvuruSureciTypeID + ", Cevap=" + Cevap + '}';
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "BasvuruSureciTypeID":
                return BasvuruSureciTypeID;
            case "BasvuruNumarasi":
                return BasvuruNumarasi;
            case "Cevap":
                return Cevap;
            default:
                return null;
        }
    }

}
