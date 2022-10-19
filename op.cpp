#include<iostream>
#include<string>
using namespace std;
class AddDistance {
    private:
        int meters;
        int centimeters;
    public:
        void setDistance();
        void getDistance();
        AddDistance addDistance( AddDistance d );
};
 

void AddDistance::setDistance() {
    cout << " meters: ";
     cin >> meters;
    cout << "centimeters: ";
     cin >> centimeters;
}
 

void AddDistance::getDistance() {
    cout << "meters: " << meters;
    cout << " centimeters: " << centimeters;
}
AddDistance AddDistance::addDistance( AddDistance d ) {
    AddDistance dist;
 
    dist.meters = meters + d.meters;
    dist.centimeters = centimeters + d.centimeters;
    if(dist.centimeters>=100)
    {
        dist.meters+=1;
        dist.centimeters-=100;
    }
    return dist;
}
 
int main() {
    AddDistance d1, d2, d3;
 
	
    cout << "Enter length of Distance 1: " << endl;
    d1.setDistance();
     
    cout << "Enter length of Distance 2: " << endl;
    d2.setDistance();
 

    d3 = d1.addDistance(d2);
 
	
    cout << "Sum of Distance 1 and Distance 2:" << endl;
    d3.getDistance();
    return 0;
}


