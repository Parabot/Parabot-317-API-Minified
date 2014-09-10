package org.rev317.min.ui;

import org.parabot.core.Context;
import org.parabot.environment.scripts.randoms.Random;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RandomUI implements ActionListener {

	private JFrame frame;
    private ArrayList<JCheckBox> checkBoxes;

	public void openFrame(ArrayList<String> randoms) {
		frame = new JFrame();
		frame.setBounds(100, 100, 351, 150);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(228, 93, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblRandoms = new JLabel("Randoms");
		lblRandoms.setBounds(6, 6, 61, 16);
		frame.getContentPane().add(lblRandoms);

        checkBoxes = new ArrayList<>();
        for (int i = 0; i < randoms.size(); i++){
            JCheckBox checkBox = new JCheckBox(randoms.get(i));
            checkBox.setBounds(6, 34 + (i * 35), 128, 23);
            frame.getContentPane().add(checkBox);
            if (isActive(randoms.get(i))){
                checkBox.setSelected(true);
            }
            checkBoxes.add(checkBox);
        }

        btnSubmit.addActionListener(this);
        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.frame.setVisible(true);
	}

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Context.getInstance().getRandomHandler().clearActiveRandoms();
        for (JCheckBox checkBox : this.checkBoxes){
            if (checkBox.isSelected()) {
                for (Random r : Context.getInstance().getRandomHandler().getRandoms()){
                    if (r.getName().equalsIgnoreCase(checkBox.getText().toLowerCase())){
                        Context.getInstance().getRandomHandler().setActive(r.getName());
                    }
                }
            }
        }
        this.frame.dispose();
    }

    private boolean isActive(String random){
        for (Random r : Context.getInstance().getRandomHandler().getActiveRandoms()){
            if (r.getName().equalsIgnoreCase(random.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
