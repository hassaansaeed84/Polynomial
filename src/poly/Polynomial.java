package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		Node poly3=null;
		Node ptr1=poly1;
		Node ptr2=poly2;
		Node ptr3=poly3;
		if(poly1==null) {
			return poly2;
		}
		if(poly2==null) {
			return poly1;
		}
		while(ptr1!=null && ptr2!=null) {
					if(ptr1.term.degree==ptr2.term.degree) {
						float c=ptr1.term.coeff+ptr2.term.coeff;
						if(poly3==null) 
						{
							poly3= new Node(c, ptr1.term.degree,null);
							ptr3=poly3;
						} 
						else
						{
						ptr3.next=new Node(c, ptr1.term.degree, null);
							ptr3=ptr3.next;
						
						}	
					}
					if(ptr1.term.degree>ptr2.term.degree) {
						if(poly3!=null) {
							ptr3.next=new Node(ptr2.term.coeff, ptr2.term.degree, null);
							ptr3=ptr3.next;
							ptr3.next=new Node(ptr1.term.coeff, ptr1.term.degree, null);
							ptr3=ptr3.next;
						}
						else {
							poly3=new Node(ptr2.term.coeff, ptr2.term.degree, null);
							ptr3=poly3;
							ptr3.next= new Node(ptr1.term.coeff, ptr1.term.degree, null);
							ptr3=ptr3.next;
						}
					}
					else if(ptr2.term.degree>ptr1.term.degree)
					{
						if(poly3!=null) {
							ptr3.next=new Node(ptr1.term.coeff, ptr1.term.degree, null);
							ptr3=ptr3.next;
							ptr3.next=new Node(ptr2.term.coeff, ptr2.term.degree, null);
							ptr3=ptr3.next;
						}
						else {
							poly3=new Node(ptr1.term.coeff, ptr1.term.degree, null);
							ptr3=poly3;
							ptr3.next= new Node(ptr2.term.coeff, ptr2.term.degree, null);
							ptr3=ptr3.next;
						}
					}
					ptr1=ptr1.next;
					ptr2=ptr2.next;
			}
		while(ptr2==null && ptr1!=null) {
//			if(ptr3.term.degree==ptr1.term.degree) {
//				float n=ptr3.term.coeff+ptr1.term.coeff;
//				ptr3.term.coeff=n;
//			}
//			if(ptr3.term.degree>ptr1.term.degree) {
				ptr3.next=new Node(ptr1.term.coeff, ptr1.term.degree, null);
//			}
//			if (ptr3.term.degree<ptr1.term.degree) {
//				ptr3.next=new Node(ptr1.term.coeff, ptr1.term.degree, null);
//			}
			ptr3=ptr3.next;
			ptr1=ptr1.next;
		}
		while(ptr1==null && ptr2!=null) {
//			if(ptr3.term.degree==ptr2.term.degree) {
//				float n=ptr3.term.coeff+ptr2.term.coeff;
//				ptr3.term.coeff=n;
//			}
//			if(ptr3.term.degree>ptr2.term.degree) {
				ptr3.next=new Node(ptr2.term.coeff, ptr2.term.degree, null);
				
//			}
//			if(ptr3.term.degree<ptr2.term.degree){
//				ptr3.next=new Node(ptr2.term.coeff, ptr2.term.degree, null);
//			}
			ptr3=ptr3.next;
			ptr2=ptr2.next;
		}
		for(Node S=poly3;S!=null;S=S.next) {
			for(Node F=S.next;F!=null;F=F.next) {
				if(F.term.degree<S.term.degree) {
					int tempd=F.term.degree;
					float tempf=F.term.coeff;
					F.term.coeff=S.term.coeff;
					F.term.degree=S.term.degree;
					S.term.coeff=tempf;
					S.term.degree=tempd;
				}
			}
		}
		Node first=poly3.next;
		Node second=poly3;
		while(first!=null) {
			if(second.term.degree==first.term.degree) {
				float f=second.term.coeff+first.term.coeff;
				second.term.coeff=f;
				second.next=first.next;
				first=first.next;
			} else {
				second=second.next;
				first=first.next;
			}
		}
//		Node first=poly3.next;
//		Node second=poly3;
//		Node third=null;
//		while(first!=null) {
//			if(first.term.degree==second.term.degree) {
//				float f=first.term.coeff+second.term.coeff;
//				third.next= new Node(f, first.term.degree, first.next);
//				second=third.next;
//				first=second.next;
//			}
//			third=second;
//			second=first;
//			if(first!=null) {
//				first=first.next;	
//			}
//		}
		Node a=poly3;
			while(a.next!=null){
				if(a.next.term.coeff==0) {
					a.next=a.next.next;
				}
				else {
					a=a.next;
				}
			}
			if(poly3.term.coeff==0) {
				poly3=poly3.next;
			}
		return poly3;
	}
	
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		Node poly3=null;
		Node ptr3=poly3;
		if(poly1==null) {
			return null;
		}
		if(poly2==null) {
			return null;
		}
		for(Node ptr=poly1;ptr!=null;ptr=ptr.next) {
			for(Node ptr2=poly2;ptr2!=null;ptr2=ptr2.next) {
				int d=ptr.term.degree+ptr2.term.degree;
				float p=ptr.term.coeff*ptr2.term.coeff;
				if(poly3==null) {
					poly3=new Node(p,d,null);
					ptr3=poly3;
				}
				else {
					ptr3.next=new Node(p,d,null);
					ptr3=ptr3.next;
				}
			}
		}
		for(Node S=poly3;S!=null;S=S.next) {
			for(Node F=S.next;F!=null;F=F.next) {
				if(F.term.degree<S.term.degree) {
					int tempd=F.term.degree;
					float tempf=F.term.coeff;
					F.term.coeff=S.term.coeff;
					F.term.degree=S.term.degree;
					S.term.coeff=tempf;
					S.term.degree=tempd;
				}
			}
		}
		Node first=poly3.next;
		Node second=poly3;
		while(first!=null) {
			if(second.term.degree==first.term.degree) {
				float f=second.term.coeff+first.term.coeff;
				second.term.coeff=f;
				second.next=first.next;
				first=first.next;
			} else {
				second=second.next;
				first=first.next;
			}
		}
		Node a=poly3;
		while(a.next!=null){
			if(a.next.term.coeff==0) {
				a.next=a.next.next;
			}
			else {
				a=a.next;
			}
		}
		if(poly3.term.coeff==0) {
			poly3=poly3.next;
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return poly3;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		float sum=0;
		for(Node ptr=poly;ptr!=null;ptr=ptr.next) {
			double a=Math.pow(x, ptr.term.degree);
			double p=ptr.term.coeff*a;
			sum+=p;
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return sum;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
