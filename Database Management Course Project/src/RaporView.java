
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class RaporView implements ViewInterface {

    @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
		case "select": return selectOperation(modelData);	
		//case "insert": return insertOperation(modelData);	
		//case "update": return updateOperation(modelData);	
		//case "delete": return deleteOperation(modelData);	
		case "select.gui": return selectGUI(modelData);
		//case "insert.gui": return insertGUI(modelData);
		//case "update.gui": return updateGUI(modelData);
		//case "delete.gui": return deleteGUI(modelData);
		}
		
		return new ViewData("MainMenu", "");
	}
    
    ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
		
                if (resultSet != null) {
			while (resultSet.next()) {
                
		if(Personel.secim == 1){
                    
                    
                    /*int Sayisi = resultSet.getInt("Sayisi");
          
                    System.out.print(Sayisi + "\t");
                    */
                    System.out.print("Sonuc: ");
                    int sayisi = resultSet.getInt("sayisi");
                    System.out.println(sayisi);
                    
                }
                else if(Personel.secim == 2){
                    System.out.print("Sonuc: ");
                   int Sayisi = resultSet.getInt("Sayisi");
          
                    System.out.println(Sayisi);
                    
                    
                }
                else if(Personel.secim == 3){
                    int RedSebebi = resultSet.getInt("RedSebebi");
                    int rs = resultSet.getInt("rs");
                    
                    System.out.print(rs + "\t");
                    if(RedSebebi==1)
                        System.out.println("Gizli Bilgi");
                    else if(RedSebebi==2)
                        System.out.println("Ucret Odenmedi");
                    else if(RedSebebi==3)
                        System.out.println("Yetersiz Aciklama");
                    
                    
                }
                else if(Personel.secim == 4){
                    int Sayisi = resultSet.getInt("Sayisi");
  
                    System.out.print("Sonuc: ");
                    System.out.print(Sayisi);
                    System.out.println("");
                    
                }
                else if(Personel.secim == 5){
                    int Sayisi = resultSet.getInt("Sayisi");
                    int BasvuruSureciTypeID = resultSet.getInt("BasvuruSureciTypeID");
                    //String DurumAciklamasi = resultSet.getString("DurumAciklamasi");
                    
                    System.out.print(Sayisi + "\t");
                    if(BasvuruSureciTypeID==1){
                        System.out.println("DevamEdenBasvuru");
                    }
                    else if(BasvuruSureciTypeID==2)
                        System.out.println("BitenBasvuru");
                    else
                        System.out.println("ReddedilenBasvuru");
                    //System.out.println(DurumAciklamasi);
                }
               
                       }
                }
 
		
		return new ViewData("MainMenu", "");
	}
    
    Map<String, Object> getWhereParameters() throws Exception {
		
                Map<String, Object> whereParameters = new HashMap<>();
        
                System.out.println("1-> Toplam Bilgi Edinme Basvuru Sayisi");
                System.out.println("2-> Toplam Olumlu Basvuru Sayisi");
                System.out.println("3-> Reddedilen Basvuru Istatistikleri");
                System.out.println("4-> Gizli Bilgi Istenmeyen Basvurularin Sayisi");
                System.out.println("5-> Itiraz Edilen Basvurular");
                
                
                
                Personel.secim = getInteger("Secim: ", true);
                
                if(Personel.secim == 4){
                    Integer RedSebebi = 1;
                    whereParameters.put("RedSebebi", RedSebebi);
                }
                else if(Personel.secim == 5){
                    Integer BasvuruGecmisi = 1;
                    whereParameters.put("BasvuruGecmisi", BasvuruGecmisi);
                }
		
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Rapor", "select", parameters);
	}
        
        

}
