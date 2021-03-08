
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class TabloGuncelleView implements ViewInterface{

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        switch(operationName) {
		case "update": return updateOperation(modelData);
                case "update.gui": return updateGUI(modelData);
                case "delete": return deleteOperation(modelData);
                case "delete.gui": return deleteGUI(modelData);
                case "select": return selectOperation(modelData);
                case "select.gui": return selectGUI(modelData);
                case "insert": return insertOperation(modelData);
                case "insert.gui": return insertGUI(modelData);
                
        }
        
        return new ViewData("MainMenu", "");
    }
    
    ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			while (resultSet.next()) {
				Basvuru.BasvuruSureciTypeID = resultSet.getInt("BasvuruSureciTypeID");
			}
			resultSet.close();	
		}
                
                
		
		return new ViewData("TabloGuncelle", "delete.gui");
	}
    
    ViewData insertOperation(ModelData modelData) throws Exception {
		//System.out.println("Number of inserted rows is " + modelData.recordCount);
		
                System.out.println("Guncelleme islemi tamamlandı.");
                
                Basvuru.BasvuruNumarasi = 0;
                Basvuru.BasvuruSureciTypeID = 0;
                
		return new ViewData("MainMenu", "");
	}
    
     ViewData updateOperation(ModelData modelData) throws Exception {
		//System.out.println("Number of updated rows is " + modelData.recordCount);
		
		return new ViewData("TabloGuncelle", "insert.gui");
    }   
    
    
    ViewData deleteOperation(ModelData modelData) throws Exception {
		//System.out.println("Number of deleted rows is " + modelData.recordCount);
		
		return new ViewData("TabloGuncelle", "update.gui");
	}	
    
    
    
    Map<String, Object> getWhereParameters() throws Exception {
		System.out.print("Guncellencek ");
		
                int BasvuruNumarasi = getInteger("BasvuruNumarasi : ", true);
                
                Basvuru.BasvuruNumarasi = BasvuruNumarasi;
		
		
		Map<String, Object> whereParameters = new HashMap<>();
		whereParameters.put("BasvuruNumarasi", BasvuruNumarasi);
		
		
		return whereParameters;
	}
    
    ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("TabloGuncelle", "select", parameters);
	}
    
    ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		

		List<Object> rows = new ArrayList<>();
		
		
		
                        if(Basvuru.BasvuruSureciTypeID == 1){
                            
                            String devamnedeni = getString("Devam nedeni: ", false);
                            int DevamSebebi=0;
                            
                            if(devamnedeni.equalsIgnoreCase("Inceleme altinda"))
                                DevamSebebi = 1;
                            else if(devamnedeni.equalsIgnoreCase("Baska kurum"))
                                DevamSebebi = 2;
                            else if(devamnedeni.equalsIgnoreCase("Ucret"))
                                DevamSebebi = 3;
                            
                            
                            parameters.put("fieldNames", "BasvuruNumarasi, BasvuruSureciTypeID, DevamSebebi");
                            rows.add(new DevamEdenBasvuru(Basvuru.BasvuruNumarasi, Basvuru.BasvuruSureciTypeID, DevamSebebi));
                        }
                        else if(Basvuru.BasvuruSureciTypeID == 2){
                            System.out.println("Basvuru sonlandırıldı.");
                            String Cevap = getString("Basvuru Cevabi: ", false);
                            parameters.put("fieldNames", "BasvuruNumarasi, BasvuruSureciTypeID, Cevap");
                            rows.add(new BitenBasvuru(Basvuru.BasvuruNumarasi, Basvuru.BasvuruSureciTypeID, Cevap));
                        }
                        else{
                            
                            String reddedilmenedeni = getString("Reddedilme nedeni: ", false);
                            int RedSebebi=0;
                            
                            if(reddedilmenedeni.equalsIgnoreCase("Gizli bilgi"))
                                RedSebebi = 1;
                            else if(reddedilmenedeni.equalsIgnoreCase("Ucret"))
                                RedSebebi = 2;
                            else if(reddedilmenedeni.equalsIgnoreCase("Yetersiz aciklama"))
                                RedSebebi = 3;
                            
                            
                            //int RedSebebi = getInteger("RedSebebi", false);
                            parameters.put("fieldNames", "BasvuruNumarasi, BasvuruSureciTypeID, RedSebebi");
                            rows.add(new ReddedilenBasvuru(Basvuru.BasvuruNumarasi, Basvuru.BasvuruSureciTypeID, RedSebebi));
                        }
     
		
		
		parameters.put("rows", rows);
		
		return new ViewData("TabloGuncelle", "insert", parameters);
	}
    

    ViewData deleteGUI(ModelData modelData) throws Exception {
        
                Map<String, Object> parameters = new HashMap<>();
                Map<String, Object> Basparamaters = new HashMap<>();
		
                Basparamaters.put("BasvuruNumarasi", Basvuru.BasvuruNumarasi);
                parameters.put("whereParameters", Basparamaters);
		
		return new ViewData("TabloGuncelle", "delete", parameters);
	}
        
   
        
    ViewData updateGUI(ModelData modelData) throws Exception {
                
                Map<String, Object> Basparamaters = new HashMap<>();
                Basparamaters.put("BasvuruNumarasi", Basvuru.BasvuruNumarasi);
                
		
		String GuncellenmekIstenen = getString("Hangi tabloya gidecek : ", false);
                
                if(GuncellenmekIstenen.equalsIgnoreCase("Devam eden basvuru tablosu"))
                    Basvuru.BasvuruSureciTypeID = 1;
                else if(GuncellenmekIstenen.equalsIgnoreCase("Biten basvuru tablosu"))
                    Basvuru.BasvuruSureciTypeID = 2;
                else if(GuncellenmekIstenen.equalsIgnoreCase("Reddedilen basvuru tablosu"))
                    Basvuru.BasvuruSureciTypeID = 3;
		
                System.out.println();
                
                Map<String, Object> updateParameters = new HashMap<>();
                updateParameters.put("BasvuruSureciTypeID", Basvuru.BasvuruSureciTypeID);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", Basparamaters);
		
		return new ViewData("TabloGuncelle", "update", parameters);
	}    
        
}

