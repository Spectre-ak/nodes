import java.io.*;
import java.util.Scanner;

public class MinorProject {

	
static Scanner sc=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
static Node start1=null;
static Node start2=null;static int tr=0;
static Node start3=null;

public static void main(String[]args)throws IOException{

	System.out.println("Note: The Polynomials must be provided in its standard form.");
	while(true) {start1=null;start2=null;start3=null;tr=0;
	create(1);
	System.out.println();
	create(2);
	System.out.println();
	
	add();
	dissum();System.out.println();
	dispoly();
	System.out.println();
	while(true) {
		System.out.println("Want more calculations[y/n]");String s=sc.next();s=s.toLowerCase();
		if(s.charAt(0)=='y') 
			break;
		else if(s.charAt(0)=='n')System.exit(0);
		else
	    System.out.println("Invalid Input");
	}
	}
}

public static void create(int n){

	System.out.println("Enter the"+(n==1?" 1st ":" 2nd ")+"Polynomial");Scanner sc1=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	String s=sc1.nextLine();s=s.replaceAll("\\s","");int in=0;double co1=0;int ep1=0;
	if(s.contains("x")==false) {
		co1=Double.parseDouble(s);ep1=0;
		if(n==1)fir(co1,ep1);
		else Sec(co1,ep1);
		if(n==1)dis1();else dis2();
	}
	else if(s.length()==1){
		if(s.charAt(0)=='x'){
			co1=1;ep1=1;
		}
		
		if(n==1)fir(co1,ep1);
		else Sec(co1,ep1);
		if(n==1)dis1();else dis2();
	} 
	else if(s.length()==2){
		if(s.charAt(0)!='x'){
			if(s.charAt(0)=='-'||s.charAt(0)=='+'){
				co1=Double.parseDouble(s.charAt(0)+"1");
				ep1=1;
			}
			else if(s.charAt(1)=='x'){
				co1=Double.parseDouble(s.charAt(0)+"");
		        ep1=1;	
			}
		}
		else{
			if(s.charAt(1)!='x'){
				ep1=Integer.parseInt(s.charAt(1)+"");
				co1=1;
			}
		}
		
		if(n==1)fir(co1,ep1);
		else Sec(co1,ep1);if(n==1)dis1();else dis2();
	}
	else{
	
	for(int i=0;i<s.length();i++){
		if(s.charAt(i)=='x'){
		double c=1;int e=0;
		
			in=i;
		if((in+1)<s.length()&&(s.charAt(in+1)=='+'||s.charAt(in+1)=='-')){
			e=1;
		}
		else{
		
		String ssf="";
		for(int j=in+1;j<s.length();j++){
			if(s.charAt(j)=='+'||s.charAt(j)=='-'){
				break;
			}else{
				ssf=ssf+s.charAt(j);
			}
		}if(ssf.length()>0)
		e=Integer.parseInt(ssf);
		else e=1;
		}
		
		if((in-1)>=0&&(s.charAt(in-1)=='+'||s.charAt(in-1)=='-')){
			String d=s.charAt(in)+"1";
			c=Double.parseDouble(d);
		}else{
		
		String ssb="";
		for(int j=in-1;j>=0;j--){
			if(s.charAt(j)=='+'||s.charAt(j)=='-'){
				ssb=ssb+s.charAt(j);
				break;
			}
			else{
				ssb=ssb+s.charAt(j);
			}
		}
		String ssb1="";
		for(int j=ssb.length()-1;j>=0;j--)
			ssb1=ssb1+ssb.charAt(j);
			
		if(ssb1.length()>0)
				c=Double.parseDouble(ssb1);
		}
		
		if(n==1)fir(c,e);
		else Sec(c,e);
		
	}			
	}
	String kk="";int j=0;int ty=0;int u=0;
	for(j=s.length()-1;j>=0;j--){
		if(s.charAt(j)!='-'&&s.charAt(j)!='+'){
			kk=kk+s.charAt(j);
		}
		else{
			kk=kk+s.charAt(j); ty++;
			break;
		}
	}
	double cc1=0;int e1=0;
	String k="";
	for(int l=kk.length()-1;l>=0;l--){
		k=k+kk.charAt(l);
	}boolean eee=true;
	if(k.contains("x"))eee=false;
	
	
	
	if(eee&&k.length()>0&&j!=0&&ty>0){
		
	
		
		cc1=Double.parseDouble(k);
		e1=0; u++;
	} 
	if(u>0)                     
	
	if(n==1)fir(cc1,e1);
	else Sec(cc1,e1);
	if(n==1)dis1();else dis2();
}
}
public static void fir(double co,int ex){
	Node n0=new Node();n0.coef=co;n0.exp=ex;
	if(start1==null ) {
		start1=n0;
	}
	else {
		Node n1=start1;
	 	for(n1=start1;n1.next!=null;n1=n1.next){
			
		}
		n1.next=n0;
	}
}

public static void Sec(double co,int ex){
	Node n0=new Node();n0.coef=co;n0.exp=ex;
	if(start2==null ) {
		start2=n0;
	}
	else {
		Node n1=start2;
	 	for(n1=start2;n1.next!=null;n1=n1.next){
			
		}
		n1.next=n0;
	}
}
public static void dis1() {

	if(start1!=null) {
		for(Node n1=start1;n1!=null;n1=n1.next) {
			System.out.print(n1.coef+","+n1.exp+"  ");
		}System.out.print("---->LinkedList Representation");
	}
	
}
public static void addany() {
	
	for(Node n1=start2;n1!=null;n1=n1.next) {
		Node n=new Node();n.coef=n1.coef;n.exp=n1.exp;
		if(start3==null) {
			start3=n;
		}
		else {
			Node q1=start3;
		 	for(q1=start3;q1.next!=null;q1=q1.next){
				
			}
			q1.next=n;
		}
	}
	
}
public static void dissum() {if(tr==0)addany();
	if(start3!=null) {System.out.println("The sum of two polynomials is");
		for(Node n1=start3;n1!=null;n1=n1.next) {
			System.out.print(n1.coef+","+n1.exp+"  ");
		}System.out.print("---->LinkedList Representation");
	}
	else {
		System.out.println("The polynomials provided can't be added");
		System.exit(0);
	}
}
public static void add(){
	
	if(cont1()>cont2() || cont1()==cont2())
	for(Node n1=start1;n1!=null;n1=n1.next) {int con=0;double adc=0;int adexp=0;
		for(Node n2=start2;n2!=null;n2=n2.next) {
			if(n1.exp==n2.exp) {tr++;
				con++;
				adc=n1.coef+n2.coef;adexp=n1.exp;
				break;
			}
				
		}
		if(con==0) {Node a1=new Node(); a1.coef=n1.coef;a1.exp=n1.exp;
			if(start3==null) {
				start3=a1;
			}       
			        
			else {
				Node q1=start3;
			 	for(q1=start3;q1.next!=null;q1=q1.next){
					
				}
				q1.next=a1;
			}
		}
		else {Node a1=new Node(); a1.coef=adc;a1.exp=adexp;
			if(start3==null) {
				start3=a1;
			}
			else {
				Node q1=start3;
			 	for(q1=start3;q1.next!=null;q1=q1.next){
					
				}
				q1.next=a1;
			}
		}
		
	}
	else
		for(Node n1=start2;n1!=null;n1=n1.next) {int con=0;double adc=0;int adexp=0;
		for(Node n2=start1;n2!=null;n2=n2.next) {
			if(n1.exp==n2.exp) {
				con++;tr++;
				adc=n1.coef+n2.coef;adexp=n1.exp;
				break;
			}
				
		}
		if(con==0) {Node a1=new Node(); a1.coef=n1.coef;a1.exp=n1.exp;
			if(start3==null) {
				start3=a1;
			}
			else {
				Node q1=start3;
			 	for(q1=start3;q1.next!=null;q1=q1.next){
					
				}
				q1.next=a1;
			}
		}
		else {Node a1=new Node(); a1.coef=adc;a1.exp=adexp;
			if(start3==null) {
				start3=a1;
			}
			else {
				Node q1=start3;
			 	for(q1=start3;q1.next!=null;q1=q1.next){
					
				}
				q1.next=a1;
			}
		}
	}
		
		
		
		
	
	
	
	
}
public static void dis2() {
	if(start2!=null) {
		for(Node n1=start2;n1!=null;n1=n1.next) {
			System.out.print(n1.coef+","+n1.exp+"  ");
		}System.out.print("---->LinkedList Representation");
	}
}	
public static int cont1() {int con=0;

	for(Node n=start1;n!=null;n=n.next) {
		con++;
	}
	return con;
}
public static int cont2() {int con=0;
for(Node n=start2;n!=null;n=n.next) {
	con++;
}
return con;
}
public static void dispoly() {String a="";
	for(Node n=start3;n!=null;n=n.next) {
		if(n.coef>0) {
			a=a+"+"+n.coef+"x"+n.exp;
		}
		else {
			a=a+n.coef+"x"+n.exp;
		}
	}
	if(a.charAt(0)=='+') {
		a=a.substring(1,a.length());
	}
	System.out.print(a);System.out.print("---->Resultant Polynomial");//Resultant 
	
}
}

class Node{
double coef;
int exp;
Node next;
}


