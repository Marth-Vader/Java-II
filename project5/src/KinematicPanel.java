import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Panel for drawing a single view of an infant
 * 
 * @author CS2334, modified by Lucas Black, Martha Nguyen
 * @version 11/21/17
 *
 */
public class KinematicPanel extends JPanel {
    /** Drawing flip direction for X: +/-1 */
    private double flipX;
    /** Drawing flip direction for Y: +/-1 */
    private double flipY;

    /** Subfield used for X dimension. */
    private String screenXSubfield;
    /** Subfield used for Y dimension. */
    private String screenYSubfield;
    /** Root of the kinematic tree. */
    private KinematicPointAbstract rootPoint;
    /** State to render. */
    private State state;
    /** Panel title */
    private String title;
    /** Font used for panel title. */
    // TO DO: initialize the font
    private static final Font FONT = new Font("Verdana", Font.BOLD, 12); 

    /**
     * Constructor
     * 
     * @param rootPoint
     *            Root of the kinematic tree for this panel
     * @param flipX
     *            Drawing direction for X dimension: +/- 1
     * @param flipY
     *            Drawing direction for Y dimension: +/- 1
     * @param screenXSubfield
     *            Subfield name for the X dimension
     * @param screenYSubfield
     *            Subfield name for the Y dimension
     * @param title
     *            Panel title
     */
    public KinematicPanel(KinematicPointAbstract rootPoint, double flipX, double flipY, String screenXSubfield,
            String screenYSubfield, String title) {
        super();
        this.flipX = flipX;
        this.flipY = flipY;
        this.rootPoint = rootPoint;
        this.screenXSubfield = screenXSubfield;
        this.screenYSubfield = screenYSubfield;
        this.setPreferredSize(new Dimension(400, 200));
        this.title = title;

        // Set up the border for the panel
        Border border = BorderFactory.createLineBorder(Color.black);
        this.setBorder(border);
    }

    /**
     * Set the state to render
     * 
     * @param state
     *            State to be rendered
     */
    public void setState(State state) {
        this.state = state;
        this.repaint();
    }

    /**
     * Render the panel
     * 
     * @param g
     *            Graphics context
     */
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        // TO DO: Draw the title
        g.setFont(FONT); 
        FontMetrics fonty = g.getFontMetrics();
        g.drawString(this.title, 20, (int) fonty.getHeight() + 10);

        // Render as long as state is defined
        if (this.state != null) {
            Graphics2D g2 = (Graphics2D) g;

            // Translate the graphics context origin to the center of the panel
            g2.translate(this.getWidth() / 2, this.getHeight() / 2);
            // Flip the drawing directions
            g2.scale(flipX, flipY);

            // TO DO: Draw the kinematic tree
            rootPoint.draw(g2, this.state, this.screenXSubfield, this.screenYSubfield);

            // These next two lines make the border drawing work properly
            // Flip back
            g2.scale(flipX, flipY);
            // Translate the origin back
            g2.translate( -this.getWidth() / 2, -this.getHeight() / 2);
        }
    }
}