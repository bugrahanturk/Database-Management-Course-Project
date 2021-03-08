
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class UcretTarifesiModel implements ModelInterface {

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO UcretTarifesi (" + fieldNames + ") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) instanceof UcretTarifesi) {
                rowCount++;

                UcretTarifesi ucrettarifesi = (UcretTarifesi) rows.get(i);

                sql.append("(");
                for (int j = 0; j < fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(ucrettarifesi.getByName(fieldName)));
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

        return rowCount;
    }

    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTransaction(Map<String, Object> updateParameters, Map<String, Object> updateParameters1, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        StringBuilder sql1 = new StringBuilder();
        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        int rowCount;
        //sql.append(" BEGIN TRANSACTION;");

        sql.append(" UPDATE Basvuru SET ");
        sql.append(" BasvuruCevapSonTarihi = '" + updateParameters.get("BasvuruCevapSonTarihi") + "'");
        sql.append(" WHERE BasvuruNumarasi = " + whereParameters.get("BasvuruNumarasi"));
        
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
         rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();
 
        return rowCount;
    }

    @Override
    public int delete(Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // Transaction kullanılarak aynı işlemde iki Table update işlemine koyulmuştur.
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();

        LocalDate TempDate = LocalDate.now();
        LocalDate Date = TempDate.plusDays(15);
        String BasvuruCevapSonTarihi = String.valueOf(Date);

        sql.append(" BEGIN TRANSACTION;");
        sql.append(" UPDATE Basvuru SET ");
        sql.append(" BasvuruCevapSonTarihi = '" + BasvuruCevapSonTarihi + "'");
        sql.append(" WHERE BasvuruNumarasi = " + Basvuru.BasvuruNumarasi);

        sql.append(" UPDATE DevamEdenBasvuru SET ");
        sql.append(" DevamSebebi = 3");
        sql.append(" WHERE BasvuruNumarasi = " + Basvuru.BasvuruNumarasi);
        sql.append(" COMMIT;");

        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

}
