

class Basvuru {

    public static short KisiID;
    public static int BasvuruNumarasi;
    public static int BasvuruSureciTypeID;
    private int DönüsTipi;
    private String BasvuruMetni;
    private int BasvuranTipi;
    private String TüzelKisiUnvani;
    private String BasvuruTarihi;
    private int BasvuruGecmisi;
    private int EskiBasvuruNumarasi;
    private String BasvuruCevapSonTarihi;
    private int BasvuruCesidi;
    private String Adres;
    private int AdresTipi;
    private String Eposta;
    private String Fax;
    private String Tel;

    public Basvuru() {
    }

    public Basvuru(short KisiID, int DönüsTipi, int BasvuruCesidi, String BasvuruMetni, int BasvuruGecmisi, int BasvuranTipi, String BasvuruTarihi, String BasvuruCevapSonTarihi, String Adres, int AdresTipi, String Eposta, String Fax, String Tel, String TüzelKisiUnvani) {
        this.KisiID = KisiID;
        this.DönüsTipi = DönüsTipi;
        this.BasvuruMetni = BasvuruMetni;
        this.BasvuranTipi = BasvuranTipi;
        this.BasvuruGecmisi = BasvuruGecmisi;
        this.BasvuruTarihi = BasvuruTarihi;
        this.BasvuruCevapSonTarihi = BasvuruCevapSonTarihi;
        this.BasvuruCesidi = BasvuruCesidi;
        this.Adres = Adres;
        this.AdresTipi = AdresTipi;
        this.Eposta = Eposta;
        this.Fax = Fax;
        this.Tel = Tel;
        this.TüzelKisiUnvani = TüzelKisiUnvani;
    }

    public Basvuru(short KisiID, int DönüsTipi, int BasvuruCesidi, String BasvuruMetni, int BasvuruGecmisi, int EskiBasvuruNumarasi, int BasvuranTipi, String BasvuruTarihi, String BasvuruCevapSonTarihi, String Adres, int AdresTipi, String Eposta, String Fax, String Tel, String TüzelKisiUnvani) {
        this.KisiID = KisiID;
        this.DönüsTipi = DönüsTipi;
        this.BasvuruMetni = BasvuruMetni;
        this.BasvuranTipi = BasvuranTipi;
        this.BasvuruGecmisi = BasvuruGecmisi;
        this.EskiBasvuruNumarasi = EskiBasvuruNumarasi;
        this.BasvuruTarihi = BasvuruTarihi;
        this.BasvuruCevapSonTarihi = BasvuruCevapSonTarihi;
        this.BasvuruCesidi = BasvuruCesidi;
        this.Adres = Adres;
        this.AdresTipi = AdresTipi;
        this.Eposta = Eposta;
        this.Fax = Fax;
        this.Tel = Tel;
        this.TüzelKisiUnvani = TüzelKisiUnvani;
    }

    public Basvuru(short KisiID, int DönüsTipi, int BasvuruCesidi, String BasvuruMetni, int BasvuruGecmisi, int BasvuranTipi, String BasvuruTarihi, String BasvuruCevapSonTarihi, String Adres, int AdresTipi, String Eposta, String Fax, String Tel) {
        this.KisiID = KisiID;
        this.DönüsTipi = DönüsTipi;
        this.BasvuruMetni = BasvuruMetni;
        this.BasvuranTipi = BasvuranTipi;
        this.BasvuruGecmisi = BasvuruGecmisi;
        this.BasvuruTarihi = BasvuruTarihi;
        this.BasvuruCevapSonTarihi = BasvuruCevapSonTarihi;
        this.BasvuruCesidi = BasvuruCesidi;
        this.Adres = Adres;
        this.AdresTipi = AdresTipi;
        this.Eposta = Eposta;
        this.Fax = Fax;
        this.Tel = Tel;
    }

