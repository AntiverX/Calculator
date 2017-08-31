package main;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

class MyOperatorButton extends Button implements ActionListener {
	
	static String strClassName = MyOperatorButton.class.getName();  
    static Logger logger = Logger.getLogger(strClassName);
	
    private static final long serialVersionUID = 1L;
    MyCalculator cl;
    public static boolean flag;
    MyOperatorButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
    	
        String opText = ((MyOperatorButton) ev.getSource()).getLabel();

        cl.setClear = true;
        double temp = Double.parseDouble(cl.displayLabel.getText());

        if (opText.equals("1/x")) {
            try {
                double tempd = 1 / (double) temp;
                cl.displayLabel.setText(MyCalculator.getFormattedText(tempd));logger.info("trigered!");
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
        if (opText.equals("sqrt")) {
            try {
                double tempd = Math.sqrt(temp);
                cl.displayLabel.setText(MyCalculator.getFormattedText(tempd));
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
        
        
//        if (!opText.equals("=")) {
//            cl.number = temp;
//            cl.op = opText.charAt(0);
//            return;
//        }
//        else {
//        	flag = true;
//        }
        
        // process = button pressed  
//        switch (cl.op) {
//        case '+':
//            temp += cl.number;flag = false;cl.displayLabel.setText(MyCalculator.getFormattedText(temp));
//            break;
//        case '-':
//            temp = cl.number - temp;flag = false;
//            break;
//        case '*':
//            temp *= cl.number;flag = false;
//            break;
//        case '%':
//            try {
//                temp = cl.number % temp;flag = false;
//            } catch (ArithmeticException excp) {
//                cl.displayLabel.setText("Divide by 0.");
//                return;
//            }
//            break;
//        case '/':
//            try {
//                temp = cl.number / temp;flag = false;
//            } catch (ArithmeticException excp) {
//                cl.displayLabel.setText("Divide by 0.");
//                return;
//            }
//            break;
//        }
//        
//        if(flag == true)
//        	return;
//        
//        cl.displayLabel.setText(MyCalculator.getFormattedText(temp));
//        flag = true;
        
        cl.op = opText.charAt(0);
        if(MyCalculator.modified_flag != false) {
            switch(cl.previous) {
            case '=':
            	cl.number = temp;
            	break;
            case '-':
            	cl.number -= temp;
            	break;
            case '+':
            	cl.number += temp;
            	break;
            case '*':
            	cl.number *= temp;
              break;
            case '%':
              try {
            	  cl.number = temp % cl.number;
              } catch (ArithmeticException excp) {
                  cl.displayLabel.setText("Divide by 0.");
                  return;
              }
              break;
          	case '/':
              try {
            	  cl.number = cl.number / temp;flag = false;
              } catch (ArithmeticException excp) {
                  cl.displayLabel.setText("Divide by 0.");
                  return;
              }
              break;
            }
        }

        cl.previous = cl.op;
        //logger.info(Double.toString(cl.number));
        cl.displayLabel.setText(MyCalculator.getFormattedText(cl.number));
        //cl.displayLabel.setText(Double.toString(cl.number));
        MyCalculator.modified_flag = false;
    }
} 