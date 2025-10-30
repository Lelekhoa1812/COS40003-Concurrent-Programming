#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
//    int myPid;
    int forkedPid;
    int waitedPid;
    int exitCode;
    int i;

    printf("P: Running execlooper\n\n");
    for( i=1 ; i<argc ; i++ ){
    	forkedPid = fork();
		
		if( forkedPid == -1 ){
	    	fprintf(stderr,"fork failed\n");
	    	return 1;
	 	}

    	if( forkedPid==0 ){
	   		sleep(1);
			printf("C: Child process now execing %s\n\n",argv[i]);
	   		execl(argv[i],"",((char*)0));

	   		printf("DISASTER OCURRED!\n");
	   		return 1;
	 	}
	 
	 //Parent here...
	 	printf("P: Child process has id %d\n",forkedPid);
	 	sleep(1);
	 	waitedPid= wait(&exitCode);
	 	printf("\nP: Process with pid= %d has terminated",waitedPid);
	 	printf(" with exitCode %d\n\n",exitCode);
    }

    return 0;
}
