#include <iostream>
using namespace std;
int main() {
  int i, n;
  cin>>n;
  int arr[n];
  for(i = 0; i < n; ++i) 
  {cin >> arr[i]; }for(i = 1;i < n; ++i) 
{if(arr[0] > arr[i])
      arr[0] = arr[i]; }
cout << "Smallest  number  = " << arr[0];
  return 0;}
