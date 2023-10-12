import java.util.Stack;
import javafx.scene.paint.Color;


public class PaintBrush
{

	
	Paint paint;
	
	
	enum BrushMode{
		paintMode,
		fillMode,
		pattern1Mode,
		pattern2Mode
	}

	BrushMode mode;
	
	Paint Gold = new PaintColor(Color.GOLD);
	Paint White = new PaintColor(Color.WHITE);
	
	
/**
set the "paint" for the paintbrush
*/	
	public void setPaint(Paint paint)
	{
		this.paint = paint;   
	}


/*
   gets the present paint on the paint brush
*/
	public Paint getPaint()
	{
		return this.paint;
	}
	
   
   /*
   makes the paint on the paint brush a brigter shade.
   */
	public void setBrighter()
	{		
		setPaint(new PaintBrighter(getPaint()));
	}


   /*
      makes the paint on the paintbrush a darker shade
   */
	public void setDarker()
	{
		setPaint(new PaintDarker(getPaint()));
	}


   /*
      paints the mesh, using the current paint and mode at point x,y
   */
	public void paint(int x, int y, Paint[][] mesh)
	{
		if(mode == BrushMode.paintMode) {
			mesh[x][y] = getPaint();
		}
		
		else if(mode == BrushMode.fillMode) {
			fill(x, y, mesh);
			
		}
		else if(mode == BrushMode.pattern1Mode) {
			pattern1(x, y, mesh, Gold, White);
	
		}
		else if(mode == BrushMode.pattern2Mode) {
			pattern2(x, y, mesh, Gold, White);
		}
		
		
	}
	
	public void fill(int x, int y, Paint[][] mesh) {
		Color c = mesh[x][y].getColor();
		
		mesh[x][y] = getPaint();
		
		if((x - 1) != -1 && mesh[x - 1][y].getColor().equals(c)) {
			fill(x - 1, y, mesh);
		}if((y + 1) < mesh[x].length && mesh[x][y + 1].getColor().equals(c)){
			fill(x, y + 1, mesh);
		}if((x + 1) < mesh.length && mesh[x + 1][y].getColor().equals(c)) {
			fill(x + 1, y, mesh);
		}if((y - 1) != -1 && mesh[x][y - 1].getColor().equals(c)){
			fill(x, y - 1, mesh);
		}
	}
	
	public void pattern1(int x, int y, Paint[][] mesh, Paint color1, Paint color2) {
		Color c = mesh[x][y].getColor();
		
		mesh[x][y] = color1;
		
		if((x - 1) != -1 && mesh[x - 1][y].getColor().equals(c)) {
			pattern1(x - 1, y, mesh, color2, color1);
		}if((y + 1) < mesh[x].length && mesh[x][y + 1].getColor().equals(c)){
			pattern1(x, y + 1, mesh, color1, color2);
		}if((x + 1) < mesh.length && mesh[x + 1][y].getColor().equals(c)) {
			pattern1(x + 1, y, mesh, color2, color1);
		}if((y - 1) != -1 && mesh[x][y - 1].getColor().equals(c)){
			pattern1(x, y - 1, mesh, color1, color2);
		}
	}

	class Pixel{
		int x;
		int y;
		boolean isColor1;
		
		Pixel(int x, int y, boolean isColor1){
			this.x = x;
			this.y = y;
			this.isColor1 = isColor1;
		}
	}
	
	public void pattern2(int x, int y, Paint[][] mesh, Paint color1, Paint color2) {
		Color c = mesh[x][y].getColor();
		Pixel pixel1 = new Pixel(x, y, true);
		Stack<Pixel> stack = new Stack<Pixel>();
		stack.push(pixel1);
		
		while (!stack.isEmpty()){
			pixel1 = stack.pop();
			if((pixel1.x - 1) != -1 && mesh[pixel1.x - 1][pixel1.y].getColor().equals(c)) {
				stack.push(new Pixel(pixel1.x -1, pixel1.y, !pixel1.isColor1));
			}if((pixel1.y + 1) < mesh[x].length && mesh[pixel1.x][pixel1.y + 1].getColor().equals(c)){
				stack.push(new Pixel(pixel1.x, pixel1.y+1, pixel1.isColor1));
			}if((pixel1.x + 1) < mesh.length && mesh[pixel1.x + 1][pixel1.y].getColor().equals(c)) {
				stack.push(new Pixel(pixel1.x +1, pixel1.y, !pixel1.isColor1));
			}if((pixel1.y - 1) != -1 && mesh[pixel1.x][pixel1.y - 1].getColor().equals(c)){
				stack.push(new Pixel(pixel1.x, pixel1.y-1, pixel1.isColor1));
			}
			
			if(pixel1.isColor1) {
				mesh[pixel1.x][pixel1.y]= color1;
			}else {
				mesh[pixel1.x][pixel1.y]= color2;
			}
		}
		
		System.out.println("Pattern fill finished");
	}
	
/*
   set the drawing mode of the paint brush.
*/
	public void pointMode()
	{
		mode= BrushMode.paintMode;
	}

	public void fillMode()
	{
		mode = BrushMode.fillMode;
	}

	public void pattern1Mode()
	{
		mode = BrushMode.pattern1Mode;
	}

	public void pattern2Mode()
	{
		mode = BrushMode.pattern2Mode;
	}

}
