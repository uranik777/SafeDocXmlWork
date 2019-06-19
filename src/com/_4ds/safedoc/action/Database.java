package com._4ds.safedoc.action;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	  public static void createDatabase() {
		    String data = "jdbc:derby:safedoc;create=true";
		    try {
		      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		      Connection conn = DriverManager.getConnection(data);
		      Statement st = conn.createStatement();
		      int result = st.executeUpdate("CREATE TABLE documents (ukey INTEGER NOT NULL PRIMARY KEY "
		          + "GENERATED ALWAYS AS identity (START WITH 1, INCREMENT BY 1), "
		          + "filename VARCHAR(200), filedata BLOB)");
		 
		      //result = st.executeUpdate("INSERT INTO contacts (name, address1, address2" + ") VALUES('J','Center', 1 , 'GA')");
		      st.close();
		    } catch (Exception e) {
		      System.out.println("Error - " + e.toString());
		    }
		  }
	  
	  public static void writeFile(String filename, byte[] filedata) throws Exception {
		    Connection con;
		    String data = "jdbc:derby:safedoc;create=true";
		    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		    con = DriverManager.getConnection(data);

		    PreparedStatement ps;
		    ps = con.prepareStatement("insert into documents(filename,filedata) " + "values(?,?)");
		    ps.setString(1, filename);

		    Blob blob = con.createBlob();
		    OutputStream bos=blob.setBinaryStream(1);
		    bos.write(filedata);
		    bos.close();
//		    ObjectOutputStream oos;
//		    oos = new ObjectOutputStream(blob.setBinaryStream(1));
//		    oos.writeObject(filedata);
//		    oos.close();
		    ps.setBlob(2, blob);
		    ps.execute();
		    blob.free();
		    ps.close();
		  }
	  
	  
	  public static void writeFile() {
		    String data = "jdbc:derby:safedoc;create=true";
		    try {
		      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		      Connection conn = DriverManager.getConnection(data);
		      Statement st = conn.createStatement();
		      int result = st.executeUpdate("INSERT INTO contacts (name, address1, address2"
		          + ") VALUES('J','Center', 1 , 'GA')");
		      st.close();
		    } catch (Exception e) {
		      System.out.println("Error - " + e.toString());
		    }
		  }	 
	  
	  
	  public byte[] readFile(String filename) {
		  byte[] buf=null;
		    String data = "jdbc:derby:safedoc";
		    try {
		      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		      Connection conn = DriverManager.getConnection(data, "", "");
		      Statement st = conn.createStatement();
		      ResultSet rec = st.executeQuery("SELECT filedata FROM documents where filename='"+filename+"'");
		      if (rec.next()) {
		          Blob filedata = rec.getBlob(1);
		          InputStream is=null;
		          //ObjectInputStream ois = null;
		          //ois = new ObjectInputStream(datafile.getBinaryStream());
		          is=filedata.getBinaryStream();
		          buf=readFully(is);
		          is.close();
		      }
		      st.close();
		    } catch (Exception e) {
		      System.out.println("Error - " + e.toString());
		    }
		    return buf;
		  }
	  
	  
	    private static byte[] readFully (InputStream in) throws IOException
	    {
	        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
	        byte[] temp = new byte[1024];
	        int read = in.read(temp);
	        while (read > 0) {
	            out.write(temp, 0, read);
	            read = in.read(temp);
	        }
	        out.flush();
	        byte[] ret=out.toByteArray();
	        out.close();
	        return ret;
	    }	  
	  
	  public void makedb() throws Exception {
		    String home, system;    
		    home = System.getProperty("user.home", ".");
		    system = home + File.separatorChar + ".database";
		    System.setProperty("derby.system.home", system);
		    createDatabase();
		    byte[] testfile={1,2,3,4,5};
		    writeFile("test_filename.xml", testfile);
		    byte[] filedata=readFile("test_filename.xml");
		    filedata=null;
	  }
	  
}
