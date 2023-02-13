package presentation.views.palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextField extends JTextField {
	private String hint;

	private Font gainFont = new Font("Optima", Font.BOLD, 18);
	private Color gainFgColor = Color.BLACK;
	private Color gainBgColor = Color.WHITE;


	private Font lostFont = new Font("Optima", Font.PLAIN, 18);
	private Color lostFgColor = Color.GRAY;
	private Color lostBgColor = Color.WHITE;

	private boolean opaque = false;

	private void initActions(){
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(gainFont);
					setForeground(gainFgColor);
					setBackground(gainBgColor);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(gainFgColor);
					setBackground(gainBgColor);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint)|| getText().length()==0) {
					setText(hint);
					setFont(lostFont);
					setForeground(lostFgColor);
					setBackground(lostBgColor);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(lostFgColor);
					setBackground(lostBgColor);
				}
			}
		});
	}

	private void initTextField(){
		setText(hint);
		setFont(lostFont);
		setForeground(lostFgColor);
		setBackground(lostBgColor);
		setOpaque(opaque);
	}

	public HintTextField(String hint) {
		this.hint = hint;
		initTextField();
		initActions();
	}

}
