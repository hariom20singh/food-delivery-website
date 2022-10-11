#include<bits/stdc++.h>
using namespace std;
struct teachers{
string name;
int age;
int salary;
bool english;
string gender;
void print()
{
    cout<<"Name : "<<name<<endl;
    cout<<"Age : "<<age<<endl;
    cout<<"Salary : "<<salary<<endl;
    cout<<"Teacher knows English : "<<english<<endl;
    cout<<"Gender : "<<gender<<endl;
    cout<<endl;
}
};
int main(){
    cout<<"Number of Teachers : ";
    int n;
    cin>>n;
    teachers teacher[n];
    // teacher[0].name="Aniket";
    // teacher[0].age=21;
    // teacher[0].salary=0;
    // teacher[0].english=true;
    // teacher[0].gender="Male";

    // teacher[1].name="Lokesh";
    // teacher[1].age=22;
    // teacher[1].salary=5000000;
    // teacher[1].english=true;
    // teacher[1].gender="Male";

    // teacher[2].name="Diwakar";
    // teacher[2].age=23;
    // teacher[2].salary=100000000;
    // teacher[2].english=true;
    // teacher[2].gender="Male";

    // teacher[3].name="Ritik";
    // teacher[3].age=20;
    // teacher[3].salary=250000;
    // teacher[3].english=true;
    // teacher[3].gender="Male";

    // teacher[4].name="Aloki";
    // teacher[4].age=19;
    // teacher[4].salary=10;
    // teacher[4].english=false;
    // teacher[4].gender="Female";

    for(int i=0;i<n;i++)
    {cout<<"Teacher's "<<i+1<<" Name : ";
    cin>>teacher[i].name;
    cout<<"Teacher's "<<i+1<<" Age : ";
    cin>>teacher[i].age;
    cout<<"Teacher's "<<i+1<<" Salary : ";
    cin>>teacher[i].salary;
    cout<<"Teacher's "<<i+1<<" English is known or not : ";
    cin>>teacher[i].english;
    cout<<"Teacher's "<<i+1<<" Gender : ";
    cin>>teacher[i].gender; 
    cout<<endl;
    }
    for(int i=0;i<n;i++)
    {
        teacher[i].print();
    }
    return 0;
} 