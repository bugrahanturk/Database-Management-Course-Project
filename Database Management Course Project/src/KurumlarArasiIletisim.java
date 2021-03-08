
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class KurumlarArasiIletisim {

    public static int BasvuruNumarasi;
    private int YonlendirilenKurumID;

    public KurumlarArasiIletisim(int BasvuruNumarasi, int YonlendirilenKurumID) {
        this.BasvuruNumarasi = BasvuruNumarasi;
        this.YonlendirilenKurumID = YonlendirilenKurumID;
    }

    public static int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public int getYonlendirilenKurumID() {
        return YonlendirilenKurumID;
    }

    @Override
    public String toString() {
        return "KurumlarArasiIletisim{" + "YonlendirilenKurumID=" + YonlendirilenKurumID + '}';
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "BasvuruNumarasi":
                return BasvuruNumarasi;
            case "YonlendirilenKurumID":
                return YonlendirilenKurumID;
            default:
                return null;
        }
    }
}
