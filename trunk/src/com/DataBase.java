package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class DataBase {
	
    public static Connection connection;
    public Statement statement;
    public ResultSet resultset;
    private String databaseType;
    private String host;
    private String databaseName;
    private String userName;
    private String password;
    private String connectionString;
	
	private static DataBase db;

	
	 /**
     * @param resultset the resultset to set
     */
    public void setResultset(ResultSet resultset) {
            this.resultset = resultset;
    }
    /**
     * @return the databaseType
     */
    public String getDatabaseType() {
            return databaseType;
    }
    /**
     * @param databaseType the databaseType to set
     */
    public void setDatabaseType(String databaseType) {
            this.databaseType = databaseType;
    }
    /**
     * @return the host
     */
    public String getHost() {
            return host;
    }
    /**
     * @param host the host to set
     */
    public void setHost(String host) {
            this.host = host;
    }
    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
            return databaseName;
    }
    /**
     * @param databaseName the databaseName to set
     */
    public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
            return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
            this.userName = userName;
    }
    /**
     * @return the password
     */
    public String getPassword() {
            return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
            this.password = password;
    }

	public static DataBase getDataBase(){
        if(DataBase.db==null){
                DataBase.db = new DataBase();
                db.setDatabaseName("forca");
                db.setDatabaseType("mysql");
                db.setHost("localhost");
                db.setPassword("");
                db.setUserName("root");
        }
        
        return db;
}

	 public void open(){
		 System.out.println("opening database");
         // exemplo de criação de conexão;
         // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/BANCO_DE_DADOS?user=root&password=root");

         try {
                 Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException e1) {
                 // TODO Auto-generated catch block
                 e1.printStackTrace();
         }
         String str = "jdbc:"+getDatabaseType()+"://"+getHost()+"/"+getDatabaseName();
         setConnectionString(str);
         try {
                 System.out.println("opening connection with connectionString = "+getConnectionString()+" user name "+ getUserName()+ " password "+getPassword());
                 setConnection(DriverManager.getConnection(getConnectionString(),getUserName(),getPassword()));
         } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
	 }
	 public boolean entraNoRanking(String nivel, int pontos){
		 boolean result = false;
		 Vector<ItemRank> rankVector = getRankingByNivel(nivel);
		 if(rankVector.size()<5){
			 return true;
		 } else {
			 if(rankVector.elementAt(4).pontos < pontos){
				 return true;
			 }
		 }
		 return result;
	 }
	 
	  public void executeQuery(String query) {
          try {
                  if(DataBase.connection!=null && !DataBase.connection.isClosed()){
                          try {
                                  setStatement(connection.createStatement());
                                  getStatement().execute(query);
                          } catch (SQLException e) {
                                  // TODO Auto-generated catch block
                                  e.printStackTrace();
                          }
                          finally{
                                  try {
                                          getStatement().close();
                                          connection.close();
                                  } catch (SQLException e) {
                                          // TODO Auto-generated catch block
                                          e.printStackTrace();
                                  }                       
                          }
                  } else {
                          open();
                          getDataBase().executeQuery(query);
                  }
          } catch (SQLException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
          }
  }

	 
	 public void inserirNoRanking(String nome, String nivel, int pontos){
		
            String sql = "INSERT INTO `forca`.`ranking` ( `id` , `nome` , `nivel` , `pontos` ) VALUES ( NULL , '"+nome+"', '"+nivel+"', '"+String.valueOf(pontos)+"' ); ";
            getDataBase().executeQuery(sql);
     
	 }
	 
	 public Vector<ItemRank>getRankingByNivel(String nivel){
		 String sql = "select * from ranking where nivel = "+nivel+" order by pontos desc limit 5";
		 System.out.println(sql);
		 Vector<ItemRank> result = new Vector<ItemRank>();
         ResultSet rs = getDataBase().getResultset(sql);
	         try {
	             while(rs.next()){
	            	 ItemRank item = new ItemRank();
	            	 item.nome = rs.getString("nome");
	            	 item.pontos = rs.getInt("pontos");
	            	 result.addElement(item);
	            	 //System.out.println(rs.getString("nome"));
	             }
	     } catch (SQLException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	     }
	     return result;
	 }
	 
     public void testaBanco(){
         
         String sql = " SELECT * FROM `ranking` LIMIT 0 , 30 ";
         System.out.println(sql);
         ResultSet rs = getDataBase().getResultset(sql);
	         try {
	             while(rs.next()){
	            	 	System.out.println(rs.getString("nome"));
	             }
	     } catch (SQLException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	     }

     }

	 
	 /**
      * @return the connectionString
      */
     public String getConnectionString() {
             return connectionString;
     }

     /**
      * @param connectionString the connectionString to set
      */
     public void setConnectionString(String connectionString) {
             this.connectionString = connectionString;
     }
     /**
      * @param connection the connection to set
      */
     public static void setConnection(Connection connection) {
             DataBase.connection = connection;
     }
     /**
      * @return the statement
      */
     public Statement getStatement() {
             return statement;
     }
     /**
      * @param statement the statement to set
      */
     public void setStatement(Statement statement) {
             this.statement = statement;
     }
     /**
      * @return the resultset
      */
     public ResultSet getResultset(String query) {
             try {
                     if(DataBase.connection!=null && !DataBase.connection.isClosed()){
                             try {
                                     setStatement(connection.createStatement());
                                     setResultset(statement.executeQuery(query));
                             } catch (SQLException e) {
                                     // TODO Auto-generated catch block
                                     e.printStackTrace();
                             }
                             finally{
                    
                             }
                     } else {
                             open();
                             return getResultset(query);
                     }
             } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
             }
             return getResultset();
     }
     public ResultSet getResultset() {
             return resultset;
     }
     
     
	 
}
