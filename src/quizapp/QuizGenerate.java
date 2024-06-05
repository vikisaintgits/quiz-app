package quizapp;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class QuizGenerate extends javax.swing.JFrame {
    String[] details;
    String teacherid;
    String quizid;
    TeacherDashboard oldframe;
    
   String getCreationDate(){
       LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
   } 
    
   
    public QuizGenerate(String details[],String teacherid,TeacherDashboard x) {
        x.setVisible(false);
        oldframe=x;
        this.teacherid=teacherid;
        this.details=details;
        initComponents();
        setResizable(false);
        ///////////////////
        String insertSql = "INSERT INTO quiz(teacher_id,class,title,q_desc,markpq,minutes,created_on) VALUES (?,?,?,?,?,?,?)";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,teacherid);
        pstmt.setString(2,details[0]);
        pstmt.setString(3,details[1]);
        pstmt.setString(4,details[2]);
        pstmt.setString(5,details[3]);
        pstmt.setString(6,details[4]);
        pstmt.setString(7,getCreationDate());
        
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if(rs.next())
        {
            quizid=Integer.toString(rs.getInt(1));
        }
        
        quiz_id_label_top.setText("quiz Id:"+quizid);
    } catch (Exception e) {
        System.out.println(e);
    }
        //////////////////
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        title_caption = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        quiz_id_label_top = new javax.swing.JLabel();
        generate_quiz_panel = new javax.swing.JPanel();
        title_caption4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        question_box = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        correct_op = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        op2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        op1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        op3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(29, 53, 87));

        title.setFont(new java.awt.Font("Cairo Play", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("EduQuiz");

        title_caption.setFont(new java.awt.Font("Raleway", 2, 12)); // NOI18N
        title_caption.setForeground(new java.awt.Color(255, 255, 255));
        title_caption.setText("Quiz Generation");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_caption, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title))
                .addContainerGap(580, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(title_caption)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        main_panel.setLayout(new java.awt.BorderLayout());

        navbar.setBackground(new java.awt.Color(189, 187, 214));
        navbar.setLayout(new java.awt.GridLayout(1, 0));

        quiz_id_label_top.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        quiz_id_label_top.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quiz_id_label_top.setText("quiz Id:0001");
        navbar.add(quiz_id_label_top);

        main_panel.add(navbar, java.awt.BorderLayout.PAGE_START);

        generate_quiz_panel.setBackground(new java.awt.Color(255, 255, 255));

        title_caption4.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption4.setForeground(new java.awt.Color(29, 53, 87));
        title_caption4.setText("Add Questions");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Question :");

        jButton5.setBackground(new java.awt.Color(69, 123, 157));
        jButton5.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add Question");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        question_box.setColumns(20);
        question_box.setRows(5);
        jScrollPane1.setViewportView(question_box);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Correct Option :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Option 2      :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Option 1         :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Option 3      :");

        jButton6.setBackground(new java.awt.Color(42, 157, 143));
        jButton6.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Generate Quiz");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout generate_quiz_panelLayout = new javax.swing.GroupLayout(generate_quiz_panel);
        generate_quiz_panel.setLayout(generate_quiz_panelLayout);
        generate_quiz_panelLayout.setHorizontalGroup(
            generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                        .addComponent(title_caption4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                        .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generate_quiz_panelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(correct_op)
                                    .addComponent(op1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(op3))
                                    .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(op2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 43, Short.MAX_VALUE)))
                        .addGap(42, 42, 42))))
        );
        generate_quiz_panelLayout.setVerticalGroup(
            generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generate_quiz_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(title_caption4)
                .addGap(33, 33, 33)
                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(correct_op, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(op2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(op1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(op3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(generate_quiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        main_panel.add(generate_quiz_panel, java.awt.BorderLayout.CENTER);

        getContentPane().add(main_panel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!jButton6.isEnabled()){
            jButton6.setEnabled(true);
        }
        String insertSql = "INSERT INTO q_and_a (question,answer1,answer2,answer3,correct_answer,quiz_id) VALUES (?,?,?,?,?,?)";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(insertSql);
        pstmt.setString(1,question_box.getText());
        pstmt.setString(2,op1.getText());
        pstmt.setString(3,op2.getText());
        pstmt.setString(4,op3.getText());
        pstmt.setString(5,correct_op.getText());
        pstmt.setString(6,quizid);
        pstmt.executeUpdate();
        
        op1.setText("");
        op2.setText("");
        op3.setText("");
        correct_op.setText("");
        question_box.setText("");

    } catch (Exception e) {
        System.out.println(e);
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
        oldframe.BackOn();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField correct_op;
    private javax.swing.JPanel generate_quiz_panel;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel navbar;
    private javax.swing.JTextField op1;
    private javax.swing.JTextField op2;
    private javax.swing.JTextField op3;
    private javax.swing.JTextArea question_box;
    private javax.swing.JLabel quiz_id_label_top;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title_caption;
    private javax.swing.JLabel title_caption4;
    // End of variables declaration//GEN-END:variables
}
