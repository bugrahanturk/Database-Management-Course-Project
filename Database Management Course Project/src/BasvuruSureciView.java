
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BasvuruSureciView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "insert":
                return insertOperation(modelData);
            case "insert.gui":
                return insertGUI(modelData);

        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {

        return new ViewData("DevamEdenBasvuru", "insert.gui");
    }

    ViewData insertGUI(ModelData modelData) throws Exception {

        Map<String, Object> parameters = new HashMap<>();

        List<Object> rows = new ArrayList<>();

        int BasvuruNumarasi;
        int BasvuruSureciTypeID;

        BasvuruNumarasi = Basvuru.BasvuruNumarasi;
        BasvuruSureciTypeID = 1;

        parameters.put("fieldNames", "BasvuruNumarasi, BasvuruSureciTypeID");

        rows.add(new BasvuruSureci(BasvuruNumarasi, BasvuruSureciTypeID));

        parameters.put("rows", rows);

        return new ViewData("BasvuruSureci", "insert", parameters);
    }

}
