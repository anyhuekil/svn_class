package javaexp.a08_objectdup;

class Draw{
	OnTouchListener listener;
	
	
	interface OnTouchListener{
		void onTouch();
	}
	
	
	public void setListener(OnTouchListener listener) {
		this.listener = listener;
	}


	void drawing(){
		if(listener!=null){
			listener.onTouch();
		}
	}
}
class CircleListener implements Draw.OnTouchListener{
	@Override
	public void onTouch() {
		System.out.println("���׶�̸� �׸���!!");
	}
}
class TriangleListener implements Draw.OnTouchListener{
	@Override
	public void onTouch() {
		System.out.println("���� �׸���!!");
	}
}


public class A03_dupInterfaceExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Draw d1 = new Draw();
		d1.setListener(new CircleListener());
		d1.drawing();
		d1.setListener(new TriangleListener());
		d1.drawing();		
		
	}

}