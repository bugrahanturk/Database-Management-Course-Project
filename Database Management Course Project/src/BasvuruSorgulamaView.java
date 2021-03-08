
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BasvuruSorgulamaView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);

        }

        return new ViewData("MainMenu", "");
    }
    //Başvuru Numarasına göre yapılan başvurunun bilgileri gösterilir 

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;
        
 
        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                String İsim = resultSet.getString("İsim");
                String Soyisim = resultSet.getString("Soyisim");
                Short KisiID = resultSet.getShort("KisiID");
                int BasvuruNumarasi = resultSet.getInt("BasvuruNumarasi");
                String DurumAciklamasi = resultSet.getString("DurumAciklamasi");
                String Aciklamasi = resultSet.getString("Aciklamasi");

                // Display values
                System.out.print(BasvuruNumarasi + "\t");
                System.out.print(KisiID + "\t");
                System.out.print(İsim + "\t");
                System.out.print(Soyisim + "\t");
                System.out.print(DurumAciklamasi + "\t");
                System.out.print(Aciklamasi + "\t" + "\n");

            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> whereParameters = new HashMap<>();
        whereParameters.put("BasvuruNumarasi", Basvuru.BasvuruNumarasi);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", whereParameters);
        

        return new ViewData("BasvuruSorgulama", "select", parameters);
    }
}
