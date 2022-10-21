#include<stdio.h>
typedef struct Student
     { int rollno;
       char name[30];
       float p,c,m;
       }STD;
void main()
     {
     STD S[3];
     int i;
     float t,per;
     for(i=0;i<=2;i++)
     {
	 
     printf("Enter Roll Number:");
     scanf("%d",&S[i].rollno);
     
     printf("Enter Name:");
     fflush(stdin);
     gets(S[i].name);
     
     printf("Enter Physics Marks:");
     scanf("%f",&S[i].p);
     
     printf("Enter Chemistry Marks:");
     scanf("%f",&S[i].c);
     
     printf("Enter Maths Marks:");
     scanf("%f",&S[i].m);
 }
     
  for(i=0;i<=2;i++)
  { t=S[i].p+S[i].c+S[i].m;
    per=t/3;
    if(per>=60)
  	printf("%d\t\t%s\t\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\tPASS\n",S[i].rollno,S[i].name,S[i].p,S[i].c,S[i].m,t,per);
  	else
  	printf("%d\t\t%s\t\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\tFAIL\n",S[i].rollno,S[i].name,S[i].p,S[i].c,S[i].m,t,per);
  }


      }
       
       
       
