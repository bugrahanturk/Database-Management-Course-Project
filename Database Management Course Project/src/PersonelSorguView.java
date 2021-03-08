
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class PersonelSorguView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        switch (operationName) {
            case "select.gui":
                return selectGUI(modelData);
            case "select":
                return selectOperation(modelData);
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        System.out.println("Sorgu Sonucu");
        System.out.println("------------");

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int BasvuruNumarasi = resultSet.getInt("BasvuruNumarasi");
                String İsim = resultSet.getString("İsim");
                String Soyisim = resultSet.getString("Soyisim");
                String BasvuruMetni = resultSet.getString("BasvuruMetni");
                String BasvuruCevapSonTarihi = resultSet.getString("BasvuruCevapSonTarihi");
                String Aciklamasi = resultSet.getString("Aciklamasi");

                // Display values
                System.out.print(BasvuruNumarasi + "\t");
                System.out.print(İsim + "\t");
                System.out.print(Soyisim + "\t");
                System.out.print(BasvuruMetni + "\t");
                System.out.print(BasvuruCevapSonTarihi + "\t");
                System.out.println(Aciklamasi);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        //System.out.println("Filter conditions:");

        int BasvuruSureciTypeID = 1;

        Map<String, Object> whereParameters = new HashMap<>();
        whereParameters.put("BasvuruSureciTypeID", BasvuruSureciTypeID);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("PersonelSorgu", "select", parameters);
    }

}
