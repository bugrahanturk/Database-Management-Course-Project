/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BasvuruSureci {

    public static int BasvuruNumarasi;
    private int BasvuruSureciTypeID;

    public BasvuruSureci() {
    }

    public BasvuruSureci(int BasvuruNumarasi, int BasvuruSureciTypeID) {
        this.BasvuruNumarasi = BasvuruNumarasi;
        this.BasvuruSureciTypeID = BasvuruSureciTypeID;
    }

    public static int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public int getBasvuruSureciTypeID() {
        return BasvuruSureciTypeID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "BasvuruSureciTypeID":
                return BasvuruSureciTypeID;
            case "BasvuruNumarasi":
                return BasvuruNumarasi;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "BasvuruSureci{" + "BasvuruSureciTypeID=" + BasvuruSureciTypeID + '}';
    }

}
