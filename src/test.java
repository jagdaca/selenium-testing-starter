
	interface One {
		default void method() {
			System.out.println("One");
		}
	}
	
	interface Two {
		default void method() {
			System.out.println("Two");
		}
	}

	class Three implements One,Two {
		public void method() {
			One.super.method();
		}
	}

public class test {
	
	public static void main(String[] args) {
		Three x = new Three();
		x.method();
		
		String message = "Hello";
		for(int i=0;i<message.length();i++) {
			System.out.println(message.charAt(i+1));
		}
		//String newmessage = message.substring(6,12);
		//System.out.println(Math.);
		
		//Math.PI;

	}
	
	boolean isHealthy(String x) {
		return true;
	}
	

}
