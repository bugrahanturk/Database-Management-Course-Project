
import java.util.*;

class MainMenuView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        Integer choice;
        do {
            System.out.println("--------------------Bilgi Edinme Modulü Ana Sayfası--------------------");
            System.out.println("1. Basvuru Sorgula\t\t\t 3. Personel Girisi");
            System.out.println("2. Basvuru Yap");
            System.out.println("6. Quit");
            System.out.println();

            choice = getInteger("Enter your choice : ", false);
        } while (choice == null || choice < 1 || choice > 6);

        Map<String, Object> userInput = new HashMap<>();
        userInput.put("mainMenuChoice", choice);

        switch (choice.intValue()) {
            case 1:
                operationName = "select.gui";
                break;
            case 2:
                operationName = "insert.gui";
                break;
            case 3: {
                return new ViewData("Personel", "select.gui", new HashMap<>());
            }

            default:
                return new ViewData(null, null);
        }

        return new ViewData("Kisi", operationName, new HashMap<>());
    }

    @Override
    public String toString() {
        return "Main Menu View";
    }
}
