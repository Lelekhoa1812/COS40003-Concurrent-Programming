#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
    int myPid;
    int forkedPid;
    int waitedPid;
    int exitCode;

    myPid= getpid();
    printf("myPid is %d\n\n", myPid );
    
    forkedPid= fork();
    
    if( forkedPid == -1 ){
        fprintf(stderr,"fork failed\n");
	    return 1;
    }

    if( forkedPid == 0 ){
        sleep(2);
        printf("Child process now execing\n\n");
	    execl("/bin/ls",".",((char*)0));
        printf("DISASTER OCCURRED!\n");
	    return 1;
    }

    printf("I am the parent process. Child pid=%d\n\n",forkedPid);
    sleep(1);

    waitedPid= wait(&exitCode);
    printf("\nProcess with pid= %d has terminated",waitedPid);
    printf(" with exitCode %d\n",exitCode);
    return 0;
}
