package quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class StudentDashboard extends javax.swing.JFrame {
    ResultSet student_info;
    public void populate_ongoing_quiz(){
    try {
        String querySql = "select * from quiz q where class=? and quiz_id not in (select quiz_id from score where student_id=?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1,student_info.getString("class"));
        pstmt.setString(2,student_info.getString("student_id"));
        ResultSet rs=pstmt.executeQuery();
        DefaultTableModel ongoing_quiz_table_model=(DefaultTableModel)ongoing_quiz_table.getModel();
        ongoing_quiz_table_model.setRowCount(0);
        while(rs.next()){
            String tbData[]={rs.getString("quiz_id"),rs.getString("title"),rs.getString("q_desc"),rs.getString("markpq"),rs.getString("minutes")};
            ongoing_quiz_table_model.addRow(tbData);
        }
    }catch (Exception e) {
        System.out.println(e);
    }
    }
    int index_finder_for_setting_profile_class(String x){
        if("1st Yr MCA".equals(x))
            return 0;
        else if("2nd Yr MCA".equals(x))
            return 1;
        else if("1st Yr CSE".equals(x))
            return 2;
        else if("2nd Yr MCA".equals(x))
            return 3;
        else if("3rd Yr CSE".equals(x))
            return 4;
        else if("4th Yr CSE".equals(x))
            return 5;
        else
            return 0;
    }
    void populate_student_profile(){
        try{
        p1.setText(student_info.getString("name"));
        p2.setSelectedIndex(index_finder_for_setting_profile_class(student_info.getString("class")));
        p3.setText(student_info.getString("email"));
        p4.setText(student_info.getString("password"));
        }
        catch(Exception e){
            System.out.println(e);}
    }
    
    void populate_progress_table(){
        try {
        String querySql = "select * from score sc,quiz q where sc.quiz_id=q.quiz_id and sc.student_id=?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1,student_info.getString("student_id"));
        ResultSet rs=pstmt.executeQuery();
        DefaultTableModel progress_table_model=(DefaultTableModel)progress_table.getModel();
        progress_table_model.setRowCount(0);
        while(rs.next()){
            String tbData[]={rs.getString("title"),rs.getString("q_desc"),rs.getString("score"),rs.getString("total_mark")};
            progress_table_model.addRow(tbData);
        }
    }catch (Exception e) {
        System.out.println(e);
    }
    }

    public StudentDashboard(String id) {
        initComponents();
        setResizable(false);
        score_display.setVisible(false);
        
        //TABLE DIMENSIONS SETTING///
        ongoing_quiz_table.getColumnModel().getColumn(0).setPreferredWidth(2);
        ongoing_quiz_table.getColumnModel().getColumn(1).setPreferredWidth(100);
        ongoing_quiz_table.getColumnModel().getColumn(2).setPreferredWidth(300);
        ongoing_quiz_table.getColumnModel().getColumn(3).setPreferredWidth(5);
        ongoing_quiz_table.getColumnModel().getColumn(4).setPreferredWidth(2);
        //TABLE DIMENSIONS SETTING///
        
        String querySql = "select * from student_profile where student_id=?";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1, id);
        student_info=pstmt.executeQuery();
        student_info.next();
        student_name_label.setText(student_info.getString("name"));
    }catch (Exception e) {
        System.out.println(e);
    }
    populate_ongoing_quiz();
    }

    
