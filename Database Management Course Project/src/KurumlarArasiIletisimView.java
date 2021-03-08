
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class KurumlarArasiIletisimView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        switch (operationName) {
            //case "select": return selectOperation(modelData);	
            case "insert":
                return insertOperation(modelData);
            case "update":
                return updateOperation(modelData);

            case "insert.gui":
                return insertGUI(modelData);
            case "update.gui":
                return updateGUI(modelData);

        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        //System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("KurumlarArasiIletisim", "update.gui");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        //System.out.println("Number of updated rows is " + modelData.recordCount);
        System.out.println("Basvuru yonlendirildi.");
        return new ViewData("MainMenu", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {

        Map<String, Object> whereParameters = new HashMap<>();
        whereParameters.put("BasvuruNumarasi", KurumlarArasiIletisim.BasvuruNumarasi);

        return whereParameters;
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "BasvuruNumarasi, YonlendirilenKurumID");

        List<Object> rows = new ArrayList<>();

        System.out.print("Yonlendirilecek Basvurunun ");
        int BasvuruNumarasi = getInteger("BasvuruNumarasi : ", false);
        int YonlendirilenKurumID = getInteger("YonlendirilenKurumID : ", false);

        rows.add(new KurumlarArasiIletisim(BasvuruNumarasi, YonlendirilenKurumID));

        parameters.put("rows", rows);

        return new ViewData("KurumlarArasiIletisim", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {

        Map<String, Object> updateParameters = new HashMap<>();

        int DevamSebebi = 2;

        updateParameters.put("DevamSebebi", DevamSebebi);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("KurumlarArasiIletisim", "update", parameters);
    }

}
