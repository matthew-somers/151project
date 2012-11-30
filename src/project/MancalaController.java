package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author jory
 */
public class MancalaController implements ActionListener {

    private MancalaModel model;

    /**
     *
     * @param model
     */
    public MancalaController(MancalaModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameButton gb = (GameButton) e.getSource();
        model.makeMove(gb.getPlayerId(), gb.getButtonId());
        if (model.isLegalMove()) {
            String htmlString = "<html> <font color=" + gb.getView().getPitTextColor() + ">UNDO?</font>";
            gb.setText(htmlString);
        }
    }
}
