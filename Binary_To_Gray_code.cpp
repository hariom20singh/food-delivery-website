// To Convert from Binary code to gray code
#include <iostream>
using namespace std;
int binaryToGray(int n)
{
    // We know what happend when we convert from binary to Gray code that is LSB remain same and we do XOR of LSB with next bit to the right till MSB
    // And we get our desired result
    // n>>1 means we are moving 1 bit to right that means taking floor(n/2)
    return (n ^ (n >> 1)); // And XOR of this gives us result
}
int main()
{
    int number; // Number for which we are taking input
    cin >> number;
    cout << binaryToGray(number) << endl;
    return 0;
}