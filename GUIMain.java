import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Users.Users;

class CreateGUI{

	JFrame loginFrame;
	JFrame viewAllFrame;
	JFrame viewQuestionFrame;
	JFrame insertNewFrame;
	JFrame generateFrame;
	Users user = new Users();
	JMenuBar menuBar = new JMenuBar();
	JButton viewMenu;
	JButton insertMenu;
	JButton generateMenu;

	
	CreateGUI(){
		loginFrame = new JFrame("Question Paper Generator");
		loginFrame.setSize(600,400);
		loginFrame.setLayout(new GridLayout(5,1));

		viewAllFrame = new JFrame("Question Paper Generator");
		viewAllFrame.setSize(600,400);
		viewAllFrame.setLayout(new GridLayout(1,1));

		viewQuestionFrame = new JFrame("Question Paper Generator");
		viewQuestionFrame.setSize(600,400);
		viewQuestionFrame.setLayout(new GridLayout(5,1));

		insertNewFrame = new JFrame("Question Paper Generator");
		insertNewFrame.setSize(600,400);
		insertNewFrame.setLayout(new GridLayout(6,1));

		generateFrame = new JFrame("Question Paper Generator");
		generateFrame.setSize(600,300);

		loginFrame.addWindowListener(new WindowAdapter() {
        		public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
        	}        
      	});
		viewAllFrame.addWindowListener(new WindowAdapter() {
        		public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
        	}        
      	});
		viewQuestionFrame.addWindowListener(new WindowAdapter() {
        		public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
        	}        
      	});
		insertNewFrame.addWindowListener(new WindowAdapter() {
        		public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
        	}        
      	});
		generateFrame.addWindowListener(new WindowAdapter() {
        		public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
        	}        
      	});

      	viewMenu = new JButton("View All");
		insertMenu = new JButton("Insert");
		generateMenu = new JButton("Generate Test");

		
		viewMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	viewAll();
         	}
      	});

		insertMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	insertNew(0);
         	}
      	});

		generateMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	generateTest();
         	}
      	});

		menuBar.add(viewMenu);
		menuBar.add(insertMenu);
		menuBar.add(generateMenu);
		
		
		insertNewFrame.setJMenuBar(menuBar);
		generateFrame.setJMenuBar(menuBar);

	}

	void createLoginGUI(){

		loginFrame.setLayout(new GridLayout(5,1));
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

		loginFrame.add(headerLabel);

		usernamePanel.add(usernamelabel);
		usernamePanel.add(userText);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		loginFrame.add(usernamePanel);
		loginFrame.add(passwordPanel);
		buttonPanel.add(loginButton);
		loginFrame.add(buttonPanel);
		loginFrame.add(alertLabel);
		loginFrame.setVisible(true);
	}

	void viewAll(){
		viewAllFrame.getContentPane().removeAll();
		loginFrame.setVisible(false);
		viewQuestionFrame.setVisible(false);
		insertNewFrame.setVisible(false);
		generateFrame.setVisible(false);


		user.getAllEntries();
		int size = user.sql.questions.size();
		// System.out.println(size);

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
		viewAllFrame.add(pane);
		viewAllFrame.setJMenuBar(menuBar);
		viewAllFrame.setVisible(true);
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

		viewQuestionFrame.getContentPane().removeAll();
		loginFrame.setVisible(false);
		viewAllFrame.setVisible(false);
		insertNewFrame.setVisible(false);
		generateFrame.setVisible(false);

		System.out.println(i);
		JLabel fullquestion = new JLabel(Integer.toString(i)+") \t "+user.sql.questions.get(i).getQuestion(), JLabel.CENTER);
		JPanel options = new JPanel();
		JPanel buttonPanel1 = new JPanel();

		int type = user.sql.questions.get(i).getType();
		if (type == 1) {
			options.setLayout(new GridLayout(4,1));
			JLabel option1 = new JLabel(" A) "+user.sql.questions.get(i).option1 + " ", JLabel.CENTER);
			JLabel option2 = new JLabel(" B) "+user.sql.questions.get(i).option2 + " ", JLabel.CENTER);
			JLabel option3 = new JLabel(" C) "+user.sql.questions.get(i).option3 + " ", JLabel.CENTER);
			JLabel option4 = new JLabel(" D) "+user.sql.questions.get(i).option4 + " ", JLabel.CENTER);
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
				viewAll();
         	}
      	});
		

		viewQuestionFrame.add(fullquestion);
		viewQuestionFrame.add(options);
		viewQuestionFrame.add(answer);
		viewQuestionFrame.add(subject);
		buttonPanel1.add(deleteButton);
		viewQuestionFrame.add(buttonPanel1);

		viewQuestionFrame.setJMenuBar(menuBar);
		viewQuestionFrame.setVisible(true);

	}

	JTextField questionText;
	JTextField answerText;
	JTextField subjectText;
	JTextField[] optionText = new JTextField[4];

	void insertNew(int typeCount){

		insertNewFrame.getContentPane().removeAll();
		loginFrame.setVisible(false);
		viewQuestionFrame.setVisible(false);
		viewAllFrame.setVisible(false);
		generateFrame.setVisible(false);

		JPanel typeComboPanel = new JPanel();
		typeComboPanel.setLayout(new FlowLayout());

		DefaultComboBoxModel type = new DefaultComboBoxModel();
		type.addElement("MCQ");
		type.addElement("Fill in the blank");
		type.addElement("True or False");
		JComboBox  typeComboBox= new JComboBox(type);    
      	typeComboBox.setSelectedIndex(typeCount);

      	// JScrollPane typeScrollPane = new JScrollPane(typeComboBox);		

		JButton selectButton = new JButton("Select");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeComboBox.getSelectedIndex() != -1) {                     
					insertNew(typeComboBox.getSelectedIndex());             
				}
			}
		});


		typeComboPanel.add(typeComboBox);
		typeComboPanel.add(selectButton);
		

		JLabel questionlabel = new JLabel("Question: ", JLabel.RIGHT);
		questionText = new JTextField(40);
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(new FlowLayout());
		questionPanel.add(questionlabel);
		questionPanel.add(questionText);

		JLabel answerLabel = new JLabel("Answer: ", JLabel.RIGHT);
		answerText = new JTextField(15);
		JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new FlowLayout());
		answerPanel.add(answerLabel);
		answerPanel.add(answerText);

		// System.out.println(typeCount);
		JPanel optionsPanel = new JPanel();
		if (typeCount == 0) {

			optionsPanel.setLayout(new GridLayout(2,1));
			JLabel[] optionLabel = new JLabel[4];
			JPanel[] optionPanel = new JPanel[4];
			JPanel optionPanelA = new JPanel();
			JPanel optionPanelB = new JPanel();
			optionPanelA.setLayout(new FlowLayout());
			optionPanelB.setLayout(new FlowLayout());
			for (int i = 0; i < 4; i++) {
				optionLabel[i] = new JLabel("option "+Integer.toString(i+1)+" : ", JLabel.RIGHT);
				optionText[i] = new JTextField(15);
				optionPanel[i] = new JPanel();
				optionPanel[i].setLayout(new FlowLayout());
				optionPanel[i].add(optionLabel[i]);
				optionPanel[i].add(optionText[i]);
			}
			optionPanelA.add(optionPanel[0]);
			optionPanelA.add(optionPanel[1]);
			optionPanelB.add(optionPanel[2]);
			optionPanelB.add(optionPanel[3]);
			optionsPanel.add(optionPanelA);
			optionsPanel.add(optionPanelB);
		}

		JLabel subjectLabel = new JLabel("Subject: ", JLabel.RIGHT);
		subjectText = new JTextField(15);
		JPanel subjectPanel = new JPanel();
		subjectPanel.setLayout(new FlowLayout());
		subjectPanel.add(subjectLabel);
		subjectPanel.add(subjectText);

		JPanel submitButtonPanel = new JPanel();
		JButton submitButton = new JButton("Submit");

		submitButton.addActionListener(new OnSubmit(typeCount+1));

		submitButtonPanel.add(submitButton);

		insertNewFrame.add(typeComboPanel);
		insertNewFrame.add(questionPanel);
		insertNewFrame.add(answerPanel);
		insertNewFrame.add(optionsPanel);
		insertNewFrame.add(subjectPanel);
		insertNewFrame.add(submitButtonPanel);
		
		insertNewFrame.setJMenuBar(menuBar);
		insertNewFrame.setVisible(true);

	}

	class OnSubmit implements ActionListener{
		int typeEntry;
		OnSubmit(int i){
			typeEntry = i;
		}
		public void actionPerformed(ActionEvent e){
        	String questionEntry = questionText.getText();
        	String answerEntry = answerText.getText();
        	String subjectEntry = subjectText.getText();
        	String[] optionEntry = new String[4];
        	if (typeEntry == 1) {
        		for (int i = 0; i < 4; i++) {
        			optionEntry[i] = optionText[i].getText();
        		}
        		try{
        			user.insertNew(questionEntry, typeEntry, optionEntry[0], optionEntry[1], optionEntry[2], optionEntry[3], answerEntry, subjectEntry);
        		}catch(Exception ex){
        			System.out.println("Error in inserting");
        		}
        	}
        	else{
        		try{
        			user.insertNew(questionEntry, typeEntry, answerEntry, subjectEntry);
        		}catch(Exception ex){
        			System.out.println("Error in inserting");
        		}
        	}
        	viewAll();
		}
	}



	void generateTest(){

	}
}

class GUIMain{
	public static void main(String[] args) {
		CreateGUI obj = new CreateGUI();
		obj.createLoginGUI();
	}	
}