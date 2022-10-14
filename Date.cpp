#include <iostream>
#include <ctime>
using namespace std;
class Date
{
private:
    int day, month, year;

public:
    Date()
    {
        time_t now = time(0);
        tm *ltime = localtime(&now);
        day = ltime->tm_mday;
        month = ltime->tm_mon + 1;
        year = ltime->tm_year + 1900;
    }

    void CalculateDate(int d, Date ob)
    {
        year = ob.year + d / 365;
        day = ob.day + d % 365;
        month = ob.month;
        int mon_days = 0;
        if (month == 2)
        {
            if (year % 4 == 0)
                mon_days = 29;
            else
                mon_days = 28;
        }
        else
        {
            if (month == 4 || month == 6 || month == 9 || month == 11)
                mon_days = 30;
            else
                mon_days = 31;
        }
        while (day > mon_days)
        {
            day = day - mon_days;
            month++;
            if (month > 12)
            {
                month = month - 12;
                year++;
            }
            if (month == 2)
            {
                if (year % 4 == 0)
                    mon_days = 29;
                else
                    mon_days = 28;
            }
            else
            {
                if (month == 4 || month == 6 || month == 9 || month == 11)
                    mon_days = 30;
                else
                    mon_days = 31;
            }
        }
    }
    void Display()
    {
        cout << day << "/" << month << "/" << year << endl;
    }
};
int main()
{
    Date Cu_date;
    cout << "The current date is:";
    Cu_date.Display();
    int no_days;
    cout << "Enter no of days";
    cin >> no_days;
    Date Fu_date;
    Fu_date.CalculateDate(no_days, Cu_date);
    cout << "The Future Date is:";
    Fu_date.Display();
    return 0;
}
