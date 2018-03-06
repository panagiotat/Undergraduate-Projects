#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <sstream>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fstream>
#include <string>


using namespace std;


//this function get as argument a String that include the //command/s that i want to execute in the terminal

void run(string a) {
    pid_t pid1, pid;

    int p;
    int m, t ;
    unsigned int i, y, r;
    bool k;
    std::string s = "";



    //with the following code i divide the String that include //the command/s into words and i store it in a vector.

    vector<string> vec;

    istringstream iss(a);
    copy(istream_iterator<string>(iss),
            istream_iterator<string>(),
            back_inserter(vec));

    std::vector<char *> argv(vec.size() + 1);

    m = vec.size() - 1;

    if (strcmp(vec[0].c_str(), "quit") == 0) {
        exit(0);
    }

    k = true;
    y = 0;

    for (i = 0; i < vec.size(); i++) {

        if ((strcmp(vec[i].c_str(), "&&") == 0) || (strcmp(vec[i].c_str(), ";") == 0)) {


            if (s == "quit") {

                exit(0);
            }


            if (strcmp(vec[i].c_str(), "&&") == 0) {

                if (k == true) {


                    k = true;
                    

                    pid1 = fork();



                    if (pid1 == 0) {


                        execvp(argv[0], argv.data());
                        

                        cout << "No such command" << endl;


                        exit(errno);




                    }





                    if (pid1 > 0) {


                        
                        int status;
                        wait(&status);

                        if (status != 0) {

                            k = false;
                        }



                    }




                    if (pid1 < 0) {
                        cout << "Error with command" << endl;
                    }



                }

            } else {

                if (k == true) {

                    
                    pid1 = fork();

                    if (pid1 == 0) {

                        execvp(argv[0], argv.data());
                        cout << "No such command" << endl;
                        
                        exit(0);

                    }



                    if (pid1 > 0) {

                        wait(0);

                    }

                    if (pid1 < 0) {

                        cout << "Error with command" << endl;
                    }

                }
                k = true;


            }

            argv.clear();
            s = "";
            y = 0;

        } else {

            argv[y] = &vec[i][0];
            s.append(&vec[i][0]);
            y++;
        }



    }

    //last part of function







    if (k == true) {

        if (s == "quit") {

            exit(0);
        }

        pid = fork();
        if (pid == 0) {

            execvp(argv[0], argv.data());


            cout << "No such command" << endl;
            exit(0);

        }

        if (pid > 0) {

            wait(0);



        }

        if (pid < 0) {
            cout << "Error with command" << endl;
        }
    }



}

//in the main function if the user did not give argument I //perform an infinite loop that prints 'Gkanoudi_8950> ' ,get //a String-command from the keyboard, 
//and call the function 'run' passing as argument the String-  //command/s.
//if he argument gives i read the argument and i open the //batch file, and i give each line of the file as input in the //function run.

int main(int argc, char** argv) {

    string comm, fp;
    int i;

    if (argc == 1) {
        while (1) {
            cout << "MyShell: >";
            std::getline(std::cin, comm);

            if (comm.find_first_not_of(' ') != std::string::npos) {
                run(comm);
            }
        }
    } else {
        fp.append(argv[1]);

        std::ifstream file(fp);

        while (std::getline(file, comm)) {
            if (comm.find_first_not_of(' ') != std::string::npos) {
                run(comm);
            }
        }
    }
    return 0;
}