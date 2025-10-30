#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
    int myPid;
    int forkedPid;
    int waitedPid;
    int exitCode;

    myPid = getpid();
    printf("myPid is %d\n",myPid);
    
    forkedPid = fork();
    
    sleep(1);
    printf("After fork, myPid is %d, forkedPid is %d\n",myPid,forkedPid);
    
    if( forkedPid==0 ){
        printf("Child starts!\n");
        sleep(1);
        printf("Child process now terminating\n");
        return 1;

    }else
    {
        printf("I am the parent process. Child pid=%d\n",forkedPid);
        
        waitedPid= wait(&exitCode);
        
        printf("Process with pid=%d has terminated",waitedPid);
        printf(" with exitCode %d\n",exitCode);
        return 0;
    }
}
