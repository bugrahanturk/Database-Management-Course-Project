
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

public class RaporModel implements ModelInterface {

    @Override // Personelin Seçimine Göre yapılacak Sorguları belirler
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        StringBuilder sql = new StringBuilder();

        if (Personel.secim == 1) {
            sql.append(" SELECT ");
            sql.append("	COUNT(BasvuruNumarasi) as sayisi ");
            sql.append(" FROM Basvuru ");

        } else if (Personel.secim == 2) {
            sql.append(" SELECT ");
            sql.append("	COUNT(BasvuruNumarasi) AS sayisi ");
            sql.append(" FROM BitenBasvuru ");

        } else if (Personel.secim == 3) {
            sql.append(" SELECT ");
            sql.append("        RedSebebi, COUNT(*) AS rs ");
            sql.append(" FROM ReddedilenBasvuru ");
            sql.append(" GROUP BY RedSebebi");
            sql.append(" ORDER BY rs DESC");
        } else if (Personel.secim == 4) {

            sql.append(" SELECT ");
            sql.append("	COUNT(BasvuruNumarasi) AS sayisi ");
            sql.append(" FROM Basvuru");
            sql.append(" WHERE BasvuruNumarasi NOT IN");
            sql.append(" (SELECT ");
            sql.append("	BasvuruNumarasi");
            sql.append(" FROM ReddedilenBasvuru ");
            List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
            sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
            sql.append(")");

            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
            ResultSet result = preparedStatement.executeQuery();

            return result;

        } else if (Personel.secim == 5) {
            sql.append(" SELECT ");
            sql.append("	COUNT(BasvuruNumarasi) AS sayisi, BasvuruSureciTypeID ");
            sql.append(" FROM BasvuruSorguView ");
            List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
            sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

            sql.append(" GROUP BY BasvuruSureciTypeID");

            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
            ResultSet result = preparedStatement.executeQuery();

            return result;

        }

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        // execute constructed SQL statement
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
