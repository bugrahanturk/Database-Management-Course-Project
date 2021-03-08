

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

class PersonelView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "insert":
                return insertOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);

        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {

                Personel.YetkiNo = resultSet.getInt("YetkiNo");

            }
            resultSet.close();
        }
        if (Personel.YetkiNo == 1) {
            Personel.basvurucesidi = 1;
            Personel.YetkiNo = 0;
            return new ViewData("Kisi", "insert.gui");
        } else if (Personel.YetkiNo == 2) {

            Personel.YetkiNo = 0;
            System.out.println("Sorgu tipiniz seciniz");

            System.out.println("1- Cevap Bekleyen Basvuralari Goster");
            System.out.println("2- Ayni Kisiye Ait Basvurulari Goster");

            int choice = getInteger("Secim : ", false);

            if (choice == 1) {
                return new ViewData("PersonelSorgu", "select.gui");
            } else {
                return new ViewData("AyniKisiBasvurulari", "select.gui");
            }

        } else if (Personel.YetkiNo == 3) {
            Personel.YetkiNo = 0;
            return new ViewData("TabloGuncelle", "select.gui");
        } else if (Personel.YetkiNo == 4) {
            Personel.YetkiNo = 0;
            return new ViewData("UcretTarifesi", "insert.gui");
        } else if (Personel.YetkiNo == 5) {
            Personel.YetkiNo = 0;
            return new ViewData("KurumlarArasiIletisim", "insert.gui");
        } else if (Personel.YetkiNo == 6) {
            Personel.YetkiNo = 0;
            return new ViewData("Rapor", "select.gui");
        }
        Personel.YetkiNo = 0;
        return new ViewData("MainMenu", "");

    }

    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Personel bilgilerinizi giriniz: ");

        Map<String, Object> whereParameters = new HashMap<>();

        int PersonelID = getInteger("PersonelID: ", false);
        String Sifre = getString("Sifre: ", false);

        int hc = Sifre.hashCode();

        whereParameters.put("PersonelID", PersonelID);
        whereParameters.put("Sifre", hc);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Personel", "select", parameters);
    }

    ViewData insertOperation(ModelData modelData) throws Exception {

        return new ViewData("Basvuru", "");
    }

    @Override
    public String toString() {
        return "Department View";
    }

}
