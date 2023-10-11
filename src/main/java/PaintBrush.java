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
		if (BrushMode == paintMode) {
			mesh[x][y] = getPaint();
		}
		
		else if (BrushMode == fillMode) {
			
		}
		else if (BrushMode == pattern1Mode) {
	
		}
		else if (BrushMode == pattern2Mode) {
	
		}
		
		
	}
	
	public void fill(int x, int y, Paint[][] mesh) {
		Color c = mesh[x][y].getColor();
		
		mesh[x][y] = getPaint();
		
		if((x - 1) != -1 && mesh[x - 1][y].getColor == c) {
			fill(x - 1, y, mesh);
		}if(y + 1) != 401 && mesh[x][y + 1].getColor == c){
			fill(x, y + 1, mesh);
		}if((x + 1) != 401 && mesh[x + 1][y].getColor == c) {
			fill(x + 1, y, mesh);
		}if(y - 1) != -1 && mesh[x][y - 1].getColor == c){
			fill(x, y - 1, mesh);
		}
	}
	
	public void pattern1(int x, int y, Paint[][] mesh, Paint color1, Paint color2) {
		Color c = mesh[x][y].getColor();
		
		mesh[x][y] = color1;
		
		if((x - 1) != -1 && mesh[x - 1][y].getColor == c) {
			fill(x - 1, y, mesh, color1, color2);
		}if(y + 1) != 401 && mesh[x][y + 1].getColor == c){
			fill(x, y + 1, mesh, color2, color1);
		}if((x + 1) != 401 && mesh[x + 1][y].getColor == c) {
			fill(x + 1, y, mesh, color1, color2);
		}if(y - 1) != -1 && mesh[x][y - 1].getColor == c){
			fill(x, y - 1, mesh, color2, color1);
		}
	}

	class Pixel{
		int x;
		int y;
		
		Pixel(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public void pattern2(int x, int y, Paint[][] mesh) {
		Color c = mesh[x][y].getColor();
		Pixel pixel1 = new Pixel(x, y);
		Stack stack = new Stack<Pixel>;
		stack.push(pixel1);
		
		while !stack.isEmpty(){
			pixel1 = stack.pop()
			if((pixel1.x - 1) != -1 && mesh[pixel1.x - 1][pixel1.y].getColor == c) {
				Pixel pixeltemp = new Pixel(pixel1.x -1, pixel1.y);
				stack.push(pixeltemp);
			}if(pixel1.y + 1) != 401 && mesh[pixel1.x][pixel1.y + 1].getColor == c){
				Pixel pixeltemp = new Pixel(pixel1.x, pixel1.y+1);
				stack.push(pixeltemp);
			}if((pixel1.x + 1) != 401 && mesh[pixel1.x + 1][pixel1.y].getColor == c) {
				Pixel pixeltemp = new Pixel(pixel1.x +1, pixel1.y);
				stack.push(pixeltemp);
			}if(pixel1.y - 1) != -1 && mesh[pixel1.x][pixel1.y - 1].getColor == c){
				Pixel pixeltemp = new Pixel(pixel1.x, pixel1.y-1);
				stack.push(pixeltemp);
			}
			mesh[pixel1.x][pixel1.y]= color1;
		}
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
