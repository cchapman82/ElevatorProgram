/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui;

import main.java.gui.ElevatorDisplay.Direction;
import static main.java.gui.ElevatorDisplay.Direction.IDLE;
import static main.java.gui.ElevatorDisplay.Direction.UP;
import java.awt.Color;

/**
 *
 * @author hieldc
 */
public class ElevatorPanel extends javax.swing.JPanel {

    private static final String UP_ARROW = Character.toString((char) 0x25b2);
    private static final String DOWN_ARROW = Character.toString((char) 0x25bc);
    private static final String IDLE_ARROW = Character.toString((char) 0x25cf);
    private int id;

    public ElevatorPanel(int idIn) {
        initComponents();
        id = idIn;

        jLabel3.setText("#" + id);
    }

    public void setMax(int max) {
        jSlider1.setMaximum(max);
        jSlider1.setMajorTickSpacing(1);
    }

    public void setFloor(int newValue) {
        int oldValue = jSlider1.getValue();
        jSlider1.setValue(newValue);
        jLabel1.setText(" " + newValue);

        Color color;

        if (oldValue < newValue) {
            color = Color.green.darker();
        } else if (oldValue > newValue) {
            color = Color.red;
        } else {
            color = Color.white;
        }
        jLabel1.setForeground(color);
    }

    public void setDirection(Direction dir) {
        if (dir == IDLE) {
            jLabel3.setText(IDLE_ARROW + " #" + id);
            jLabel3.setForeground(Color.white);
        } else {
            jLabel3.setText((dir == UP ? UP_ARROW : DOWN_ARROW) + "#" + id);
            jLabel3.setForeground((dir == UP ? Color.green : Color.red));
        }
    }

    public void setNumRiders(int value) {
        jLabel2.setText("NR: " + value);
    }

    public void openDoors() {
        jLabel4.setText("[  |  ]");
        jLabel1.setForeground(Color.white);
    }

    public void closeDoors() {
        jLabel4.setText("[|]");
        jLabel1.setForeground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new Color(0, 0, 0));

        jSlider1.setBackground(new Color(0, 0, 0));
        jSlider1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jSlider1.setForeground(new Color(255, 255, 255));
        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(1);
        jSlider1.setMinimum(1);
        jSlider1.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setEnabled(false);

        jLabel1.setBackground(new Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("---");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NR: 0");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        jLabel3.setForeground(new Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("#");

        jLabel4.setBackground(new Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("[|]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
