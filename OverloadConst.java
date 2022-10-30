class Box
{
double width;
double height;
double depth;
Box(double wid,double hei,double dep)
{
width=wid;
height=hei;
depth=dep;
}
Box(double len)
{
width=height=depth=len;
}
double volume()
{
return width*height*depth;
}
}
class OverloadConst
{
public static void main(String [] args)
{
Box myBox1= new Box(50,100,120);
Box mycube= new Box(10);
double vol;
vol=myBox1.volume();
System.out.println("volume of my box is " +vol);
vol=mycube.volume();
System.out.println("volume of my cube is " +vol);
}
}
