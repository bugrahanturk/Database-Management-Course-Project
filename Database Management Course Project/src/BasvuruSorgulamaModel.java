
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BasvuruSorgulamaModel implements ModelInterface {

    @Override   //Başvurunun Durumu Açıklamasına göre Select Sorgusus Düzenlenir
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        
        
        
        if (BasvuruSorgulama.DurumAciklamasi.equals("Devam Eden Basvuru")) {

            sql.append(" SELECT ");
            sql.append("	KisiID, İsim, Soyisim, BasvuruNumarasi, DurumAciklamasi, Aciklamasi");
            sql.append(" FROM DevamEdenBasvuruView ");

            sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
           
        } else if (BasvuruSorgulama.DurumAciklamasi.equals("Kabul Edildi")) {
            
            sql.append(" SELECT ");
            sql.append("	KisiID, İsim, Soyisim, BasvuruNumarasi, DurumAciklamasi, Aciklamasi");
            sql.append(" FROM BitenBasvuruView ");

            sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        } else {
            
            sql.append(" SELECT ");
            sql.append("	KisiID, İsim, Soyisim, BasvuruNumarasi, DurumAciklamasi, Aciklamasi");
            sql.append(" FROM ReddedilenBasvuruView ");

            sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        }

        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();
        
        

        return result;
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTransaction(Map<String, Object> updateParameters, Map<String, Object> updateParameters1, Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
