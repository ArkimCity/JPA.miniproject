package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class bargraph
{
    public static void main(String args[])
    {
        JFrame frame = new JFrame("Program Language School");
        frame.setLocation(500,200);
        frame.setPreferredSize(new Dimension(400,350));
        Container contentPane = frame.getContentPane();

        DrawingPanel drawingPanel = new DrawingPanel();
        contentPane.add(drawingPanel, BorderLayout.CENTER);
        //그래프를 그릴 패널

        JPanel controlPanel = new JPanel();
        JTextField text1 = new JTextField(3);
        JTextField text2 = new JTextField(3);
        JTextField text3 = new JTextField(3);
        JButton button = new JButton("Show");
        controlPanel.add(new JLabel("Java"));
        controlPanel.add(text1);
        controlPanel.add(new JLabel("Python"));
        controlPanel.add(text2);
        controlPanel.add(new JLabel("C#"));
        controlPanel.add(text3);
        controlPanel.add(button);
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new DrawActionListener(text1,text2,text3,drawingPanel));
        //"그래프 그리기" 버튼을 눌렀을때 작동 할 리스터등록
        frame.pack();
        frame.setVisible(true);
    }
}

//그래프를 그리는 패널 클래스
class DrawingPanel extends JPanel
{
    int score_java, score_python, score_cs;
    public void paint(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        g.drawLine(50,250,350,250);
        for(int cnt = 1 ;cnt<11;cnt++)
        {
            g.drawString(cnt *10 +"",25,255-20*cnt);
            g.drawLine(50, 250-20*cnt, 350,250-20*cnt);
        }
        g.drawLine(50,20,50,250);
        g.drawString("Java",100,270);
        g.drawString("Python",200,270);
        g.drawString("C#",300,270);
        g.setColor(Color.BLUE);
        if (score_java>0)
            g.fillRect(110,250-score_java*2,10,score_java*2);
        if(score_python>0)
            g.fillRect(210,250-score_python*2,10,score_python*2);
        if(score_cs>0)
            g.fillRect(310,250-score_cs*2,10,score_cs*2);
    }
    void setScores(int score_java, int score_python, int score_cs)
    {
        this.score_java=score_java;
        this.score_python=score_python;
        this.score_cs=score_cs;
    }
}

//버튼 눌렀을때 동작하는 리스너
class DrawActionListener implements ActionListener
{
    JTextField text1,text2,text3;
    DrawingPanel drawingPanel;
    DrawActionListener(JTextField text1, JTextField text2, JTextField text3, DrawingPanel drawingPanel)
    {
        this.text1=text1;
        this.text2=text2;
        this.text3=text3;
        this.drawingPanel = drawingPanel;
    }
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            int korean = Integer.parseInt(text1.getText());
            int english = Integer.parseInt(text2.getText());
            int math = Integer.parseInt(text3.getText());
            drawingPanel.setScores(korean, english, math);
            drawingPanel.repaint();
        }
        catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(drawingPanel,"잘못된 숫자 입력입니다","에러메시지",JOptionPane.ERROR_MESSAGE);
        }
    }
}