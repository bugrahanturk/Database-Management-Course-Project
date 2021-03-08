/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class DevamEdenBasvuru {

    private int BasvuruNumarasi;
    private int BasvuruSureciTypeID;
    private int DevamSebebi;

    public DevamEdenBasvuru() {
    }

    public DevamEdenBasvuru(int BasvuruNumarasi, int BasvuruSureciTypeID, int DevamSebebi) {
        this.BasvuruNumarasi = BasvuruNumarasi;
        this.BasvuruSureciTypeID = BasvuruSureciTypeID;
        this.DevamSebebi = DevamSebebi;
    }

    public int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public int getBasvuruSureciTypeID() {
        return BasvuruSureciTypeID;
    }

    public int getDevamSebebi() {
        return DevamSebebi;
    }

    @Override
    public String toString() {
        return "DevamEdenBasvuru{" + "BasvuruNumarasi=" + BasvuruNumarasi + ", BasvuruSureciTypeID=" + BasvuruSureciTypeID + ", DevamSebebi=" + DevamSebebi + '}';
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "BasvuruSureciTypeID":
                return BasvuruSureciTypeID;
            case "BasvuruNumarasi":
                return BasvuruNumarasi;
            case "DevamSebebi":
                return DevamSebebi;
            default:
                return null;
        }
    }

}
