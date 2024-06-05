package quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class TeacherDashboard extends javax.swing.JFrame {
    String teacherid;
    ResultSet teacherinfo=null;
    public void BackOn(){
        this.setVisible(true);
        cq_desc.setText("");
        cq_markpq.setText("");
        cq_time.setText("");
        cq_title.setText("");

    }
    

    public TeacherDashboard(String id) {
        initComponents();
        setResizable(false);
        all_quiz.getColumnModel().getColumn(0).setPreferredWidth(1);
        all_quiz.getColumnModel().getColumn(1).setPreferredWidth(300);
        all_quiz.getColumnModel().getColumn(2).setPreferredWidth(10);
        all_quiz.getColumnModel().getColumn(3).setPreferredWidth(10);
        all_quiz.getColumnModel().getColumn(4).setPreferredWidth(10);
        quiz_student_list.setVisible(false);
    try {
        String querySql = "select * from teacher_profile where teacher_id=?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1, id);
        teacherinfo=pstmt.executeQuery();
        teacherinfo.next();
        teacherid=teacherinfo.getString("teacher_id");
        teacher_name_label.setText(teacherinfo.getString("name"));
        querySql = "select * from quiz where teacher_id=?";
        pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1, id);
        ResultSet quizes=pstmt.executeQuery();
        int no_quizes=0;
        String last_quiz_id=null;
        String last_quiz_topic=null;
        String last_quiz_class=null;
        while(quizes.next()){
            no_quizes++;
            last_quiz_id=quizes.getString("quiz_id");
            last_quiz_class=quizes.getString("class");
            last_quiz_topic=quizes.getString("title");
        }
        total_quiz_label.setText(Integer.toString(no_quizes));
        last_class_label.setText(last_quiz_class);
        last_topic_label.setText(last_quiz_topic);
        
        querySql = "select * from score where quiz_id="+last_quiz_id;
        pstmt = conn.prepareStatement(querySql);
        ResultSet scores=pstmt.executeQuery();
        float net_score=0;
        float net_total=0;
        int stud_count=0;
        while(scores.next()){
            stud_count++;
            net_score=net_score+scores.getInt("score");
            net_total=net_total+scores.getInt("total_mark");
        }
        System.out.println(net_score+"  "+net_total);
        int final_percentage=(int) ((net_score/net_total)*100);
        last_attendance_label.setText(Integer.toString(stud_count));
        last_avg_label.setText(Integer.toString(final_percentage)+"%");
    }catch (Exception e) {
        System.out.println(e);
    }
    
    }
public void populate_all_quiz(){
    try {
        String querySql = "select * from quiz where teacher_id=?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1,teacherid);
        ResultSet rs=pstmt.executeQuery();
        DefaultTableModel all_quiz_model=(DefaultTableModel)all_quiz.getModel();
        all_quiz_model.setRowCount(0);
        while(rs.next()){
            pstmt = conn.prepareStatement("select count(*) as att,avg(score) as avg from score where quiz_id="+rs.getString("quiz_id"));
            ResultSet scorers=pstmt.executeQuery();
            scorers.next();
            String tbData[]={rs.getString("quiz_id"),rs.getString("title"),rs.getString("class"),Integer.toString(Integer.parseInt(scorers.getString("att"))),Double.toString(Math.floor(scorers.getFloat("avg")))};
            all_quiz_model.addRow(tbData);
        }
    }catch (Exception e) {
        System.out.println(e);
    }
    }
   
public void populate_student_details(){
    try {
        String cls=(String) class_selector_box.getSelectedItem();
        String querySql = "select * from student_profile where class=? order by rollno";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(querySql);
        pstmt.setString(1, cls);
        ResultSet rs=pstmt.executeQuery();
        DefaultTableModel cls_table=(DefaultTableModel)class_student_table.getModel();
        cls_table.setRowCount(0);
        while(rs.next()){
            String tbData[]={rs.getString("rollno"),rs.getString("name")};
            cls_table.addRow(tbData);
        }
    }catch (Exception e) {
        System.out.println(e);
    }
    }
