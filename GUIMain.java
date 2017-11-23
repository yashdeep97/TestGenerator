import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Users.Users;

public class GUIMain{
	private JFrame mainFrame;
	Users user = new Users();
	JMenuBar menuBar = new JMenuBar();
	
	GUIMain(){
		createGUI();
	}

	public static void main(String[] args) {
		GUIMain obj = new GUIMain();
		// obj.createLoginGUI();
	}

	void createGUI(){
		mainFrame = new JFrame("Question Paper Generator");
		mainFrame.setSize(600,300);
		mainFrame.setLayout(new GridLayout(5,1));
		mainFrame.addWindowListener(new WindowAdapter() {
        		public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
        	}        
      	});
      	createLoginGUI();
		mainFrame.setVisible(true);

	}

	void createLoginGUI(){
		JLabel headerLabel;
		JLabel alertLabel;
		JPanel usernamePanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		usernamePanel.setLayout(new FlowLayout());
		passwordPanel.setLayout(new FlowLayout());


		headerLabel = new JLabel("Login",JLabel.CENTER);
		alertLabel = new JLabel("",JLabel.CENTER);

		JLabel usernamelabel = new JLabel("Username: ", JLabel.RIGHT);
		JLabel passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
		JTextField userText = new JTextField(10);
      	JPasswordField passwordText = new JPasswordField(10);
		JButton loginButton = new JButton("Login");

		loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {     
            	String userEntry = userText.getText();
            	String passEntry = passwordText.getText(); 
            	if(user.login(userEntry,passEntry) == 1){
            		alertLabel.setText("Logged in");
            		viewAll();
            	}
            	else{
            		alertLabel.setText("Incorrect Username or Password");
            	}      
         	}
      	});

		mainFrame.add(headerLabel);

		usernamePanel.add(usernamelabel);
		usernamePanel.add(userText);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		mainFrame.add(usernamePanel);
		mainFrame.add(passwordPanel);
		buttonPanel.add(loginButton);
		mainFrame.add(buttonPanel);
		mainFrame.add(alertLabel);
	}

	void viewAll(){

		JMenu viewMenu = new JMenu("View");
		viewMenu.setActionCommand("view");

		JMenu insertMenu = new JMenu("Insert");
		insertMenu.setActionCommand("insert");

		JMenu generateMenu = new JMenu("Generate Test");
		generateMenu.setActionCommand("generate");

		MenuItemListener menuItemListener = new MenuItemListener();
		viewMenu.addActionListener(menuItemListener);
		insertMenu.addActionListener(menuItemListener);
		generateMenu.addActionListener(menuItemListener);

		mainFrame.repaint();
		mainFrame.getContentPane().removeAll();

		menuBar.add(viewMenu);
		menuBar.add(insertMenu);
		menuBar.add(generateMenu);
		mainFrame.setJMenuBar(menuBar);

		user.getAllEntries();
		int size = user.sql.questions.size();

		JPanel[] allQuestionPanels = new JPanel[size];
		JButton[] allQuestionButtons = new JButton[size];
		JPanel allQuestions = new JPanel();
		allQuestions.setLayout(new GridLayout(size,1));

		for (int i = 0; i<size; i++) {
			allQuestionPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			allQuestionButtons[i] = new JButton(Integer.toString(i)+") \t "+user.sql.questions.get(i).getQuestion());
			allQuestionButtons[i].addActionListener(new OnClick(i));
			allQuestionPanels[i].add(allQuestionButtons[i]);
			
			allQuestions.add(allQuestionPanels[i]);
		}

		
		JScrollPane pane = new JScrollPane(allQuestions);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		mainFrame.setLayout(new GridLayout(1,1));
		mainFrame.add(pane);
	}

	class MenuItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();

			 if(command.equals("insert")){
			 	insertNew();
			}else if(command.equals("generate")){
				generateTest();
			}else{
				viewAll();
			}
		}
	}

	class OnClick implements ActionListener{
		
		int index;
		OnClick(int i){
			index = i;
		}
		public void actionPerformed(ActionEvent e){
			viewQuestion(index);
		}

	}


	void viewQuestion(int i){
		System.out.println(i);
		JLabel fullquestion = new JLabel(Integer.toString(i)+") \t "+user.sql.questions.get(i).getQuestion(), JLabel.CENTER);
		JPanel options = new JPanel();
		JPanel buttonPanel1 = new JPanel();

		int type = user.sql.questions.get(i).getType();
		if (type == 1) {
			options.setLayout(new GridLayout(4,1));
			JLabel option1 = new JLabel(" A) "+user.sql.questions.get(i).option1 + " ", JLabel.CENTER);
			JLabel option2 = new JLabel(" B) "+user.sql.questions.get(i).option1 + " ", JLabel.CENTER);
			JLabel option3 = new JLabel(" C) "+user.sql.questions.get(i).option1 + " ", JLabel.CENTER);
			JLabel option4 = new JLabel(" D) "+user.sql.questions.get(i).option1 + " ", JLabel.CENTER);
			options.add(option1);
			options.add(option2);
			options.add(option3);
			options.add(option4);
		}
		else if(type == 3){
			JLabel tf = new JLabel("true / false", JLabel.CENTER);
			options.add(tf);
		}
		JLabel answer = new JLabel("Answer : " + user.sql.questions.get(i).answer, JLabel.CENTER);
		JLabel subject = new JLabel("Subject : " + user.sql.questions.get(i).subject, JLabel.CENTER);

		JButton deleteButton = new JButton("DELETE");

		deleteButton.addActionListener(new ActionListener() {

        	public void actionPerformed(ActionEvent e) {     
				user.delete(i);
				// viewAll();
         	}
      	});

		mainFrame.repaint();
		mainFrame.getContentPane().removeAll();
		mainFrame.setLayout(new GridLayout(5,1));

		mainFrame.add(fullquestion);
		mainFrame.add(options);
		mainFrame.add(answer);
		mainFrame.add(subject);
		buttonPanel1.add(deleteButton);
		mainFrame.add(buttonPanel1);
		mainFrame.setVisible(true);

	}

	void insertNew(){


	}

	void generateTest(){

	}
}