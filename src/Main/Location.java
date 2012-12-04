package Main;
import java.awt.geom.Point2D;

//This class is used for the location of the rock.
public class Location extends Point2D.Double {
	public Location(double x, double y) {
		super(x,y);
	}

	public void setX(double tx){
		x = tx;
	}

	public void setY(double ty){
		y=ty;
	}

}