public void quiz_finish(int score,int totalscore){
    populate_ongoing_quiz();
    home.setVisible(false);
    profile_panel.setVisible(false);
    progress_panel.setVisible(false);
    score_label.setText(score+"/"+totalscore);
    score_display.setVisible(true);
}    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        title_caption = new javax.swing.JLabel();
        student_name_label = new javax.swing.JLabel();
        sign_out_btn = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        content_panel = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ongoing_quiz_table = new javax.swing.JTable();
        title_caption9 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        profile_panel = new javax.swing.JPanel();
        title_caption11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        p1 = new javax.swing.JTextField();
        p3 = new javax.swing.JTextField();
        p4 = new javax.swing.JPasswordField();
        p2 = new javax.swing.JComboBox<>();
        p_btn1 = new javax.swing.JButton();
        p_btn2 = new javax.swing.JButton();
        progress_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        progress_table = new javax.swing.JTable();
        title_caption10 = new javax.swing.JLabel();
        score_display = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        score_label = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(29, 53, 87));

        title.setFont(new java.awt.Font("Cairo Play", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("EduQuiz");

        title_caption.setFont(new java.awt.Font("Raleway", 2, 12)); // NOI18N
        title_caption.setForeground(new java.awt.Color(255, 255, 255));
        title_caption.setText("Student Dashboard");

        student_name_label.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        student_name_label.setForeground(new java.awt.Color(255, 255, 255));
        student_name_label.setText("Jude Antony");

        sign_out_btn.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        sign_out_btn.setForeground(new java.awt.Color(255, 204, 204));
        sign_out_btn.setText("Sign Out");
        sign_out_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sign_out_btnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(student_name_label))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(title_caption, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 515, Short.MAX_VALUE)
                        .addComponent(sign_out_btn)))
                .addGap(20, 20, 20))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student_name_label))
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_caption)
                    .addComponent(sign_out_btn))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        main_panel.setLayout(new java.awt.BorderLayout());

        navbar.setBackground(new java.awt.Color(189, 187, 214));
        navbar.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(29, 53, 87));
        jButton1.setText("Home");
        jButton1.setPreferredSize(new java.awt.Dimension(0, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        navbar.add(jButton1);

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(29, 53, 87));
        jButton3.setText("Progress");
        jButton3.setPreferredSize(new java.awt.Dimension(0, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        navbar.add(jButton3);

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(29, 53, 87));
        jButton4.setText("Profile");
        jButton4.setPreferredSize(new java.awt.Dimension(0, 40));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        navbar.add(jButton4);

        main_panel.add(navbar, java.awt.BorderLayout.PAGE_START);

        content_panel.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(255, 255, 255));

        ongoing_quiz_table.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        ongoing_quiz_table.setForeground(new java.awt.Color(29, 53, 87));
        ongoing_quiz_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Description", "Mark per Question", "Time allowed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ongoing_quiz_table.setGridColor(new java.awt.Color(29, 53, 87));
        ongoing_quiz_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(ongoing_quiz_table);
        if (ongoing_quiz_table.getColumnModel().getColumnCount() > 0) {
            ongoing_quiz_table.getColumnModel().getColumn(0).setResizable(false);
            ongoing_quiz_table.getColumnModel().getColumn(1).setResizable(false);
            ongoing_quiz_table.getColumnModel().getColumn(1).setPreferredWidth(10);
            ongoing_quiz_table.getColumnModel().getColumn(2).setResizable(false);
            ongoing_quiz_table.getColumnModel().getColumn(2).setPreferredWidth(20);
            ongoing_quiz_table.getColumnModel().getColumn(3).setResizable(false);
            ongoing_quiz_table.getColumnModel().getColumn(4).setResizable(false);
            ongoing_quiz_table.getColumnModel().getColumn(4).setPreferredWidth(5);
        }
        ongoing_quiz_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ongoing_quiz_table.getColumnModel().getColumn(0).setWidth(100); // First column width
        ongoing_quiz_table.getColumnModel().getColumn(1).setWidth(200); // Second column width
        ongoing_quiz_table.getColumnModel().getColumn(2).setWidth(20);

        title_caption9.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption9.setForeground(new java.awt.Color(29, 53, 87));
        title_caption9.setText("Ongoing Quizes");

        jButton12.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        jButton12.setText("Take Quiz");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addComponent(title_caption9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(title_caption9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        content_panel.add(home, "card2");

        profile_panel.setBackground(new java.awt.Color(255, 255, 255));

        title_caption11.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption11.setForeground(new java.awt.Color(29, 53, 87));
        title_caption11.setText("My Profile");

        jLabel3.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel3.setText("Name");

        jLabel5.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel5.setText("Password");

        jLabel4.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel4.setText("Emaiil");

        jLabel6.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel6.setText("Class");

        p1.setEnabled(false);

        p3.setEnabled(false);

        p4.setEnabled(false);

        p2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        p2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Yr MCA", "2nd Yr MCA", "1st Yr CSE", "2nd Yr CSE", "3rd Yr CSE", "4th Yr CSE" }));
        p2.setEnabled(false);

        p_btn1.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        p_btn1.setText("Edit");
        p_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn1ActionPerformed(evt);
            }
        });

        p_btn2.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        p_btn2.setText("Submit");
        p_btn2.setEnabled(false);
        p_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout profile_panelLayout = new javax.swing.GroupLayout(profile_panel);
        profile_panel.setLayout(profile_panelLayout);
        profile_panelLayout.setHorizontalGroup(
            profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_panelLayout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_caption11)
                    .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(profile_panelLayout.createSequentialGroup()
                            .addComponent(p_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(p_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, profile_panelLayout.createSequentialGroup()
                            .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(18, 18, 18)
                            .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(p1)
                                .addComponent(p3)
                                .addComponent(p4)
                                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(268, Short.MAX_VALUE))
        );
        profile_panelLayout.setVerticalGroup(
            profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_panelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(title_caption11)
                .addGap(29, 29, 29)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3)
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profile_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6))
                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profile_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4))
                    .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profile_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5))
                    .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_btn2)
                    .addComponent(p_btn1))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        content_panel.add(profile_panel, "card4");

        progress_panel.setBackground(new java.awt.Color(255, 255, 255));

        progress_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Topic", "Description", "Marks Obtained", "Total Marks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(progress_table);
        if (progress_table.getColumnModel().getColumnCount() > 0) {
            progress_table.getColumnModel().getColumn(0).setResizable(false);
            progress_table.getColumnModel().getColumn(1).setResizable(false);
            progress_table.getColumnModel().getColumn(2).setResizable(false);
            progress_table.getColumnModel().getColumn(3).setResizable(false);
        }

        title_caption10.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption10.setForeground(new java.awt.Color(29, 53, 87));
        title_caption10.setText("My Progress");

        javax.swing.GroupLayout progress_panelLayout = new javax.swing.GroupLayout(progress_panel);
        progress_panel.setLayout(progress_panelLayout);
        progress_panelLayout.setHorizontalGroup(
            progress_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progress_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(progress_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                    .addGroup(progress_panelLayout.createSequentialGroup()
                        .addComponent(title_caption10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        progress_panelLayout.setVerticalGroup(
            progress_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, progress_panelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(title_caption10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        content_panel.add(progress_panel, "card3");

        score_display.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("You have Completed the Quiz !");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("Your Score is:");

        score_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        score_label.setText("XX");

        jButton2.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        jButton2.setText("View Progress");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout score_displayLayout = new javax.swing.GroupLayout(score_display);
        score_display.setLayout(score_displayLayout);
        score_displayLayout.setHorizontalGroup(
            score_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(score_displayLayout.createSequentialGroup()
                .addGroup(score_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(score_displayLayout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(score_displayLayout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(score_label, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(304, Short.MAX_VALUE))
        );
        score_displayLayout.setVerticalGroup(
            score_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(score_displayLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(score_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(score_label))
                .addGap(48, 48, 48)
                .addComponent(jButton2)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        content_panel.add(score_display, "card5");

        main_panel.add(content_panel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(main_panel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sign_out_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sign_out_btnMouseClicked
        new StudentLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_sign_out_btnMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel ongoing_quiz_table_model=(DefaultTableModel)ongoing_quiz_table.getModel();
        String quiz_id=ongoing_quiz_table_model.getValueAt(ongoing_quiz_table.getSelectedRow(), 0).toString();
        String markpq=ongoing_quiz_table_model.getValueAt(ongoing_quiz_table.getSelectedRow(), 3).toString();
        String tm=ongoing_quiz_table_model.getValueAt(ongoing_quiz_table.getSelectedRow(), 4).toString();
        String student_id=null;
        try {
            student_id=student_info.getString("student_id");
        } catch (SQLException ex) {
            System.out.println("did not get student info");
        }
        new QuizModule(quiz_id,student_id,tm,Integer.parseInt(markpq),this).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        home.setVisible(true);
        profile_panel.setVisible(false);
        progress_panel.setVisible(false);
        populate_ongoing_quiz();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        home.setVisible(false);
        profile_panel.setVisible(false);
        progress_panel.setVisible(true);
        populate_progress_table();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        home.setVisible(false);
        profile_panel.setVisible(true);
        progress_panel.setVisible(false);
        populate_student_profile();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        home.setVisible(false);
    profile_panel.setVisible(false);
    progress_panel.setVisible(true);
    score_display.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void p_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn1ActionPerformed
       p1.setEnabled(true);
       p2.setEnabled(true);
       p3.setEnabled(true);
       p4.setEnabled(true);
       p_btn2.setEnabled(true);
       p_btn1.setEnabled(false);
    }//GEN-LAST:event_p_btn1ActionPerformed

    private void p_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn2ActionPerformed
       try{
            Connection conn=DBConnection.getConnection();
            String sql="update student_profile set name=?,class=?,email=?,password=? where student_id=?";
            PreparedStatement prest=conn.prepareStatement(sql);
            prest.setString(1, p1.getText());
            prest.setString(2, (String) p2.getSelectedItem());
            prest.setString(3, p3.getText());
            prest.setString(4, String.valueOf(p4.getPassword()));
            prest.setString(5, student_info.getString("student_id"));
            prest.executeUpdate();
            student_name_label.setText(p1.getText());
            p1.setEnabled(false);
            p2.setEnabled(false);
            p3.setEnabled(false);
            p4.setEnabled(false);
            p_btn2.setEnabled(false);
            p_btn1.setEnabled(true);
        }
       catch(Exception e){
           System.out.println(e);
       }
    }//GEN-LAST:event_p_btn2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content_panel;
    private javax.swing.JPanel header;
    private javax.swing.JPanel home;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel navbar;
    private javax.swing.JTable ongoing_quiz_table;
    private javax.swing.JTextField p1;
    private javax.swing.JComboBox<String> p2;
    private javax.swing.JTextField p3;
    private javax.swing.JPasswordField p4;
    private javax.swing.JButton p_btn1;
    private javax.swing.JButton p_btn2;
    private javax.swing.JPanel profile_panel;
    private javax.swing.JPanel progress_panel;
    private javax.swing.JTable progress_table;
    private javax.swing.JPanel score_display;
    private javax.swing.JLabel score_label;
    private javax.swing.JLabel sign_out_btn;
    private javax.swing.JLabel student_name_label;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title_caption;
    private javax.swing.JLabel title_caption10;
    private javax.swing.JLabel title_caption11;
    private javax.swing.JLabel title_caption9;
    // End of variables declaration//GEN-END:variables
}
