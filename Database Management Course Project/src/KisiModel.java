
import java.sql.*;
import java.util.*;

class KisiModel implements ModelInterface {

    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement<
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	KisiID, İsim, Soyisim, BasvuruNumarasi, BasvuruTarihi ,BasvuruCevapSonTarihi, DurumAciklamasi");
        sql.append(" FROM BasvuruSorguView ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        //sql.append("ORDER BY AddressID");		
        //System.out.println(sql.toString() + "\n");
        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    @Override // Önceden kayıtlı kişi kontrolü için TcNumber ve Pasaport Number kullanılarak KISI tablosundan gerekli kontroller edilir.
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        int rowCount = 0;
        Kisi kisi1 = (Kisi) rows.get(0);

        String uyruk = String.valueOf(kisi1.getByName("Uyruk"));

        if (uyruk.equals("1")) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("	KisiID, TCNumber");
            sql.append(" FROM KISI ");

            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            //DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
            ResultSet result = preparedStatement.executeQuery();

            if (result != null) {
                while (result.next()) {

                    String Tc = result.getString("TCNumber");

                    if (String.valueOf(kisi1.getByName("TCNumber")).equals(Tc)) {
                        Kisi.KisiID = Short.valueOf(String.valueOf(result.getInt("KisiID")));

                        return rowCount;
                    }
                }
                result.close();
            }
        } else if (uyruk.equals("0")) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("	KisiID, PasaportNumber");
            sql.append(" FROM KISI ");

            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            //DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
            ResultSet result = preparedStatement.executeQuery();

            if (result != null) {
                while (result.next()) {

                    String Pasaport = result.getString("PasaportNumber");

                    if (String.valueOf(kisi1.getByName("PasaportNumber")).equals(Pasaport)) {
                        Kisi.KisiID = Short.valueOf(String.valueOf(result.getInt("KisiID")));

                        return rowCount;
                    }
                }
                result.close();
            }

        }

        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO KISI (" + fieldNames + ") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) instanceof Kisi) {
                rowCount++;
                Kisi kisi = (Kisi) rows.get(i);

                sql.append("(");
                for (int j = 0; j < fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();

                    sql.append(DatabaseUtilities.formatField(kisi.getByName(fieldName)));
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

        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        Kisi kisi = (Kisi) rows.get(0);

        StringBuilder sql1 = new StringBuilder();
        sql1.append(" SELECT ");
        sql1.append("	KisiID");
        sql1.append(" FROM KISI ");
        sql1.append("WHERE TCNumber=" + kisi.getTCNumber() + " OR PasaportNumber=" + kisi.getPasaportNumber());

        Connection connection1 = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1.toString());
        ResultSet result = preparedStatement1.executeQuery();

        while (result.next()) {
            Kisi.KisiID = Short.valueOf(String.valueOf(result.getInt("KisiID")));
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
    public int updateTransaction(Map<String, Object> updateParameters, Map<String, Object> updateParameters1,
            Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
