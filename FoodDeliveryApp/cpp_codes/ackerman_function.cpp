#include <iostream>
using namespace std;
 
int ack(int m, int n)
{
    if (m == 0){
        return n + 1;
    }
    else if((m > 0) && (n == 0)){
        return ack(m - 1, 1);
    }
    else if((m > 0) && (n > 0)){
        return ack(m - 1, ack(m, n - 1));
    }
}
 
// Driver code
int main()
{
    int m,n;
    cout<<"Enter two numbers";
    cin>>m>>n;
    int A = ack(m , n);
    cout << " Resultant is :"<< A << endl;
    return 0;
}