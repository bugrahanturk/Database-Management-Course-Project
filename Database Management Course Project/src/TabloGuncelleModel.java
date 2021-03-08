
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

public class TabloGuncelleModel implements ModelInterface {

    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	BasvuruNumarasi, BasvuruSureciTypeID");
        sql.append(" FROM BasvuruSureciTypeDisJoint ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

    
        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;

    }

    @Override //  Başvurunun Başvuru Sürecinin Durumuna Göre Insert işlemi uygulanıyor
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        
        
        StringBuilder sql = new StringBuilder();
        int rowCount=0;

        if (Basvuru.BasvuruSureciTypeID == 1) {
            
            sql.append(" INSERT INTO DevamEdenBasvuru(" + fieldNames + ") ");
            sql.append(" VALUES ");

            String[] fieldList = fieldNames.split(",");

            rowCount = 0;
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i) instanceof DevamEdenBasvuru) {
                    rowCount++;

                    DevamEdenBasvuru devamedenbasvuru = (DevamEdenBasvuru) rows.get(i);

                    sql.append("(");
                    for (int j = 0; j < fieldList.length; j++) {
                        String fieldName = fieldList[j].trim();
                        sql.append(DatabaseUtilities.formatField(devamedenbasvuru.getByName(fieldName)));
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
        } else if (Basvuru.BasvuruSureciTypeID == 2) {
            
            sql.append(" INSERT INTO BitenBasvuru (" + fieldNames + ") ");
            sql.append(" VALUES ");

            String[] fieldList = fieldNames.split(",");

            rowCount = 0;
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i) instanceof BitenBasvuru) {
                    rowCount++;

                    BitenBasvuru bitenbasvuru = (BitenBasvuru) rows.get(i);

                    sql.append("(");
                    for (int j = 0; j < fieldList.length; j++) {
                        String fieldName = fieldList[j].trim();
                        sql.append(DatabaseUtilities.formatField(bitenbasvuru.getByName(fieldName)));
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
        } else {
            
           
            sql.append(" INSERT INTO ReddedilenBasvuru (" + fieldNames + ") ");
            sql.append(" VALUES ");

            String[] fieldList = fieldNames.split(",");

            rowCount = 0;
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i) instanceof ReddedilenBasvuru) {
                    
                    
                    rowCount++;

                    ReddedilenBasvuru reddedilenbasvuru = (ReddedilenBasvuru) rows.get(i);

                    sql.append("(");
                    for (int j = 0; j < fieldList.length; j++) {
                        String fieldName = fieldList[j].trim();
                        sql.append(DatabaseUtilities.formatField(reddedilenbasvuru.getByName(fieldName)));
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
        }

        //System.out.println(sql.toString());
        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        return rowCount;
    }

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE BasvuruSureciTypeDisJoint SET ");
        int appendCount = 0;
        for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
            sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
            if (++appendCount < updateParameters.size()) {
                sql.append(", ");
            }
        }
        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    @Override
    public int delete(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();

        if (Basvuru.BasvuruSureciTypeID == 1) {

            sql.append(" DELETE FROM DevamEdenBasvuru ");
        } else if (Basvuru.BasvuruSureciTypeID == 2) {

            sql.append(" DELETE FROM BitenBasvuru ");

        } else {

            sql.append(" DELETE FROM ReddedilenBasvuru ");

        }

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    @Override
    public int updateTransaction(Map<String, Object> updateParameters, Map<String, Object> updateParameters1, Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
