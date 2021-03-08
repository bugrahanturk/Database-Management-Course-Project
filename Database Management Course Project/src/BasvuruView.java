
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BasvuruView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "insert":
                return insertOperation(modelData);
            case "update":
                return updateOperation(modelData);
            case "delete":
                return deleteOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);
            case "insert.gui":
                return insertGUI(modelData);

        }

        return new ViewData("Basvuru", "insert.gui");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int BasvuruNumarasi = resultSet.getInt("BasvuruNumarasi");
                int dtipi = resultSet.getInt("DönüsTipi");
                String adres = resultSet.getString("Adres");

                // Display values
                System.out.print(BasvuruNumarasi + "\t");
                System.out.print(dtipi + "\t");
                System.out.print(adres + "\t");
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Basvurunuz alindi.");

        System.out.println("Basvuru Numaraniz : " + Basvuru.BasvuruNumarasi);

        return new ViewData("BasvuruSureci", "insert.gui");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        Map<String, Object> whereParameters = new HashMap<>();
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Department", "select", parameters);
    }
        // Gerekli inputlar alınarak constructlarla gerekli başvuru oluşturulur
    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();

        List<Object> rows = new ArrayList<>();

        LocalDate BasvuruTarihi = LocalDate.now();
        String str = String.valueOf(BasvuruTarihi);
        LocalDate TempDate = LocalDate.now();
        LocalDate BasvuruCevapSonTarihi = TempDate.plusDays(15);
        String str2 = String.valueOf(BasvuruCevapSonTarihi);

        int DönüsTipi = 2;
        int BasvuranTipi = 2;
        int BasvuruGecmisi = 2;
        int BasvuruCesidi = 0;
        int AdresTipi = 0;

        System.out.println("Basvuru bilgilerini giriniz");
        System.out.println("---------------------------");
        short KisiID = Kisi.KisiID;
        String donustipi = getString("DönüsTipi: ", false);
        if (donustipi.equalsIgnoreCase("Eposta")) {
            DönüsTipi = 0;
        } else if (donustipi.equalsIgnoreCase("yazili")) {
            DönüsTipi = 1;
        }

        String BasvuruMetni = getString("BasvuruMetni : ", false);
        String Eposta = getString("Eposta : ", false);
        String Fax = getString("Fax : ", true);
        String Tel = getString("Tel : ", false);
        String Adres = getString("Adres : ", false);

        String adrestipi = getString("AdresTipi: ", false);
        if (adrestipi.equalsIgnoreCase("Ev")) {
            AdresTipi = 1;
        } else if (adrestipi.equalsIgnoreCase("Is")) {
            AdresTipi = 2;
        }

        String basvurantipi = getString("BasvuranTipi: ", false);
        if (basvurantipi.equalsIgnoreCase("Kisisel")) {
            BasvuranTipi = 0;
        } else if (basvurantipi.equalsIgnoreCase("Tuzel")) {
            BasvuranTipi = 1;
        }

        String basvurugecmisi = getString("BasvuruGecmisi: ", false);
        if (basvurugecmisi.equalsIgnoreCase("Var")) {
            BasvuruGecmisi = 1;
        } else if (basvurugecmisi.equalsIgnoreCase("Yok")) {
            BasvuruGecmisi = 0;
        }
        if (Personel.basvurucesidi == 1) {
            String Basvurucesidi = getString("Basvuru Cesidi: ", false);
            if (Basvurucesidi.equalsIgnoreCase("fax")) {
                BasvuruCesidi = 1;
            } else if (Basvurucesidi.equalsIgnoreCase("posta")) {
                BasvuruCesidi = 2;
            } else if (Basvurucesidi.equalsIgnoreCase("dilekce")) {
                BasvuruCesidi = 3;
            }

            if (BasvuranTipi == 1) {
                String TüzelKisiUnvani = getString("TüzelKisiUnvani : ", false);

                if (BasvuruGecmisi == 1) {
                    int EskiBasvuruNumarasi = getInteger("EskiBasvuruNumarasi: ", false);
                    parameters.put("fieldNames", " KisiID, DönüsTipi, BasvuruCesidi,  BasvuruMetni, BasvuruGecmisi,  EskiBasvuruNumarasi,  BasvuranTipi,  BasvuruTarihi,  BasvuruCevapSonTarihi, Adres,  AdresTipi,  Eposta,  Fax,  Tel,  TüzelKisiUnvani");
                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuruGecmisi, EskiBasvuruNumarasi, BasvuranTipi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel, TüzelKisiUnvani));
                } else {
                    parameters.put("fieldNames", " KisiID, DönüsTipi, BasvuruCesidi,  BasvuruMetni, BasvuruGecmisi,  BasvuranTipi,  BasvuruTarihi,  BasvuruCevapSonTarihi, Adres,  AdresTipi,  Eposta,  Fax,  Tel,  TüzelKisiUnvani");
                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuruGecmisi, BasvuranTipi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel, TüzelKisiUnvani));
                }

            } else {

                if (BasvuruGecmisi == 1) {
                    int EskiBasvuruNumarasi = getInteger("EskiBasvuruNumarasi: ", false);
                    parameters.put("fieldNames", "KisiID, DönüsTipi,BasvuruCesidi,BasvuruMetni,BasvuranTipi,BasvuruGecmisi,EskiBasvuruNumarasi,BasvuruTarihi,BasvuruCevapSonTarihi,Adres,AdresTipi,Eposta,Fax,Tel");
                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuranTipi, BasvuruGecmisi, EskiBasvuruNumarasi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel));
                } else {

                    parameters.put("fieldNames", "KisiID, DönüsTipi,BasvuruCesidi,BasvuruMetni,BasvuranTipi,BasvuruGecmisi,BasvuruTarihi,BasvuruCevapSonTarihi,Adres,AdresTipi,Eposta,Fax,Tel");

                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuranTipi, BasvuruGecmisi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel));
                }
            }
            Personel.basvurucesidi = 0;
        } else {
            if (BasvuranTipi == 1) {
                String TüzelKisiUnvani = getString("TüzelKisiUnvani : ", false);

                if (BasvuruGecmisi == 1) {
                    int EskiBasvuruNumarasi = getInteger("EskiBasvuruNumarasi: ", false);
                    parameters.put("fieldNames", " KisiID, DönüsTipi, BasvuruCesidi,  BasvuruMetni, BasvuruGecmisi,  EskiBasvuruNumarasi,  BasvuranTipi,  BasvuruTarihi,  BasvuruCevapSonTarihi, Adres,  AdresTipi,  Eposta,  Fax,  Tel,  TüzelKisiUnvani");
                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuruGecmisi, EskiBasvuruNumarasi, BasvuranTipi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel, TüzelKisiUnvani));
                } else {
                    parameters.put("fieldNames", " KisiID, DönüsTipi, BasvuruCesidi,  BasvuruMetni, BasvuruGecmisi,  BasvuranTipi,  BasvuruTarihi,  BasvuruCevapSonTarihi, Adres,  AdresTipi,  Eposta,  Fax,  Tel,  TüzelKisiUnvani");
                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuruGecmisi, BasvuranTipi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel, TüzelKisiUnvani));
                }

            } else {

                if (BasvuruGecmisi == 1) {
                    int EskiBasvuruNumarasi = getInteger("EskiBasvuruNumarasi: ", false);
                    parameters.put("fieldNames", "KisiID, DönüsTipi,BasvuruCesidi,BasvuruMetni,BasvuranTipi,BasvuruGecmisi,EskiBasvuruNumarasi,BasvuruTarihi,BasvuruCevapSonTarihi,Adres,AdresTipi,Eposta,Fax,Tel");
                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuranTipi, BasvuruGecmisi, EskiBasvuruNumarasi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel));
                } else {

                    parameters.put("fieldNames", "KisiID, DönüsTipi,BasvuruCesidi,BasvuruMetni,BasvuranTipi,BasvuruGecmisi,BasvuruTarihi,BasvuruCevapSonTarihi,Adres,AdresTipi,Eposta,Fax,Tel");

                    rows.add(new Basvuru(KisiID, DönüsTipi, BasvuruCesidi, BasvuruMetni, BasvuranTipi, BasvuruGecmisi, str, str2, Adres, AdresTipi, Eposta, Fax, Tel));
                }
            }
        }

        System.out.println();

        parameters.put("rows", rows);

        return new ViewData("Basvuru", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Department View";
    }
}
