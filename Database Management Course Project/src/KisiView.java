
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KisiView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "insert":
                return insertOperation(modelData);
             
            case "select.gui":
                return selectGUI(modelData);
            case "insert.gui":
                return insertGUI(modelData);
            case "update.gui":
                return updateGUI(modelData);
            
        }

        return new ViewData("MainMenu", "");
    }

    //GIRILEN BASVURU NUMARASINI
    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {

                BasvuruSorgulama.DurumAciklamasi = resultSet.getString("DurumAciklamasi");;
                Basvuru.BasvuruNumarasi = resultSet.getInt("BasvuruNumarasi");

            }
            resultSet.close();
        }

        return new ViewData("BasvuruSorgulama", "select.gui");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        //System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("Basvuru", "");
    }



    Map<String, Object> getWhereParameters() throws Exception {

        Map<String, Object> whereParameters = new HashMap<>();

        int BasvuruNumarasi = getInteger("Sorgulama Istediginiz Basvurunun Numarasi : ", false);
        
        String BasvuruTarihi = getString("Sorgulama Istediginiz Basvurunun Tarihi : ", false);

        whereParameters.put("BasvuruNumarasi", BasvuruNumarasi);
        whereParameters.put("BasvuruTarihi", BasvuruTarihi);
        

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Kisi", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {          
        Map<String, Object> parameters = new HashMap<>();

        List<Object> rows = new ArrayList<>();

        int Uyruk;
        long TCNumber;
        String Ad, Soyad, PasaportNumber;

        System.out.println("Kisi bilgilerini giriniz");
        System.out.println("------------------------");
        Ad = getString("İsim : ", false);
        Soyad = getString("Soyisim: ", false);
        Uyruk = getInteger("Uyruk(TC vatandaşı ise ->1 değilse Yabancı -> 0) : ", false);
        if (Uyruk == 1) {
            TCNumber = Long.parseLong(getString("TCNumber : ", true));
            parameters.put("fieldNames", "İsim, Soyisim, Uyruk, TCNumber ");
            if (Ad != null && Soyad != null) {
                rows.add(new Kisi(Ad, Soyad, Uyruk, TCNumber));
            }
        } else {
            PasaportNumber = getString("PasaportNumber: ", true);
            parameters.put("fieldNames", "İsim, Soyisim, Uyruk, PasaportNumber ");
            if (Ad != null && Soyad != null) {
                rows.add(new Kisi(Ad, Soyad, Uyruk, PasaportNumber));
            }
        }

        parameters.put("rows", rows);

        return new ViewData("Kisi", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
       

        return new ViewData("Department", "update");
    }

   

    @Override
    public String toString() {
        return "Department View";
    }
}
