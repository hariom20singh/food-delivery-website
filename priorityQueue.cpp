#include<bits/stdc++.h>
#include<queue>
using namespace std;
void print(priority_queue<int> que)
{
    priority_queue<int>p=que;
    while(!p.empty())
    {
        cout<<p.top()<<" ";
        p.pop();

    }
    cout<<endl;
}
int main(){
    priority_queue<int>q;
    q.push(1);
    q.push(3);
    q.push(5);
    q.push(7);
    q.push(9);
    print(q);
    return 0;
}