package quizapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.ArrayList;


public class QuizModule extends javax.swing.JFrame {
    
    private static Object[][] resultSetToArray(ResultSet resultSet) throws SQLException {
    ArrayList<Object[]> rows = new ArrayList<>();

    while (resultSet.next()) {
        String question = resultSet.getString("question");
        String answer1 = resultSet.getString("answer1");
        String answer2 = resultSet.getString("answer2");
        String answer3 = resultSet.getString("answer3");
        String correctAnswer = resultSet.getString("correct_answer");

        String[] row = {question, answer1, answer2, answer3, correctAnswer};
        rows.add(row);
    }

    // Convert the list of rows to a 2D array
    int rowCount = rows.size();
    int columnCount = 5; // Assuming 5 columns in each row
    String[][] resultArray = new String[rowCount][columnCount];
    for (int i = 0; i < rowCount; i++) {
        resultArray[i] = (String[]) rows.get(i);
    }
    return resultArray;
}
    
private static String[] extractCorrectAnswers(String[][] result) throws SQLException {
        ArrayList<String> correctAnswers = new ArrayList<>();
        for(int i=0;i<result.length;i++){
            String correctAnswer = result[i][4];
            correctAnswers.add(correctAnswer);
        }
        // Convert the list of correct answers to a 1D array of strings
        String[] correctAnswersArray = correctAnswers.toArray(new String[correctAnswers.size()]);
        return correctAnswersArray;
    }
    
    
    
String[][] questionsAndAnswers = null;
String[] correctAnswers = null;
int currentpanel=0;
String[] selectedAnswers=null;
String quiz_id,student_id;
int markpq=0;
StudentDashboard j1=null;
    public QuizModule(String quiz_id,String student_id,String time_minutes,int markpq,StudentDashboard j1) {
        setUndecorated(true);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        this.quiz_id=quiz_id;
        this.student_id=student_id;
        this.markpq=markpq;
        this.j1=j1;
        j1.setVisible(false);
               
        ///////////////////
        try {
        Connection conn = DBConnection.getConnection();
        String querySql = "select * from q_and_a where quiz_id=?";
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1, quiz_id);
        ResultSet questionrs=pstmt.executeQuery();
        questionsAndAnswers=(String[][]) resultSetToArray(questionrs); 
        correctAnswers=extractCorrectAnswers(questionsAndAnswers);
        for(int i=0;i<correctAnswers.length;i++){
            System.out.println(correctAnswers[i]);
        }
        querySql = "select subject,name from teacher_profile where teacher_id=(select teacher_id from quiz where quiz_id=?)";
        pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1, quiz_id);
        ResultSet t_info=pstmt.executeQuery();
        t_info.next();
        String subject=t_info.getString("subject");
        String t_name=t_info.getString("name");
        subject_title.setText(subject);
        subject_teacher_name.setText(t_name);
   }catch (Exception e) {
        System.out.println(e);
    }
    selectedAnswers=new String[questionsAndAnswers.length];
    unanswered_label.setText(Integer.toString(questionsAndAnswers.length));
    answered_label.setText("0");
    generateQuestionPanels();
    add_legend_buttons();
    updateTime(Integer.parseInt(time_minutes));
    }


    @SuppressWarnings("unchecked")
    
    ///////////////////////////////////////////////////
    ////////ONLY FOR SHUFFLING ANSWERS////////////////
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    //////////////////////////////////////////////////////
    
    
    private void generateQuestionPanels(){
        int len=questionsAndAnswers.length;
        for(int i=0;i<len;i++){
            javax.swing.JPanel m1=new javax.swing.JPanel();
            m1.setBackground(Color.white);
            //////////////////////////////////////////////////
            ///////////CODE FOR SHUFFLING ANSWERS/////////////
                            int[] numbers = {1, 2, 3, 4};
                        Random random = new Random();

                        for (int z = numbers.length - 1; z > 0; z--) {
                            int j = random.nextInt(z + 1);
                            swap(numbers, z, j);
                        }
        /////////////////
            JLabel questionLabel = new JLabel(questionsAndAnswers[i][0]);
        JRadioButton radioButton1 = new JRadioButton(questionsAndAnswers[i][numbers[0]]);
        JRadioButton radioButton2 = new JRadioButton(questionsAndAnswers[i][numbers[1]]);
        JRadioButton radioButton3 = new JRadioButton(questionsAndAnswers[i][numbers[2]]);
        JRadioButton radioButton4 = new JRadioButton(questionsAndAnswers[i][numbers[3]]);

        // Set the font and alignment of the labels
      
        questionLabel.setFont(new Font("Serif", Font.BOLD, 18));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
     // Create a button group and add the radio buttons to it
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);

        // Set the layout manager for the panel
        m1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        m1.add(questionLabel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        m1.add(radioButton1, c);
        c.gridx = 0;
        c.gridy = 2;
        m1.add(radioButton2, c);
        c.gridx = 0;
        c.gridy = 3;
        m1.add(radioButton3, c);
        c.gridx = 0;
        c.gridy = 4;
        m1.add(radioButton4, c);
       
            ///////////////////////////////////
        question_panel.add(m1);
        }
    }
    
    public void add_legend_buttons() {
    question_legend.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 
    for (int i = 0; i < questionsAndAnswers.length; i++) {
        final int val=i;
        JButton button = new JButton(Integer.toString(i+1));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goto_legend(val);
            }
        });
        button.setPreferredSize(new Dimension(50, 50));
        question_legend.add(button);
    }
}
    
    Timer timer;
    public void updateTime(int minutes) {
        timer = new Timer(1000, new ActionListener() {
            int seconds=minutes*60;
            public void actionPerformed(ActionEvent e) {
                if(seconds==0){
                    int score=validate_answers();
                    int result = JOptionPane.showConfirmDialog(null, "You got "+Integer.toString(score)+" marks","Marks", JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
                else{
                String formattedTime = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
                time_count.setText(formattedTime);
                seconds--;
                }
            }
        });
        timer.start();
}
public void get_selected_answer(){
    Component[] components = question_panel.getComponents();
        javax.swing.JPanel x=(javax.swing.JPanel) components[currentpanel];
        Component[] answer_components = x.getComponents();
        for(int i=1;i<=4;i++){
            javax.swing.JRadioButton y=(javax.swing.JRadioButton) answer_components[i];
            if(y.isSelected()){
                selectedAnswers[currentpanel]=y.getText();
                break;
            }
        }
        unanswered_label.setText(Integer.toString(Integer.parseInt(unanswered_label.getText())-1));
        answered_label.setText(Integer.toString(Integer.parseInt(answered_label.getText())+1));
}

public void goto_legend(int i){
    Component[] components = question_panel.getComponents();
    components[currentpanel].setVisible(false);
    components[i].setVisible(true);
    currentpanel=i;
}    
public int validate_answers(){
    int count = 0;
    for (int i = 0; i < selectedAnswers.length; i++) {
        for (int j = 0; j < correctAnswers.length; j++) {
            if (selectedAnswers[i] == correctAnswers[j]) {
                count++;
                break;
            }
        }
    }
    timer.stop();
    return count*markpq;
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        subject_title = new javax.swing.JLabel();
        time_count = new javax.swing.JLabel();
        time_count1 = new javax.swing.JLabel();
        teacher_placeholder = new javax.swing.JLabel();
        subject_teacher_name = new javax.swing.JLabel();
        question_legend = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        unanswered_label = new javax.swing.JLabel();
        answered_label = new javax.swing.JLabel();
        question_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(234, 234, 243));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));

        subject_title.setBackground(new java.awt.Color(0, 0, 0));
        subject_title.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        subject_title.setForeground(new java.awt.Color(0, 48, 73));
        subject_title.setText("Computer Organisation and Fundamentals");

        time_count.setBackground(new java.awt.Color(0, 0, 0));
        time_count.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        time_count.setForeground(new java.awt.Color(193, 18, 31));
        time_count.setText("XX:XX:XX");

        time_count1.setBackground(new java.awt.Color(0, 0, 0));
        time_count1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        time_count1.setText("Remaining Time:");

        teacher_placeholder.setBackground(new java.awt.Color(0, 0, 0));
        teacher_placeholder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        teacher_placeholder.setForeground(new java.awt.Color(0, 48, 73));
        teacher_placeholder.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        teacher_placeholder.setText("Teacher :");

        subject_teacher_name.setBackground(new java.awt.Color(0, 0, 0));
        subject_teacher_name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        subject_teacher_name.setForeground(new java.awt.Color(0, 48, 73));
        subject_teacher_name.setText("XXXXXX");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(subject_title, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addComponent(time_count1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time_count, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(teacher_placeholder, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subject_teacher_name, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subject_title)
                    .addComponent(time_count)
                    .addComponent(time_count1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacher_placeholder)
                    .addComponent(subject_teacher_name))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        question_legend.setBackground(new java.awt.Color(255, 255, 255));
        question_legend.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(53, 53, 53), 1, true));
        question_legend.setPreferredSize(new java.awt.Dimension(310, 230));

        javax.swing.GroupLayout question_legendLayout = new javax.swing.GroupLayout(question_legend);
        question_legend.setLayout(question_legendLayout);
        question_legendLayout.setHorizontalGroup(
            question_legendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );
        question_legendLayout.setVerticalGroup(
            question_legendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(question_legend, java.awt.BorderLayout.LINE_END);

        footer.setBackground(new java.awt.Color(29, 53, 87));
        footer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));

        jButton30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton30.setText("Submit Test");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton31.setText("Next Question");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton32.setText("Mark for Review");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Unanswered  :");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Answered      :");

        unanswered_label.setBackground(new java.awt.Color(0, 0, 0));
        unanswered_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        unanswered_label.setForeground(new java.awt.Color(193, 18, 31));
        unanswered_label.setText("XX");

        answered_label.setBackground(new java.awt.Color(0, 0, 0));
        answered_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        answered_label.setForeground(new java.awt.Color(0, 255, 0));
        answered_label.setText("XX");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(footerLayout.createSequentialGroup()
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unanswered_label)
                            .addComponent(answered_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(footerLayout.createSequentialGroup()
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(unanswered_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(answered_label))
                        .addGap(89, 89, 89))
                    .addGroup(footerLayout.createSequentialGroup()
                        .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(footerLayout.createSequentialGroup()
                                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))))
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        question_panel.setBackground(new java.awt.Color(255, 255, 255));
        question_panel.setLayout(new java.awt.CardLayout());
        getContentPane().add(question_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        get_selected_answer();
        Component[] components = question_panel.getComponents();
        Component[] legend_components = question_legend.getComponents();
        if(selectedAnswers[currentpanel]!=null){
           legend_components[currentpanel].setBackground(new java.awt.Color(82, 183, 136));
        }
        if(currentpanel==questionsAndAnswers.length-1){
            components[currentpanel].setVisible(false);
            currentpanel=0;
            components[currentpanel].setVisible(true);
        }
        else{
        components[currentpanel].setVisible(false);
        components[++currentpanel].setVisible(true);
        }

    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        get_selected_answer();
        Component[] components = question_panel.getComponents();
        Component[] legend_components = question_legend.getComponents();
        if(selectedAnswers[currentpanel]!=null){
           legend_components[currentpanel].setBackground(new java.awt.Color(255, 209, 102));
        }
        if(currentpanel==questionsAndAnswers.length-1){
            components[currentpanel].setVisible(false);
            currentpanel=0;
            components[currentpanel].setVisible(true);
        }
        else{
        components[currentpanel].setVisible(false);
        components[++currentpanel].setVisible(true);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        int score=validate_answers();
        Connection conn=DBConnection.getConnection();
        try{
            PreparedStatement prep=conn.prepareStatement("insert into score values(?,?,?,?)");
            prep.setString(1, student_id);
            prep.setString(2,quiz_id);
            prep.setString(3,Integer.toString(score));
            prep.setInt(4,(correctAnswers.length*markpq));
            prep.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        dispose();
        j1.quiz_finish(score, correctAnswers.length*markpq);
        j1.setVisible(true);
    }//GEN-LAST:event_jButton30ActionPerformed


    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel answered_label;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel question_legend;
    private javax.swing.JPanel question_panel;
    private javax.swing.JLabel subject_teacher_name;
    private javax.swing.JLabel subject_title;
    private javax.swing.JLabel teacher_placeholder;
    private javax.swing.JLabel time_count;
    private javax.swing.JLabel time_count1;
    private javax.swing.JLabel unanswered_label;
    // End of variables declaration//GEN-END:variables
}
