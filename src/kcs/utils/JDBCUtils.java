package kcs.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Egidijus on 3/7/2017.
 */
public class JDBCUtils
{
    public static boolean isTableExist(Connection connection, String tableName)
    {
        boolean isExist=false;
        try {
            DatabaseMetaData metaData= connection.getMetaData();
            ResultSet resulset= metaData.getTables(null,null,tableName, null);
            isExist=resulset.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }
}
