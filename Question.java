package Questions;

public class Question{
   private int questionId;
   private String question;
   private int type;
   public String option1;
   public String option2;
   public String option3;
   public String option4;
   public String answer;
   public String subject;

   public Question(int qid, String q, int t, String o1, String o2, String o3, String o4, String a, String s){
      questionId = qid;
      question = q;
      type = t;
      option1 = o1;
      option2 = o2;
      option3 = o3;
      option4 = o4;
      answer = a;
      subject = s;
   }

   public void printQuestion(){
      System.out.println(this.question);
   }

   public void printComplete(){
   	System.out.println("Q] "+this.question);
   	System.out.println("\t a)"+this.option1);
   	System.out.println("\t b)"+this.option2);
   	System.out.println("\t c)"+this.option3);
   	System.out.println("\t d)"+this.option4);
   }

   public int getQuestionId(){
   	return questionId;
   }

   public String getQuestion(){
      return this.question;
   }

   public int getType(){
      return this.type;
   }
}