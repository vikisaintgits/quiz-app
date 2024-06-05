
package quizapp;

public class QuizApp extends javax.swing.JFrame {

    public QuizApp() {
        initComponents();
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        title_caption = new javax.swing.JLabel();
        context_panel = new javax.swing.JPanel();
        context_label = new javax.swing.JLabel();
        student_btn = new javax.swing.JButton();
        teacher_btn = new javax.swing.JButton();
        stud_bbtn_label = new javax.swing.JLabel();
        tchr_btn_label = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(29, 53, 87));

        title.setFont(new java.awt.Font("Cairo Play", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("EduQuiz");

        title_caption.setFont(new java.awt.Font("Raleway", 2, 12)); // NOI18N
        title_caption.setForeground(new java.awt.Color(255, 255, 255));
        title_caption.setText("Create and Assign Quizzes in Minutes..!");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title)
                    .addComponent(title_caption, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(574, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(title_caption)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        context_panel.setBackground(new java.awt.Color(255, 255, 255));

        context_label.setFont(new java.awt.Font("Raleway", 0, 15)); // NOI18N
        context_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        context_label.setText("Are you a teacher or a student? Let us know to personalize your experience.");

        student_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizapp/assets/student-150x150.png"))); // NOI18N
        student_btn.setOpaque(false);
        student_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_btnActionPerformed(evt);
            }
        });

        teacher_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizapp/assets/teacher-150x150.png"))); // NOI18N
        teacher_btn.setOpaque(false);
        teacher_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacher_btnActionPerformed(evt);
            }
        });

        stud_bbtn_label.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        stud_bbtn_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stud_bbtn_label.setText("Teacher");

        tchr_btn_label.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        tchr_btn_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tchr_btn_label.setText("Student");

        javax.swing.GroupLayout context_panelLayout = new javax.swing.GroupLayout(context_panel);
        context_panel.setLayout(context_panelLayout);
        context_panelLayout.setHorizontalGroup(
            context_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(context_label, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
            .addGroup(context_panelLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(context_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stud_bbtn_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teacher_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addGroup(context_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(student_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tchr_btn_label, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        context_panelLayout.setVerticalGroup(
            context_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(context_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(context_label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(context_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teacher_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(student_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(context_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stud_bbtn_label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tchr_btn_label, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        getContentPane().add(context_panel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void teacher_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacher_btnActionPerformed
        new TeacherLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_teacher_btnActionPerformed

    private void student_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_btnActionPerformed
        new StudentLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_student_btnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel context_label;
    private javax.swing.JPanel context_panel;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel stud_bbtn_label;
    private javax.swing.JButton student_btn;
    private javax.swing.JLabel tchr_btn_label;
    private javax.swing.JButton teacher_btn;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title_caption;
    // End of variables declaration//GEN-END:variables

///////////////SUPPORT LIBRARY FROM STACKOVERFLOW//////////////////////////
    
///////////////////////////////////////////////////////////////////////////
}
