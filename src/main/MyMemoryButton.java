package main;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyMemoryButton extends Button implements ActionListener {

    private static final long serialVersionUID = 1L;
    MyCalculator cl;

    MyMemoryButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        char memop = ((MyMemoryButton) ev.getSource()).getLabel().charAt(1);
        cl.setClear = true;
        switch (memop) {
        case 'C':
            cl.memLabel.setText(" ");
            cl.memValue = 0.0;
            break;
        case 'R':
            cl.displayLabel.setText(MyCalculator.getFormattedText(cl.memValue));
            break;
        case '-':
            cl.memValue -= Double.parseDouble(cl.displayLabel.getText());
            break;
        case '+':
            cl.memValue += Double.parseDouble(cl.displayLabel.getText());
            if (cl.displayLabel.getText().equals("0") || cl.displayLabel.getText().equals("0.0"))
                cl.memLabel.setText(" ");
            else
                cl.memLabel.setText("M");
            break;
        }
    }
}