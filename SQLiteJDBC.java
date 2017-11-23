package SQLite;

import java.sql.*;
import java.util.*;
import Questions.Question;


public class SQLiteJDBC {

   private Connection conn = null;
   private Statement statement = null;
   public Vector<Question> questions = new Vector<Question> (20);

   public SQLiteJDBC() {      
      try {
         Class.forName("org.sqlite.JDBC");
         this.conn = DriverManager.getConnection("jdbc:sqlite:test.db");
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }

   public void selectQuestions(){
      
      try{
         statement = conn.createStatement();
         ResultSet result = statement.executeQuery( "SELECT * FROM questions;" );

         while(result.next()){
            questions.addElement(new Question(result.getInt("qid"),result.getString("question"),result.getInt("type"),result.getString("option1"),result.getString("option2"),result.getString("option3"),result.getString("option4"),result.getString("answer"),result.getString("subject")));
         }
         // System.out.println("vector size : "+questions.size());
         // System.out.println("vetor capacity : "+questions.capacity());
         // questions.get(0).printQuestion();

      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.out.println("No questions.");
         System.exit(0);
      }

      // System.out.println("Operation done successfully");
   }

   public void deleteQuestion(int qid){
      try{
         statement = conn.createStatement();
         statement.executeQuery( "DELETE FROM questions where qid = "+ qid +";" );
         conn.commit();
      }
      catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         // System.exit(0);
         return;
      }
   }

   public void insertQuestion(String question, int type, String option1, String option2, String option3, String option4, String answer, String subject){
      try{
         statement = conn.createStatement();
         String sql = "INSERT INTO questions (question,type,option1,option2,option3,option4,answer,subject) " +
                  "VALUES ('"+question+"', "+type+", '"+option1+"', '"+option2+"','"+option3+"','"+option4+"','"+answer+"','"+subject+"');"; 
         // System.out.println(sql);
         statement.executeQuery(sql);
         conn.commit();
      }
      catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         // System.exit(0);
      }
   }

   public int authenticate(String userName, String enteredPassword){
      String password = null;
      try{
         statement = conn.createStatement();
         String sql = "SELECT * FROM users WHERE name = '"+userName+"';";
         ResultSet result = statement.executeQuery(sql);
         password = result.getString("password");
         
      }
      catch(Exception e){
         // System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return 0;
      }
      if (password.equals(enteredPassword)) {
         return 1;
      }
      else{
         return 0;
      }
   
   }

   // public static void main(String[] args) {
   //    SQLiteJDBC obj = new SQLiteJDBC();
   //    // obj.selectQuestions();
   //    // System.out.println("auth result : "+obj.authenticate("yashdeep","234"));
   //    obj.insertQuestion("john snow knows everything",1,"a","b","c","d","false","GOT");
   // }
}