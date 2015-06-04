package dbpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBAccess {

	private String driver;
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    /** 引数有りのコンストラクタ */
    public DBAccess(String driver, String url, String user, String password) {
       this.driver = driver;
       this.url = url;
       this.user = user;
       this.password = password;
    }

    /** 引数なしのコンストラクタ */
    public DBAccess() {
        driver = "org.gjt.mm.mysql.Driver";
        url = "jdbc:mysql://localhost:3306/tom_sample";
        user = "root";
        password = "rontech";
    }

    /** データベースへの接続を行う */
    public synchronized Connection open() throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();

        return connection;
    }

    /** SQL 文を実行した結果の ResultSet を返す */
    public ResultSet getResultSet(String sql) throws Exception {
        if ( statement.execute(sql) ) {
	        return statement.getResultSet();
        }
        return null;
    }

    /** SQL実行文 */
    public void execute(String sql) throws Exception {
        statement.execute(sql);
    }

    /** データベースへのコネクションのクローズ */
    public synchronized void close() throws Exception {
        if ( resultset != null ) resultset.close();
        if ( statement != null ) statement.close();
        if ( connection != null ) connection.close();
    }

}
