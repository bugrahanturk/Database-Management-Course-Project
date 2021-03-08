import java.util.*;


public class ModelViewControllerConsole {

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();
                
		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();		
		router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
		router.put("Kisi", new Controller(new KisiView(), new KisiModel()));
                router.put("Basvuru",new Controller (new BasvuruView(), new BasvuruModel()));
                router.put("BasvuruSureci", new Controller (new BasvuruSureciView(), new BasvuruSureciModel()));
                router.put("DevamEdenBasvuru", new Controller (new DevamEdenBasvuruView(), new DevamEdenBasvuruModel()));
                router.put("BasvuruSorgulama", new Controller (new BasvuruSorgulamaView(), new BasvuruSorgulamaModel()));
                router.put("Personel", new Controller (new PersonelView(), new PersonelModel()));
                router.put("PersonelSorgu", new Controller (new PersonelSorguView(), new PersonelSorguModel()));
                router.put("TabloGuncelle", new Controller (new TabloGuncelleView(), new TabloGuncelleModel()));
                router.put("UcretTarifesi", new Controller (new UcretTarifesiView(), new UcretTarifesiModel()));
                router.put("KurumlarArasiIletisim", new Controller (new KurumlarArasiIletisimView(), new KurumlarArasiIletisimModel()));
                router.put("AyniKisiBasvurulari", new Controller (new AyniKisiBasvurulariView(), new AyniKisiBasvurulariModel()));
                router.put("Rapor", new Controller (new RaporView(), new RaporModel()));
                
		ViewData viewData = new ViewData("MainMenu", "");		
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			 

		}
		while (viewData.functionName != null);
                
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");

		// Disconnect from database
		disconnectFromDatabase();
	}
	public static void connectToDatabase() {
		DatabaseUtilities.host = "localhost:55774";
		DatabaseUtilities.databaseName = "Group15_Proje";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
}