void populate_teacher_profile(){
        try{
        p1.setText(teacherinfo.getString("name"));
        p2.setText(teacherinfo.getString("subject"));
        p3.setText(teacherinfo.getString("email"));
        p4.setText(teacherinfo.getString("password"));
        }
        catch(Exception e){
            System.out.println(e);}
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        title_caption = new javax.swing.JLabel();
        teacher_name_label = new javax.swing.JLabel();
        sign_out_btn = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        navbar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        context_panel = new javax.swing.JPanel();
        home_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        total_quiz_label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        last_attendance_label = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        last_avg_label = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        last_class_label = new javax.swing.JLabel();
        title_caption6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        last_topic_label = new javax.swing.JLabel();
        title_caption7 = new javax.swing.JLabel();
        students_panel = new javax.swing.JPanel();
        title_caption8 = new javax.swing.JLabel();
        class_selector_box = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        class_student_table = new javax.swing.JTable();
        quizes_panel = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        create_quiz_btn = new javax.swing.JButton();
        inner_panel = new javax.swing.JPanel();
        myquiz_panel = new javax.swing.JPanel();
        title_caption3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        all_quiz = new javax.swing.JTable();
        view_quiz_details_btn = new javax.swing.JButton();
        generatequiz_panel = new javax.swing.JPanel();
        title_caption9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        cq_desc = new javax.swing.JTextArea();
        cq_title = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cq_time = new javax.swing.JTextField();
        cq_markpq = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        cq_class = new javax.swing.JComboBox<>();
        quiz_student_list = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        quiz_attend_list = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        remaining_stud = new javax.swing.JTable();
        title_caption4 = new javax.swing.JLabel();
        title_caption10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        q1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        q3 = new javax.swing.JLabel();
        quiz_detail_topic = new javax.swing.JLabel();
        create_quiz_btn1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        q4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        q2 = new javax.swing.JLabel();
        title_caption5 = new javax.swing.JLabel();
        profile_panel = new javax.swing.JPanel();
        title_caption11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        p1 = new javax.swing.JTextField();
        p3 = new javax.swing.JTextField();
        p4 = new javax.swing.JPasswordField();
        p_btn1 = new javax.swing.JButton();
        p_btn2 = new javax.swing.JButton();
        p2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(29, 53, 87));

        title.setFont(new java.awt.Font("Cairo Play", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("EduQuiz");

        title_caption.setFont(new java.awt.Font("Raleway", 2, 12)); // NOI18N
        title_caption.setForeground(new java.awt.Color(255, 255, 255));
        title_caption.setText("Teacher Dashboard");

        teacher_name_label.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        teacher_name_label.setForeground(new java.awt.Color(255, 255, 255));
        teacher_name_label.setText("Jude Antony");

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
                        .addComponent(teacher_name_label))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(title_caption, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 513, Short.MAX_VALUE)
                        .addComponent(sign_out_btn)))
                .addGap(20, 20, 20))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacher_name_label))
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

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(29, 53, 87));
        jButton2.setText("Students");
        jButton2.setPreferredSize(new java.awt.Dimension(0, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        navbar.add(jButton2);

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(29, 53, 87));
        jButton3.setText("Quizes");
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

        context_panel.setBackground(new java.awt.Color(255, 255, 255));
        context_panel.setLayout(new java.awt.CardLayout());

        home_panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(42, 157, 143));

        jLabel6.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total Quizes");

        total_quiz_label.setFont(new java.awt.Font("Raleway", 1, 36)); // NOI18N
        total_quiz_label.setForeground(new java.awt.Color(255, 255, 255));
        total_quiz_label.setText("69");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(total_quiz_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_quiz_label, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(29, 53, 87));

        jLabel7.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Attendance");

        last_attendance_label.setFont(new java.awt.Font("Raleway", 1, 36)); // NOI18N
        last_attendance_label.setForeground(new java.awt.Color(255, 255, 255));
        last_attendance_label.setText("69");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(last_attendance_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(last_attendance_label, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(69, 123, 157));

        jLabel8.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Class Average %");

        last_avg_label.setFont(new java.awt.Font("Raleway", 1, 36)); // NOI18N
        last_avg_label.setForeground(new java.awt.Color(255, 255, 255));
        last_avg_label.setText("69%");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(last_avg_label)
                .addGap(19, 19, 19))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(last_avg_label, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(230, 57, 70));

        jLabel9.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Class   :");

        last_class_label.setFont(new java.awt.Font("Raleway", 1, 18)); // NOI18N
        last_class_label.setForeground(new java.awt.Color(255, 255, 255));
        last_class_label.setText("1st Yr MCA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(last_class_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(last_class_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        title_caption6.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption6.setForeground(new java.awt.Color(29, 53, 87));
        title_caption6.setText("Information about last Quiz");

        jPanel6.setBackground(new java.awt.Color(76, 76, 76));

        jLabel14.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Topic  :");

        last_topic_label.setFont(new java.awt.Font("Raleway", 1, 18)); // NOI18N
        last_topic_label.setForeground(new java.awt.Color(255, 255, 255));
        last_topic_label.setText("DS Quiz 1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(last_topic_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(last_topic_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        title_caption7.setFont(new java.awt.Font("Raleway", 0, 20)); // NOI18N
        title_caption7.setForeground(new java.awt.Color(29, 53, 87));
        title_caption7.setText("Dashboard");

        javax.swing.GroupLayout home_panelLayout = new javax.swing.GroupLayout(home_panel);
        home_panel.setLayout(home_panelLayout);
        home_panelLayout.setHorizontalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(home_panelLayout.createSequentialGroup()
                        .addComponent(title_caption7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 683, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(home_panelLayout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(title_caption6, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        home_panelLayout.setVerticalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_caption7)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(title_caption6)
                .addGap(18, 18, 18)
                .addGroup(home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(home_panelLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        context_panel.add(home_panel, "card4");

        students_panel.setBackground(new java.awt.Color(255, 255, 255));

        title_caption8.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption8.setForeground(new java.awt.Color(29, 53, 87));
        title_caption8.setText("Class :");

        class_selector_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Yr MCA", "2nd Yr MCA", "1st Yr CSE", "2nd Yr CSE", "3rd Yr CSE", "4th Yr CSE" }));
        class_selector_box.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                class_selector_boxItemStateChanged(evt);
            }
        });

        class_student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Roll No", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(class_student_table);

        javax.swing.GroupLayout students_panelLayout = new javax.swing.GroupLayout(students_panel);
        students_panel.setLayout(students_panelLayout);
        students_panelLayout.setHorizontalGroup(
            students_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(students_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(students_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(students_panelLayout.createSequentialGroup()
                        .addComponent(title_caption8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(class_selector_box, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        students_panelLayout.setVerticalGroup(
            students_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(students_panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(students_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_caption8)
                    .addComponent(class_selector_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        context_panel.add(students_panel, "card5");

        quizes_panel.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        jButton6.setText("My Quizes");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        create_quiz_btn.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        create_quiz_btn.setText("Generate Quiz");
        create_quiz_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_quiz_btnActionPerformed(evt);
            }
        });

        inner_panel.setLayout(new java.awt.CardLayout());

        myquiz_panel.setBackground(new java.awt.Color(255, 255, 255));
        myquiz_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        title_caption3.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption3.setForeground(new java.awt.Color(29, 53, 87));
        title_caption3.setText("All Quizes");

        all_quiz.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        all_quiz.setForeground(new java.awt.Color(29, 53, 87));
        all_quiz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Class", "Attendance", "Class Avg"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        all_quiz.setGridColor(new java.awt.Color(29, 53, 87));
        jScrollPane1.setViewportView(all_quiz);
        if (all_quiz.getColumnModel().getColumnCount() > 0) {
            all_quiz.getColumnModel().getColumn(0).setResizable(false);
            all_quiz.getColumnModel().getColumn(1).setResizable(false);
            all_quiz.getColumnModel().getColumn(2).setResizable(false);
            all_quiz.getColumnModel().getColumn(3).setResizable(false);
            all_quiz.getColumnModel().getColumn(4).setResizable(false);
        }

        view_quiz_details_btn.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        view_quiz_details_btn.setText("View Details");
        view_quiz_details_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_quiz_details_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myquiz_panelLayout = new javax.swing.GroupLayout(myquiz_panel);
        myquiz_panel.setLayout(myquiz_panelLayout);
        myquiz_panelLayout.setHorizontalGroup(
            myquiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myquiz_panelLayout.createSequentialGroup()
                .addGroup(myquiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myquiz_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(view_quiz_details_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myquiz_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(myquiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(myquiz_panelLayout.createSequentialGroup()
                                .addComponent(title_caption3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE))))
                .addContainerGap())
        );
        myquiz_panelLayout.setVerticalGroup(
            myquiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myquiz_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_caption3)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(view_quiz_details_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        inner_panel.add(myquiz_panel, "card2");

        generatequiz_panel.setBackground(new java.awt.Color(255, 255, 255));
        generatequiz_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        title_caption9.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption9.setForeground(new java.awt.Color(29, 53, 87));
        title_caption9.setText("Create a Quiz");

        jLabel16.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel16.setText("Class            :");

        jLabel17.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel17.setText("Title              :");

        jLabel18.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel18.setText("Description   :");

        cq_desc.setColumns(20);
        cq_desc.setRows(5);
        jScrollPane4.setViewportView(cq_desc);

        jLabel19.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel19.setText("Time Limit (Mins)       :");

        jLabel20.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel20.setText("Marks per Question   :");

        jButton10.setBackground(new java.awt.Color(29, 53, 87));
        jButton10.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        jButton10.setText("Generate Quiz");
        jButton10.setOpaque(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        cq_class.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cq_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1st Yr MCA", "2nd Yr MCA", "1st Yr CSE", "2nd Yr CSE", "3rd Yr CSE", "4th Yr CSE" }));

        javax.swing.GroupLayout generatequiz_panelLayout = new javax.swing.GroupLayout(generatequiz_panel);
        generatequiz_panel.setLayout(generatequiz_panelLayout);
        generatequiz_panelLayout.setHorizontalGroup(
            generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generatequiz_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_caption9)
                    .addGroup(generatequiz_panelLayout.createSequentialGroup()
                        .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generatequiz_panelLayout.createSequentialGroup()
                                .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cq_title)
                                    .addComponent(cq_class, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generatequiz_panelLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generatequiz_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cq_time, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generatequiz_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addGap(18, 18, 18)
                                    .addComponent(cq_markpq, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        generatequiz_panelLayout.setVerticalGroup(
            generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generatequiz_panelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(title_caption9)
                .addGap(18, 18, 18)
                .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generatequiz_panelLayout.createSequentialGroup()
                        .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel19)
                            .addComponent(cq_class))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cq_title, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20)
                            .addComponent(cq_markpq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(generatequiz_panelLayout.createSequentialGroup()
                        .addComponent(cq_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generatequiz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(generatequiz_panelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(111, 111, 111))
        );

        inner_panel.add(generatequiz_panel, "card3");

        quiz_student_list.setBackground(new java.awt.Color(255, 255, 255));
        quiz_student_list.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        quiz_attend_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Roll No", "Name", "Mark Obtained"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(quiz_attend_list);
        if (quiz_attend_list.getColumnModel().getColumnCount() > 0) {
            quiz_attend_list.getColumnModel().getColumn(0).setResizable(false);
            quiz_attend_list.getColumnModel().getColumn(1).setResizable(false);
            quiz_attend_list.getColumnModel().getColumn(2).setResizable(false);
            quiz_attend_list.getColumnModel().getColumn(2).setHeaderValue("Mark Obtained");
        }

        remaining_stud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Roll No", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(remaining_stud);
        if (remaining_stud.getColumnModel().getColumnCount() > 0) {
            remaining_stud.getColumnModel().getColumn(0).setResizable(false);
            remaining_stud.getColumnModel().getColumn(1).setResizable(false);
        }

        title_caption4.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        title_caption4.setForeground(new java.awt.Color(29, 53, 87));
        title_caption4.setText("Students Attended");

        title_caption10.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        title_caption10.setForeground(new java.awt.Color(29, 53, 87));
        title_caption10.setText("Remaining");

        jPanel1.setBackground(new java.awt.Color(69, 123, 157));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Class             :");

        q1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        q1.setForeground(new java.awt.Color(255, 255, 255));
        q1.setText("XX");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(q1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(q1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(69, 123, 157));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Average Mark  :");

        q3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        q3.setForeground(new java.awt.Color(255, 255, 255));
        q3.setText("XX");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(q3, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(q3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        quiz_detail_topic.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        quiz_detail_topic.setForeground(new java.awt.Color(29, 53, 87));
        quiz_detail_topic.setText("Quiz Topic");

        create_quiz_btn1.setFont(new java.awt.Font("Raleway", 0, 11)); // NOI18N
        create_quiz_btn1.setText("All Quizes");
        create_quiz_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_quiz_btn1ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(69, 123, 157));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Total Mark      :");

        q4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        q4.setForeground(new java.awt.Color(255, 255, 255));
        q4.setText("XX");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(q4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(69, 123, 157));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Attendance     :");

        q2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        q2.setForeground(new java.awt.Color(255, 255, 255));
        q2.setText("XX");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(q2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(q2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout quiz_student_listLayout = new javax.swing.GroupLayout(quiz_student_list);
        quiz_student_list.setLayout(quiz_student_listLayout);
        quiz_student_listLayout.setHorizontalGroup(
            quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quiz_student_listLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quiz_student_listLayout.createSequentialGroup()
                        .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(create_quiz_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title_caption4))
                        .addGap(18, 18, 18)
                        .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title_caption10)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(quiz_student_listLayout.createSequentialGroup()
                        .addComponent(quiz_detail_topic)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        quiz_student_listLayout.setVerticalGroup(
            quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quiz_student_listLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quiz_detail_topic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quiz_student_listLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(create_quiz_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(quiz_student_listLayout.createSequentialGroup()
                        .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title_caption4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(title_caption10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(quiz_student_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inner_panel.add(quiz_student_list, "card4");

        title_caption5.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption5.setForeground(new java.awt.Color(29, 53, 87));
        title_caption5.setText("Manage Quizes");

        javax.swing.GroupLayout quizes_panelLayout = new javax.swing.GroupLayout(quizes_panel);
        quizes_panel.setLayout(quizes_panelLayout);
        quizes_panelLayout.setHorizontalGroup(
            quizes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quizes_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quizes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(quizes_panelLayout.createSequentialGroup()
                        .addComponent(inner_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(quizes_panelLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(create_quiz_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(quizes_panelLayout.createSequentialGroup()
                        .addComponent(title_caption5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        quizes_panelLayout.setVerticalGroup(
            quizes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quizes_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(title_caption5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(quizes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(create_quiz_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(inner_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        context_panel.add(quizes_panel, "card2");

        profile_panel.setBackground(new java.awt.Color(255, 255, 255));

        title_caption11.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        title_caption11.setForeground(new java.awt.Color(29, 53, 87));
        title_caption11.setText("My Profile");

        jLabel5.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel5.setText("Name");

        jLabel10.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel10.setText("Subject");

        jLabel11.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel11.setText("Emaiil");

        jLabel12.setFont(new java.awt.Font("Raleway", 0, 14)); // NOI18N
        jLabel12.setText("Password");

        p1.setEnabled(false);

        p3.setEnabled(false);

        p4.setEnabled(false);

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

        p2.setEnabled(false);

        javax.swing.GroupLayout profile_panelLayout = new javax.swing.GroupLayout(profile_panel);
        profile_panel.setLayout(profile_panelLayout);
        profile_panelLayout.setHorizontalGroup(
            profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_panelLayout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_caption11)
                    .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(profile_panelLayout.createSequentialGroup()
                            .addComponent(p_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(p_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, profile_panelLayout.createSequentialGroup()
                            .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel10))
                            .addGap(18, 18, 18)
                            .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addComponent(p3)
                                .addComponent(p4)
                                .addComponent(p2)))))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        profile_panelLayout.setVerticalGroup(
            profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profile_panelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(title_caption11)
                .addGap(29, 29, 29)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profile_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel11))
                    .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profile_panelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12))
                    .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profile_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_btn2)
                    .addComponent(p_btn1))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        context_panel.add(profile_panel, "card5");

        main_panel.add(context_panel, java.awt.BorderLayout.CENTER);

        getContentPane().add(main_panel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sign_out_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sign_out_btnMouseClicked
        new TeacherLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_sign_out_btnMouseClicked

    private void create_quiz_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_quiz_btnActionPerformed
    myquiz_panel.setVisible(false);
    generatequiz_panel.setVisible(true);
    }//GEN-LAST:event_create_quiz_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        home_panel.setVisible(true);
        quizes_panel.setVisible(false);
        students_panel.setVisible(false);
        profile_panel.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        populate_all_quiz();
        home_panel.setVisible(false);
        quizes_panel.setVisible(true);
        students_panel.setVisible(false);
        profile_panel.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        home_panel.setVisible(false);
        quizes_panel.setVisible(false);
        students_panel.setVisible(true);
        profile_panel.setVisible(false);
        populate_student_details();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        populate_all_quiz();
        myquiz_panel.setVisible(true);
        generatequiz_panel.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String cls=(String) cq_class.getSelectedItem();
        String desc=cq_desc.getText();
        String title=cq_title.getText();
        String mark=cq_markpq.getText();
        String tm=cq_time.getText();
        String arr[]={cls,title,desc,mark,tm};
        String id=null;
        new QuizGenerate(arr,teacherid,this).setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void view_quiz_details_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_quiz_details_btnActionPerformed
        DefaultTableModel all_quiz_model=(DefaultTableModel)all_quiz.getModel();
        String quiz_id=all_quiz_model.getValueAt(all_quiz.getSelectedRow(), 0).toString();
        String tl=all_quiz_model.getValueAt(all_quiz.getSelectedRow(), 1).toString();
        String cl=all_quiz_model.getValueAt(all_quiz.getSelectedRow(), 2).toString();
        String aten=all_quiz_model.getValueAt(all_quiz.getSelectedRow(), 3).toString();
        String cls_avg=all_quiz_model.getValueAt(all_quiz.getSelectedRow(), 4).toString();
        quiz_detail_topic.setText(tl);
        q1.setText(cl);
        q2.setText(aten);
        q3.setText(cls_avg);
        try{
            Connection conn=DBConnection.getConnection();
            String sql="select * from student_profile st,score s where s.quiz_id=? and s.student_id=st.student_id";
            PreparedStatement prep=conn.prepareStatement(sql);
            prep.setString(1, quiz_id);
            ResultSet rs=prep.executeQuery();
            DefaultTableModel att=(DefaultTableModel)quiz_attend_list.getModel();
            att.setRowCount(0);
            while(rs.next()){
                String tbData[]={rs.getString("rollno"),rs.getString("name"),rs.getString("score")};
                att.addRow(tbData);
                q4.setText(rs.getString("total_mark"));
            }
            
            sql="select * from student_profile where student_id not in (select student_id from score where quiz_id=?) and class=?";
            prep=conn.prepareStatement(sql);
            prep.setString(1, quiz_id);
            prep.setString(2, (String)all_quiz.getValueAt(all_quiz.getSelectedRow(), 2));
            rs=prep.executeQuery();
            DefaultTableModel rem=(DefaultTableModel)remaining_stud.getModel();
            rem.setRowCount(0);
            while(rs.next()){
                String tbData[]={rs.getString("rollno"),rs.getString("name")};
                rem.addRow(tbData);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        myquiz_panel.setVisible(false);
        generatequiz_panel.setVisible(false);
        quiz_student_list.setVisible(true);
    }//GEN-LAST:event_view_quiz_details_btnActionPerformed

    private void class_selector_boxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_class_selector_boxItemStateChanged
        populate_student_details();
    }//GEN-LAST:event_class_selector_boxItemStateChanged

    private void create_quiz_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_quiz_btn1ActionPerformed
        myquiz_panel.setVisible(true);
        generatequiz_panel.setVisible(false);
        quiz_student_list.setVisible(false);
    }//GEN-LAST:event_create_quiz_btn1ActionPerformed

    private void p_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn1ActionPerformed
        p1.setEnabled(true);
        p2.setEnabled(true);
        p3.setEnabled(true);
        p4.setEnabled(true);
        p_btn2.setEnabled(true);
        p_btn1.setEnabled(false);
    }//GEN-LAST:event_p_btn1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        home_panel.setVisible(false);
        quizes_panel.setVisible(false);
        students_panel.setVisible(false);
        profile_panel.setVisible(true);
        populate_teacher_profile();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void p_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_btn2ActionPerformed
        try{
            Connection conn=DBConnection.getConnection();
            String sql="update teacher_profile set name=?,subject=?,email=?,password=? where teacher_id=?";
            PreparedStatement prest=conn.prepareStatement(sql);
            prest.setString(1, p1.getText());
            prest.setString(2, p2.getText());
            prest.setString(3, p3.getText());
            prest.setString(4, String.valueOf(p4.getPassword()));
            prest.setString(5, teacherid);
            prest.executeUpdate();
            teacher_name_label.setText(p1.getText());
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
    private javax.swing.JTable all_quiz;
    private javax.swing.JComboBox<String> class_selector_box;
    private javax.swing.JTable class_student_table;
    private javax.swing.JPanel context_panel;
    private javax.swing.JComboBox<String> cq_class;
    private javax.swing.JTextArea cq_desc;
    private javax.swing.JTextField cq_markpq;
    private javax.swing.JTextField cq_time;
    private javax.swing.JTextField cq_title;
    private javax.swing.JButton create_quiz_btn;
    private javax.swing.JButton create_quiz_btn1;
    private javax.swing.JPanel generatequiz_panel;
    private javax.swing.JPanel header;
    private javax.swing.JPanel home_panel;
    private javax.swing.JPanel inner_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel last_attendance_label;
    private javax.swing.JLabel last_avg_label;
    private javax.swing.JLabel last_class_label;
    private javax.swing.JLabel last_topic_label;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel myquiz_panel;
    private javax.swing.JPanel navbar;
    private javax.swing.JTextField p1;
    private javax.swing.JTextField p2;
    private javax.swing.JTextField p3;
    private javax.swing.JPasswordField p4;
    private javax.swing.JButton p_btn1;
    private javax.swing.JButton p_btn2;
    private javax.swing.JPanel profile_panel;
    private javax.swing.JLabel q1;
    private javax.swing.JLabel q2;
    private javax.swing.JLabel q3;
    private javax.swing.JLabel q4;
    private javax.swing.JTable quiz_attend_list;
    private javax.swing.JLabel quiz_detail_topic;
    private javax.swing.JPanel quiz_student_list;
    private javax.swing.JPanel quizes_panel;
    private javax.swing.JTable remaining_stud;
    private javax.swing.JLabel sign_out_btn;
    private javax.swing.JPanel students_panel;
    private javax.swing.JLabel teacher_name_label;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title_caption;
    private javax.swing.JLabel title_caption10;
    private javax.swing.JLabel title_caption11;
    private javax.swing.JLabel title_caption3;
    private javax.swing.JLabel title_caption4;
    private javax.swing.JLabel title_caption5;
    private javax.swing.JLabel title_caption6;
    private javax.swing.JLabel title_caption7;
    private javax.swing.JLabel title_caption8;
    private javax.swing.JLabel title_caption9;
    private javax.swing.JLabel total_quiz_label;
    private javax.swing.JButton view_quiz_details_btn;
    // End of variables declaration//GEN-END:variables
}
