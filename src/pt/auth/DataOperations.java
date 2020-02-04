/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.auth;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class DataOperations {
  Connection con;
  
  public DataOperations()
    {
    con=ConnectionObj.getConnection();
    }
  
  public ResultSet getScores(String classname)
    {
        try
        {
                Statement stmt=con.createStatement();
                ResultSet   rs=stmt.executeQuery("select *from tblscores where classname like '" + classname + "'");
                return(rs);
            }
        catch(Exception ex)
        {
            return(null);
        }
    }
  
  public boolean saveScores(ArrayList<String> scores,String classname)
  {
      String sql;
      try
      {
          Statement stmt=con.createStatement();
          for(int i=0;i<scores.size();i++)
          {
              sql="insert into tblscores values('" + scores.get(i).toString().split(",")[0] + "','" + classname + "'," + Double.parseDouble(scores.get(i).toString().split(",")[1])+ ")";
              stmt.addBatch(sql);
          }
          stmt.executeBatch();
          return(true);
      }
      catch(Exception ex)
      {
          System.out.println("Error In saving scores ");
          return(false);
      }
  }
  
    public int getRowCount(String classname)
    {
               try
        {
                Statement stmt=con.createStatement();
                ResultSet   rs=stmt.executeQuery("select count(*) from tblscores where classname like '" + classname + "'");
                rs.next();
                int cnt=rs.getInt(1);
                return(cnt);
            }
        catch(Exception ex)
        {
            return(-1);
        }
    }
  public String getTopic(String classname)
  {
      String topicname="";
      try
      {
          Statement stmt=con.createStatement();
          String sql="select topic from tbltopics where classname like '" + classname + "'";
          System.out.println(sql);
          ResultSet rs=stmt.executeQuery(sql);
          rs.next();
          topicname=rs.getString(1);
          rs.close();
          stmt.close();
          return(topicname);
      }
      catch(Exception ex)
      {
          System.out.println(ex);
          return("NA");
      }
  }
  public StringBuilder getEssayBody(String studentid,String classname)
  {
     StringBuilder contents=new StringBuilder();
      try
      {
          Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select essaycontents from tblposts where studentid like '" + studentid + "' and classname like '" + classname + "'");
          rs.next();
          Clob c;
          contents=new StringBuilder(rs.getString(1));
                  rs.close();
          stmt.close();
          return(contents);
      }
      catch(Exception ex)
      {
          return(new StringBuilder("Error"));
      } 
  }
  public ArrayList getStudents(String classname)
  {
      ArrayList students=new ArrayList();
      try
      {
          Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select studentid from tblposts where classname like '" + classname + "'");
          rs.next();
          do
          {
              students.add(rs.getString(1));
          }while(rs.next());
          rs.close();
          stmt.close();
          return(students);
      }
      catch(Exception ex)
      {
          return(students);
      }
  }
  public String getCount(String classname)
  {
   String topicname="";
      try
      {
          Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select count(studentid) from tblposts where classname like '" + classname + "'");
          rs.next();
          topicname=rs.getInt(1)+"";
          rs.close();
          stmt.close();
          return(topicname);
      }
      catch(Exception ex)
      {
          return("NA");
      }   
  }
  public boolean postEssay(String regid,String classname,String topic,StringBuilder contents)
  {
      try
      {
          StringReader sr=new StringReader(contents.toString());
            PreparedStatement pstmt=con.prepareStatement("insert into tblposts values(?,?,?,?)");
           pstmt.setString(1,regid);
           pstmt.setString(2,classname);
           pstmt.setString(3,topic);
           pstmt.setString(4,contents.toString());
            pstmt.executeUpdate();
            return(true);
      }
      catch(Exception ex)
      {
          return(false);
      }
  }
  public StringBuilder getBagwords(String classname,String topic)
  {
      StringBuilder sb=new StringBuilder();
      try
      {
          Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select bagwords from tbltopics where classname like '" + classname + "' and topic like '" + topic + "'");
          rs.next();
          sb.append(rs.getString(1));
          rs.close();
          stmt.close();
          return(sb);
      }
      catch(Exception ex)
      {
          return(new StringBuilder("NA"));
      }
  }
  public boolean insertTopic(String classname,String topic,String bagwords)
  {
       try
       {
           Statement stmt=con.createStatement();
           String insertst="insert into tbltopics values('" + classname + "','" + topic + "','" + bagwords + "')";
           stmt.execute(insertst);
           return(true);
       }
       catch(Exception ex)
       {
           return(false);
       }
  }
  public boolean insertUser(User user)
  {
      try{
          Statement stmt=con.createStatement();
          String insertStr="insert into tblRegister values('"+user.Name+"','"+user.Uname+"','"+user.Pwd+"','"+user.PhoneNo+"')";
          System.out.print(insertStr);
          stmt.execute(insertStr);
          return true;
      }
      catch(Exception ex)
      {
          return false;
      }
  }
  public String getPwd(String Uname)
  {
      try{
          Statement stmt=con.createStatement();
          String cmdStr="select pwd from tblRegister where uname='"+Uname+"'";
          System.out.print(cmdStr);
          ResultSet rs=stmt.executeQuery(cmdStr);
          
          if(rs.next())
              return rs.getString(1);
          return null;
      }
      catch(Exception ex)
      {
          return null;
      }
  }
}
