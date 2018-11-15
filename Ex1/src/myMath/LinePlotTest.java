package myMath;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.*;

public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        //0.2x^4-1.5x^3+3.0x^2-x-5
        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = -2.0; x <= 6.0; x+=0.1) {
            double y = 0.2*Math.pow(x, 4) - 1.5 * Math.pow(x, 3) + 3*Math.pow(x, 2) - x - 5;
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();

    }

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}