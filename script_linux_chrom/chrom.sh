#!/bin/bash

#������������ �������� ���:2627
#��������� �������� ���:2572
#������ ������� ���:2557
#�������� ��������� ���:2553

#��� ������ "output.txt" ����� ������������ �� ���������� ����� �� ��������� ����������� ������ �������� site.
#��� ������ "out.txt" ����� ������������ �� ���������� ���������� ����������� 2 �������� site.

initialize(){
	
	if [ -s url.in ] #��������� �� �� ������ "url.in" ����� �����.
	then
		counter=0
	
		for t in `pgrep chromium`;    #��������� �� �������� �������� ���������� ���� chromium.�� �������� �������� 						      ���������� ��� ������������.�������� ������������ �� ������ ������("output.txt")   						      ��� ����� �� ������������ �� ����������.
		do 		               
		counter=$(($counter+1))    
		done
	
		if [ $counter -gt 0 ]
		then
			pkill -f -9 chromium
		fi	
		
		echo "Terminal Messages" > terminalmessages.txt
		echo "	       Statistics" > output.txt
		echo "Time NumberOfChromiumProcesses 	  VMRSS" >> output.txt	
	else
		echo "The url.in file is empty."
		exit
	fi
	
}

statistics(){
	sleep 0.05
	
	declare -a pid #������������ ��� ������ "pid".
	
	timestamp=0
	timestamp=`echo $timestamp + 0.5 | bc` #�� �� ��������� "timestamp" ������� ��� ����� ���������� ��� �����������.
	
	pids=$(ps -C chromium-browser -o pid=) #������������ ���� ��������� "pids" �� pid ��� ���������� ��� chromium.
	
	#���� �� while loop �� ������ ��� �������� �������� ���������� ���� chromium.
	while [ -n "$pids" ]
	do
		m=0 #���� ��������� "m" ������� �� ������ ��� ����������.
		
		for g in `pgrep chromium`; #�� ���� �� for loop ��� ���� ��� ���������� ��� chromium ������� �� ����: 1)��� ���� 						   ��������� ������� �� pid ��� ���� ������ "pid".2)��������� �� ������ ��� ���������� ���� 1.
		do      
			pid[$m]=${g}
			m=$(($m+1)) 
		done
		
		echo -e -n "\n$timestamp s		$m		" >> output.txt  #��������� ��� ������ ������("output.txt") 							  ��� ����� ���������� ��� �� ������ ��� ���������� ��� ������������ ������� ������.

		echo " " > results.txt #������������ �� ������ "results.txt".��� ������ ����,��� ���� ������� ������ ��� ���������� 					       ��� �����������,������������ ���� �����(�� kilobytes) �������� � ���� ��������� chromium.(��� 					       ���� ������� ������ �� ������ "results.txt" ������������� ��� ��� ����.)
		
		#�� ���� �� for loop ��������� ��� ������ "results.txt" ���� �����(�� kilobytes) �������� � ���� ��������� chromium 			�� ������� ������ ��� ���������� ��� �����������.
		for ((i=0;i<m;i++));    

		do     
 			cat /proc/${pid[$i]}/status | grep RSS  | awk ' { print $2 } ' >> results.txt 
			#� �������� ������ ��������� �� ���� ����.��� ����� �����(cat /proc/${pid[$i]}/status) ��������� ��� �� 		���������� ��� ����������.��� ������� �����(grep RSS) ��� ��� �� ���������� ��� ���������� �������������� �� 			"������ ����������" �� "RSS" ���������� ��� ��������� ������ �� �� VMRSS.��� ����� �����                            			(awk ' { print $2 } ' >> results.txt ) ���������� ��� 2 "�����" ��� ������� VMRSS,� ����� �������� ���� ����� ��   			kilobytes ��� ������� � ��������� ��� �� ��������� ��� ������ "results.txt"
		done
		
		sum=0 #�� ���� ��� ��������� �� ���������� �� �������� ������� ��� ������(�� kilobytes) ��� ��������� ���� �� 			      ���������� ��� chromium ����,�� ������� ������ ��� ���������� ��� �����������.
		
		for y in `cat results.txt`;
		do      
			sum=$(($sum+$y))  
		done
		
		echo -n $(bc<<<"scale=2; $sum/1024") >> output.txt #��������� ��� ������ ������("output.txt") �� ������� ���          			������(�� Mb) ��� ��������� ���� �� ���������� ��� chromium ����,�� ������� ������ ��� ���������� ��� �����������.
		
		echo " MB" >> output.txt
		
		sleep 0.5				
		
		timestamp=`echo $timestamp + 0.5 | bc`
		pids=$(ps -C chromium-browser -o pid=)	#����������� �� ������� ��� while loop.		   					
	done
}

openurls(){
	statistics &	#�������� ��� ��������� statistics ��� background. 
	chromium-browser &
	
	for l in `cat url.in`; #�� ���� �� for loop �������� �� sites ��� ����� �������� ��� ������ "url.in". 
	do		
		chromium-browser ${l} &
		sleep 10
	done
}

closeurls(){
	peds=$(ps -C chromium-browser -o pid=) #������������ ���� ��������� "pids" �� pid ��� ���������� ��� chromium.
	
	#�� ���� �� while loop ��� �������� �������� ���������� ���� chromium,��������� ���� ���� ��� ��� ��������.
	while [ -n "$peds" ]
	do
		pkill -n -f chromium
		sleep 10
		peds=$(ps -C chromium-browser -o pid=)
	done
}

initialize 
openurls >> terminalmessages.txt 2>&1 #������������� �� �������� ��� �� ������������ ��� terminal,��� ������ "terminalmessages.txt".
sleep 30
closeurls
rm -f results.txt #�� ���� ��� ������ ����������� �� ������ "results.txt" ����� ��� ��� ����� ���������� ��� �����.
exit