    public Basvuru(short KisiID, int DönüsTipi, int BasvuruCesidi, String BasvuruMetni, int BasvuranTipi, int BasvuruGecmisi, int EskiBasvuruNumarasi, String BasvuruTarihi, String BasvuruCevapSonTarihi, String Adres, int AdresTipi, String Eposta, String Fax, String Tel) {
        this.KisiID = KisiID;
        this.DönüsTipi = DönüsTipi;
        this.BasvuruMetni = BasvuruMetni;
        this.BasvuranTipi = BasvuranTipi;
        this.BasvuruGecmisi = BasvuruGecmisi;
        this.EskiBasvuruNumarasi = EskiBasvuruNumarasi;
        this.BasvuruTarihi = BasvuruTarihi;
        this.BasvuruCevapSonTarihi = BasvuruCevapSonTarihi;
        this.BasvuruCesidi = BasvuruCesidi;
        this.Adres = Adres;
        this.AdresTipi = AdresTipi;
        this.Eposta = Eposta;
        this.Fax = Fax;
        this.Tel = Tel;
    }

    public short getKisiID() {
        return KisiID;
    }

    public int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public int getDönüsTipi() {
        return DönüsTipi;
    }

    public String getBasvuruMetni() {
        return BasvuruMetni;
    }

    public int getBasvuruTipi() {
        return BasvuranTipi;
    }

    public String getTüzelKisiUnvani() {
        return TüzelKisiUnvani;
    }

    public String getBasvuruTarihi() {
        return BasvuruTarihi;
    }

    public int getBasvuruGecmisi() {
        return BasvuruGecmisi;
    }

    public int getEskiBasvuruNumarasi() {
        return EskiBasvuruNumarasi;
    }

    public String getBasvuruCevapSonTarihi() {
        return BasvuruCevapSonTarihi;
    }

    public int getBasvuruCesidi() {
        return BasvuruCesidi;
    }

    public String getAdress() {
        return Adres;
    }

    public int getAdresTipi() {
        return AdresTipi;
    }

    public Basvuru(int AdresTipi) {
        this.AdresTipi = AdresTipi;
    }

    public String getEposta() {
        return Eposta;
    }

    public String getFax() {
        return Fax;
    }

    public String getTel() {
        return Tel;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "KisiID":
                return KisiID;
            case "BasvuruNumarasi":
                return BasvuruNumarasi;
            case "DönüsTipi":
                return DönüsTipi;
            case "BasvuruMetni":
                return BasvuruMetni;
            case "BasvuranTipi":
                return BasvuranTipi;
            case "TüzelKisiUnvani":
                return TüzelKisiUnvani;
            case "BasvuruTarihi":
                return BasvuruTarihi;
            case "BasvuruGecmisi":
                return BasvuruGecmisi;
            case "EskiBasvuruNumarasi":
                return EskiBasvuruNumarasi;
            case "BasvuruCevapSonTarihi":
                return BasvuruCevapSonTarihi;
            case "BasvuruCesidi":
                return BasvuruCesidi;
            case "Adres":
                return Adres;
            case "AdresTipi":
                return AdresTipi;
            case "Eposta":
                return Eposta;
            case "Fax":
                return Fax;
            case "Tel":
                return Tel;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Basvuru{" + "KisiID=" + KisiID + ", BasvuruNumarasi=" + BasvuruNumarasi + ", D\u00f6n\u00fcsTipi=" + DönüsTipi + ", BasvuruMetni=" + BasvuruMetni
                + ", BasvuranTipi=" + BasvuranTipi + ", T\u00fczelKisiUnvani=" + TüzelKisiUnvani + ", BasvuruTarihi=" + BasvuruTarihi + ", BasvuruGecmisi=" + BasvuruGecmisi
                + ", EskiBasvuruNumarasi=" + EskiBasvuruNumarasi + ", BasvuruCevapSonTarihi=" + BasvuruCevapSonTarihi + ", BasvuruCesidi=" + BasvuruCesidi + ", Adres=" + Adres
                + ", AdresTipi=" + AdresTipi + ", Eposta=" + Eposta + ", Fax=" + Fax + ", Tel=" + Tel + '}';
    }

}
