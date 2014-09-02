package util;

	public class teste {

		public static void main(String[] args){  
		   int segundos = 3;  
		   try{  
		       for (int i = segundos; i > 0; i--){  
		           System.out.println(i + " segundos");  
		           Thread.sleep(1000); // 1 segundo  
		       }  
		       System.out.println("Terminei!");  
		   } catch (InterruptedException e){  
		       System.out.println("Interromperam meu sono!");  
		   }  
		} 

		}
	
	

