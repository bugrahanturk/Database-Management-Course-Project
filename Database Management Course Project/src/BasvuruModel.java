
import java.sql.*;
import java.util.*;

class BasvuruModel implements ModelInterface {

    @Override//Get all applications from table with specified colums
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement

        
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	BasvuruNumarasi, DönüsTipi, Adres, BasvuruTarihi");
        sql.append(" FROM Basvuru ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY BasvuruNumarasi");
        

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    @Override // Son BaşvuruNumarası Her Insert işleminde Başvuru sınıfına eklenerek Başvuru Numarası bilgisi tutulur.
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Basvuru (" + fieldNames + ") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;

        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) instanceof Basvuru) {
                rowCount++;

                Basvuru basvuru = (Basvuru) rows.get(i);

                sql.append("(");
                for (int j = 0; j < fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(basvuru.getByName(fieldName)));
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");

                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }
        //System.out.println(sql.toString());

        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        Basvuru basvuru = (Basvuru) rows.get(0);

        StringBuilder sql1 = new StringBuilder();
        sql1.append(" SELECT top 1 ");
        sql1.append("	BasvuruNumarasi");
        sql1.append(" FROM Basvuru ");
        sql1.append(" ORDER BY BasvuruNumarasi desc");

        Connection connection1 = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1.toString());
        ResultSet result = preparedStatement1.executeQuery();

        while (result.next()) {
            Basvuru.BasvuruNumarasi = result.getInt("BasvuruNumarasi");
        }

        return rowCount;
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
    public String toString() {
        return "Department Model";
    }

    @Override
    public int updateTransaction(Map<String, Object> updateParameters, Map<String, Object> updateParameters1, Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
