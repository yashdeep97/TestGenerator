package Users;

import SQLite.SQLiteJDBC;
import Questions.Question;
import java.util.Collections;
import java.util.Vector;

public class Users{

	public SQLiteJDBC sql = new SQLiteJDBC();

	public int login(String userName,String password){
		return sql.authenticate(userName,password);
	}

	public void getAllEntries(){
		sql.selectQuestions();
		// System.out.println(sql.questions.size());
		// for (int i = 0; i<sql.questions.size() ; i++) {
			// sql.questions.get(i).printQuestion();
		// }

	}

	public Question viewQuestion(int i){
		return sql.questions.get(i);
	}

	public void insertNew(String question, int type, String option1, String option2, String option3, String option4, String answer, String subject){
		sql.insertQuestion(question,type,option1,option2,option3,option4,answer,subject);
	}
	public void insertNew(String question, int type, String answer, String subject){
		sql.insertQuestion(question,type,null,null,null,null,answer,subject);
	}

	public void delete(int i){
		sql.deleteQuestion(sql.questions.get(i).getQuestionId());
		return;
		// System.out.println(sql.questions.get(i).getQuestionId());
	}

	public void generateTest(int quantity){
		Collections.shuffle(sql.questions);
		Question testQuestions[] = new Question[quantity];

		for (int i = 0; i<quantity ; i++) {
			testQuestions[i] = sql.questions.get(i);
			// testQuestions[i].printQuestion();
		}
	} 

// 	public static void main(String[] args) {
// 		Users user = new Users();
// 		System.out.println(user.login("yashdeep","1234"));

// 		user.getAllEntries();

// 		Question q = user.viewQuestion(2);
// 		q.printComplete();

// 		user.generateTest(3);
// 		// user.delete(4);

// 		// user.insertNew("john snow knows everything",1,,"false","GOT");
// 	}
}