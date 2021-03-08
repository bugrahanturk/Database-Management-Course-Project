
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


class UcretTarifesiView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {	
		case "insert": return insertOperation(modelData);	
                case "update": return updateOperation(modelData);
                case "updateTransaction": return updateTransaction(modelData);
			
		//case "select.gui": return selectGUI(modelData);
		case "insert.gui": return insertGUI(modelData);
		case "update.gui": return updateGUI(modelData);
		//case "delete.gui": return deleteGUI(modelData);
		}
		
		return new ViewData("MainMenu", "");
	}
        
        
        ViewData insertOperation(ModelData modelData) throws Exception {
		//System.out.println("Number of inserted rows is " + modelData.recordCount);
		
		return new ViewData("UcretTarifesi", "update.gui");
	}
        
        
        ViewData updateOperation(ModelData modelData) throws Exception {
		//System.out.println("Number of updated rows is " + modelData.recordCount);
		 System.out.println("Basvuru ucretlendirildi.");
                 System.out.println("Basvurunun son cevap tarihi guncellendi.");
                
		return new ViewData("MainMenu", "");
	}
        
        
        Map<String, Object> getWhereParameters() throws Exception {
		
                Map<String, Object> whereParameters = new HashMap<>();
		whereParameters.put("BasvuruNumarasi", Basvuru.BasvuruNumarasi);
		
		
		return whereParameters;
	}
        
        ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "BasvuruNumarasi, UcretTalepTarihi, Ucreti");

		List<Object> rows = new ArrayList<>();

		System.out.print("Ucretlendirilecek ");
		Basvuru.BasvuruNumarasi = getInteger("BasvuruNumarasi : ", false);
                LocalDate UcretTalepTarihi = LocalDate.now();
                String str = String.valueOf(UcretTalepTarihi);
                double Ucreti = getDouble("Ucreti : ",false);
                 
                rows.add(new UcretTarifesi(Basvuru.BasvuruNumarasi, str, Ucreti));
			
                parameters.put("rows", rows);
		
		return new ViewData("UcretTarifesi", "insert", parameters);
	}
        
        ViewData updateGUI(ModelData modelData) throws Exception {
		
		
		Map<String, Object> updateParameters = new HashMap<>();
		
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("UcretTarifesi", "update", parameters);
	}
        
        
        ViewData updateTransaction(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		LocalDate TempDate = LocalDate.now();
                LocalDate Date = TempDate.plusDays(15);
                
                System.out.println(Date);
                String BasvuruCevapSonTarihi = String.valueOf(Date);
                System.out.println(BasvuruCevapSonTarihi);
                int DevamSebebi = 3;
                
		Map<String, Object> updateParameters = new HashMap<>();
		updateParameters.put("BasvuruCevapSonTarihi", BasvuruCevapSonTarihi);
		
                Map<String, Object> updateParameters1 = new HashMap<>();
                updateParameters1.put("DevamSebebi", DevamSebebi);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
                parameters.put("updateParameters1", updateParameters1);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("UcretTarifesi", "updateTransaction", parameters);
	}

}