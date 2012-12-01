package project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Wesley Eversole
 */
public class MenuFrame extends JFrame {

    private MancalaModel model;
    private MancalaController controller;
    private int numStones;

    /**
     *
     * @param model2
     * @param controller2
     */
    public MenuFrame(MancalaModel model2, final MancalaController controller2) {
        model = model2;
        controller = controller2;

        setTitle("Mancala Game");
        setLayout(new FlowLayout());
        setSize(new Dimension(250, 250));



        JButton simpleViewButton = new JButton("Simple View");
        JButton LuxuryViewButton = new JButton("Luxury View");
        JButton IconViewButton = new JButton("Icon View");
        JButton resetbutton = new JButton("Reset Game");

        simpleViewButton.setPreferredSize(new Dimension(150, 50));
        simpleViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                NumView numview = new NumView(controller);
                if (model.isEmpty()) {
                    setStartingStones();
                    model.initializeStones(numStones);
                }

                model.addView(numview);
            }
        });

        IconViewButton.setPreferredSize(new Dimension(150, 50));
        IconViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                IconView iconview = new IconView(controller);
                if (model.isEmpty()) {
                    setStartingStones();
                    model.initializeStones(numStones);
                }

                model.addView(iconview);
            }
        });

        LuxuryViewButton.setPreferredSize(new Dimension(150, 50));
        LuxuryViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                LuxuryView luxview = new LuxuryView(controller);
                if (model.isEmpty()) {
                    setStartingStones();
                    model.initializeStones(numStones);
                }

                model.addView(luxview);
            }
        });

        resetbutton.setPreferredSize(new Dimension(125, 50));
        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	model.resetGame();
            }
        });
        
        
        this.add(simpleViewButton);
        this.add(IconViewButton);
        this.add(LuxuryViewButton);
        this.add(resetbutton);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *Sets the amount of starting stones
     */
    public void setStartingStones() {
        Object[] possibleValues = {"3", "4"};
        Object selectedValue = JOptionPane.showInputDialog(this,
                "Select Number of Starting Stones:\n", "Pick One", JOptionPane.PLAIN_MESSAGE, null, possibleValues, possibleValues[0]);
        if (selectedValue != null) {
            numStones = Integer.parseInt((String) selectedValue);
        }
    }
}
